import { useEffect, useState } from "react";
import * as styles from "./Dropdown.styles";
import { Link } from "react-router-dom";
import { linkTag } from "../Dashboard/Dashboard.styles";
import { useSelector } from "react-redux";
import { RootState } from "../../redux/store";
import { io } from "socket.io-client";
import { DropdownProps, IStock } from "../../utils/interfaces";


const socket = io("http://localhost:3000");

export function Dropdown({ currentStock }: Readonly<DropdownProps>) {
  const [isActive, setIsActive] = useState<boolean>(false);
  const stocksList = useSelector((state: RootState) => state.stocksSlice.stocksList).filter((stock) => stock.stock_name !== currentStock.stock_name);

  const handleStockChange = (newStockName: string) => {
    socket.emit('leave-room', currentStock?.stock_name);
    socket.emit('join-room', newStockName);
  };

  useEffect(() => {
    setIsActive(false);
  }, [currentStock]);

  return (
    <div style={styles.dropdown}>
      <button style={styles.dropdownBtn} onClick={() => setIsActive(!isActive)}>
        <div style={styles.logoName}>
          <div style={styles.logo}>{currentStock?.stock_symbol}</div>
          <div style={styles.name}>{currentStock?.stock_name}</div>
        </div>
        {isActive ? (
          <svg
            width="24"
            height="24"
            viewBox="0 0 24 24"
            xmlns="http://www.w3.org/2000/svg"
          >
            <path d="m16.843 13.789c.108.141.157.3.157.456 0 .389-.306.755-.749.755h-8.501c-.445 0-.75-.367-.75-.755 0-.157.05-.316.159-.457 1.203-1.554 3.252-4.199 4.258-5.498.142-.184.36-.29.592-.29.23 0 .449.107.591.291 1.002 1.299 3.044 3.945 4.243 5.498z" />
          </svg>
        ) : (
          <svg
            viewBox="0 0 24 24"
            width="24"
            height="24"
            xmlns="http://www.w3.org/2000/svg"
          >
            <path d="m16.843 10.211c.108-.141.157-.3.157-.456 0-.389-.306-.755-.749-.755h-8.501c-.445 0-.75.367-.75.755 0 .157.05.316.159.457 1.203 1.554 3.252 4.199 4.258 5.498.142.184.36.29.592.29.23 0 .449-.107.591-.291 1.002-1.299 3.044-3.945 4.243-5.498z" />
          </svg>
        )}
      </button>
      {isActive && (
        <div style={styles.dropdownContent}>
          {stocksList.map((stock: IStock) => (
            <Link
              key={stock.stock_name}
              style={linkTag}
              to={`/stock/${stock.stock_name}`}
            >
              <button style={styles.dropdownItem} onClick={() => handleStockChange(stock.stock_name)}>
                <div style={styles.logo}>{stock.stock_symbol}</div>
                <div style={styles.name}>{stock.stock_name}</div>
              </button>
            </Link>
          ))}
        </div>
      )}
    </div>
  );
}
