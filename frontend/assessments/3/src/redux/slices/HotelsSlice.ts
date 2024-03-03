import { createSlice } from "@reduxjs/toolkit";
import { HotelsSliceState } from "../../interface";
import getHotels from '../thunks/getHotels'

const initialState: HotelsSliceState = {
    hotelsList: [],
    state: "pending",
    error: ''
}
const HotelsSlice = createSlice({
    name:'hotels',
    initialState,
    reducers: {
        
    },
    extraReducers(builder) {
        builder.addCase(getHotels.fulfilled, (state, action) => {
            state.hotelsList = action.payload;
            state.state = "fulfilled";
        })
        .addCase(getHotels.pending, (state) => {
            state.state = "pending";
        })
        .addCase(getHotels.rejected, (state,action) => {
            state.state = "rejected";
            state.error= action.payload as string;
        })
    },
})

export default HotelsSlice.reducer