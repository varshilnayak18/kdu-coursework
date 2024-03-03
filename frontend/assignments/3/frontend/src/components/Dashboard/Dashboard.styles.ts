import styled from "styled-components";

export const DashboardContainer = styled.div`
  padding: 0 1.25rem;
`;

export const DashboardItems = styled.div`
  padding: 1.25rem 0;
  display: flex;
  align-items: center;
`;

export const BorderBottom = styled.div`
  border: 2px solid #468ccd;
  border-radius: 5px;
`;

export const StocksContainer = styled.div`
  width: 75%;
  font-size: 1.2rem;
  margin: 1.25rem auto;
  border: 4px solid #5c6065;
  border-radius: 25px;
  min-height: 642px;
  position: relative;


  @media (max-width: 950px) {
    min-height: 510px; 
  }

  @media (max-width: 700px) {
    width: 95%;
    min-height: 500px; 
  }

  @media (max-width: 450px) {
    min-height: 570px
  }

  @media (max-width: 374px) {
    min-height: 740px
  }
`;

export const InfoHeader = styled.div`
  display: flex;
  align-items: center;
  justify-content: space-between;
  border-bottom: 4px solid #5c6065;
  padding: 1.25rem 3rem;

  @media (max-width: 700px) {
    padding: 1.25rem 1rem;
  }
`;

export const PriceWatch = styled.div`
  width: 13rem;
  display: flex;
  align-items: center;
  justify-content: space-between;
`;

export const Price = styled.div`
`;


export const PriceTitle = styled.div`
  margin-right: 0.7rem;
`;

export const Stock = styled.div`
  display: flex;
  align-items: center;
  justify-content: space-between;
  margin: 0 3rem;
  padding: 1.25rem 0;
  border-bottom: 2px solid #d7d7d8;

  @media (max-width: 700px) {
    margin: 0 1rem;
  }
`;

export const StockName = styled.div`
  @media (max-width: 450px) {
    max-width: 150px
  }
  @media (max-width: 374px) {
    max-width: 80px
  }
`;

export const StockMobile = styled.div`
  display: flex;
  align-items: center;
  justify-content: space-between;
  margin: 0 1rem;
  padding: 0.5rem 0;
  border-bottom: 2px solid #d7d7d8;
`;

export const PaginationContainer = styled.div`
  display: flex;
  align-items: center;
  justify-content: center;
  padding: 1.25rem;
  position: absolute;
  bottom: 0;
  left: 50%;
  width: 100%;
  transform: translateX(-50%);

`;

export const NoStocks = styled.div`
  min-height: 400px;
  display: flex;
  align-items: center;
  justify-content: center;
  text-align: center;
  padding-top: 1.25rem;
  font-size: 2rem;
`;

export const DashboardItem = styled.div`
  border: none;
  background: transparent;
  font-size: 1.3rem;
  cursor: pointer;
  margin-right: 1rem;
`;

export const linkTag: React.CSSProperties = {
  textDecoration: "none",
  color: "inherit",
};
