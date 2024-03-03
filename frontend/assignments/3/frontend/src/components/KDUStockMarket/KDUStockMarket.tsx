import { Header } from '../Header/Header';
import { Dashboard } from '../Dashboard/Dashboard';
import { useEffect } from 'react';
import { useDispatch } from 'react-redux';
import { getStocks } from '../../redux/thunk/getStocks';
import { AppDispatch } from '../../redux/store';
import { Route, Routes } from 'react-router-dom';
import { StockDetail } from '../StockDetail/StockDetail';
import { Portfolio } from '../Portfolio/Portfolio';
import { getTransactions } from '../../redux/thunk/getTransactions';
import { setUsername } from '../../redux/slice/UsersSlice';
import { Summarizer } from '../Summarizer/Summarizer';
import { io } from 'socket.io-client';

const socket = io("http://localhost:3000");

export function KDUStockMarket() {
  const reduxDispatch: AppDispatch = useDispatch();

  useEffect(() => {
    reduxDispatch(getStocks());
    reduxDispatch(getTransactions());
    socket.on('username', (name: string) => {
      reduxDispatch(setUsername(name));
    });
  }, [reduxDispatch]); 
  return (
    <>
        <Header />
        <Routes>
          <Route path='/' element={<Dashboard />}/>
          <Route path='/stock/:stock_name' element={<StockDetail/>}/>
          <Route path='/portfolio' element={<Portfolio/>}/>
          <Route path='/summarizer'element={<Summarizer/>}/>
        </Routes>
    </>
  );
}
