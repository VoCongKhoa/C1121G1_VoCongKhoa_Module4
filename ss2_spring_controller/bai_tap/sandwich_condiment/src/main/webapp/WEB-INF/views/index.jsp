<%--
  Created by IntelliJ IDEA.
  User: OS
  Date: 3/29/2022
  Time: 7:39 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <link rel="stylesheet" href="<c:url value="/resources/css/bootstrap.min.css"/>">
    <title>$Title$</title>
</head>
<body>

<div class="container">
    <div class="row">
        <div class="col-lg-6 m-lg-auto">
            <h1>Sandwich condiments</h1>
            <form method="post" action="/submit">
                <input type="checkbox" id="vehicle1" name="lettuce" value="lettuce">
                <label for="vehicle1"> Lettuce</label><br>
                <input type="checkbox" id="vehicle2" name="tomato" value="tomato">
                <label for="vehicle2"> Tomato</label><br>
                <input type="checkbox" id="vehicle3" name="mustard" value="mustard">
                <label for="vehicle3"> Mustard</label><br>
                <input type="checkbox" id="vehicle4" name="sprouts" value="sprouts">
                <label for="vehicle4"> Sprouts</label><br><br>
                <input type="submit" value="Choose">
            </form>
            <c:if test="${result == null}">
                <h2 hidden>Chosen condiments: <b>${result}</b></h2>
            </c:if>
            <c:if test="${result != null}">
                <h2>Chosen condiments: <b>${result}</b></h2>
            </c:if>
        </div>
    </div>
</div>

</body>
</html>
