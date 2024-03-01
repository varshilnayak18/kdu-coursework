import { configureStore } from "@reduxjs/toolkit";
import HotelsSliceReducer from './slices/HotelsSlice'
export const store = configureStore({
    reducer: {
        hotelsList: HotelsSliceReducer,
    }
})

export type RootState = ReturnType<typeof store.getState>
export type AppDispatch = typeof store.dispatch