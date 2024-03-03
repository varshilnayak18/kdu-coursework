import { createSlice, PayloadAction } from "@reduxjs/toolkit";
import { getTransactions } from "../thunk/getTransactions";
import { ITransaction, TransactionsSliceState } from "../../utils/interfaces";

const initialState: TransactionsSliceState = {
  transactionsList: [],
  status: "pending",
  error: "",
};

const toSorted = (transactions: ITransaction[]): ITransaction[] => {
  return transactions
    .slice()
    .sort((a, b) => {const dateA = new Date(a.timestamp);
        const dateB = new Date(b.timestamp);
        return dateB.getTime() - dateA.getTime();});
};

const transactionsSlice = createSlice({
  name: "transactionsSlice",
  initialState,
  reducers: {
    addTransaction(state, action: PayloadAction<ITransaction>) {
        state.transactionsList = [action.payload, ...state.transactionsList];
    },
  },
  extraReducers: (builder) => {
    builder
      .addCase(getTransactions.pending, (state) => {
        state.status = "pending";
      })
      .addCase(getTransactions.fulfilled, (state, action) => {
        state.status = "fulfilled";
        state.transactionsList = toSorted(action.payload);
      })
      .addCase(getTransactions.rejected, (state, action) => {
        state.status = "rejected";
        state.error = action.payload as string;
      });
  },
});

export const { addTransaction } = transactionsSlice.actions;

export default transactionsSlice.reducer;
