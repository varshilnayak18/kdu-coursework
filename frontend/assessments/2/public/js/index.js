document.addEventListener('DOMContentLoaded', () => {
    fetch('http://localhost:3000/load', {
        method: 'GET'
      })
      .then(response => response.json())
      .then(data => {
        document.querySelector('.name').textContent = data.name;
        document.querySelector('.image').src = data.image;
        document.querySelector('.price-now').textContent = data.initialPrice;
        document.querySelector('.percentage').innerHTML = '0.00 % ';
      })
      .catch(error => {
        console.error('Error:', error);
      });

    
      fetch('http://localhost:3000/history', {
        method: 'GET'
      })
      .then(response => response.json())
      .then(data => {
        data.forEach(element => {
            const orderContainer = document.createElement('div');
            orderContainer.classList.add('order');
            const priceElement = document.querySelector('.price-now');
            let oldPrice = parseFloat(priceElement.textContent);
            const infoDiv = document.createElement('div');
            infoDiv.classList.add('info');
            const priceQuantityDiv = document.createElement('div');
            priceQuantityDiv.classList.add('price-quantity');
            priceQuantityDiv.textContent = element.price;

            const timeDiv = document.createElement('div');
            timeDiv.classList.add('time');
            timeDiv.textContent = element.time;

            infoDiv.appendChild(priceQuantityDiv);
            infoDiv.appendChild(timeDiv);

            const typeGreenDiv = document.createElement('div');
            if(element.type === 'Buy'){
                typeGreenDiv.classList.add('type-green');    
            }
            else{
                typeGreenDiv.classList.add('type-red');
            }
            typeGreenDiv.textContent = element.type;

            orderContainer.appendChild(infoDiv);
            orderContainer.appendChild(typeGreenDiv);
            document.querySelector('.orders').prepend(orderContainer);
        });
      })
      .catch(error => {
        console.error('Error:', error);
      });
})

let count = 0;
const socket = io("http://localhost:3000");
const price=document.querySelector(".price-now");
const graph = document.querySelector('.graph');

socket.on('update', (priceObject) => {
    const currentPriceElement = document.querySelector('.current-price');
    const priceElement = document.querySelector('.price-now');
    const percentageElement = document.querySelector('.percentage');
    const arrowElement = document.querySelector('.arrow');

    let oldPrice = parseFloat(priceElement.textContent);
    let newPrice = oldPrice;
    let percentage = 0;
    const bar = document.createElement('div');

    if(priceObject.priceUporDown === 'up'){
        newPrice += priceObject.newPrice;
        bar.classList.add('bar-green');
        arrowElement.innerHTML = '&ShortUpArrow;';
        currentPriceElement.style.color = '#2f9e44';
        bar.style.height = (priceObject.newPrice>500? '500px': `${priceObject.newPrice}px`);
    }
    else{
        newPrice -= priceObject.newPrice;
        bar.classList.add('bar-red');
        arrowElement.innerHTML = '&ShortDownArrow;';
        currentPriceElement.style.color = '#e03131';
        bar.style.height = (priceObject.newPrice>500? '500px': `${priceObject.newPrice}px`);
    }
    if(newPrice>0){
        priceElement.textContent = newPrice;
        percentage = ((newPrice - oldPrice) / oldPrice) * 100;
        percentageElement.textContent = `${percentage.toFixed(2)}%`; 
        graph.appendChild(bar);
        count++;
        if(count === 60){
            graph.innerHTML = '';
            count = 0;
        }
    }
});



document.querySelector('.buy').addEventListener('click', () => {
    const quantity = document.getElementById('stock-quantity').value;
    const orderContainer = document.createElement('div');
    orderContainer.classList.add('order');
    const priceElement = document.querySelector('.price-now');
    let oldPrice = parseFloat(priceElement.textContent);
    const infoDiv = document.createElement('div');
    infoDiv.classList.add('info');
    const priceQuantityDiv = document.createElement('div');
    priceQuantityDiv.classList.add('price-quantity');
    priceQuantityDiv.textContent = `${quantity} Stocks @${oldPrice} rs`;

    const timeDiv = document.createElement('div');
    timeDiv.classList.add('time');
    timeDiv.textContent = 'Fri, 16 Feb 10:55 am';

    infoDiv.appendChild(priceQuantityDiv);
    infoDiv.appendChild(timeDiv);

    const typeGreenDiv = document.createElement('div');
    typeGreenDiv.classList.add('type-green');
    typeGreenDiv.textContent = 'Buy';

    orderContainer.appendChild(infoDiv);
    orderContainer.appendChild(typeGreenDiv);
    document.querySelector('.orders').prepend(orderContainer);


    fetch('http://localhost:3000/history', {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json'
        },
        body: JSON.stringify({ price: priceQuantityDiv.textContent, time: timeDiv.textContent, type: typeGreenDiv.textContent })
      })
      .then(response => {
        if (response.ok) {
          console.log('successful');
        } else {
          console.error('failed');
        }
      })
      .catch(error => {
        console.error('Error:', error);
      });
});

document.querySelector('.sell').addEventListener('click', () => {
    const quantity = document.getElementById('stock-quantity').value;
    const orderContainer = document.createElement('div');
    orderContainer.classList.add('order');
    const priceElement = document.querySelector('.price-now');
    let oldPrice = parseFloat(priceElement.textContent);
    const infoDiv = document.createElement('div');
    infoDiv.classList.add('info');
    const priceQuantityDiv = document.createElement('div');
    priceQuantityDiv.classList.add('price-quantity');
    priceQuantityDiv.textContent = `${quantity} Stocks @${oldPrice} rs`;

    const timeDiv = document.createElement('div');
    timeDiv.classList.add('time');
    timeDiv.textContent = 'Fri, 16 Feb 10:55 am';

    infoDiv.appendChild(priceQuantityDiv);
    infoDiv.appendChild(timeDiv);

    const typeGreenDiv = document.createElement('div');
    typeGreenDiv.classList.add('type-red');
    typeGreenDiv.textContent = 'Sell';

    orderContainer.appendChild(infoDiv);
    orderContainer.appendChild(typeGreenDiv);
    document.querySelector('.orders').prepend(orderContainer);

    fetch('http://localhost:3000/history', {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json'
        },
        body: JSON.stringify({ price: priceQuantityDiv.textContent, time: timeDiv.textContent, type: typeGreenDiv.textContent })
      })
      .then(response => {
        if (response.ok) {
          console.log('successful');
        } else {
          console.error('failed');
        }
      })
      .catch(error => {
        console.error('Error:', error);
      });
});