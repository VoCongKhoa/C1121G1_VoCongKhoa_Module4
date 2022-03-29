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
                    <form action="/meaning">
                    <div class="mb-3">
                        <label for="number1" class="form-label">Enter your word</label>
                        <input type="text" class="form-control" id="number1" name="word">
                    </div>
                    <button type="submit" class="btn btn-primary">Submit</button>
                    </form>
                    <p>${meaning}</p>
                </div>
            </div>
        </div>

</body>
</html>
