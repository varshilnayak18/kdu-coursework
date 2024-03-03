import styled,{css} from 'styled-components';

export const PortfolioContainer = styled.div`
  padding: 4rem 7rem;
  display: flex;
  justify-content: space-between;

  @media (max-width: 1700px) {
    padding: 4rem 4rem
  }

  @media (max-width: 850px) {
    align-items: center;
    flex-direction: column;
    padding: 4rem 2rem
  }
`;

export const TransactionsContainer = styled.div`
  font-size: 1.2rem;
  width: 100%;
  max-height: 70vh;
  overflow-y: scroll;
  scrollbar-width: none;
  -ms-overflow-style: none;

  @media (max-width: 850px) {
    max-height: 30vh
  }
`;

export const DateWiseContainer = styled.div`
  margin-bottom: 2rem;
`;

export const TransactionsDate = styled.div`
  color: #8b8b8c;
  padding: 1rem 0;
  border-bottom: 1px dotted #8b8b8c;
`;

export const Transactions = styled.div``;

const baseTransactionStyles = css`
  display: flex;
  justify-content: space-between;
  align-items: center;
  padding: 2rem 0;
  border-bottom: 1px solid black;
`;

export const FilteredTransaction = styled.div`
  ${baseTransactionStyles}
  opacity: 1;
  color: inherit;
`;

export const UnfilteredTransaction = styled.div`
  ${baseTransactionStyles}
  opacity: 0.7;
  color: #c5c5c6;
`;
export const Info = styled.div`
  display: flex;
  align-items: center;
  justify-content: space-between;
  width: 95%;
  flex-wrap: wrap;
`;

export const NameSymbol = styled.div`
  display: flex;
  align-items: center;
  justify-content: space-between;
  width: 450px;

  @media (max-width: 1700px) {
    width: 90%;
    margin-bottom: 1rem;
  }
`;

export const PriceTime = styled.div`
  display: flex;
  align-items: center;
  justify-content: space-between;
  width: 450px;

  @media (max-width: 1700px) {
    width: 90%
  }
`;

export const Outcome = styled.div`
  display: flex;
  align-items: center;
  justify-content: flex-end;
`;

export const Green = styled.svg`
  fill: #6bb97b;
`;

export const Red = styled.svg`
  fill: #e76b6b;
`;

export const HideScroll = styled.div`
  overflow-y: scroll;
  scrollbar-width: none;
  -ms-overflow-style: none;
`;

export const transactionFiltered: React.CSSProperties = {
  opacity: 1,
};

export const transactionLight: React.CSSProperties = {
  opacity: 0.7,
  color: "#c5c5c6",
};

export const Loader = styled.div`
  display: flex;
  align-items: center;
  justify-content: center;
  height: 70vh;

  @media (max-width: 850px) {
    height: 30vh
  }
`;
