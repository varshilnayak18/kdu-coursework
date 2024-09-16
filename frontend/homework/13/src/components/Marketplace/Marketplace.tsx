import Header from "../Header/Header";
import Content from "../Content/Content";
import { useEffect, useState } from "react";
import { ProductsListContext } from "../../context/ProductListContext";
import { HeaderContext } from "../../context/HeaderContext";
import ClipLoader from "react-spinners/ClipLoader";
import { Route, Routes, useSearchParams } from "react-router-dom";
import ProductDetail from "../ProductDetail/ProductDetail";

export interface IProduct {
  id: number;
  title: string;
  price: number;
  description: string;
  category: string;
  image: string;
  rating: {
    rate: number;
    count: number;
  };
}

function Marketplace() {
  const [allProducts, setAllProducts] = useState<IProduct[]>([]);
  const [productsList, setProductsList] = useState<IProduct[]>([]);
  const [selectedFilter, setSelectedFilter] = useState<string>("default");
  const [selectedSort, setSelectedSort] = useState<string>("default");
  const [searchInput, setSearchInput] = useState<string>("");
  const [searchedProducts, setSearchedProducts] = useState<IProduct[]>([]);
  const [isLoading, setIsLoading] = useState<boolean>(false);
  const [searchParams] = useSearchParams();
  useEffect(() => {
    setIsLoading(true);
    fetch("https://fakestoreapi.com/products")
      .then((response) => response.json())
      .then((data: IProduct[]) => {
        setAllProducts(data);
        setIsLoading(false);
      })
      .catch((error) => {
        console.error("Error fetching data: ", error);
      });
  }, []);

  useEffect(() => {
    const filter = searchParams.get("filter");
    if (filter !== null) {
      setSelectedFilter(filter);
    }
  }, [searchParams]);

  useEffect(() => {
    let filteredProducts = allProducts;
    if (selectedFilter !== "default") {
      filteredProducts = allProducts.filter(
        (product) => product.category === selectedFilter
      );
    }
    if (searchInput.length > 0) {
      const searched = filteredProducts.filter((product) =>
        product.title.toLowerCase().includes(searchInput.toLowerCase())
      );
      setSearchedProducts(searched);
    } else {
      setSearchedProducts(filteredProducts);
    }
  }, [allProducts, selectedFilter, searchInput]);

  useEffect(() => {
    let sortedProducts = [...searchedProducts];
    if (selectedSort !== "default") {
      if (selectedSort === "asc") {
        sortedProducts = sortedProducts.sort((p1, p2) => p1.price - p2.price);
      } else if (selectedSort === "desc") {
        sortedProducts = sortedProducts.sort((p1, p2) => p2.price - p1.price);
      }
    }
    setProductsList(sortedProducts);
  }, [selectedSort, searchedProducts]);

  return (
    <>
      <HeaderContext.Provider
        value={{
          selectedFilter,
          setSelectedFilter,
          selectedSort,
          setSelectedSort,
          searchInput,
          setSearchInput,
        }}
      >
        <Header />
      </HeaderContext.Provider>
      <Routes>
        <Route
          path="/"
          element={
            isLoading ? (
              <div style={{ display: "flex", justifyContent: "center" }}>
                <ClipLoader
                  color="black"
                  loading={isLoading}
                  size={150}
                  aria-label="Loading Spinner"
                  data-testid="loader"
                  className="loader"
                />
              </div>
            ) : (
              <ProductsListContext.Provider
                value={{ productsList, setProductsList }}
              >
                <Content />
              </ProductsListContext.Provider>
            )
          }
        />

        <Route
          path="/product/:id"
          element={
            <ProductsListContext.Provider
              value={{ productsList, setProductsList }}
            >
              {<ProductDetail />}
            </ProductsListContext.Provider>
          }
        />
      </Routes>
    </>
  );
}

export default Marketplace;
