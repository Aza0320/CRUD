function pushOptions(country, region, to) {
    if (country !== "") {
        if (country === "Toshkent shahar") {
            country = "Toshkentc1"
        } else if (country === "Qoraqalpog'iston Resp.") {
            country = "Qoraqalpog'istonR1"
        } else {
            country = country.split(" ")[0] + "v1"
        }

        $.getJSON("http://localhost:8080/peopleRequests/getCountry/" + country, function (data) {
            let optionAR = []
            let select = document.getElementById(region)
            select.remove()

            let func = ""
            let cl
            if (region === "regionS") {
                cl = ""
                optionAR.push("<option value=\"\"></option>")
            }else if (region === "table-region-select") {
                cl = ""
                optionAR.push("<option value=\"\"></option>")
                func = "table_select_region(this.value)"
            } else {
                cl = "input-group-text"
            }

            $.each(data, function (key, val) {
                let region = []
                region.push(Object.values(val)[3])
                for (let j = 0; j < region.length; j++) {
                    optionAR.push(
                        "<option value=\"" + region[j] + "\">" + region [j] + "</option>" + "\n")
                }
            })

            $("<select>", {
                "class": cl,
                "name": "region",
                "id": region,
                "onchange": func,
                html: optionAR.join("")
            }).appendTo(to)

        })
    } else {
        let select = document.getElementById(region)
        select.className = "ui-state-disabled"
        select.value = ""
    }
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