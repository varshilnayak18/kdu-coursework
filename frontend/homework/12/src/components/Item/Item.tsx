import { useContext } from "react";
import "./Item.scss";
import { ItemsListContext } from "../../context/ItemsListContext";
import { ItemContext } from "../../context/ItemContext";

function Item() {
  const {itemsList, setItemsList} = useContext(ItemsListContext);
  const {id, text} = useContext(ItemContext);
  return (
    <li className="list-item">
      {text}
      <button
        type="submit"
        className="delete-btn"
        onClick={() =>
          setItemsList([...itemsList.filter((item) => item.id !== id)])
        }
      >
        X
      </button>
    </li>
  );
}

export default Item;
