import { PayloadAction, createSlice } from "@reduxjs/toolkit";

interface SearchInputState {
    searchInput: string
}

const initialState: SearchInputState = {
    searchInput: ''
}
const searchInputSlice = createSlice({
    name: 'searchInput',
    initialState: initialState,
    reducers: {
        changeInput: (state, action: PayloadAction<string>) => {
            state.searchInput = action.payload;
        }
    }
})

export const {changeInput} = searchInputSlice.actions;
export default searchInputSlice.reducer