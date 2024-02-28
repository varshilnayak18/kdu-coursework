import Header from "../Header/Header";
import Content from "../Content/Content";
import { useEffect } from "react";
import ClipLoader from "react-spinners/ClipLoader";
import { Route, Routes, useSearchParams } from "react-router-dom";
import ProductDetail from "../ProductDetail/ProductDetail";
import { useDispatch, useSelector } from "react-redux";
import { AppDispatch, RootState } from "../../redux/store";
import { setSelectedFilter } from "../../redux/HeaderSlice";
import getProducts from "../../redux/thunks/getProducts";
import MUISnackbar from "../Snackbar/MUISnackbar";

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
  const isLoading = useSelector((state: RootState) => state.productsList.state);
  const snackbar = useSelector((state: RootState) => state.snackbar);
  const [searchParams] = useSearchParams();

  const reduxDispatch: AppDispatch = useDispatch();

  const setFilterAction = (filter: string) => {
    reduxDispatch(setSelectedFilter(filter));
  };

  useEffect(() => {
    reduxDispatch(getProducts());
  }, [reduxDispatch]);

  useEffect(() => {
    const filter = searchParams.get("filter");
    if (filter !== null) {
      setFilterAction(filter);
    }
  }, [searchParams]);

  return (
    <>
      <Header />
      <Routes>
        <Route
          path="/"
          element={
            isLoading === "pending" ? (
              <div style={{ display: "flex", justifyContent: "center", marginTop: "20%" }}>
                <ClipLoader
                  color="black"
                  loading={isLoading === "pending"}
                  size={150}
                  aria-label="Loading Spinner"
                  data-testid="loader"
                  className="loader"
                />
              </div>
            ) : (
              <Content />
            )
          }
        />

        <Route
          path="/product/:id"
          element={
            <ProductDetail />
          }
        />
      </Routes>
      {snackbar.show && (
        <MUISnackbar
          message={snackbar.message}
          type={snackbar.type}
          open={snackbar.show}
        />
      )}
    </>
  );
}

export default Marketplace;
