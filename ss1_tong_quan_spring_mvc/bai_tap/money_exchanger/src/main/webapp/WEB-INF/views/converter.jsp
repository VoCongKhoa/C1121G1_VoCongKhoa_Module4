<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Greeting</title>
    <link rel="stylesheet" href="<c:url value="/resources/css/bootstrap.min.css"/>">
</head>
<body>

<div class="container">

    <div class="row">
        <div class="col-lg-6 m-lg-auto">
            <form action="/convert" method="post">
                <div class="mb-3">
                    <label for="number1" class="form-label">Amount</label>
                    <input type="text" class="form-control" id="number1" name="money">
                </div>
                <div class="mb-3">
                    <label for="number2" class="form-label">From</label>
                    <select name="rate1" id="number2" class="form-control">
                        <option value="23000">USD - US Dollar</option>
                        <option value="30000">EUR - Euro</option>
                        <option value="185">JPY - Japanese Yen</option>
                        <option value="1">VND - Vietnam Dong</option>
                    </select>
                </div>
                <div class="mb-3">
                    <label for="number3" class="form-label">To</label>
                    <select name="rate2" id="number3" class="form-control">
                        <option value="23000">USD - US Dollar</option>
                        <option value="30000">EUR - Euro</option>
                        <option value="185">JPY - Vietnam Dong</option>
                        <option value="1">VND - Vietnam Dong</option>
                    </select>
                </div>
                <button type="submit" class="btn btn-primary text-center">Submit</button>
            </form>
            <c:if test="${result == null}">
                <p hidden>RESULT: ${result}</p>
            </c:if>
            <c:if test="${result != null}">
                <p>RESULT: ${result}</p>
            </c:if>
        </div>
    </div>

</div>

</body>
</html>
