export interface ISkill {
  id: number;
  skill: string;
}

export interface IHobby {
  id: number;
  hobby: string;
}

export interface IProfile {
  name: string;
  fullName: string;
  qualification: string;
  skills: ISkill[];
  hobbies: IHobby[];
}