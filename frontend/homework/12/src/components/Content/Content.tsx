import "./Content.scss";
import { IItem } from "../../interface";
import Item from "../Item/Item";
import { useContext, useState } from "react";
import { ItemsListContext } from "../../context/ItemsListContext";
import { SearchContext } from "../../context/SearchContext";
import { ItemContext } from "../../context/ItemContext";

function Content() {
  const {itemsList, setItemsList} = useContext(ItemsListContext);
  const {searchInput} = useContext(SearchContext);
  const [input, setInput] = useState<string>("");

  const addItem = () => {
    if (input.trim() !== "") {
      const newItem: IItem = {
        id: itemsList.length + 1,
        text: input.trim(),
      };

      setItemsList([...itemsList, newItem]);
      setInput("");
    }
  };

  const searchedItems = itemsList.filter((item) => item.text.toLowerCase().includes(searchInput.toLowerCase()));

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
            <ItemContext.Provider key={item.id} value={{id: item.id, text: item.text}}>
              <Item
                key={item.id}
              />
            </ItemContext.Provider>
          ))}
        </ul>
      );
    } else {
      return (
        <ul className="list-items">
          {itemsList.map((item) => (
            <ItemContext.Provider key={item.id} value={{id: item.id, text: item.text}}>
            <Item
              key={item.id}
            />
          </ItemContext.Provider>
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
            onChange={(e) => setInput(e.target.value)}
            value={input}
          />
          <button type="submit" className="item-submit-btn" onClick={addItem}>
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
