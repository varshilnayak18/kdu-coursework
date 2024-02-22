import { IItem } from "../interface";
import { createContext } from "react";

interface IItemsListContext {
  itemsList: IItem[];
  setItemsList: React.Dispatch<React.SetStateAction<IItem[]>>;
}
export const ItemsListContext = createContext<IItemsListContext>({
  itemsList: [],
  setItemsList: () => {}
});
