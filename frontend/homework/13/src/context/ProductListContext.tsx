import { IProduct } from "../components/Marketplace/Marketplace";
import { createContext } from "react";

interface IProductssListContext {
  productsList: IProduct[];
  setProductsList: React.Dispatch<React.SetStateAction<IProduct[]>>;
}
export const ProductsListContext = createContext<IProductssListContext>({
  productsList: [],
  setProductsList: () => {}
});
