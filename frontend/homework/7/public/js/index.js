const socket=io("http://localhost:5000")
const input=document.getElementById("msg")
const btn=document.getElementById("btn")
const msgDiv=document.querySelector(".messages")


btn.addEventListener("click",()=>{
    const message=input.value;
    socket.emit("message",message);
    addMessage('Y', message);
    input.value='';
    input.focus();
});

input.addEventListener('keyup', (e) => {
    if(e.key === 'Enter'){
        const message=input.value;
        socket.emit("message",message);
        addMessage('Y', message);
        input.value='';
        input.focus();
    }
})

socket.on("newMsg",(message)=>{
    addMessage('U',message);
})

function addMessage(user, message) {
    const div = document.createElement('div');
    div.className = 'message'; 

    const userDiv = document.createElement('div');
    userDiv.className = 'user';
    userDiv.innerText = user;

    const messageElement = document.createElement('p');
    messageElement.innerText =  message;

    div.appendChild(userDiv);
    div.appendChild(messageElement);

    if(user === 'Y'){
        div.style.flexDirection = 'row-reverse';
        div.style.backgroundColor = '#016a43';
        div.style.color = 'white';
    }
    msgDiv.appendChild(div);
    msgDiv.scrollTop = msgDiv.scrollHeight;
}