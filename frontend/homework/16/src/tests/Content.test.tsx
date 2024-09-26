import { describe, test, expect, } from 'vitest';
import { render, fireEvent, screen } from "@testing-library/react";
import { Provider } from 'react-redux';
import { store } from '../redux/store';
import Content from '../components/Content/Content';

describe('Content Component', () => {
  test('initial page with no items', () => {
    render(
      <Provider store={store}>
        <Content />
      </Provider>
    );

    expect(screen.getByText('No items to show')).toBeTruthy();
  });

  test('renders title and input box and add button', () => {
    render(
      <Provider store={store}>
        <Content />
      </Provider>
    );

    expect(screen.getByText('Add Items')).toBeTruthy();
    expect(screen.getByTestId('item-input')).toBeTruthy();
    expect(screen.getByTestId('submit-btn')).toBeTruthy();
  });

  test('adds, checks, deletes, and clears items', () => {
    render(
      <Provider store={store}>
        <Content />
      </Provider>
    );

    // Add item to list
    const input = screen.getByTestId('item-input');
    const submitButton = screen.getByTestId('submit-btn');
    fireEvent.change(input, { target: { value: 'Item 1' } });
    fireEvent.click(submitButton);
    expect(screen.getByTestId('item-1')).toBeTruthy();

    // Mark item as completed
    const checkbox = screen.getByTestId('checkbox-1');
    fireEvent.click(checkbox);
    expect(screen.getByTestId('item-1').classList.contains('completed')).toBeTruthy();

    // Delete item from list
    const deleteButton = screen.getByTestId('delete-btn-1');
    fireEvent.click(deleteButton);
    expect(screen.queryByTestId('item-1')).toBeNull();

    // Clear completed items
    fireEvent.change(input, { target: { value: 'Item 2' } });
    fireEvent.click(submitButton);
    const checkbox2 = screen.getByTestId('checkbox-1');
    fireEvent.click(checkbox2);
    const clearButton = screen.getByTestId('completed-btn');
    fireEvent.click(clearButton);
    expect(screen.queryByTestId('item-1')).toBeNull();
  });
});
