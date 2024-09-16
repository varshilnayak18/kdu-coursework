const string = '{"firstName":"Alex","lastName":"Hunter","email":"alex@yahoo.com","age":24, "city":"london", "country":"england"}';

const jsonObject = JSON.parse(string);

const modifiedObject = {};

for(const key in jsonObject){
    if(key === 'email' || key === 'age'){
        modifiedObject[key] = jsonObject[key];
    }
    else{
        modifiedObject[key] = jsonObject[key].toUpperCase();
    }
}
console.log(modifiedObject);

const secondObject = modifiedObject;
delete secondObject.email;
const modifiedString = JSON.stringify(secondObject);
console.log(modifiedString);