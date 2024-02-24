import { useContext } from "react";
import {
  image,
  imageContainer,
  price,
  product,
  productInfo,
  title,
} from "./Product.styles";
import { ProductsListContext } from "../../context/ProductListContext";

interface ProductProps {
  readonly id: number;
}

function Product({ id }: ProductProps) {
  const { productsList } = useContext(ProductsListContext);
  const currentProduct = productsList.find((p) => p.id === id)!;
  return (
    <div style={product}>
      <div style={imageContainer}>
        <img style={image} src={currentProduct.image} alt="product img" />
      </div>
      <div style={productInfo}>
        <div style={title}>{currentProduct.title}</div>
        <div style={price}>$ {currentProduct.price}</div>
      </div>
    </div>
  );
}

export default Product;
