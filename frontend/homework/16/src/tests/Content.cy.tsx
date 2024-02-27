import Content from '../components/Content/Content'
import { Provider } from 'react-redux'
import { store } from '../redux/store'

describe('<Content />', () => {
  it('renders', () => {
    cy.viewport(1076,876)
    cy.mount(<Provider store={store}><Content /></Provider>)
    cy.get('.main-container').should('exist').and('be.visible');
    cy.get('.input-container').should('exist').and('be.visible');
    cy.get('#add-item-header').should('exist').and('be.visible').and('have.text', 'Add Items');
    cy.get('.item-input').should('exist').and('be.visible');
    cy.get('.item-submit-btn').should('exist').and('be.visible').and('have.text', 'Submit').and('be.disabled');

    cy.get('.item-input').type('Item 1');
    cy.get('.item-submit-btn').click();
    cy.get('.items-container').should('exist').and('be.visible');
    cy.get('#items-title').should('exist').and('be.visible').and('have.text', 'Items');
    cy.get('.list-items').should('exist').contains('Item 1');
    cy.get('.delete-btn').should('exist').and('be.visible').click();
    
    cy.get('.item-input').type('Item 1');
    cy.get('.item-submit-btn').click();
    cy.get('#checkbox').should('exist').and('be.visible').click();
    cy.get('.completed-btn-container').should('exist').and('be.visible');
    cy.get('.completed-btn').should('exist').and('be.visible').and('have.text', 'Clear completed').click();
  })
})