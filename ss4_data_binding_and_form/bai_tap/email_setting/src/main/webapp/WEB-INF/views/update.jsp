<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Create a new emailDetail</title>
    <link rel="stylesheet" href="<c:url value="/resources/css/bootstrap.min.css"/>">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css"
          integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
</head>
<body>
<div class="container mt-5">
    <form:form modelAttribute="emailDetail" method="post" action="/emailDetail/update">
        <form:hidden path="id"/>
        <div class="form-row">
            <div class="form-group col-md-6">
                <form:label path="email">Email</form:label>
                <form:input class="form-control" path="email"/>
            </div>
            <div class="form-group col-md-6">
                <form:label path="language">Language</form:label>
                <form:select class="form-control" path="language" items="${languageList}">
                </form:select>
            </div>
        </div>
        <div class="form-group">
            <form:label path="pageSize">Page size</form:label>
            <form:select class="form-control" path="pageSize" items="${pageSizeList}">
            </form:select>
        </div>
        <div class="form-group">
            <div class="form-check">
                <form:checkbox class="form-check-input" path="spamStatus" items = "spamsEnable"/>
                <form:label class="form-check-label" path="spamStatus">
                    Enable spams filter
                </form:label>
            </div>
        </div>
        <div class="form-group">
            <form:label path="signature">Signature</form:label>
            <form:input class="form-control" path="signature"/>
        </div>
        <button type="submit" class="btn btn-primary">Update</button>
    </form:form>
</div>

<!-- Optional JavaScript -->
<!-- jQuery first, then Popper.js, then Bootstrap JS -->
<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"
        integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN"
        crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.12.9/dist/umd/popper.min.js"
        integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q"
        crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/js/bootstrap.min.js"
        integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl"
        crossorigin="anonymous"></script>
</body>
</html>