dp()
pushOptions(document.getElementById("country").value)
let inputG = document.getElementById("gender")
let countryJ = document.getElementById("country").value
let region = document.getElementById("regionI").value

let option = document.getElementsByTagName("option")
for (let i = 0; i < 2; i++) {
    let text = "MALE";
    if (inputG.value === "W") {
        text = "FEMALE"
    }
    if (option[i].innerText === text) {
        option[i].setAttribute("selected", "")
    }
}
for (let i = 2; i < 16; i++) {
    if (option[i].innerText === countryJ) {
        option[i].setAttribute("selected", "")
        break;
    }
}

for (let i = 16; i < option.length; i++) {
    if (option[i].innerText === region) {
        option[i].setAttribute("selected", "")
        break;
    }
}