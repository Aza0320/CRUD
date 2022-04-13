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
                <form:label path="address" class="input-group-text">
                    ADDRESS </form:label>
                <input id="address" type="hidden" class="input-group-text " value="${address}"/>
                <select name="address" class="input-group-text">
                    <option value="Toshkent">Toshkent</option>
                    <option value="Toshkent viloyati">Toshkent viloyati</option>
                    <option value="Andijon">Andijon</option>
                    <option value="Buxoro">Buxoro</option>
                    <option value="Jizzax">Jizzax</option>
                    <option value="Qashqadaryo">Qashqadaryo</option>
                    <option value="Navoiy">Navoiy</option>
                    <option value="Namangan">Namangan</option>
                    <option value="Samarqand">Samarqand</option>
                    <option value="Surxondaryo">Surxondaryo</option>
                    <option value="Sirdaryo">Sirdaryo </option>
                    <option value="Farg'ona">Farg'ona </option>
                    <option value="Xorazm">Xorazm</option>
                    <option value="Samarqand">Samarqand</option>
                    <option value="Qoraqalpog'iston Respublikasi">Qoraqalpog'iston Respublikasi</option>
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

<script src="${pageContext.request.contextPath}/static/scripts/edit.js"></script>

</body>
</html>