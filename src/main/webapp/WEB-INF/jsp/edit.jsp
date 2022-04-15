<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Update person</title>

    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/ns.css">
    <link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/smoothness/jquery-ui.css">

    <script src="https://code.jquery.com/jquery-3.6.0.js"></script>
    <script src="//code.jquery.com/jquery-1.12.4.js"></script>
    <script src="//code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
</head>
<body>

<div class="container my-5">
    <div class="conteyner">
        <form:form method="post" action="/people/update" modelAttribute="person">
            <div class="input-group mb-3">
                <form:label path="passport" class="input-group-text">
                    PASSPORT</form:label>
                <form:input path="passport" class="input-group-text" value="${passport}"/>
            </div>
            <div class="input-group mb-3">
                <form:label path="name" class="input-group-text">

                    NAME</form:label>
                <form:input path="name" class="input-group-text" value="${name}"/>
            </div>
            <div class="input-group mb-3">
                <form:label path="surname" class="input-group-text">

                    SURNAME </form:label>
                <form:input path="surname" class="input-group-text" value="${surname}"/>
            </div>
            <div class="input-group mb-3">
                <form:label path="toDob" class="input-group-text">
                    DATE OF BIRTHDAY </form:label>
                <form:input path="toDob" class="input-group-text datepicker" value="${dob}"/>
            </div>
            <div class="input-group mb-3">
                <form:label path="sex" class="input-group-text">
                    GENDER </form:label>
                <input type="hidden" id="gender" class="input-group-text" value="${sex}"/>
                <select name="sex" class="input-group-text">
                    <option value="M">MALE</option>
                    <option value="W">FEMALE</option>
                </select>
            </div>
            <div class="input-group mb-3">
                <form:label path="country" class="input-group-text">
                    COUNTRY </form:label>
                <input id="country" type="hidden" class="input-group-text " value="${country}"/>
                <select onchange="pushOptions(this.value)" name="country" class="input-group-text">
                    <option value="Toshkent shahar">Toshkent shahar</option>
                    <option value="Toshkent viloyati">Toshkent viloyati</option>
                    <option value="Andijon viloyati">Andijon viloyati</option>
                    <option value="Buxoro viloyati">Buxoro viloyati</option>
                    <option value="Jizzax viloyati">Jizzax viloyati</option>
                    <option value="Qashqadaryo viloyati">Qashqadaryo viloyati</option>
                    <option value="Navoiy viloyati">Navoiy viloyati</option>
                    <option value="Namangan viloyati">Namangan viloyati</option>
                    <option value="Samarqand viloyati">Samarqand viloyati</option>
                    <option value="Surxondaryo viloyati">Surxondaryo viloyati</option>
                    <option value="Sirdaryo viloyati">Sirdaryo viloyati</option>
                    <option value="Farg'ona viloyati">Farg'ona viloyati</option>
                    <option value="Xorazm viloyati">Xorazm viloyati</option>
                    <option value="Qoraqalpog'iston Resp.">Qoraqalpog'iston Resp.</option>
                </select>
            </div>

            <div class="input-group mb-3" id="rd">
                <form:label path="region" class="input-group-text">
                    REGION </form:label>
                <input id="regionI" type="hidden" class="input-group-text " value="${region}"/>
                <select name="region" class="input-group-text" id="region">
                    <option value="Shayxontohur tumani">Shayxontohur tumanii</option>
                    <option value="Mirzo Ulug'bek tumani">Mirzo Ulug'bek tumani</option>
                    <option value="Yunusobod tumani">Yunusobod tumanii</option>
                    <option value="Chilonzor tumani">Chilonzor tumanii</option>
                    <option value="Yashnobod tumani">Yashnobod tumanii</option>
                    <option value="Olmazor tumani">Olmazor tumanii</option>
                    <option value="Bektemir tumani">Bektemir tumanii</option>
                    <option value="Yangihayot tumani">Yangihayot tumanii</option>
                    <option value="Mirobod tumani">Mirobod tumanii</option>
                    <option value="Yakkasaroy tumani">Yakkasaroy tumanii</option>
                    <option value="Sergeli tumani">Sergeli tumanii</option>
                    <option value="Uchtepa tumani">Uchtepa tumanii</option>
                </select>
            </div>

            <div class="input-group mb-3">
                <form:label path="toDoi" class="input-group-text">
                    DATE OF ISSUE </form:label>
                <form:input path="toDoi" class="input-group-text datepicker" value="${doi}"/>
            </div>
            <div class="input-group mb-3">
                <form:label path="toDoe" class="input-group-text">
                    DATE OF EXPIRY </form:label>
                <form:input path="toDoe" class="input-group-text datepicker" value="${doe}"/>
            </div>

            <form:hidden value="${id}" path="id"/>

            <button id="changeBtn" type="submit" class="save change btn btn-outline-success my-1">EDIT</button>
        </form:form>
    </div>
</div>

<script src="${pageContext.request.contextPath}/static/scripts/general.js"></script>
<script src="${pageContext.request.contextPath}/static/scripts/edit.js"></script>

</body>
</html>