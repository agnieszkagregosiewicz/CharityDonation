const categories = document.querySelectorAll('input[name="categories"]:checked ~ span.description');
const quantity = document.querySelector("#Q");
const institution = document.querySelector("#inst");
const street = document.querySelector("#street");
const city = document.querySelector("#city");
const zipcode = document.querySelector("#zip");
const phone = document.querySelector("#phone");
const pickupTime = document.querySelector("#time");
const pickupDate = document.querySelector("#date");
const comment = document.querySelector("#com");

const quan = document.querySelector("#quan");
const fund = document.querySelector("#fund");
const whereS = document.querySelector("#where1");
const whereC = document.querySelector("#where2");
const whereZ = document.querySelector("#where3");
const whereP = document.querySelector("#where4");
const whenD = document.querySelector("#when1");
const whenT = document.querySelector("#when2");
const whenC = document.querySelector("#when3");

const finishButton =document.querySelector("#finish");
let names = '';
categories.forEach(function(element) {names += element.innerHTML + ' ';})

finishButton.addEventListener('click', summary);
function summary() {
    quan.innerHTML = quantity.value + " worki z: " + names;
    fund.innerHTML = "Dla fundacji " + institution.value;
    whereS.innerHTML = "" + street.value;
    whereC.innerHTML = "" + city.value;
    whereZ.innerHTML = "" + zipcode.value;
    whereP.innerHTML = "" + phone.value;
    whenD.innerHTML = "" + pickupDate.value;
    whenT.innerHTML = "" + pickupTime.value;
    whenC.innerHTML = "" + comment.value;


}
