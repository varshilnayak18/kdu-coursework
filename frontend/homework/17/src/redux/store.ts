import { configureStore } from "@reduxjs/toolkit";
import ProductsReducer from './ProductsSlice'
import HeaderReducer from './HeaderSlice'
import SnackbarReducer from './SnackbarSlice'

export const store = configureStore({
    reducer: {
        productsList: ProductsReducer,
        header: HeaderReducer, 
        snackbar: SnackbarReducer
    }
})

export type RootState = ReturnType<typeof store.getState>
export type AppDispatch = typeof store.dispatch