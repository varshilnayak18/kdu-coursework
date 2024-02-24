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
import { useContext } from "react";
import { ProductsListContext } from "../../context/ProductListContext";

function ProductDetail() {
  const { id } = useParams();
  const productId = parseInt(id!);
  const navigate = useNavigate();
  const { productsList } = useContext(ProductsListContext);
  console.log(productsList);
  const currentProduct = productsList.find((p) => p.id === productId)!;
  return (
    <div style={productDetail}>
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
