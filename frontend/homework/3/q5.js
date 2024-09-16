const player = {
  firstName: "Leo",
  lastName: "Messi",
  address: {
    country: "Spain",
    city: "Barcelona",
  },
  careerInfo: {
    fcBarcelona: {
      appearances: 780,
      goals: {
        premierLeagueGoals: 590,
        championsLeagueGoals: 50,
      },
    },
  },
};

let getKeys = (object) => {
    let keys = [];
    for(let prop in object){
        if(typeof object[prop] === 'object'){
            keys = keys.concat(getKeys(object[prop]));
        }
        else{
            keys.push(prop);
        }
    }
    return keys;
}

let getValues = (object) => {
    let values = [];
    for(let prop in object){
        if(typeof object[prop] === 'object'){
            values = values.concat(getValues(object[prop]));
        }
        else{
            values.push(object[prop]);
        }
    }
    return values;
}

const keys = getKeys(player);
console.log("Keys: ");
console.log(keys);

const values = getValues(player);
console.log("Values: ");
console.log(values);    
