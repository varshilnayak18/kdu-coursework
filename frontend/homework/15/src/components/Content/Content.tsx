import "./Content.scss";
import { IItem } from "../../interface";
import { useDispatch, useSelector } from "react-redux";
import { RootState } from "../../redux/store";
import { addItem, removeItem, setItemInput } from "../../redux/ItemsListSlice";

function Content() {
  const itemsList = useSelector((state: RootState) => state.itemsList.itemsList)
  const searchInput = useSelector((state: RootState) => state.searchInput.searchInput)
  const input = useSelector((state: RootState) => state.itemsList.itemInput);

  const reduxDispatch = useDispatch();

  const addItemAction = (input: IItem) => {
    reduxDispatch(addItem(input));
  }

  const removeItemAction = (id: number) => {
    reduxDispatch(removeItem(id));
  }

  const setInputAction = (newItem: string) => {
    reduxDispatch(setItemInput(newItem));
  }

  const addItemtoList = () => {
    if (input.trim() !== "") {
      const newItem: IItem = {
        id: itemsList.length + 1,
        text: input.trim(),
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

  const renderItemList = () => {
    if (noItemsToShow && !noSearchInput) {
      return <ul className="list-items">No match found</ul>;
    } else if (noItemsToShow && noSearchInput) {
      return <ul className="list-items">No items to show</ul>;
    } else if (!noSearchInput && noMatchFound) {
      return <ul className="list-items">No match found</ul>;
    } else if (!noMatchFound) {
      return (
        <ul className="list-items">
          {searchedItems.map((item) => (
            <li key={item.id} className="list-item">
              {item.text}
              <button
                type="submit"
                className="delete-btn"
                onClick={() =>
                  removeItemAction(item.id)
                }
              >
                X
              </button>
            </li>
          ))}
        </ul>
      );
    } else {
      return (
        <ul className="list-items">
          {itemsList.map((item) => (
            <li key={item.id} className="list-item">
              {item.text}
              <button
                type="submit"
                className="delete-btn"
                onClick={() =>
                  removeItemAction(item.id)
                }
              >
                X
              </button>
            </li>
          ))}
        </ul>
      );
    }
  };

  return (
    <main>
      <div className="main-container">
        <div className="input-container">
          <h2 className="title">Add Items</h2>
          <input
            type="text"
            name="item-name"
            id="item-input"
            className="item-input"
            onChange={(e) => setInputAction(e.target.value)}
            value={input}
          />
          <button type="submit" className="item-submit-btn" onClick={addItemtoList}>
            Submit
          </button>
        </div>
        <div className="items-container">
          <h2 className="title">Items</h2>
          {renderItemList()}
        </div>
      </div>
    </main>
  );
}

export default Content;
