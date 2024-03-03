import { configureStore } from "@reduxjs/toolkit";
import stocksReducer from "./slice/StocksSlice";
import transactionsReducer from "./slice/TransactionsSlice";
import filtersReducer from "./slice/FiltersSlice";
import usersReducer from "./slice/UsersSlice";
import snackbarReducer from "./slice/SnackbarSlice";

export const store = configureStore({
  reducer: {
    stocksSlice: stocksReducer,
    transactionsSlice: transactionsReducer,
    filtersSlice: filtersReducer,
    usersSlice: usersReducer,
    snackbarSlice: snackbarReducer,
  },
});

export type RootState = ReturnType<typeof store.getState>;
export type AppDispatch = typeof store.dispatch;
