import { createContext } from "react";

interface ISearchContext {
  searchInput: string;
  setSearchInput: React.Dispatch<React.SetStateAction<string>>;
}
export const SearchContext = createContext<ISearchContext>({
  searchInput: '',
  setSearchInput: () => {}
});
