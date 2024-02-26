import { PayloadAction, createSlice } from "@reduxjs/toolkit";
import { IItem } from "../interface";

interface ItemsListState {
    itemsList: IItem[],
    itemInput: string
}

const initialState: ItemsListState = {
    itemsList: [{ id: 1, text: "Item 1" }],
    itemInput: ''
}
const itemsListSlice = createSlice({
    name: 'itemsList',
    initialState: initialState,
    reducers: {
        addItem: (state, action: PayloadAction<IItem>) => {
            state.itemsList = [...state.itemsList, action.payload];
        },
        removeItem: (state, action: PayloadAction<number>) => {
            state.itemsList = state.itemsList.filter(item => item.id !== action.payload);
        },
        setItemInput: (state, action: PayloadAction<string>) => {
            state.itemInput = action.payload;
        }
    }
})

export const {addItem, removeItem, setItemInput} = itemsListSlice.actions;
export default itemsListSlice.reducer