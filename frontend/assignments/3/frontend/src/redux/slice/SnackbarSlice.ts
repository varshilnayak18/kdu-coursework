import { createSlice } from "@reduxjs/toolkit";
import { SnackbarState } from "../../utils/interfaces";

const initialState: SnackbarState = {
  message: "",
  show: false,
  type: "success",
};

const snackbarSlice = createSlice({
  name: "snackbarSlice",
  initialState,
  reducers: {
    showSnackbar(state, action) {
      state.show = true;
      state.message = action.payload.message;
      state.type = action.payload.type;
    },
    hideSnackbar(state) {
      state.show = false;
    },
  },
});

export const {showSnackbar, hideSnackbar} = snackbarSlice.actions;
export default snackbarSlice.reducer;
