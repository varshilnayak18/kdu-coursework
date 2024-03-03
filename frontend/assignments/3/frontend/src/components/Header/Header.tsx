import { Link } from "react-router-dom";
import { linkTag } from "../Dashboard/Dashboard.styles";
import {
  AppInfo,
  MainHeader,
  HeaderContainer,
  AppLogo,
  AppName,
  HeaderItems,
  HeaderItem,
  HamburgerMenu,
  DropdownMenu,
  DropdownItem,
} from "./Header.styles";
import { useState } from "react";

export function Header() {
  const [showDropdown, setShowDropdown] = useState(false);

  const toggleDropdown = () => {
    setShowDropdown(!showDropdown);
  };
  return (
    <MainHeader>
      <HeaderContainer>
        <AppInfo>
          <Link to="/">
            <AppLogo
              xmlns="http://www.w3.org/2000/svg"
              id="Layer_1"
              data-name="Layer 1"
              viewBox="0 0 24 24"
              width="30"
              height="30"
            >
              <path d="M5,10.41L.29,5.71l1.41-1.41,3.29,3.29L12,.59l5,5L22.29,.29l1.41,1.41-6.71,6.71L12,3.41l-7,7Zm3,3.59h-2v10h2V14Zm-5-2H1v12H3V12Zm10-4h-2V24h2V8Zm5,3h-2v13h2V11Zm5-4h-2V24h2V7Z" />
            </AppLogo>
          </Link>
          <AppName>KDU Stock Market</AppName>
        </AppInfo>
        <HeaderItems>
          <Link style={linkTag} to="/summarizer">
            <HeaderItem>Summarizer</HeaderItem>
          </Link>
          <Link style={linkTag} to="/portfolio">
            <HeaderItem>My Portfolio</HeaderItem>
          </Link>
        </HeaderItems>
        <HamburgerMenu onClick={toggleDropdown}>☰</HamburgerMenu>
        {showDropdown && (
          <DropdownMenu>
            <Link style={linkTag} to="/summarizer">
              <DropdownItem style={{borderBottom: '1px solid black'}} onClick={toggleDropdown}>Summarizer</DropdownItem>
            </Link>
            <Link style={linkTag} to="/portfolio">
              <DropdownItem onClick={toggleDropdown}>My Portfolio</DropdownItem >
            </Link>
          </DropdownMenu>
        )}
      </HeaderContainer>
    </MainHeader>
  );
}
