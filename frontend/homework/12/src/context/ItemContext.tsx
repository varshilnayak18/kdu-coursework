import { createContext } from "react";

interface IItemContext {
  id: number;
  text: string;
}
export const ItemContext = createContext<IItemContext>({
  id: 0,
  text: ''
});
