import { configureStore } from "@reduxjs/toolkit";
import itemsListReducer from './ItemsListSlice';
import SearchInputReducer from './SearchInputSlice';

export const store = configureStore({
    reducer: {
        itemsList: itemsListReducer,
        searchInput: SearchInputReducer
    }
})

export type RootState = ReturnType<typeof store.getState>