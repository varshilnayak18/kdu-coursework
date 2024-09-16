import { useContext, useRef } from "react";
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
import { HeaderContext } from "../../context/HeaderContext";

function Header() {
  const {
    selectedFilter,
    setSelectedFilter,
    selectedSort,
    setSelectedSort,
    searchInput,
    setSearchInput,
  } = useContext(HeaderContext);
  const searchInputRef = useRef<HTMLInputElement | null>(null);
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
            onChange={() => setSearchInput(searchInputRef.current?.value ?? "")}
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
              onChange={(e) => setSelectedFilter(e.target.value)}
            >
              <option value="default">Select</option>
              <option value="men's clothing">Men's Clothing</option>
              <option value="women's clothing">Women's Clothing</option>
              <option value="jewelery">Jewelery</option>
              <option value="electronics">Electronics</option>
            </select>
          </div>
          <div style={sort}>
            <span>Sort :&nbsp;</span>
            <select
              style={filterSelect}
              name="sort"
              id="sort"
              value={selectedSort}
              onChange={(e) => setSelectedSort(e.target.value)}
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
