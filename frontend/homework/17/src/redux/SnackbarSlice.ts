import { createSlice } from "@reduxjs/toolkit";
import getProducts from "./thunks/getProducts";

interface SnackbarState {
  message: string;
  show: boolean;
  type: "success" | "error";
}

const initialState: SnackbarState = {
  message: "",
  show: false,
  type: "success",
};

const SnackbarSlice = createSlice({
  name: "snackbar",
  initialState,
  reducers: {
  },
  extraReducers(builder) {
    builder
      .addCase(getProducts.fulfilled, (state) => {
        state.message = "Products details fetched successfully";
        state.show = true;
        state.type = "success";
      })
      .addCase(getProducts.rejected, (state) => {
        state.message = "Failed to fetch product details";
        state.show = true;
        state.type = "error";
      });
  },
});

export default SnackbarSlice.reducer;
