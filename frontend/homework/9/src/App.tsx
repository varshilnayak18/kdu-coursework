import React from "react";
import "./App.css";
import Profile from "./components/Profile/Profile";
import { IProfile } from "./utils/interface";

function App() {
  const fetchDatafromAPI = () => {
    return {
      name: "Amey",
      fullName: "Amey Aditya",
      qualification: "SSE",
      skills: [
        {
          id: 1,
          skill: "Python",
        },
        {
          id: 2,
          skill: "React",
        },
      ],
      hobbies: [
        {
          id: 1,
          hobby: "Cricket",
        },
      ],
    };
  };

  const data: IProfile = fetchDatafromAPI();
  return (
    <div className="App">
      <Profile profileData={data} />
    </div>
  );
}

export default App;