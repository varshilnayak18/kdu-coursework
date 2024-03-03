import { useDispatch, useSelector } from "react-redux";
import { RootState } from "../../redux/store";
import { Filter } from "../Filter/Filter";
import {
  PortfolioContainer,
  TransactionsContainer,
  DateWiseContainer,
  TransactionsDate,
  Transactions,
  Info,
  NameSymbol,
  PriceTime,
  Outcome,
  Green,
  Red,
  Loader,
  UnfilteredTransaction,
  FilteredTransaction,
} from "./Portfolio.styles";
import ClipLoader from "react-spinners/ClipLoader";
import { Alert, Snackbar } from "@mui/material";
import { hideSnackbar, showSnackbar } from "../../redux/slice/SnackbarSlice";
import { useEffect } from "react";
import { IGroupedTransactions, ITransaction } from "../../utils/interfaces";

const formatTime = (timeString: string) => {
  const timeParts = timeString.split("T")[1].split(":");
  const hours = parseInt(timeParts[0], 10);
  const minutes = timeParts[1];
  const ampm = hours >= 12 ? "PM" : "AM";
  const formattedHours = hours % 12 === 0 ? 12 : hours % 12;
  return `${formattedHours}:${minutes} ${ampm}`;
};

export function Portfolio() {
  const transactionsList = useSelector(
    (state: RootState) => state.transactionsSlice.transactionsList
  );
  const status = useSelector(
    (state: RootState) => state.transactionsSlice.status
  );
  const isLoading = status === "pending";
  const filters = useSelector((state: RootState) => state.filtersSlice);
  const snackbar = useSelector((state: RootState) => state.snackbarSlice);
  const reduxDispatch = useDispatch();

  const searchText = filters.searchText;
  const showPassed = filters.showPassed;
  const showFailed = filters.showFailed;
  const startDate = filters.startDate;
  const endDate = filters.endDate;
  const selectedStocks = filters.selectedStocks;

  const isWithinSelectedDateRange = (
    transactionDate: Date,
    startDateToFilter: Date | null,
    endDateToFilter: Date | null
  ): boolean => {
    if (startDateToFilter && endDateToFilter) {
      return (
        transactionDate >= startDateToFilter &&
        transactionDate <=
          new Date(endDateToFilter.setDate(endDateToFilter.getDate() + 1))
      );
    } else if (startDateToFilter) {
      return (
        transactionDate >= startDateToFilter && transactionDate <= new Date()
      );
    } else if (endDateToFilter) {
      return (
        transactionDate <=
        new Date(endDateToFilter.setDate(endDateToFilter.getDate() + 1))
      );
    }
    return true;
  };

  const filteredTransactionsList = transactionsList.filter((transaction) => {
    const searchFilter =
      searchText.trim() === "" ||
      transaction.stock_name.toLowerCase().includes(searchText.toLowerCase()) ||
      transaction.stock_symbol.toLowerCase().includes(searchText.toLowerCase());

    const statusFilter =
      (!showPassed && !showFailed) ||
      (showPassed && showFailed) ||
      (showPassed && transaction.status === "Passed") ||
      (showFailed && transaction.status === "Failed");

    const startDateToFilter = startDate.length > 0 ? new Date(startDate) : null;
    const endDateToFilter = endDate.length > 0 ? new Date(endDate) : null;

    const transactionDate = new Date(transaction.timestamp);

    const withinSelectedDateRange = isWithinSelectedDateRange(
      transactionDate,
      startDateToFilter,
      endDateToFilter
    );

    return searchFilter && statusFilter && withinSelectedDateRange;
  });

  const groupByDate = (array: ITransaction[]): IGroupedTransactions => {
    return array.reduce(
      (result: IGroupedTransactions, currentValue: ITransaction) => {
        const date = currentValue.timestamp.split("T")[0];
        const transactionsForDate = result[date] || [];
        transactionsForDate.push(currentValue);
        result[date] = transactionsForDate;
        return result;
      },
      {}
    );
  };

  const formatDate = (dateString: string) => {
    const dateParts = dateString.split("-");
    const year = dateParts[0];
    const month = dateParts[1];
    const day = dateParts[2];
    const months = [
      "Jan",
      "Feb",
      "Mar",
      "Apr",
      "May",
      "Jun",
      "Jul",
      "Aug",
      "Sep",
      "Oct",
      "Nov",
      "Dec",
    ];
    return `${day} ${months[parseInt(month, 10) - 1]} ${year}`;
  };

  const renderTransactionStyle = (
    transaction: ITransaction,
    selectedStocks: string[]
  ): boolean => {
    if (selectedStocks.length > 0) {
      return selectedStocks.includes(transaction.stock_name);
    }
    return true;
  };

  const handleHideSnackbar = () => {
    reduxDispatch(hideSnackbar());
  };  

  useEffect(() => {
    if (status === "fulfilled") {
      reduxDispatch(
        showSnackbar({
          message: "Transactions details fetched successfully",
          type: "success",
        })
      );
    } else if (status === "rejected") {
      reduxDispatch(
        showSnackbar({
          message: "Failed to fetch transactions details",
          type: "error",
        })
      );
    }
  }, [status, reduxDispatch]);

  return (
    <PortfolioContainer>
      <Filter />
      <TransactionsContainer>
        {!isLoading ? (
          Object.entries(groupByDate(filteredTransactionsList)).map(
            ([date, transactions]) => (
              <DateWiseContainer key={date}>
                <TransactionsDate>{formatDate(date)}</TransactionsDate>
                <Transactions>
                  {transactions.map((transaction) =>
                    renderTransactionStyle(transaction, selectedStocks) ? (
                      <FilteredTransaction key={transaction.timestamp}>
                        <Info>
                          <NameSymbol>
                            <div>{transaction.stock_name}</div>
                            <div>{transaction.stock_symbol}</div>
                          </NameSymbol>
                          <PriceTime>
                            <div>&#8377; {transaction.transaction_price}</div>
                            <div>{formatTime(transaction.timestamp)}</div>
                          </PriceTime>
                        </Info>
                        <Outcome>
                          {transaction.status === "Passed" ? (
                            <Green
                              width="15"
                              height="15"
                              viewBox="0 0 24 24"
                              xmlns="http://www.w3.org/2000/svg"
                            >
                              <circle cx="11.998" cy="11.998" r="9.998" />
                            </Green>
                          ) : (
                            <Red
                              width="15"
                              height="15"
                              viewBox="0 0 24 24"
                              xmlns="http://www.w3.org/2000/svg"
                            >
                              <circle cx="11.998" cy="11.998" r="9.998" />
                            </Red>
                          )}
                        </Outcome>
                      </FilteredTransaction>
                    ) : (
                      <UnfilteredTransaction key={transaction.timestamp}>
                        <Info>
                          <NameSymbol>
                            <div>{transaction.stock_name}</div>
                            <div>{transaction.stock_symbol}</div>
                          </NameSymbol>
                          <PriceTime>
                            <div>&#8377; {transaction.transaction_price}</div>
                            <div>{formatTime(transaction.timestamp)}</div>
                          </PriceTime>
                        </Info>
                        <Outcome>
                          {transaction.status === "Passed" ? (
                            <Green
                              width="15"
                              height="15"
                              viewBox="0 0 24 24"
                              xmlns="http://www.w3.org/2000/svg"
                            >
                              <circle cx="11.998" cy="11.998" r="9.998" />
                            </Green>
                          ) : (
                            <Red
                              width="15"
                              height="15"
                              viewBox="0 0 24 24"
                              xmlns="http://www.w3.org/2000/svg"
                            >
                              <circle cx="11.998" cy="11.998" r="9.998" />
                            </Red>
                          )}
                        </Outcome>
                      </UnfilteredTransaction>
                    )
                  )}
                </Transactions>
              </DateWiseContainer>
            )
          )
        ) : (
          <Loader>
            <ClipLoader
              color="black"
              loading={isLoading}
              size={100}
              aria-label="Loading Spinner"
              data-testid="loader"
              className="loader"
            />
          </Loader>
        )}
      </TransactionsContainer>
      <Snackbar
        open={snackbar.show}
        anchorOrigin={{
          vertical: "bottom",
          horizontal: "center",
        }}
        autoHideDuration={2000}
        onClose={handleHideSnackbar}
      >
        <Alert severity={snackbar.type} variant="filled">
          {snackbar.message}
        </Alert>
      </Snackbar>
    </PortfolioContainer>
  );
}
