import { useRef } from "react";
import {
  header,
  headerContainer,
  searchContainer,
  filters,
  filter,
  sort,
  search,
  searchButton,
  filterSelect,
} from "./Header.styles";
import { useDispatch, useSelector } from "react-redux";
import { AppDispatch, RootState } from "../../redux/store";
import { setSearchInput, setSelectedFilter, setSelectedSort } from "../../redux/HeaderSlice";

function Header() {
  const selectedFilter = useSelector((state: RootState) => state.header.selectedFilter)
  const selectedSort = useSelector((state: RootState) => state.header.selectedSort)
  const searchInput = useSelector((state: RootState) => state.header.searchInput)
  const productFilters = useSelector((state: RootState) => state.productsList.filters)
  const searchInputRef = useRef<HTMLInputElement | null>(null);
  const reduxDispatch: AppDispatch = useDispatch();

  const setFilterAction = (filter: string) => {
    reduxDispatch(setSelectedFilter(filter));
  };

  const setSortAction = (sort: string) => {
    reduxDispatch(setSelectedSort(sort));
  };

  const setInputAction = (input: string) => {
    reduxDispatch(setSearchInput(input));
  };

  return (
    <header style={header}>
      <div style={headerContainer}>
        <div style={searchContainer}>
          <input
            style={search}
            type="search"
            name="search"
            id="search"
            value={searchInput}
            placeholder="Search.."
            ref={searchInputRef}
            onChange={() => setInputAction(searchInputRef.current?.value ?? "")}
          />
          <button style={searchButton}>
            <i className="fa fa-search"></i>
          </button>
        </div>
        <div style={filters}>
          <div style={filter}>
            <span>Filter :&nbsp;</span>
            <select
              style={filterSelect}
              name="filter"
              id="filter"
              value={selectedFilter}
              onChange={(e) => setFilterAction(e.target.value)}
            >
              <option value="default">Select</option>
              {
                productFilters.map((flter) => (
                  <option key={flter} value={flter}>{flter}</option>
                ))
              }
            </select>
          </div>
          <div style={sort}>
            <span>Sort :&nbsp;</span>
            <select
              style={filterSelect}
              name="sort"
              id="sort"
              value={selectedSort}
              onChange={(e) => setSortAction(e.target.value)}
            >
              <option value="default">Select</option>
              <option value="asc">ASC</option>
              <option value="desc">DESC</option>
            </select>
          </div>
        </div>
      </div>
    </header>
  );
}

export default Header;
