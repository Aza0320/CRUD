$(function () {
    $(".datepicker").datepicker({
        dateFormat: "dd.mm.yy",
        language: "ru",
        showAnim: "clip",
        showOtherMonths: true,
        selectOtherMonths: true,
        changeMonth: true,
        changeYear: true
    });
});

let inputG = document.getElementById("gender")
let inputA = document.getElementById("address").value
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
for (let i = 2; i < option.length; i++) {
    if (option[i].innerText === inputA) {
        option[i].setAttribute("selected", "")
        break;
    }
}