import { useSelector } from "react-redux";
import { RootState } from "../../redux/store";
import { Title } from "../Title/Title";
import { Card } from "../Card/Card";
import * as styles from "./HotelBooking.styles";
import { useEffect, useState } from "react";
import { IAddOn, IHotel } from "../../interface";
import { AddOn } from "../AddOn/AddOn";

export function HotelBooking() {
  const hotelsList = useSelector(
    (state: RootState) => state.hotelsList.hotelsList
  );
  const [selectedHotel, setSelectedHotel] = useState<IHotel | null>(null);
  const [selectedAddOns, setSelectedAddOns] = useState<IAddOn[]>([]);
  const [startDate, setStartDate] = useState<string>("");
  const [endDate, setEndDate] = useState<string>("");

  const checkDate = () => {
    return selectedHotel && startDate && (new Date(startDate) < new Date(endDate));
  }

  const calculateDays = () => {
    const start = new Date(startDate);
    const end = new Date(endDate);
    return Math.max((end - start) / (1000 * 60 * 60 * 24), 1); 
  };

  useEffect ( () => {
    if(!checkDate()){
      setEndDate('');
    }
  }, [endDate])

  const calculateTotal = () => {
    if (!selectedHotel || !startDate || !endDate) return '0';
    let cost:number = 0;
    const days = calculateDays();
    console.log(days);
    cost += days * parseInt(selectedHotel?.costPerNight);
    let addOnsCost = 0; 
    selectedAddOns.forEach((addOn) => (addOnsCost += parseInt(addOn.cost)));
    cost += addOnsCost;
    const tax = cost * 0.18; 
    return cost + tax;
  };

  return (
    <div style={styles.mainContainer}>
      <div style={styles.header}>Hotel Booking</div>
      <div style={styles.optionContainer}>
        <Title title="Select Room Type" />
        <div style={styles.cardsContainer}>
          {hotelsList.map((hotel) => (
            <Card
              key={hotel.id}
              type={hotel.name}
              hotel={hotel}
              selectedHotel={selectedHotel}
              setSelectedHotel={setSelectedHotel}
            />
          ))}
        </div>
      </div>
      <div style={styles.optionContainer}>
        <Title title="Select Date" />
        <div style={styles.cardsContainer}>
          <input
            style={styles.date}
            type="date"
            name="start-date"
            id="start-date"
            onChange={(event) => setStartDate(event.target.value)}
          />
          <input
            style={styles.date}
            type="date"
            name="end-date"
            id="end-date"
            onChange={(event) => {
              setEndDate(event.target.value);
            }}
          />
        </div>
      </div>
      <div style={styles.optionContainer}>
        <Title title="Select additional add ons / preferences" />
        <div style={styles.cardsContainer}>
          {selectedHotel ? (
            selectedHotel.addOns.map((addOn) => (
              <AddOn
                key={addOn.name}
                addOn={addOn}
                selectedAddOns={selectedAddOns}
                setSelectedAddOns={setSelectedAddOns}
              />
            ))
          ) : (
            <div style={styles.noSelect}> Select Hotel Type first</div>
          )}
        </div>
      </div>
      <div style={styles.overviewContainer}>
        {selectedHotel && (
          <>
            <div style={styles.overviewContainer}>
              Cost + 18% GST = <span>{calculateTotal()}</span>
            </div>
            <div style={styles.button}>Submit</div>
          </>
        )}
      </div>
    </div>
  );
}
