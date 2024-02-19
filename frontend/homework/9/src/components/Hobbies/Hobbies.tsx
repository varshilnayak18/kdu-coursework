import React from "react";
import "./Hobbies.css";
import { IHobby } from "../../utils/interface";

interface HobbiesProps {
  readonly hobbies: ReadonlyArray<IHobby>;
}

function Hobbies({ hobbies }: HobbiesProps) {
  return (
    <div className="hobbies">
      <h1 className="title">Hobbies</h1>
      <ul className="list">
        {hobbies.map((hobby) => (
          <li key={hobby.id} className="list-item">
            {hobby.hobby}
          </li>
        ))}
      </ul>
    </div>
  );
}

export default Hobbies;