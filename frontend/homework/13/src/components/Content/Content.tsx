import {
  main,
  mainContainer,
  title,
  colorTitle,
  linkTag,
} from "./Content.styles";
import Product from "../Product/Product";
import { products } from "../Content/Content.styles";
import { useContext } from "react";
import { ProductsListContext } from "../../context/ProductListContext";
import { Link } from "react-router-dom";

function Content() {
  const { productsList } = useContext(ProductsListContext);
  return (
    <main style={main}>
      <div style={mainContainer}>
        <div style={title}>
          <span>
            KDU &nbsp; <span style={colorTitle}>MARKETPLACE</span>
          </span>
        </div>
        <div style={products}>
          {productsList.map((p) => (
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
