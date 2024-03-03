import { createSlice, PayloadAction } from "@reduxjs/toolkit";
import { UserSliceState } from "../../utils/interfaces";


const initialState: UserSliceState = {
  username: "Admin",
  walletBalance: Math.floor(Math.random() * 100000) + 10001,
  portfolio: {},
};

const usersSlice = createSlice({
  name: "usersSlice",
  initialState,
  reducers: {
    setUsername: (state, action: PayloadAction<string>) => {
      state.username = action.payload;
    },
    buyStock: (
      state,
      action: PayloadAction<{ stock: string; quantity: number; price: number }>
    ) => {
      const { stock, quantity, price } = action.payload;
      state.walletBalance -= price;
      state.portfolio[stock] = (state.portfolio[stock] || 0) + quantity;
    },
    sellStock: (
      state,
      action: PayloadAction<{ stock: string; quantity: number; price: number }>
    ) => {
      const { stock, quantity, price } = action.payload;
        state.walletBalance += price;
        state.portfolio[stock] -= quantity;
        if (state.portfolio[stock] === 0) {
          delete state.portfolio[stock];
        }
    },
  },
});

export const { setUsername, buyStock, sellStock } = usersSlice.actions;

export default usersSlice.reducer;
