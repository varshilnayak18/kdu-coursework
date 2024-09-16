import { BrowserRouter } from "react-router-dom";
import "./App.css";
import Marketplace from "./components/Marketplace/Marketplace";
function App() {
  return (
    <BrowserRouter>
      <Marketplace />
    </BrowserRouter>
  );
}

export default App;
