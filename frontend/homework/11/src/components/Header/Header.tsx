import React from "react";
import "./Header.scss";
import { RawQuote } from "../../interface";
import ClipLoader from "react-spinners/ClipLoader";

interface HeaderProps {
  readonly allQuotes: RawQuote[];
  readonly setAllQuotes: React.Dispatch<React.SetStateAction<RawQuote[]>>;
  readonly isLoading: boolean;
  readonly setIsLoading: React.Dispatch<React.SetStateAction<boolean>>;
  readonly selectedFilters: string[];
  readonly setSelectedFilters: React.Dispatch<React.SetStateAction<string[]>>;
}

function Header({
  allQuotes,
  setAllQuotes,
  isLoading,
  setIsLoading,
  selectedFilters,
  setSelectedFilters,
}: HeaderProps) {
  const addQuote = () => {
    setIsLoading(true);
    fetch("https://api.quotable.io/quotes/random")
      .then((response) => response.json())
      .then((data: RawQuote[]) => {
        setAllQuotes([...data, ...allQuotes]);
        setIsLoading(false);
      });
  };

  const removeFilter = (filter: string) => {
    setSelectedFilters(selectedFilters.filter((f) => f !== filter));
  };
  return (
    <header className="header">
      <button
        className="new-quote"
        onClick={() => addQuote()}
        disabled={isLoading}
      >
        {isLoading ? (
          <>
            <span>NEW QUOTE</span>
            <ClipLoader
              color="black"
              loading={isLoading}
              size={15}
              aria-label="Loading Spinner"
              data-testid="loader"
              className="loader"
            />
          </>
        ) : (
          "NEW QUOTE"
        )}
      </button>
      <h3 className="title">Filters</h3>
      <div className="filters">
        {selectedFilters.length > 0 &&
          selectedFilters.map((filter) => {
            return (
              <span key={filter} className="filter">
                {filter}{" "}
                <button
                  className="remove-btn"
                  onClick={() => removeFilter(filter)}
                >
                  X
                </button>
              </span>
            );
          })}
      </div>
    </header>
  );
}

export default Header;
