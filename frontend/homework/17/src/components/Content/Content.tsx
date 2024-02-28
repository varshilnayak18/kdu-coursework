import {
  main,
  mainContainer,
  title,
  colorTitle,
  linkTag,
} from "./Content.styles";
import Product from "../Product/Product";
import { products } from "../Content/Content.styles";
import { Link } from "react-router-dom";
import { useSelector } from "react-redux";
import { RootState } from "../../redux/store";
import { IProduct } from "../Marketplace/Marketplace";

function Content() {
  const productsList = useSelector(
    (state: RootState) => state.productsList.productsList
  );
  const selectedFilter = useSelector(
    (state: RootState) => state.header.selectedFilter
  );
  const selectedSort = useSelector(
    (state: RootState) => state.header.selectedSort
  );
  const searchInput = useSelector(
    (state: RootState) => state.header.searchInput
  );

  let filteredProducts: IProduct[] =
    selectedFilter === "default"
      ? productsList
      : productsList.filter((product) => product.category === selectedFilter);

  if (selectedSort === "asc") {
    filteredProducts = [...filteredProducts].sort((a, b) => a.price - b.price);
  } else if (selectedSort === "desc") {
    filteredProducts = [...filteredProducts].sort((a, b) => b.price - a.price);
  }

  if (searchInput !== "") {
    filteredProducts = filteredProducts.filter((product) =>
      product.title.toLowerCase().includes(searchInput.toLowerCase())
    );
  }
  return (
    <main style={main}>
      <div style={mainContainer}>
        <div style={title}>
          <span>
            KDU &nbsp; <span style={colorTitle}>MARKETPLACE</span>
          </span>
        </div>
        <div style={products}>
          {filteredProducts.length === 0 ? <div style={{width: '100%', textAlign: 'center', fontSize: '200%'}}><span>No products to show</span></div> : filteredProducts.map((p) => (
            <Link style={linkTag} key={p.id} to={`/product/${p.id}`}>
              <Product id={p.id} />
            </Link>
          ))}
        </div>
      </div>
    </main>
  );
}

export default Content;
