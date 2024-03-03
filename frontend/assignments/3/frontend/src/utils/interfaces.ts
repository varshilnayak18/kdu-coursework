
export interface ButtonProps {
  readonly stock: IStock;
}

export interface DropdownProps {
  readonly currentStock: IStock;
}

export interface ITransaction {
  stock_name: string;
  stock_symbol: string;
  transaction_price: number;
  timestamp: string;
  status: string;
}

export interface IStock {
  stock_name: string;
  stock_symbol: string;
  base_price: number;
  isWatchlisted: boolean;
}

export interface IGroupedTransactions {
  [date: string]: ITransaction[];
}

export interface IHistory {
  stock_name: string;
  quantity: number;
  type: "Buy" | "Sell";
  username: string;
  timestamp: string;
}

export interface IBar {
  price: number;
  fill: string;
}

export interface IData {
  date: string;
  prices: number[];
}

export interface ICompanyStock {
  company: string;
  symbol: string;
  data: IData[];
}

export interface ISummary {
  company: string;
  bestBuyDate: string;
  bestBuyPrice: number;
  bestSellDate: string;
  bestSellPrice: number;
  maxProfit: number;
}

export interface SnackbarState {
  message: string;
  show: boolean;
  type: "success" | "info" | "error";
}

export interface UserSliceState {
  username: string;
  walletBalance: number;
  portfolio: { [key: string]: number };
}

export interface FiltersSliceState {
  searchText: string;
  showPassed: boolean;
  showFailed: boolean;
  startDate: string;
  endDate: string;
  selectedStocks: string[];
}

export interface TransactionsSliceState {
  transactionsList: ITransaction[];
  status: "fulfilled" | "pending" | "rejected";
  error: string;
}

export interface StocksSliceState {
  stocksList: IStock[];
  status: "fulfilled" | "pending" | "rejected";
  error: string;
}
