import { useEffect, useState } from "react";
import { useDispatch, useSelector } from "react-redux";
import { RootState } from "../../redux/store";
import Pagination from "@mui/material/Pagination";
import { Button } from "../Button/Button";
import { Link } from "react-router-dom";
import ClipLoader from "react-spinners/ClipLoader";
import {
  BorderBottom,
  DashboardContainer,
  DashboardItem,
  DashboardItems,
  InfoHeader,
  NoStocks,
  PaginationContainer,
  Price,
  PriceTitle,
  PriceWatch,
  Stock,
  StockName,
  StocksContainer,
  linkTag,
} from "./Dashboard.styles";
import { hideSnackbar, showSnackbar } from "../../redux/slice/SnackbarSlice";
import { Alert, Snackbar } from "@mui/material";

export function Dashboard() {
  const [activeTab, setActiveTab] = useState("Explore");
  const [page, setPage] = useState(1);
  const stocksPerPage = 7;
  const reduxDispatch = useDispatch();

  const stocks = useSelector(
    (state: RootState) => state.stocksSlice.stocksList
  );
  const status = useSelector(
    (state: RootState) => state.stocksSlice.status
  );
  const isLoading = status === "pending"; 
  const snackbar = useSelector((state: RootState) => state.snackbarSlice);

  const displayedStocks =
    activeTab === "My WatchList"
      ? stocks.filter((stock) => {
          return stock.isWatchlisted;
        })
      : stocks;

  const startIndex = (page - 1) * stocksPerPage;
  const endIndex = startIndex + stocksPerPage;
  const slicedStocks = displayedStocks.slice(startIndex, endIndex);

  const handlePageChange = (
    event: React.ChangeEvent<unknown>,
    value: number
  ) => {
    setPage(value);
    event.stopPropagation();
  };

  const handleTabClick = (tabName: string) => {
    setActiveTab(tabName);
  };

  const handleHideSnackbar = () => {
    reduxDispatch(hideSnackbar());
  };

  useEffect(() => {
    setPage(1);
  }, [activeTab]);

  useEffect(() => {
    if(status === "fulfilled"){
      reduxDispatch(showSnackbar({message: "Stocks details fetched successfully", type: "success"}))
    }
    else if(status === "rejected"){
      reduxDispatch(showSnackbar({message: "Failed to fetch stocks details", type: "error"}))
    }
  },[status, reduxDispatch])

  return (
    <DashboardContainer>
      <DashboardItems>
        <DashboardItem onClick={() => handleTabClick("Explore")}>
          Explore
          {activeTab === "Explore" ? <BorderBottom></BorderBottom> : <></>}
        </DashboardItem>
        <DashboardItem onClick={() => handleTabClick("My WatchList")}>
          My WatchList
          {activeTab === "My WatchList" ? <BorderBottom></BorderBottom> : <></>}
        </DashboardItem>
      </DashboardItems>
      <StocksContainer>
        <InfoHeader>
          <div>Company</div>
          <PriceWatch>
            <PriceTitle>Base Price</PriceTitle>
            <div>Watchlist</div>
          </PriceWatch>
        </InfoHeader>
        {!isLoading ? (
          <>
            <div>
              {slicedStocks.length > 0 ? (
                slicedStocks.map((stock) => (
                  <Stock key={stock.stock_name}>
                    <Link style={linkTag} to={`/stock/${stock.stock_name}`}>
                      <StockName>{stock.stock_name}</StockName>
                    </Link>
                    <PriceWatch>
                      <Price>&#8377; {stock.base_price}</Price>
                      <Button stock={stock} />
                    </PriceWatch>
                  </Stock>
                ))
              ) : (
                <NoStocks>No stocks in WatchList currently, Add some!</NoStocks>
              )}
            </div>
            <PaginationContainer>
              <Pagination
                count={Math.ceil(displayedStocks.length / stocksPerPage)}
                color="primary"
                page={page}
                onChange={handlePageChange}
              />
            </PaginationContainer>
          </>
        ) : (
          <NoStocks>
            <ClipLoader
              color="black"
              loading={isLoading}
              size={100}
              aria-label="Loading Spinner"
              data-testid="loader"
              className="loader"
            />
          </NoStocks>
        )}
      </StocksContainer>
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
    </DashboardContainer>
  );
}
