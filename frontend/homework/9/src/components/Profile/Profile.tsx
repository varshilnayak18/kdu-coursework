import React from "react";
import Header from "../Header/Header";
import Skills from "../Skills/Skills";
import Hobbies from "../Hobbies/Hobbies";
import "./Profile.css";
import { IProfile } from "../../utils/interface";

interface ProfileProps {
  readonly profileData: IProfile;
}

function Profile({ profileData }: ProfileProps) {
  return (
    <div className="profile">
      <Header
        name={profileData.name}
        fullName={profileData.fullName}
        qualification={profileData.qualification}
      />
      <div className="qualities">
        <Skills skills={profileData.skills} />
        <Hobbies hobbies={profileData.hobbies} />
      </div>
    </div>
  );
}

export default Profile;