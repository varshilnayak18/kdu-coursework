import { createSlice, PayloadAction } from "@reduxjs/toolkit";
import { getStocks } from "../thunk/getStocks";
import { IStock, StocksSliceState } from "../../utils/interfaces";



const initialState: StocksSliceState = {
  stocksList: [],
  status: "pending",
  error: "",
};

const toSorted = (stocks: IStock[]): IStock[] => {
  return stocks
    .slice()
    .sort((a, b) => a.stock_name.localeCompare(b.stock_name));
};

const stocksSlice = createSlice({
  name: "stocksSlice",
  initialState,
  reducers: {
    addToWatchlist(state, action: PayloadAction<string>) {
      const stockName = action.payload;
      state.stocksList = state.stocksList.map(stock =>
        stock.stock_name === stockName ? { ...stock, isWatchlisted: true } : stock
      );
    },
    removeFromWatchlist(state, action: PayloadAction<string>) {
      const stockName = action.payload;
      state.stocksList = state.stocksList.map(stock =>
        stock.stock_name === stockName ? { ...stock, isWatchlisted: false } : stock
      );
    },
  },
  extraReducers: (builder) => {
    builder
      .addCase(getStocks.pending, (state) => {
        state.status = "pending";
      })
      .addCase(getStocks.fulfilled, (state, action) => {
        state.status = "fulfilled";
        const stocks: IStock[] = action.payload;
        state.stocksList = toSorted(stocks).map(stock => ({
          ...stock,
          isWatchlisted: false 
        }));
      })
      .addCase(getStocks.rejected, (state, action) => {
        state.status = "rejected";
        state.error = action.payload as string;
      });
  },
});

export const { addToWatchlist, removeFromWatchlist } = stocksSlice.actions;

export default stocksSlice.reducer;
