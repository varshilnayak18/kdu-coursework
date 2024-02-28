import { useNavigate, useParams } from "react-router-dom";
import {
  backBtn,
  productDesc,
  productDetail,
  productImage,
  productImageContainer,
  productInfo,
  productOverview,
  productPrice,
  productTitle,
} from "./ProductDetail.styles";
import { useSelector } from "react-redux";
import { RootState } from "../../redux/store";
import MUISnackbar from "../Snackbar/MUISnackbar";

function ProductDetail() {
  const { id } = useParams();
  const productId = parseInt(id!);
  const navigate = useNavigate();
  const productsList = useSelector((state: RootState) => state.productsList.productsList);
  console.log(productsList);
  const currentProduct = productsList.find((p) => p.id === productId)!;
  return (
    <div style={productDetail}>
      <MUISnackbar
          message={`Details for '${currentProduct.title}' fetched successfully`}
          type='info'
          open={true}
        />
      <div style={productTitle}>{currentProduct.title}</div>
      <div style={productOverview}>
        <div style={productImageContainer}>
          <img
            style={productImage}
            src={currentProduct.image}
            alt="product img"
          />
        </div>
        <div style={productInfo}>
          <h1>Category: {currentProduct.category}</h1>
          <h2 style={productPrice}>Price : ${currentProduct.price}</h2>
          <h3>Product Description:</h3>
          <p style={productDesc}>{currentProduct.description}</p>
          <button onClick={() => navigate(-1)} style={backBtn}>
            Back to Products
          </button>
        </div>
      </div>
    </div>
  );
}

export default ProductDetail;
