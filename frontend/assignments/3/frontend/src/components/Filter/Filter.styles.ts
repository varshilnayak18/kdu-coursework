import styled from "styled-components";

export const FilterContainer = styled.div`
  border: 2px solid #1e1e1e;
  border-radius: 25px;
  display: flex;
  flex-direction: column;
  background-color: #e9ecef;
  width: 350px;
  color: #848687;
  margin-right: 6rem;
  height: fit-content;

  @media (max-width: 850px) {
    width: 300px;
    margin-right: 0;
    margin-bottom: 2rem;
  }

  @media (max-width: 400px) {
    width: 250px;
  }
`;

export const FilterHeader = styled.div`
  padding: 0.7rem 1rem;
  border-bottom: 2px solid #78797a;
`;

export const Header = styled.div`
  display: flex;
  justify-content: space-between;
  font-size: 1.2rem;
  color: #000;
`;

export const ClearAll = styled.button`
  color: #1971c2;
  cursor: pointer;
  border: none;
  background: transparent;
  font-size: 1.2rem;
  padding: 0;
`;

export const CheckboxContainer = styled.div`
  display: flex;
  align-items; center;
`;

export const StyledCheckbox = styled.input`
  margin-right: 10px;
  &:hover {
    cursor: pointer;
  }
`;

export const StyledLabel = styled.label`
  &:hover {
    cursor: pointer;
  }
`;

export const SearchBar = styled.div`
  padding: 0.3rem 1rem;
  display: flex;
  align-items: center;
  border: 1px solid #6f7072;
  border-bottom: 1px solid #6f7072;
  border-radius: 5px;
  font-size: 1rem;
`;

export const SearchInput = styled.input`
  width: 100%;
  background: transparent;
  border: none;
  font-size: 1rem;
  outline: none;
`;

export const DateContainer = styled.div`
  padding: 0.7rem 0.5rem;
  border-bottom: 2px solid #78797a;
  display: flex;
  justify-content: space-between;
`;

export const DateInput = styled.input`
  padding: 0.4rem 0;
  background: transparent;
  border: 1px solid #6f7072;
  border-radius: 5px;
  font-size: 1rem;
  color: #848687;
  margin: 0 0.5rem;

  &:hover {
    cursor: pointer;
  }
`;

export const TransactionOutcomeContainer = styled.div`
  padding: 0.7rem 1rem;
  display: flex;
  flex-direction: column;
  border-bottom: 2px solid #78797a;
  font-size: 1rem;
`;

export const StocksFilterContainer = styled.div`
  padding: 0.7rem 1rem;
  display: flex;
  flex-direction: column;
  font-size: 1rem;
  max-height: 20vh;
  overflow-y: scroll;
  scrollbar-width: none;
`;

export const FilterCheckbox = styled.input`
  cursor: pointer;
`;

export const FilterLabel = styled.label`
  cursor: pointer;
`;

export const FilterCheckboxContainer = styled.div`
  display: flex;
  align-items: center;
`;
