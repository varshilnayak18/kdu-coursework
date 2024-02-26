import { changeInput } from "../../redux/SearchInputSlice";
import { RootState } from "../../redux/store";
import "./Header.scss";
import { useDispatch, useSelector } from "react-redux";

function Header() {
  const searchInput = useSelector((state: RootState) => state.searchInput.searchInput)
  const reduxDispatch = useDispatch();

  const setSearchInput = (input: string) => {
    reduxDispatch(changeInput(input));
  }

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
