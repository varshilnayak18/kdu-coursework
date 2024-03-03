import { useDispatch } from "react-redux";
import {
  addToWatchlist,
  removeFromWatchlist,
} from "../../redux/slice/StocksSlice";
import { useState } from "react";
import { AddBtn, RemoveBtn, TickBtn, WatchlistIcon } from "./Button.styles";
import { showSnackbar } from "../../redux/slice/SnackbarSlice";
import { ButtonProps, IStock } from "../../utils/interfaces";

export function Button({ stock }: Readonly<ButtonProps>) {
  const [isHovered, setIsHovered] = useState(false);
 
  const reduxDispatch = useDispatch();

  const handleAddToWatchlist = (stockName: string) => {
    reduxDispatch(showSnackbar({message: `${stockName} added to watchlist`, type: "success"}));
    reduxDispatch(addToWatchlist(stockName));
  };

  const handleRemoveFromWatchlist = (stockName: string) => {
    reduxDispatch(showSnackbar({message: `${stockName} removed from watchlist`, type: "success"}));
    reduxDispatch(removeFromWatchlist(stockName));
  };

  const handleMouseEnter = () => {
    setIsHovered(true);
  };

  const handleMouseLeave = () => {
    setIsHovered(false);
  };

  const renderIcon = (
    isHovered: boolean,
    stock: IStock
  ) => {
    if (stock.isWatchlisted) {
      if (isHovered) {
        return (
          <RemoveBtn
            xmlns="http://www.w3.org/2000/svg"
            width="24"
            height="24"
            viewBox="0 0 24 24"
          >
            <path d="M12 0c-6.627 0-12 5.373-12 12s5.373 12 12 12 12-5.373 12-12-5.373-12-12-12zm4.151 17.943l-4.143-4.102-4.117 4.159-1.833-1.833 4.104-4.157-4.162-4.119 1.833-1.833 4.155 4.102 4.106-4.16 1.849 1.849-4.1 4.141 4.157 4.104-1.849 1.849z" />
          </RemoveBtn>
        );
      } else {
        return (
          <TickBtn
            xmlns="http://www.w3.org/2000/svg"
            width="24"
            height="24"
            viewBox="0 0 24 24"
          >
            <path d="M12 0c-6.627 0-12 5.373-12 12s5.373 12 12 12 12-5.373 12-12-5.373-12-12-12zm-1 17l-5-5.299 1.399-1.43 3.574 3.736 6.572-7.007 1.455 1.403-8 8.597z" />
          </TickBtn>
        );
      }
    } else {
      return (
        <AddBtn
          xmlns="http://www.w3.org/2000/svg"
          width="24"
          height="24"
          viewBox="0 0 24 24"
        >
          <path d="M12 2c5.514 0 10 4.486 10 10s-4.486 10-10 10-10-4.486-10-10 4.486-10 10-10zm0-2c-6.627 0-12 5.373-12 12s5.373 12 12 12 12-5.373 12-12-5.373-12-12-12zm6 13h-5v5h-2v-5h-5v-2h5v-5h2v5h5v2z" />
        </AddBtn>
      );
    }
  };

  return (
    <WatchlistIcon
      onMouseEnter={handleMouseEnter}
      onMouseLeave={handleMouseLeave}
      onClick={() =>
        stock.isWatchlisted
          ? handleRemoveFromWatchlist(stock.stock_name)
          : handleAddToWatchlist(stock.stock_name)
      }
    >
      {renderIcon(isHovered, stock)}
    </WatchlistIcon>
  );
}
