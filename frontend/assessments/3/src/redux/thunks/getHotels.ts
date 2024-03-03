import { createAsyncThunk } from "@reduxjs/toolkit";

const fetchData = () => {
  return {
    roomTypes: [
      {
        id: 1,
        name: "Single Room",
        costPerNight: "5000",
        currency: "INR",
        addOns: [
          {
            name: "Breakfast",
            cost: "300",
            currency: "INR",
          },
          {
            name: "Extra Bed",
            cost: "2000",
            currency: "INR",
          },
        ],
      },
      {
        id: 2,
        name: "Twin Room",
        costPerNight: "7000",
        currency: "INR",
        addOns: [
          {
            name: "Breakfast",
            cost: "300",
            currency: "INR",
          },
          {
            name: "Balcony Unit",
            cost: "2000",
            currency: "INR",
          },
        ],
      },
      {
        id: 3,
        name: "Deluxe",
        costPerNight: "10000",
        currency: "INR",
        addOns: [
          {
            name: "Breakfast",
            cost: "300",
            currency: "INR",
          },
          {
            name: "Balcony Unit",
            cost: "2000",
            currency: "INR",
          },
          {
            name: "Sea Facing",
            cost: "3000",
            currency: "INR",
          },
        ],
      },
      {
        id: 4,
        name: "Presidential Suite",
        costPerNight: "12000",
        currency: "INR",
        addOns: [
          {
            name: "Breakfast",
            cost: "300",
            currency: "INR",
          },
          {
            name: "Pent House Unit",
            cost: "8000",
            currency: "INR",
          },
          {
            name: "Limousine Service",
            cost: "15000",
            currency: "INR",
          },
        ],
      },
    ],
  };
};
const getHotels = createAsyncThunk("getHotels", async () => {
  try {
    const data = fetchData();
    return data.roomTypes;
  } catch {
    throw new Error("Fetching data failed");
  }
});

export default getHotels;
