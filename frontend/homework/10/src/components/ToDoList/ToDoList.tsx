import React, { useState } from "react";
import "./ToDoList.scss";
import Header from "../Header/Header";
import Content from "../Content/Content";
function ToDoList() {
  const [itemsList, setItemsList] = useState([{ id: 1, text: "Item 1" }]);
  const [searchInput, setSearchInput] = useState<string>('');

  return (
    <div className="to-do-list">
      <Header itemsList={itemsList} searchInput={searchInput} setSearchInput={setSearchInput}/>
      <Content itemsList={itemsList} setItemsList={setItemsList} searchInput={searchInput}/>
    </div>
  );
}

export default ToDoList;
