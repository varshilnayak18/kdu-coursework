import styled from 'styled-components';

export const SummaryContainer = styled.div`
  width: 87%;
  margin: auto;
  padding-top: 2.5rem;
`;

export const Summary = styled.div`
  background-color: #1871c2;
  display: flex;
  align-items: center;
  justify-content: space-between;
  color: white;
  padding: 1rem 2rem;
  border-radius: 25px;
  margin-bottom: 2.5rem;
  font-size: 1rem;

`;

export const RightContainer = styled.div`
  text-align: right;
`;

export const Company = styled.div`
  font-size: 2rem;

  @media (max-width: 600px){
    max-width: 180px
  }
`;

export const StyledDate = styled.div`
  margin: 0.25rem 0;
  display: flex;

  @media (max-width: 450px){
    flex-direction: column;
  }
`;

export const redContainer = {
    backgroundColor: '#e24445',
}