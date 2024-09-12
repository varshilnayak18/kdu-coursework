import { useContext } from "react";
import "./Header.scss";
import { SearchContext } from "../../context/SearchContext";

function Header() {
  const {searchInput, setSearchInput} = useContext(SearchContext)
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
