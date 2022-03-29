<%--
  Created by IntelliJ IDEA.
  User: OS
  Date: 3/29/2022
  Time: 7:39 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <!-- Required meta tags -->
    <meta charset="utf-8" />
    <meta
            name="viewport"
            content="width=device-width, initial-scale=1, shrink-to-fit=no"
    />

    <!-- Bootstrap CSS -->
    <link
            rel="stylesheet"
            href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css"
            integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm"
            crossorigin="anonymous"
    />

    <title>Hello, world!</title>
    <style>
        select {
            /* for Firefox */
            -moz-appearance: none;
            /* for Chrome */
            -webkit-appearance: none;
        }

        /* For IE10 */
        select::-ms-expand {
            display: none;
        }
    </style>
</head>
<body>
<body>
<div class="container">
    <div class="row">
        <div class="col-lg-6 m-lg-auto">
            <h1>Simple Calculator</h1><br>
            <form method="post" action="/calculate">
                <input type="text" id="vehicle1" name="number1" />
                <select name="calculate" id="">
                    <option value="+" selected>+</option>
                    <option value="-">-</option>
                    <option value="x">x</option>
                    <option value=":">:</option>
                </select>
                <input type="text" id="vehicle2" name="number2" />
                <br><br>
                <input type="submit" value="Calculate" />
            </form>
            <c:if test="${result == null}">
                <h2 hidden>Result: <b>${result}</b></h2>
            </c:if>
            <c:if test="${result != null}">
                <h2>Result: <b>${result}</b></h2>
            </c:if>
        </div>
    </div>
</div>
</body>

<!-- Optional JavaScript -->
<!-- jQuery first, then Popper.js, then Bootstrap JS -->
<script
        src="https://code.jquery.com/jquery-3.2.1.slim.min.js"
        integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN"
        crossorigin="anonymous"
></script>
<script
        src="https://cdn.jsdelivr.net/npm/popper.js@1.12.9/dist/umd/popper.min.js"
        integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q"
        crossorigin="anonymous"
></script>
<script
        src="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/js/bootstrap.min.js"
        integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl"
        crossorigin="anonymous"
></script>
</body>
</html>

