// let currentPage = document.getElementById("currentPage").innerText
let currentPage = document.getElementById("currentPage")

let li = document.querySelectorAll("li.page-item")
for (let i = 0; i < li.length; i++) {
    if (li[i].innerText === currentPage.value) {
        let btn = document.querySelector("#active")
        btn.id = ""
        btn.className = "page-link my-btn"
        let active = li[i].children[0]
        active.id = "active"
    }
}
$(document).ready(dt());

function dt() {
    let g = document.getElementById("findG").value
    let c = document.getElementById("findC").value
    let d = document.getElementById("findD").value
    let r = document.getElementById("findR").value
    let list = {}
    let option = document.querySelectorAll("th select option")
    if (g !== "") {
        list['gender'] = g
        let text
        if (g === "M") text = "MALE"
        else text = "FEMALE"
        for (let i = 0; i < 3; i++) {
            if (option[i].innerHTML === text) {
                option[i].setAttribute("selected", "")
            }
        }
    }
    if (c !== "") {
        list['country'] = c
    }
    if (d !== "") {
        list['date'] = d
    }
    if (r !== "") {
        list['region'] = r
    }

    let keys = Object.keys(list)
    console.log(keys)
    console.log(list)
    console.log(g)

    let offset = 100 * currentPage.value
    let next = 100
    let url = "http://localhost:8080/testAza/getTest" + keys.length +"?offset=" + offset + "&next=" + next

    switch (keys.length) {
        case 1:
            url += "&" + keys[0] + "=" + list[keys[0]]
            break
        case 2:
            url += "&" + keys[0] + "=" + list[keys[0]] + "&" + keys[1] + "=" + list[keys[1]]
            break
        case 3:
            url += "&" + keys[0] + "=" + list[keys[0]] + "&" + keys[1] + "=" + list[keys[1]] + keys[2] + "=" + list[keys[2]]
            break
        case 4:
            url += "&" + keys[0] + "=" + list[keys[0]] + "&" + keys[1] + "=" + list[keys[1]] + keys[2] + "=" + list[keys[2]] + keys[3] + "=" + list[keys[3]]
    }

    console.log(url)

    $('#table').DataTable({
            "scrollX": false,
            "ajax": {
                "url": url,
                "dataSrc": ""
            },
            "columns": [
                {"data": "id"},
                {"data": "passport"},
                {"data": "name"},
                {"data": "surname"},
                {"data": "pol"},
                {"data": "country"},
                {"data": "region"},
                {"data": "dobString"},
                {
                    "data": function () {
                        return "<div class=\"d-flex justify-content-evenly\">" +
                            "<button class=\"edit btn btn-outline-secondary\" type=\"submit\"\n " +
                            "onclick=\"editThis(this)\">" +

                            "<svg xmlns=\"http://www.w3.org/2000/svg\" width=\"20\" height=\"20\" fill=\"currentColor\"\n" + "class=\"bi bi-pencil-square mx-1\" viewBox=\"0 0 16 16\">\n" + "<path d=\"M15.502 1.94a.5.5 0 0 1 0 .706L14.459 3.69l-2-2L13.502.646a.5.5 0 0 1 .707 0l1.293 1.293zm-1.75 2.456-2-2L4.939 9.21a.5.5 0 0 0-.121.196l-.805 2.414a.25.25 0 0 0 .316.316l2.414-.805a.5.5 0 0 0 .196-.12l6.813-6.814z\"></path>\n" + "<path fill-rule=\"evenodd\" d=\"M1 13.5A1.5 1.5 0 0 0 2.5 15h11a1.5 1.5 0 0 0 1.5-1.5v-6a.5.5 0 0 0-1 0v6a.5.5 0 0 1-.5.5h-11a.5.5 0 0 1-.5-.5v-11a.5.5 0 0 1 .5-.5H9a.5.5 0 0 0 0-1H2.5A1.5 1.5 0 0 0 1 2.5v11z\"></path>\n" +
                            "</svg>Edit</button>"

                            + "<button class=\"delete btn btn-outline-success\" type=\"button\" onclick=\"viewFind(this)\" data-bs-toggle=\"modal\"\n" +
                            "        data-bs-target=\"#viewModal\">\n" +
                            "    <svg xmlns=\"http://www.w3.org/2000/svg\" width=\"22\" height=\"22\" fill=\"currentColor\" class=\"bi bi-person-square mb-1\"\n" +
                            "         viewBox=\"0 0 16 16\">\n" +
                            "        <path d=\"M11 6a3 3 0 1 1-6 0 3 3 0 0 1 6 0z\"></path>\n" +
                            "        <path d=\"M2 0a2 2 0 0 0-2 2v12a2 2 0 0 0 2 2h12a2 2 0 0 0 2-2V2a2 2 0 0 0-2-2H2zm12 1a1 1 0 0 1 1 1v12a1 1 0 0\n" +
                            "              1-1 1v-1c0-1-1-4-6-4s-6 3-6 4v1a1 1 0 0 1-1-1V2a1 1 0 0 1 1-1h12z\"></path>\n" +
                            "    </svg>\n" +
                            "    View\n" +
                            "</button>"

                            + "<button class=\"delete btn btn-outline-danger\" type=\"button\"\n " +
                            "onclick=\"deleteFind(this)\" data-bs-toggle=\"modal\" data-bs-target=\"#exampleModal\">" +

                            "<svg xmlns=\"http://www.w3.org/2000/svg\" width=\"20\" height=\"20\" fill=\"currentColor\"\n" + "class=\"bi bi-person-dash mx-1 mb-1\" viewBox=\"0 0 16 16\">" + "<path d=\"M6 8a3 3 0 1 0 0-6 3 3 0 0 0 0 6zm2-3a2 2 0 1 1-4 0 2 2 0 0 1 4 0zm4 8c0 1-1 1-1 1H1s-1 0-1-1 1-4 6-4 6 3 6 4zm-1-.004c-.001-.246-.154-.986-.832-1.664C9.516 10.68 8.289 10 6 10c-2.29 0-3.516.68-4.168 1.332-.678.678-.83 1.418-.832 1.664h10z\"></path>\n" + "<path fill-rule=\"evenodd\" d=\"M11 7.5a.5.5 0 0 1 .5-.5h4a.5.5 0 0 1 0 1h-4a.5.5 0 0 1-.5-.5z\"></path>\n" +
                            "</svg>Delete</button>" +
                            "</div>"
                    }
                }
            ],
        },
    )
}

$(function () {
    $(".dob").datepicker({
        defaultDate: -11789,
        dateFormat: "dd.mm.yy",
        language: "ru",
        showAnim: "clip",
        showOtherMonths: true,
        selectOtherMonths: true,
        changeMonth: true,
        changeYear: true
    });
    dp()
});

function deleteFind(n) {
    let td = n.parentElement.parentElement.getElementsByTagName("td")[0]
    let b = document.getElementById("delete")
    b.value = td.textContent;
}

function deleteCancel() {
    let a = document.getElementById("delete")
    a.value = ""
}

function editThis(n) {
    let td = n.parentElement.parentElement.getElementsByTagName("td")[0]
    let b = document.getElementById("edit")
    b.value = td.textContent;
}

function viewFind(n) {
    let td = n.parentElement.parentElement.getElementsByTagName("td").item(0).innerText
    let img = document.getElementById("viewImg")
    let a = document.getElementById("download")
    a.setAttribute("href", "http://localhost:8080/people/getPdf/" + td)
    img.setAttribute("src", "http://localhost:8080/people/getImg/" + td)
}

function pagination(n) {
    let page = n.childNodes[1].outerText
    let input = document.getElementById("page")

    input.setAttribute("value", page)
}
