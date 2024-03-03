import { useDispatch, useSelector } from "react-redux";
import * as styles from "./StockDetail.styles";
import { AppDispatch, RootState } from "../../redux/store";
import { useParams } from "react-router-dom";
import { Dropdown } from "../Dropdown/Dropdown";
import {
  addTransaction,
} from "../../redux/slice/TransactionsSlice";
import { useEffect, useState } from "react";
import { buyStock, sellStock } from "../../redux/slice/UsersSlice";
import { Alert, Snackbar } from "@mui/material";
import { hideSnackbar, showSnackbar } from "../../redux/slice/SnackbarSlice";
import {
  BarChart,
  Bar,
  XAxis,
  YAxis,
  CartesianGrid,
  Tooltip,
  Legend,
} from "recharts";
import { io } from "socket.io-client";
import { IBar, IHistory, ITransaction } from "../../utils/interfaces";


const socket = io("http://localhost:3000");

export function StockDetail() {
  const reduxDispatch: AppDispatch = useDispatch();

  const stocksList = useSelector(
    (state: RootState) => state.stocksSlice.stocksList
  );
  const [userHistory, setUserHistory] = useState<IHistory[]>([]);
  const [globalHistory, setGlobalHistory] = useState<IHistory[]>([]);
  const [currentPrice, setCurrentPrice] = useState<number>(0);
  const [percentage, setPercentage] = useState<number>(0.0);
  const [graphBars, setGraphBars] = useState<IBar[]>([]);

  const username = useSelector((state: RootState) => state.usersSlice.username);
  const snackbar = useSelector((state: RootState) => state.snackbarSlice);
  const portfolio = useSelector(
    (state: RootState) => state.usersSlice.portfolio
  );
  const walletBalance = useSelector(
    (state: RootState) => state.usersSlice.walletBalance
  );
  const { stock_name } = useParams();
  const currentStock = stocksList.find(
    (stock) => stock.stock_name === stock_name
  )!;

  const executeTransaction = (type: "Buy" | "Sell") => {
    const stockQuantityInput = document.getElementById(
      "stock-quantity"
    ) as HTMLInputElement;
    const stockQuantity = Number(stockQuantityInput?.value) || 0;
    const stockPrice = currentPrice;
    const transactionAmount = stockQuantity * stockPrice;

    const transaction: ITransaction = {
      stock_name: currentStock.stock_name,
      stock_symbol: currentStock.stock_symbol,
      transaction_price: transactionAmount,
      timestamp: new Date().toISOString(),
      status: "Passed",
    };

    if (
      (type === "Buy" && transactionAmount > walletBalance) ||
      (type === "Sell" &&
        stockQuantity > (portfolio[currentStock.stock_name] || 0))
    ) {
      type === "Buy"
        ? reduxDispatch(
            showSnackbar({ message: "Insufficient balance", type: "error" })
          )
        : reduxDispatch(
            showSnackbar({ message: "Insufficient stocks", type: "error" })
          );
      transaction.status = "Failed";
      reduxDispatch(addTransaction(transaction));
      return;
    }

    const history: IHistory = {
      stock_name: currentStock.stock_name,
      quantity: stockQuantity,
      type,
      username: username,
      timestamp: new Date().toISOString(),
    };
    reduxDispatch(addTransaction(transaction));
    setUserHistory([history, ...userHistory]);
    socket.emit("new-transaction", history);

    if (type === "Buy") {
      reduxDispatch(
        buyStock({
          stock: currentStock.stock_name,
          quantity: stockQuantity,
          price: transactionAmount,
        })
      );
      reduxDispatch(
        showSnackbar({ message: "Buy succesfully executed", type: "success" })
      );
    } else {
      reduxDispatch(
        sellStock({
          stock: currentStock.stock_name,
          quantity: stockQuantity,
          price: transactionAmount,
        })
      );
      reduxDispatch(
        showSnackbar({ message: "Sell succesfully executed", type: "success" })
      );
    }
  };

  const formatTime = (timeString: string) => {
    const timeParts = timeString.split("T")[1].split(":");
    const hours = parseInt(timeParts[0], 10);
    const minutes = timeParts[1];
    const ampm = hours >= 12 ? "PM" : "AM";
    const formattedHours = hours % 12 === 0 ? 12 : hours % 12;
    return `${formattedHours}:${minutes} ${ampm}`;
  };

  function formatTimestamp(timestamp: string) {
    const date = new Date(timestamp);
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
    const days = ["Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat"];
    const formattedDate = `${days[date.getUTCDay()]}, ${date.getUTCDate()} ${
      months[date.getUTCMonth()]
    } ${date.getUTCFullYear()} ${date.getUTCHours()}:${date.getUTCMinutes()}:${date.getUTCSeconds()} GMT`;
    return formattedDate;
  }

  const generateRandomPriceChange = () => {
    const priceChanges = ["up", "down"];
    const newPrice = Math.floor(Math.random() * 501);
    const index = Math.floor(Math.random() * priceChanges.length);
    if (priceChanges[index] === "up") {
      return newPrice;
    } else {
      return -newPrice;
    }
  };

  const handleHideSnackbar = () => {
    reduxDispatch(hideSnackbar());
  };

  useEffect(() => {
    socket.emit("join-room", currentStock?.stock_name);

    socket.on("initial-transactions", (data) => {
      setGlobalHistory(data);
    });

    socket.on("new-transaction", (transaction) => {
      setGlobalHistory((prevHistory) => [...prevHistory, transaction]);
    });

    return () => {
      socket.off("initial-transactions");
      socket.off("new-transaction");
      socket.off("username");
    };
  }, [currentStock, reduxDispatch]);

  useEffect(() => {
    setCurrentPrice(currentStock?.base_price);
    setPercentage(0.0);
    setGraphBars([]);

    const priceUpdateInterval = setInterval(() => {
      setCurrentPrice((prevPrice: number) => {
        const newPrice = generateRandomPriceChange();
        const newPercentage = (newPrice / prevPrice) * 100;
        setPercentage(newPercentage);
        if (prevPrice + newPrice < 0) {
          return prevPrice;
        }
        prevPrice += newPrice;
        setGraphBars((prevGraphBars) => [
          ...prevGraphBars,
          {
            price: prevPrice / 10,
            fill: newPercentage > 0 ? "#b2f2bb" : "#ffc9c9",
          },
        ]);
        return prevPrice;
      });
    }, 1000);

    return () => clearInterval(priceUpdateInterval);
  }, [currentStock]);

  return (
    <main style={styles.main}>
      <div style={styles.leftContainer}>
        <div style={styles.stockInfo}>
          <div style={styles.logoName}>
            <Dropdown currentStock={currentStock} />
          </div>
          <div style={styles.price}>
            Price
            <div style={styles.currentPrice}>
              <span style={percentage >= 0 ? styles.typeGreen : styles.typeRed}>
                {currentPrice.toFixed(2)} &nbsp;
              </span>
              {percentage >= 0 ? (
                <span style={{ ...styles.typeGreen, fontSize: "2rem" }}>
                  &uarr;
                </span>
              ) : (
                <span style={{ ...styles.typeRed, fontSize: "2rem" }}>
                  &darr;
                </span>
              )}
              <span style={styles.percentage}>
                &nbsp; {percentage.toFixed(2)} %
              </span>
            </div>
          </div>
          <div style={styles.quantityContainer}>
            <input
              type="number"
              name="stock-quantity"
              id="stock-quantity"
              placeholder="Enter QTY"
              style={styles.quantity}
            />
          </div>
          <button style={styles.buy} onClick={() => executeTransaction("Buy")}>
            BUY
          </button>
          <button
            style={styles.sell}
            onClick={() => executeTransaction("Sell")}
          >
            SELL
          </button>
        </div>
        <BarChart
          width={1300}
          height={571}
          data={graphBars}
          barGap={0}
          barCategoryGap={0}
        >
          <CartesianGrid strokeDasharray="3 3" />
          <XAxis />
          <YAxis />
          <Tooltip />
          <Legend />
          <Bar dataKey="price" barSize={20} />
        </BarChart>
      </div>
      <div style={styles.rightContainer}>
        <div style={styles.history}>
          <div style={styles.title}>History</div>
          <div style={styles.orders}>
            {userHistory.length > 0 ? (
              userHistory.map((txn) => (
                <div key={txn.timestamp} style={styles.order}>
                  <div style={styles.info}>
                    <div style={styles.priceQuantity}>
                      {txn.quantity} Stocks
                    </div>
                    <div style={styles.time}>{formatTimestamp(txn.timestamp)} </div>
                  </div>
                  <div
                    style={
                      txn.type === "Buy" ? styles.typeGreen : styles.typeRed
                    }
                  >
                    {txn.type}
                  </div>
                </div>
              ))
            ) : (
              <>You have not done any transactions</>
            )}
          </div>
        </div>
        <div style={styles.globalHistory}>
          <div style={styles.globalOrders}>
            {globalHistory.length > 0 ? (
              globalHistory.map((txn) => (
                <div key={txn.timestamp} style={styles.globalOrder}>
                  <div style={styles.info}>
                    <div style={styles.priceQuantity}>
                      {txn.username} {txn.type === "Buy" ? "bought" : "sold"}{" "}
                      {txn.quantity} {currentStock.stock_name}
                    </div>
                    <div style={styles.time}>{formatTime(txn.timestamp)} </div>
                  </div>
                </div>
              ))
            ) : (
              <>
                No transactions currently in the market for{" "}
                {currentStock?.stock_name}
              </>
            )}
          </div>
        </div>
      </div>
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
    </main>
  );
}
