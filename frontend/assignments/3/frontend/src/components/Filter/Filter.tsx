import { useDispatch, useSelector } from "react-redux";
import { RootState } from "../../redux/store";
import {
  removeAllSelectedStocks,
  setEndDate,
  setSearchText,
  setSelectedStocks,
  setShowFailed,
  setShowPassed,
  setStartDate,
} from "../../redux/slice/FiltersSlice";
import { StyledCheckbox, ClearAll, DateContainer, DateInput, FilterContainer, FilterHeader, Header, SearchBar, SearchInput, StocksFilterContainer, TransactionOutcomeContainer, CheckboxContainer, StyledLabel } from "./Filter.styles";
import { ITransaction } from "../../utils/interfaces";

export function Filter() {
  const transactionsList = useSelector(
    (state: RootState) => state.transactionsSlice.transactionsList
  );
  const filters = useSelector((state: RootState) => state.filtersSlice);

  const searchText = filters.searchText;
  const showPassed = filters.showPassed;
  const showFailed = filters.showFailed;
  const startDate = filters.startDate;
  const endDate = filters.endDate;
  const selectedStocks = filters.selectedStocks;
  const reduxDispatch = useDispatch();

  const setSearchTextAction = (text: string) => {
    reduxDispatch(setSearchText(text));
  };

  const setShowPassedAction = (setTrue: boolean) => {
    reduxDispatch(setShowPassed(setTrue));
  };

  const setShowFailedAction = (setTrue: boolean) => {
    reduxDispatch(setShowFailed(setTrue));
  };

  const setStartDateAction = (date: string) => {
    reduxDispatch(setStartDate(date));
  };

  const setEndDateAction = (date: string) => {
    reduxDispatch(setEndDate(date));
  };

  const clearAllFilters = () => {
    setSearchTextAction("");
    setShowPassedAction(false);
    setShowFailedAction(false);
    setStartDateAction("");
    setEndDateAction("");
    reduxDispatch(removeAllSelectedStocks());
  };

  const stocksToFilter: string[] = Array.from(
    new Set(
      transactionsList.map(
        (transaction: ITransaction) => transaction.stock_name
      )
    )
  );

  const handleCheckboxChange = (stock: string) => {
    reduxDispatch(setSelectedStocks(stock));
  };

  return (
    <FilterContainer>
      <FilterHeader>
        <Header>
          <div>Filters</div>
          <ClearAll onClick={clearAllFilters}>Clear All</ClearAll>
        </Header>
      </FilterHeader>
      <FilterHeader>
        <SearchBar>
          <svg
            width="20"
            height="20"
            viewBox="0 0 24 24"
            xmlns="http://www.w3.org/2000/svg"
          >
            <path d="m15.97 17.031c-1.479 1.238-3.384 1.985-5.461 1.985-4.697 0-8.509-3.812-8.509-8.508s3.812-8.508 8.509-8.508c4.695 0 8.508 3.812 8.508 8.508 0 2.078-.747 3.984-1.985 5.461l4.749 4.75c.146.146.219.338.219.531 0 .587-.537.75-.75.75-.192 0-.384-.073-.531-.22zm-5.461-13.53c-3.868 0-7.007 3.14-7.007 7.007s3.139 7.007 7.007 7.007c3.866 0 7.007-3.14 7.007-7.007s-3.141-7.007-7.007-7.007z" />
          </svg>
          <SearchInput
            type="search"
            name="search"
            id="search"
            value={searchText}
            placeholder="  Search for a stock"
            onChange={(e) => setSearchTextAction(e.target.value)}
          />
        </SearchBar>
      </FilterHeader>
      <DateContainer>
        <DateInput
          type="date"
          name="start-date"
          id="start-date"
          value={startDate}
          placeholder="Start Date"
          onChange={(e) => setStartDateAction(e.target.value)}
        />
        <DateInput
          type="date"
          name="end-date"
          id="end-date"
          value={endDate}
          placeholder="End Date"
          onChange={(e) => setEndDateAction(e.target.value)}
        />
      </DateContainer>
      <TransactionOutcomeContainer>
        <CheckboxContainer>
          <StyledCheckbox
            type="checkbox"
            name="passed"
            id="passed"
            checked={showPassed}
            onChange={() => setShowPassedAction(!showPassed)}
          />
          <StyledLabel htmlFor="passed">Passed</StyledLabel>
        </CheckboxContainer>
        <CheckboxContainer>
          <StyledCheckbox
            type="checkbox"
            name="failed"
            id="failed"
            checked={showFailed}
            onChange={() => setShowFailedAction(!showFailed)}
          />
          <StyledLabel htmlFor="failed">Failed</StyledLabel>
        </CheckboxContainer>
      </TransactionOutcomeContainer>
      <StocksFilterContainer>
        {stocksToFilter.map((stock) => (
          <CheckboxContainer key={stock}>
            <StyledCheckbox
              type="checkbox"
              name={stock}
              id={stock}
              checked={selectedStocks.includes(stock)}
              onChange={() => handleCheckboxChange(stock)}
            />
            <StyledLabel htmlFor={stock}>{stock}</StyledLabel>
          </CheckboxContainer>
        ))}
      </StocksFilterContainer>
    </FilterContainer>
  );
}
