import React, { useState } from "react";
import "./ToDoList.scss";
import Header from "../Header/Header";
import Content from "../Content/Content";
import { IItem } from "../../interface";
import {ItemsListContext} from '../../context/ItemsListContext';
import {SearchContext} from '../../context/SearchContext';

function ToDoList() {
  const [itemsList, setItemsList] = useState<IItem[]>([{ id: 1, text: "Item 1" }]);
  const [searchInput, setSearchInput] = useState<string>('');

  return (
    <div className="to-do-list">
      <ItemsListContext.Provider value={{itemsList, setItemsList}}>
        <SearchContext.Provider value={{searchInput, setSearchInput}}>
          <Header />
          <Content />
        </SearchContext.Provider>
      </ItemsListContext.Provider>
    </div>
  );
}

export default ToDoList;
