const days = ["Sunday "," Monday ",
" Tuesday","Wednesday "," Thursday "," Friday",
"Saturday "];

const abbreviatedDays = days.map((day) => day.trim().slice(0,3).toUpperCase());

console.log(abbreviatedDays);