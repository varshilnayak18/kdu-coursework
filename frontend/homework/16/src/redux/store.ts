import { configureStore, combineReducers } from "@reduxjs/toolkit";
import itemsListReducer from './ItemsListSlice';
import SearchInputReducer from './SearchInputSlice';
import storageSession from "redux-persist/lib/storage/session";
import { persistReducer } from "redux-persist";

const persistConfig = {
    key: 'root',
    version: 1,
    storage: storageSession
};

const reducer = combineReducers({
    itemsList: itemsListReducer,
    searchInput: SearchInputReducer
});

const persistedReducer = persistReducer(persistConfig, reducer);

export const store = configureStore({
    reducer: persistedReducer
})

export type RootState = ReturnType<typeof store.getState>