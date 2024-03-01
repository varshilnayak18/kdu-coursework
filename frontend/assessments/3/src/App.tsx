import { useDispatch } from 'react-redux';
import './App.css'
import { HotelBooking } from './components/HotelBooking/HotelBooking'
import { AppDispatch } from './redux/store';
import { useEffect } from 'react';
import getHotels from './redux/thunks/getHotels';

function App() {
  const reduxDispatch: AppDispatch = useDispatch();
  useEffect(() => {
    reduxDispatch(getHotels());
  }, [reduxDispatch]);
  return (
    <>
      <HotelBooking/>
    </>
  )
}

export default App
