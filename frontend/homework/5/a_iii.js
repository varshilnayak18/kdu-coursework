const http = require('http');
const fs = require('fs');

const NAME = 'Varshil';
const PORT = 5000;
const server = http.createServer((req, res) => {
    if (req.url === '/' && req.method === 'GET') {
        fs.readFile('info.json', 'utf8', (err, data) => {
            if (err) {
                res.writeHead(500, { 'Content-Type': 'text/plain' });
                res.end('Error reading system info file');
            } else {
                res.writeHead(200, { 'Content-Type': 'application/json' });
                let response = `Hello, my name is ${NAME}!\nHere is my system information:\n${data}`;
                res.end(response);
            }
        });
    } else {
        res.writeHead(404, { 'Content-Type': 'text/plain' });
        res.end('404 Not Found');
    }
});


server.listen(PORT, () => {
    console.log(`Server running on port ${PORT}`);
});