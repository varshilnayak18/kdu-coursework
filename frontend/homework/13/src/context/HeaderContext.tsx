import { createContext } from "react";

interface IHeaderContext {
  selectedFilter: string;
  setSelectedFilter: React.Dispatch<React.SetStateAction<string>>;
  selectedSort: string;
  setSelectedSort: React.Dispatch<React.SetStateAction<string>>;
  searchInput: string;
  setSearchInput: React.Dispatch<React.SetStateAction<string>>;
}
export const HeaderContext = createContext<IHeaderContext>({
  selectedFilter: 'default',
  setSelectedFilter: () => {},
  selectedSort: 'default',
  setSelectedSort: () => {},
  searchInput: '',
  setSearchInput: () => {}
});
