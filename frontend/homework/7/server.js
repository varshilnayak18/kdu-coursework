const express=require('express');
const http=require('http');
const cors=require('cors');
const path= require('path');
const socketIo=require('socket.io');

const app=express();
const server=http.createServer(app);
const io=new socketIo.Server(server,{
    cors:{
        origin:"http://127.0.0.1:5500"
    }
});

app.use(cors())
app.use(express.static(path.join(__dirname, 'public')));
app.use(express.json());

io.on("connection",(socket)=>{
    console.log('New user connected');
    io.except(socket.id).emit("newMsg",'A new user has joined the channel');
    socket.on("message",(message)=>{
        console.log(message);
        io.except(socket.id).emit("newMsg",message);
    })

    socket.on('disconnect', () => {
        console.log('User left the channel');
        io.except(socket.id).emit("newMsg",'A user has left the channel');
    })
});

const PORT= process.env.PORT || 5000;
server.listen(PORT,()=>{
    console.log(`Server running on port ${PORT}`);
});
