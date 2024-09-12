import React from "react";
import "./Header.css";

interface HeaderProps {
  readonly name: string;
  readonly fullName: string;
  readonly qualification: string;
}

function Header({ name, fullName, qualification }: HeaderProps) {
  return (
    <div className="header">
      <h1 className="name">{name}</h1>
      <h3 className="full-name">{fullName}</h3>
      <h2 className="qualification">{qualification}</h2>
    </div>
  );
}

export default Header;