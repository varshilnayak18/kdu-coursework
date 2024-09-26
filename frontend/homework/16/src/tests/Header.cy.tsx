import { Provider } from 'react-redux'
import Header from '../components/Header/Header'
import { store } from '../redux/store'

describe('<Header />', () => {
  it('renders', () => {
    cy.viewport(1076,876)
    cy.mount(<Provider store={store}><Header /></Provider>)
    cy.get('.header-container').should('exist').and('be.visible');
    cy.get('.title').should('exist').and('be.visible').and('have.text', 'Item Lister');
    cy.get('#search').should('exist').and('be.visible').and('have.text', '');
  })
})