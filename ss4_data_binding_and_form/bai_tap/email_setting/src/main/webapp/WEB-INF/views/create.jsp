<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Create a new emailDetail</title>
    <link rel="stylesheet" href="<c:url value="/resources/css/bootstrap.min.css"/>">
</head>
<body>
<div class="container mt-5">
    <form>
        <div class="form-row">
            <div class="form-group col-md-6">
                <label for="inputEmail4">Email</label>
                <input type="email" class="form-control" id="inputEmail4" placeholder="Email" name="email">
            </div>
            <div class="form-group col-md-6">
                <label for="1">Language</label>
                <select id="1" class="form-control" name="language">
                    <option value="english" selected>English</option>
                    <option value="vietnamese">Vietnamese</option>
                    <option value="japanese">Japanese</option>
                    <option value="chinese">Chinese</option>
                </select>
            </div>
        </div>
        <div class="form-group">
            <label for="2">Page size</label>
            <select id="2" class="form-control" name="pageSize">
                <option value="5" selected>5</option>
                <option value="10">10</option>
                <option value="15">15</option>
                <option value="25">25</option>
                <option value="50">50</option>
                <option value="100">100</option>
            </select>
        </div>
        <div class="form-group">
            <div class="form-check">
                <input class="form-check-input" type="checkbox" id="gridCheck" name="spamStatus">
                <label class="form-check-label" for="gridCheck">
                    Enable spams filter
                </label>
            </div>
        </div>
        <div class="form-group">
            <label for="Textarea1">Signature</label>
            <textarea class="form-control" id="Textarea1" rows="3" name="signature"></textarea>
        </div>
        <button type="submit" class="btn btn-primary">Sign in</button>
    </form>
</div>

<!-- Optional JavaScript -->
<!-- jQuery first, then Popper.js, then Bootstrap JS -->
<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.12.9/dist/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
</body>
</html>