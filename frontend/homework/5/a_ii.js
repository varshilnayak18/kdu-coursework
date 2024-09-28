const fs = require('fs');
const getInfo = require('./a_i');

const info = getInfo();
fs.writeFile('info.json', info, 'utf8', (err) => {
    if (err) throw err;
    console.log('System info written to info.json');
});