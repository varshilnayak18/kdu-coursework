import { createSlice, PayloadAction } from "@reduxjs/toolkit";
import { FiltersSliceState } from "../../utils/interfaces";

const initialState: FiltersSliceState = {
  searchText: "",
  showPassed: false,
  showFailed: false,
  startDate: '',
  endDate: '',
  selectedStocks: [],
};

const filtersSlice = createSlice({
  name: "filtersSlice",
  initialState,
  reducers: {
    setSearchText(state, action: PayloadAction<string>) {
      state.searchText = action.payload;
    },
    setShowPassed(state, action: PayloadAction<boolean>) {
      state.showPassed = action.payload;
    },
    setShowFailed(state, action: PayloadAction<boolean>) {
      state.showFailed = action.payload;
    },
    setStartDate(state, action: PayloadAction<string>) {
      state.startDate = action.payload;
    },
    setEndDate(state, action: PayloadAction<string>) {
      state.endDate = action.payload;
    },
    setSelectedStocks(state, action: PayloadAction<string>) {
      if (state.selectedStocks.includes(action.payload)) {
        state.selectedStocks = state.selectedStocks.filter((selected) => selected !== action.payload)
      } else {
        state.selectedStocks =[...state.selectedStocks, action.payload];
      }
    },
    removeAllSelectedStocks(state) {
        state.selectedStocks = [];
    }
  },
});

export const {
  setSearchText,
  setShowPassed,
  setShowFailed,
  setStartDate,
  setEndDate,
  setSelectedStocks,
  removeAllSelectedStocks
} = filtersSlice.actions;

export default filtersSlice.reducer;
