describe('to-do-list test', () => {
  beforeEach(() => {
    cy.visit('http://localhost:5173');
  });

  const addItemToList = (itemName: string) => {
    cy.get('.item-input').type(itemName);
    cy.get('.item-submit-btn').click();
  };

  it('renders the home page', () => {
    cy.get('.to-do-list').should('exist').and('be.visible');
    cy.get('.list-items').should('have.text', 'No items to show');
  });

  it('add items to list', () => {
    addItemToList('Item 1');
    addItemToList('Item 2');
  });

  it('remove items from list', () => {
    addItemToList('Item 1');
    cy.get('.delete-btn').click();
    cy.get('.list-items').should('have.text', 'No items to show');
  });

  it('mark item as completed', () => {
    addItemToList('Item 1');
    cy.get('.list-item').find('input[type="checkbox"]').check();
  });

  it('remove completed items', () => {
    addItemToList('Item 1');
    addItemToList('Item 2');
    cy.get('.list-item').eq(0).find('input[type="checkbox"]').check();
    cy.get('.completed-btn').click();
  });

  it('search items', () => {
    addItemToList('Item 1');
    addItemToList('Item 2');
    cy.get('#search').type('1');
    cy.get('.list-items').contains('Item 1');
  });

  it('search unavailable item', () => {
    cy.get('#search').type('1');
    cy.get('.list-items').should('have.text', 'No match found');
  });
});
