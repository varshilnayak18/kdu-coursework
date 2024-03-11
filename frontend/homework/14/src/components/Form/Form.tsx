import { useEffect, useRef, useState } from "react";
import { form } from "./Form.styles";

function Form() {
  const searchInputRef = useRef<HTMLInputElement>(null);
  const nameInputref = useRef<HTMLInputElement>(null);

  const [nameInput, setNameInput] = useState<string>("");

  useEffect(() => {
    if (searchInputRef.current) {
      searchInputRef.current.focus();
    }
  }, []);

  return (
    
      <form style={form}>
        <div>
          <label htmlFor="search">Search:</label>
          <input type="text" id="search" ref={searchInputRef} />
        </div>
        <div>
          <label htmlFor="name">Name:</label>
          <input
            type="text"
            id="name"
            onChange={(e) => {
              setNameInput(e.target.value);
              nameInputref.current!.innerText = e.target.value;
            }}
            value={nameInput}
            ref={nameInputref}
          />
        </div>
        <button type="submit">Submit</button>
        <p>
          Welcome <span ref={nameInputref}>User</span>
        </p>
      </form>
  );
}

export default Form;
