import { PayloadAction, createSlice } from "@reduxjs/toolkit";

interface HeaderSliceState {
    selectedFilter: string,
    selectedSort: string,
    searchInput: string
}

const initialState: HeaderSliceState = {
    selectedFilter: 'default',
    selectedSort: 'default',
    searchInput: ''
}
const HeaderSlice = createSlice({
    name: 'header',
    initialState,
    reducers: {
        setSelectedFilter: (state, action: PayloadAction<string>) => {
            state.selectedFilter = action.payload;
        },
        setSelectedSort: (state, action: PayloadAction<string>) => {
            state.selectedSort = action.payload;
        },
        setSearchInput: (state, action: PayloadAction<string>) => {
            state.searchInput = action.payload;
        }, 
    }
})

export const {setSelectedFilter, setSelectedSort, setSearchInput} = HeaderSlice.actions;
export default HeaderSlice.reducer