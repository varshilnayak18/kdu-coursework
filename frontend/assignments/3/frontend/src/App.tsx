import "./App.css";
import { KDUStockMarket } from "./components/KDUStockMarket/KDUStockMarket";
import { BrowserRouter } from "react-router-dom";

function App() {
  return (
    <BrowserRouter>
      <KDUStockMarket />
    </BrowserRouter>
  );
}

export default App;
