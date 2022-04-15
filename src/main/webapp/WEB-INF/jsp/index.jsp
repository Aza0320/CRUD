<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <title>People</title>

    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <link rel="stylesheet" type="text/css"
          href="https://cdn.datatables.net/v/bs5/dt-1.11.5/b-2.2.2/b-colvis-2.2.2/datatables.min.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/ns.css">
    <link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/smoothness/jquery-ui.css">

    <script src="https://code.jquery.com/jquery-3.6.0.js"></script>
    <script src="//code.jquery.com/jquery-1.12.4.js"></script>
    <script src="//code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<body>

<div class="container my-5">

    <%--    Save Form   --%>
    <div class="forma">
        <form:form method="post" action="/people/addPerson" enctype="multipart/form-data" modelAttribute="person">
            <div class="input-group mb-3">
                <form:label path="passport" class="input-group-text">
                    PASSPORT</form:label>
                <form:input path="passport" class="input-group-text" value=""/>
            </div>

            <div class="input-group mb-3">
                <form:label path="name" class="input-group-text">
                    NAME</form:label>
                <form:input path="name" class="input-group-text" value=""/>
            </div>

            <div class="input-group mb-3">
                <form:label path="surname" class="input-group-text">
                    SURNAME </form:label>
                <form:input path="surname" class="input-group-text" value=""/>
            </div>

            <div class="input-group mb-3">
                <form:label path="toDob" class="input-group-text">
                    DATE OF BIRTHDAY </form:label>
                <form:input path="toDob" data-provide="datepicker" class="input-group-text dob" value=""/>
            </div>

            <div class="input-group mb-3">
                <form:label path="sex" class="input-group-text">
                    GENDER </form:label>
                <select name="sex" class="input-group-text">
                    <option value="M">MALE</option>
                    <option value="W">FEMALE</option>
                </select>
            </div>

            <div class="input-group mb-3">
                <form:label path="country" class="input-group-text">
                    COUNTRY </form:label>
                <select onchange="pushOptions(this.value)" name="country" class="input-group-text" id="country">
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
                <form:label path="region" class="input-group-text" id="rl">
                    REGION </form:label>
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

            <div class="input-group mb-3 ">
                <form:label path="toDoi" class="input-group-text">
                    DATE OF ISSUE </form:label>
                <form:input path="toDoi" data-provide="datepicker" class="input-group-text datepicker" value=""/>
            </div>

            <div class="input-group mb-3 ">
                <form:label path="toDoe" class="input-group-text">
                    DATE OF EXPIRY </form:label>
                <form:input path="toDoe" data-provide="datepicker" class="input-group-text datepicker" value=""/>
            </div>

            <input type="file" name="file"/>
            <br>
            <br>
            <button id="saveBtn" type="submit" class="save btn btn-outline-success my-1">SAVE</button>
        </form:form>
    </div>

    <div class="title">
        <h1>INFORMATION OF PEOPLE</h1>
    </div>

    <%--    Table Form  --%>
    <div class="tablica text-center">
        <form:form method="post" action="/people/edit" modelAttribute="person">
            <table id="table" class="table table-light table-striped align-middle" style="width: 100%">
                <thead class="table-dark align-middle">
                <tr>
                    <th>id</th>
                    <th>PASSPORT</th>
                    <th>Name</th>
                    <th>surname</th>
                    <th>gender</th>
                    <th>country</th>
                    <th>region</th>
                    <th>Date of birthday</th>
                    <th>MANIPULATION BUTTONS</th>
                </tr>
                <form:hidden value="" path="id" id="edit"/>
                </thead>
            </table>
        </form:form>
    </div>

    <!-- Modal For Delete -->
    <form method="post" action="http://localhost:8080/people/delete">
        <div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel"
             aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 style="color: black" class="modal-title" id="exampleModalLabel">Are you sure you want to
                            remove this person?</h5>
                    </div>
                    <div class="modal-footer">
                        <button type="button" onclick="deleteCancel()" class="btn btn-secondary"
                                data-bs-dismiss="modal">no
                        </button>
                        <input type="hidden" name="surname" id="delete">
                        <button type="submit" class="btn btn-danger">yes</button>
                    </div>
                </div>
            </div>
        </div>
    </form>

    <!-- Modal For Img -->
    <div class="modal fade" id="viewModal" tabindex="-1" aria-labelledby="viewModalLabel"
         aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <img src="" class="figure-img img-fluid img-thumbnail rounded" id="viewImg"
                         alt="Error">
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">back
                    </button>
                    <input type="hidden" name="name" id="nameP">
                    <button type="button" data-bs-dismiss="modal" class="btn btn-danger">
                        <a href="" download="" id="download">Download</a>
                    </button>
                </div>
            </div>
        </div>
    </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p"
        crossorigin="anonymous"></script>
<script type="text/javascript"
        src="https://cdn.datatables.net/v/bs5/dt-1.11.5/b-2.2.2/b-colvis-2.2.2/datatables.min.js"></script>
<script src="${pageContext.request.contextPath}/static/scripts/people.js"></script>
<script src="${pageContext.request.contextPath}/static/scripts/general.js"></script>

</body>
</html>
