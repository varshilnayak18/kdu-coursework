const express = require('express');
const http = require('http');
const socketio = require('socket.io');

const app = express();
const server = http.createServer(app);
const io = new socketio.Server(server, {
    cors: {
        origin: "http://localhost:5173",
        methods: ["GET", "POST"]
    }
});

let transactions = {};

const users = ["Varshil", "Nitesh", "Sagun", "Jainam", "Kirtan", "Pratham", "Aakash"];
let currentIndex = 0;

app.use(express.json());

io.on('connection', (socket) => {
  const username = users[currentIndex];
  currentIndex = (currentIndex + 1) % users.length; 
  socket.emit('username', username);
  
  console.log(`${username} connected`);
  socket.on('join-room', (room) => {
    if (!transactions[room]) {
      transactions[room] = [];
    }
    socket.join(room);
    socket.emit('initial-transactions', transactions[room]);
  });

  socket.on('leave-room', (room) => {
    socket.leave(room);
  });

  socket.on('new-transaction', (transaction) => {
    transactions[transaction.stock_name].push(transaction);
    io.to(transaction.stock_name).emit('new-transaction', transaction);
  });

  socket.on('disconnect', () => {
    console.log('User disconnected');
  });
});

const PORT = process.env.PORT || 3000;
server.listen(PORT, () => {
    console.log(`Server running on port ${PORT}`);
});