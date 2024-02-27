import { PayloadAction, createSlice } from "@reduxjs/toolkit";
import { IItem } from "../interface";

interface ItemsListState {
    itemsList: IItem[],
    itemInput: string
}

const initialState: ItemsListState = {
    itemsList: [],
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
        },
        setItemCompleted: (state, action: PayloadAction<number>) => {
            const itemToUpdate = state.itemsList.find(item => item.id === action.payload);
            if(itemToUpdate){
                itemToUpdate.completed = !itemToUpdate.completed;
            }
        },
        removeCompletedItems: (state) => {
            state.itemsList = state.itemsList.filter(item => !(item.completed));
        }
    }
})

export const {addItem, removeItem, setItemInput, setItemCompleted, removeCompletedItems} = itemsListSlice.actions;
export default itemsListSlice.reducer