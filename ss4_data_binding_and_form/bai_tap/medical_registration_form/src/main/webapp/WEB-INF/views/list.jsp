<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>List</title>
<%--    <link rel="stylesheet" href="<c:url value="/resources/css/bootstrap.min.css"/>">--%>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css"
          integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
</head>
<body>
<p><a href="/create">Create</a></p>
<table class="table table-striped" modelAttribute = "detailEmail">
    <thead>
    <tr>
        <th scope="col">#</th>
        <th scope="col">Họ và tên</th>
        <th scope="col">Năm sinh</th>
        <th scope="col">Giới tính</th>
        <th scope="col">Quốc tịch</th>
        <th scope="col">Số CMND</th>
        <th scope="col">Phương tiện</th>
        <th scope="col">Số hiệu</th>
        <th scope="col">Số ghế</th>
        <th scope="col">Ngày khởi hành</th>
        <th scope="col">Ngày kết thúc</th>
        <th scope="col">Địa điểm đến trong vòng 14 ngày</th>
        <th scope="col">Địa chỉ</th>
        <th scope="col">Điện thoại</th>
        <th scope="col">Email</th>
        <th scope="col">Triệu chứng sốt</th>
        <th scope="col">Triệu chứng buồn nôn</th>
        <th scope="col">Triệu chứng ho</th>
        <th scope="col">Triệu chứng tiêu chảy</th>
        <th scope="col">Triệu chứng khó thở</th>
        <th scope="col">Triệu chứng xuất huyết ngoài da</th>
        <th scope="col">Triệu chứng đau họng</th>
        <th scope="col">Triệu chứng nổi ban ngoài da</th>
        <th scope="col" colspan="2">Lịch sử phơi nhiễm</th>
        <th scope="col">Detail</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach varStatus="stt" var="donDangKy" items="${donDangKyList}">
        <tr>
            <td>${stt.count}</td>
            <td>${donDangKy.hoVaTen}</td>
            <td>${donDangKy.namSinh}</td>
            <td>${donDangKy.gioiTinh}</td>
            <td>${donDangKy.quocTich}</td>
            <td>${donDangKy.soCMND}</td>
            <td>${donDangKy.loaiPhuongTien}</td>
            <td>${donDangKy.soHieu}</td>
            <td>${donDangKy.soGhe}</td>
            <td>${donDangKy.ngayKhoiHanh}</td>
            <td>${donDangKy.ngayKetThuc}</td>
            <td>${donDangKy.thanhPhoDaDen}</td>
            <td>${donDangKy.noiO},${donDangKy.phuongXa},${donDangKy.quanHuyen},${donDangKy.tinhThanh}</td>
            <td>${donDangKy.dienThoai}</td>
            <td>${donDangKy.email}</td>
            <td>${donDangKy.sot? "Co":"Khong"}</td>
            <td>${donDangKy.buonNon? "Co":"Khong"}</td>
            <td>${donDangKy.ho? "Co":"Khong"}</td>
            <td>${donDangKy.tieuChay? "Co":"Khong"}</td>
            <td>${donDangKy.khoTho? "Co":"Khong"}</td>
            <td>${donDangKy.xuatHuyetNgoaiDa? "Co":"Khong"}</td>
            <td>${donDangKy.dauHong? "Co":"Khong"}</td>
            <td>${donDangKy.noiBanNgoaiDa? "Co":"Khong"}</td>
            <td>${donDangKy.tiepXucNgoai? "Co tiep xuc voi noi nguy co cao":""}</td>
            <td>${donDangKy.tiepXucNguoi? "Co tiep xuc voi nguoi nguy co cao nhiem nCoV":""}</td>
            <td><a href="/listOne/${donDangKy.maDon}">Detail</a></td>
        </tr>
    </c:forEach>

    </tbody>
</table>

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