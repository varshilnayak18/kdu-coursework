import "./Header.scss";
import { IItem } from "../../interface";

interface HeaderProps {
  readonly itemsList: IItem[];
  readonly searchInput: string;
  readonly setSearchInput: React.Dispatch<React.SetStateAction<string>>;
}
function Header({ itemsList, searchInput, setSearchInput }: HeaderProps) {
  return (
    <header>
      <div className="header-container">
        <h1 className="title">Item Lister</h1>
        <input
          type="search"
          name="search"
          id="search"
          className="search-bar"
          placeholder="   Search Items..."
          value={searchInput}
          onChange={e => setSearchInput(e.target.value)}
        />
      </div>
    </header>
  );
}

export default Header;
