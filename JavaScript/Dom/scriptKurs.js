changeMoney.addEventListener ('click', changeKurs);

function changeKurs(){
    let money = moneySum.value;
    let kurs = changeMoney.value;

if (kurs=="KZT"){
    document.getElementById('tradeKZT').textContent = "-";
    document.getElementById('tradeUSD').textContent = (money/429).toFixed(2);
    document.getElementById('tradeEUR').textContent = (money/509).toFixed(2);
}
else if (kurs=="USD"){
    document.getElementById('tradeKZT').textContent = (money*429).toFixed(2);
    document.getElementById('tradeUSD').textContent = "-";
    document.getElementById('tradeEUR').textContent = (money*431/507).toFixed(2);
}
else if (kurs=="EUR"){
    document.getElementById('tradeKZT').textContent = (money*507).toFixed(2);
    document.getElementById('tradeUSD').textContent = (money*507/429).toFixed(2);
    document.getElementById('tradeEUR').textContent = "-";
}
}