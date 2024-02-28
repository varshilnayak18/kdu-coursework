import { createAsyncThunk } from "@reduxjs/toolkit";
import { IProduct } from "../../components/Marketplace/Marketplace";

const getProducts = createAsyncThunk(
    "getProducts",
    async () => {
        try{
            const res = await fetch('https://fakestoreapi.com/products');
            const data = await res.json();
            const filters = [...new Set(data.map((product: IProduct) => product.category))];
            return {data, filters};
        }
        catch{
            throw new Error("Fetching data failed");
        }
    }
)

export default getProducts;