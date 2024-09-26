import "./Content.scss";
import { IItem } from "../../interface";
import { useDispatch, useSelector } from "react-redux";
import { RootState } from "../../redux/store";
import {
  addItem,
  removeCompletedItems,
  removeItem,
  setItemCompleted,
  setItemInput,
} from "../../redux/ItemsListSlice";

function Content() {
  const itemsList = useSelector(
    (state: RootState) => state.itemsList.itemsList
  );
  const searchInput = useSelector(
    (state: RootState) => state.searchInput.searchInput
  );
  const input = useSelector((state: RootState) => state.itemsList.itemInput);

  const reduxDispatch = useDispatch();

  const addItemAction = (input: IItem) => {
    reduxDispatch(addItem(input));
  };

  const removeItemAction = (id: number) => {
    reduxDispatch(removeItem(id));
  };

  const setInputAction = (newItem: string) => {
    reduxDispatch(setItemInput(newItem));
  };

  const removeCompletedItemsAction = () => {
    reduxDispatch(removeCompletedItems());
  };
  
  const setItemCompletedAction = (id: number) => {
    reduxDispatch(setItemCompleted(id));
  }

  const addItemtoList = () => {
    if (input.trim() !== "") {
      const newItem: IItem = {
        id: itemsList.length + 1,
        text: input.trim(),
        completed: false,
      };

      addItemAction(newItem);
      setInputAction("");
    }
  };
  
  const searchedItems = itemsList.filter((item) =>
    item.text.toLowerCase().includes(searchInput.toLowerCase())
  );

  const noItemsToShow = itemsList.length === 0;
  const noSearchInput = searchInput.length === 0;
  const noMatchFound = !noSearchInput && searchedItems.length === 0;

  const renderNoItemsMessage = (message: string) => (
    <ul className="list-items">{message}</ul>
  );
  
  const renderItem = (item: IItem) => (
    <li key={item.id} className={`list-item ${item.completed ? 'completed' : ''}`} data-testid={`item-${item.id}`}>
      <div>
        <input onClick={() => setItemCompletedAction(item.id)} type="checkbox" data-testid={`checkbox-${item.id}`} id="checkbox" checked={item.completed}></input>
        <span className={item.completed ? 'item-text-completed' : 'item-text'}>{item.text}</span>
      </div>
      <button
        type="submit"
        className="delete-btn"
        data-testid={`delete-btn-${item.id}`}
        onClick={() => removeItemAction(item.id)}
      >
        X
      </button>
    </li>
  );
  
  const renderItemList = () => {
    if (noItemsToShow && !noSearchInput) {
      return renderNoItemsMessage('No match found');
    } else if (noItemsToShow && noSearchInput) {
      return renderNoItemsMessage('No items to show');
    } else if (!noSearchInput && noMatchFound) {
      return renderNoItemsMessage('No match found');
    } else {
      const itemsToRender = noMatchFound ? itemsList : searchedItems;
      return (
        <ul className="list-items">
          {itemsToRender.map(renderItem)}
        </ul>
      );
    }
  };
  

  return (
    <main>
      <div className="main-container">
        <div className="input-container">
          <h2 id="add-item-header" className="title">Add Items</h2>
          <input
            type="text"
            name="item-name"
            id="item-input"
            className="item-input"
            data-testid="item-input"
            onChange={(e) => setInputAction(e.target.value)}
            value={input}
          />
          <button
            type="submit"
            className="item-submit-btn"
            data-testid="submit-btn"
            onClick={addItemtoList}
            disabled={!input.trim()}
          >
            Submit
          </button>
        </div>
        <div className="items-container">
          <h2 id="items-title" className="title">Items</h2>
          {renderItemList()}
        </div>
        <div className="completed-btn-container">
          <button
            onClick={() => removeCompletedItemsAction()}
            className="completed-btn"
            data-testid="completed-btn"
          >
            Clear completed
          </button>
        </div>
      </div>
    </main>
  );
}

export default Content;
