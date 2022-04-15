function pushOptions(country) {
    if (country === "Toshkent shahar") {
        country = "Toshkentc1"
    } else if (country === "Qoraqalpog'iston Resp.") {
        country = "Qoraqalpog'istonR1"
    } else {
        country = country.split(" ")[0] + "v1"
    }

    $.getJSON("http://localhost:8080/testAza/getCountry/" + country, function (data) {
        let optionAR = []
        let select = document.getElementById("region")
        select.remove()

        $.each(data, function (key, val) {
            let region = []
            region.push(Object.values(val)[3])

            for (let j = 0; j < region.length; j++) {
                optionAR.push(
                    "<option value=\"" + region[j] + "\">" + region [j] + "</option>" + "\n")
            }
        })

        $("<select>", {
            "class": "input-group-text",
            "name": "region",
            "id": "region",
            html: optionAR.join("")
        }).appendTo("#rd")
    })
}

function dp() {
    $(".datepicker").datepicker({
        dateFormat: "dd.mm.yy",
        language: "ru",
        showAnim: "clip",
        showOtherMonths: true,
        selectOtherMonths: true,
        changeMonth: true,
        changeYear: true
    });
}
