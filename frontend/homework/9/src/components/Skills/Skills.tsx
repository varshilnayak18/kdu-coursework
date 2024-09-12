import React from "react";
import "./Skills.css";
import { ISkill } from "../../utils/interface";

interface SkillsProps {
  readonly skills: ReadonlyArray<ISkill>;
}

function Skills({ skills }: SkillsProps) {
  return (
    <div className="skills">
      <h1 className="title">Skills</h1>
      <ul className="list">
        {skills.map((skill) => (
          <li key={skill.id} className="list-item">
            {skill.skill}
          </li>
        ))}
      </ul>
    </div>
  );
}

export default Skills;