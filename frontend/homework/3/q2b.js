const replaceChar = {
    'a': '4',
    'e': '3',
    'i': '1',
    'o': '0',
    's': '5'
}
const codeString = (strings) => {
    return strings.map((originalString) => originalString.trim().replace(/[aeios]/g, char => replaceChar[char] || char));
}

const strings = ["javascript is cool ", "programming is fun", " become a coder"];
const codedStrings = codeString(strings);
console.log(codedStrings);