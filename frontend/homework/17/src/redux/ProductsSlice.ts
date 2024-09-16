import { createSlice } from "@reduxjs/toolkit";
import { IProduct } from "../components/Marketplace/Marketplace";
import getProducts from "./thunks/getProducts";

interface ProductsSliceState{
    productsList: IProduct[],
    state: "fulfilled" | "pending" | "rejected",
    error: string,
    filters: string[]
}

const initialState: ProductsSliceState = {
    productsList: [],
    state: "pending",
    error: '',
    filters: []
}
const ProductsSlice = createSlice({
    name:'products',
    initialState,
    reducers: {

    },
    extraReducers(builder) {
        builder.addCase(getProducts.fulfilled, (state, action) => {
            state.productsList = action.payload.data;
            state.filters = action.payload.filters as string[];
            state.state = "fulfilled";
        })
        .addCase(getProducts.pending, (state) => {
            state.state = "pending";
        })
        .addCase(getProducts.rejected, (state,action) => {
            state.state = "rejected";
            state.error= action.payload as string;
        })
    },
})

export default ProductsSlice.reducer