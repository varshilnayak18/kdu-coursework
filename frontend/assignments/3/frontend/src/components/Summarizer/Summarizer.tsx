import { useEffect, useState } from "react";
import worker from "../../utils/Worker/app.worker?worker";
import {
  Company,
  StyledDate,
  RightContainer,
  Summary,
  SummaryContainer,
  redContainer,
} from "./Summarizer.styles";
import ClipLoader from "react-spinners/ClipLoader";
import { info } from "../StockDetail/StockDetail.styles";
import { ISummary } from "../../utils/interfaces";

export function Summarizer() {
  const [stocksSummary, setStocksSummary] = useState<ISummary[]>([]);
  const [isLoading, setIsLoading] = useState<boolean>(false);

  useEffect(() => {
    const fetchDataWorker = async () => {
      const startTime = performance.now();
      const serviceWorker = new worker();
      serviceWorker.postMessage("getData");
      setIsLoading(true);
      serviceWorker.addEventListener("message", (event) => {
        setStocksSummary([
          ...event.data,
          {
            company: "Test",
            bestBuyDate: "",
            bestBuyPrice: 0,
            bestSellDate: "",
            bestSellPrice: 0,
            maxProfit: 0,
          },
        ]);
        const endTime = performance.now();
        const elapsedTime = endTime - startTime;
        console.log("Time taken by web worker (ms):", elapsedTime);
        setIsLoading(false);
      });
    };

    fetchDataWorker();
  }, []);

  return isLoading ? (
    <div style={{ textAlign: "center", marginTop: "5rem" }}>
      <ClipLoader
        color="black"
        loading={isLoading}
        size={100}
        aria-label="Loading Spinner"
        data-testid="loader"
        className="loader"
      />
    </div>
  ) : (
    <SummaryContainer>
      {stocksSummary.map((stock) => (
        <Summary key={stock.company} style={stock.maxProfit === 0 ? redContainer : info}>
          <div>
            <Company>{stock.company}</Company>
            <div>Profit margin: &#8377;{stock.maxProfit}</div>
          </div>
          {stock.maxProfit > 0 && (
            <RightContainer>
              <StyledDate>
                <div>Buy: &#8377;{stock.bestBuyPrice} on &nbsp;</div>
                <div>{stock.bestBuyDate}</div>
              </StyledDate>
              <StyledDate>
                <div>Sell: &#8377;{stock.bestSellPrice} on &nbsp;</div>
                <div>{stock.bestSellDate}</div>
              </StyledDate>
            </RightContainer>
          )}
        </Summary>
      ))}
    </SummaryContainer>
  );
}
