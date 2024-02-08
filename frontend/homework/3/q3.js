const shoes = [
    {
        "type": "sports",
        "color": "black",
        "size": "10",
        "price": 1499
    },
    {
        "type": "sneakers",
        "color": "blue",
        "size": "9",
        "price": 999
    }
];

const shirts = [
    {
        "type": "formal",
        "color": "white",
        "size": "L",
        "price": 1399
    },
    {
        "type": "casual",
        "color": "black",
        "size": "XL",
        "price": 1599
    },
    {
        "type": "beach",
        "color": "blue",
        "size": "M",
        "price": 999
    }
];

const warehouse = [...shoes, ...shirts];
console.log("Warehouse items: ");
console.log(warehouse);

let totalPrice = 0;
warehouse.forEach((object) => {
    totalPrice += object.price;    
});
console.log("Total price of warehouse items: " + totalPrice);

const sortedWarehouse = warehouse.sort((a,b) => b.price - a.price);
console.log("Warehouse items sorted by price: ");
console.log(sortedWarehouse);

const blueProducts = warehouse.filter((object) => object.color === 'blue');
console.log("Warehouse items with blue color: ");
console.log(blueProducts);