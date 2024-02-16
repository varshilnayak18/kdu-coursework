const path = require("path");
const http = require("http");
const express = require("express");
const socketio = require("socket.io");
const fs = require('fs');
const bodyParser = require('body-parser');
const app = express();
const cors = require('cors');
const server = http.createServer(app);

const io=new socketio.Server(server,{
    cors:{
        origin:"*"
    }
});


app.use(express.static(path.join(__dirname, "public")));
app.use(bodyParser.json());
app.use(cors());

io.on("connection", (socket) => {
    
});
  

const stockData = JSON.parse(fs.readFileSync('data/stocks.json'));
console.log(stockData.name);
app.get('/load', (req, res) => {
    res.status(200).send(stockData);
});

const PORT = process.env.PORT || 3000;

server.listen(PORT, () => console.log(`Server running on port ${PORT}`));



let transactionHistory = [];

app.get('/history', (req, res) => {
    res.status(200).send(transactionHistory);
});

app.post('/history', (req, res) => {
    transactionHistory.push(req.body);
    console.log(transactionHistory);
})

const priceChanges = ['up', 'down'];
io.on('connection', (socket) => {

    setInterval(() => {
        const newPrice = Math.floor(Math.random() * 501); 
        const index = Math.floor(Math.random() * priceChanges.length);
        io.emit('update', {newPrice, priceUporDown: priceChanges[index]});
    }, 5000);

    socket.on('disconnect', () => {
        console.log('User disconnected');
    });
});