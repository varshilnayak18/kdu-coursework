const path = require('path');

// b_i
function extractFileInfo(filePath) {
    return {
        extension: path.extname(filePath),
        baseName: path.basename(filePath),
        directory: path.dirname(filePath)
    };
}

console.log(extractFileInfo('dir1/dir2/file1.txt'));

// b_ii
function processFilePaths(filePaths) {
    return filePaths.map(filePath => extractFileInfo(filePath));
}

// b_iii
const filePaths = [
    'dir1/dir2/file1.txt',
    'dir1/dir3/file2.txt',
    'dir1/dir3/file3.md',
    'dir4/file4.jpg',
    'dir4/file5.pdf',
];

console.log(processFilePaths(filePaths));