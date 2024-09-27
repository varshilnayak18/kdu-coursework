import "./Item.scss";
import { IItem } from "../../interface";

interface ItemProps {
  readonly id: number;
  readonly text: string;
  readonly itemsList: ReadonlyArray<IItem>;
  readonly setItemsList: React.Dispatch<React.SetStateAction<IItem[]>>;
}
function Item({ id, text, itemsList, setItemsList }: ItemProps) {
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
