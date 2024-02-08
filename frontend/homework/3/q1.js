const tipCalculator = (billAmounts) => {
    const tipAmounts = [];
    const finalAmounts = [];

    billAmounts.forEach(amount => {
        let tip = 0;
        if(amount < 50){
            tip = amount * 0.2;
        }
        else if(amount >= 50 && amount <= 200){
            tip = amount * 0.15;
        }
        else{
            tip = amount * 0.1;
        }
        tipAmounts.push(tip);
        finalAmounts.push(tip + amount);
    });
    return [tipAmounts, finalAmounts];
}

const billAmounts = [140, 45, 280];
const [tipAmounts, finalAmounts] = tipCalculator(billAmounts);

console.log("Tip Amounts: " + tipAmounts);
console.log("Final Amounts: " + finalAmounts);