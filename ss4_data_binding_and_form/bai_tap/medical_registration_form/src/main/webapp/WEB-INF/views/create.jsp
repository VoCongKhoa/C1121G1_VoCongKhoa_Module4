<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <!-- Required meta tags-->
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="Colorlib Templates">
    <meta name="author" content="Colorlib">
    <meta name="keywords" content="Colorlib Templates">

    <!-- Title Page-->
    <title>Au Register Forms by Colorlib</title>

    <!-- Icons font CSS-->
    <link href="<c:url value="/resources/vendor/mdi-font/css/material-design-iconic-font.min.css"/>" rel="stylesheet"
          media="all"/>
    <link href="<c:url value="/resources/vendor/font-awesome-4.7/css/font-awesome.min.css"/>" rel="stylesheet"
          media="all">

    <!-- Font special for pages-->
    <link href="https://fonts.googleapis.com/css?family=Poppins:100,100i,200,200i,300,300i,400,400i,500,500i,600,600i,700,700i,800,800i,900,900i"
          rel="stylesheet">

    <!-- Vendor CSS-->
    <link href="<c:url value="/resources/vendor/select2/select2.min.css"/>" rel="stylesheet" media="all">
    <link href="<c:url value="/resources/vendor/datepicker/daterangepicker.css"/>" rel="stylesheet" media="all">

    <!-- Main CSS-->
    <link href="<c:url value="/resources/css/main.css"/>" rel="stylesheet" media="all">
</head>

<body>
<div class="page-wrapper bg-blue p-t-130 p-b-100 font-poppins">
    <div class="wrapper wrapper--w680">
        <div class="card card-4">
            <div class="card-body">
                <h2 style="text-align: center;margin: 0px 0px 40px;">Tờ khai y tế</h2>
                <form:form method="POST" action="/save" modelAttribute="donDangKy">
                    <div class="input-group">
                        <form:label path="hoVaTen">Họ và tên</form:label>
                        <form:input class="input--style-4" type="text" path="hoVaTen" />
                    </div>
                    <div class="row row-space">
                        <div class="col-3">
                            <div class="input-group">
                                <form:label path="namSinh">Năm sinh</form:label>
                                <form:select class="input--style-4" path="namSinh" items="${namSinhList}">
                                </form:select>
                            </div>
                        </div>
                        <div class="col-3">
                            <div class="input-group">
                                <form:label path="gioiTinh">Giới tính</form:label>
                                <form:select class="input--style-4" path="gioiTinh">
                                    <form:option value="Nam">Nam</form:option>
                                    <form:option value="Nu">Nữ</form:option>
                                </form:select>
                            </div>
                        </div>
                        <div class="col-3">
                            <div class="input-group">
                                <form:label path="quocTich">Quốc tịch</form:label>
                                <form:select class="input--style-4" path="quocTich" items="${quocTichList}">
                                </form:select>
                            </div>
                        </div>
                    </div>
                    <div class="input-group">
                        <form:label path="soCMND">Số hộ chiếu hoặc số CMND hoặc giấy thông hành hợp pháp khác</form:label>
                        <form:input class="input--style-4" type="text" path="soCMND" />
                    </div>
                    <div class="input-group">
                        <label class="label">Thông tin đi lại</label>
                        <div class="p-t-10">
                            <form:label class="radio-container m-r-45" path="loaiPhuongTien">Tàu bay</form:label>
                                <form:radiobutton class="form-check-input" path="loaiPhuongTien" value="Tau bay"/>
                            <form:label class="radio-container m-r-45" path="loaiPhuongTien">Tàu thuyền</form:label>
                                <form:radiobutton class="form-check-input" path="loaiPhuongTien" value="Tau thuyen"/>
                            <form:label class="radio-container m-r-45" path="loaiPhuongTien">Ô tô</form:label>
                                <form:radiobutton class="form-check-input" path="loaiPhuongTien" value="O to"/>
                            <form:label class="radio-container m-r-45" path="loaiPhuongTien">Khác (Ghi rõ)</form:label>
                                <form:radiobutton class="form-check-input" path="loaiPhuongTien" value="Khac"/>
                        </div>
                    </div>
                    <div class="row row-space">
                        <div class="col-2">
                            <div class="input-group">
                                <form:label class="label" path="soHieu">Số hiệu phương tiện</form:label>
                                <form:input class="input--style-4" type="text" path="soHieu" />
                            </div>
                        </div>
                        <div class="col-2">
                            <div class="input-group">
                                <form:label class="label" path="soGhe">Số ghế</form:label>
                                <form:input class="input--style-4" type="text" path="soGhe" />
                            </div>
                        </div>
                    </div>
                    <div class="row row-space">
                        <div class="col-2">
                            <div class="input-group">
                                <form:label class="label" path="ngayKhoiHanh">Ngày khởi hành</form:label>
                                <form:input class="input--style-4" type="date" path="ngayKhoiHanh" />
                            </div>
                        </div>
                        <div class="col-2">
                            <div class="input-group">
                                <form:label class="label" path="ngayKetThuc">Ngày kết thúc</form:label>
                                <form:input class="input--style-4" type="date" path="ngayKetThuc" />
                            </div>
                        </div>
                    </div>
                    <div class="input-group">
                        <form:label class="label" path="thanhPhoDaDen">Trong vòng 14 ngày qua, Anh/Chị đã đến tỉnh, thành phố nào?</form:label>
                        <form:textarea  type="text" cols="129" rows="1" style="padding: 10px;" path="thanhPhoDaDen"  class="input--style-4" />
                    </div>
                    <div><b>Địa chỉ liên lạc</b></div><br>
                    <div class="row row-space">
                        <div class="col-3">
                            <div class="input-group">
                                <form:label class="label" path="tinhThanh">Tỉnh/thành</form:label>
                                <form:input class="input--style-4" type="text" path="tinhThanh" />
                            </div>
                        </div>
                        <div class="col-3">
                            <div class="input-group">
                                <form:label class="label" path="quanHuyen">Quận/huyện</form:label>
                                <form:input class="input--style-4" type="text" path="quanHuyen" />
                            </div>
                        </div>
                        <div class="col-3">
                            <div class="input-group">
                                <form:label class="label" path="phuongXa">Phường/xã</form:label>
                                <form:input class="input--style-4" type="text" path="phuongXa" />
                            </div>
                        </div>
                    </div>
                    <div class="input-group">
                        <form:label class="label" path="noiO">Địa chỉ nơi ở</form:label>
                        <form:input class="input--style-4" type="text" path="noiO" />
                    </div>
                    <div class="row row-space">
                        <div class="col-2">
                            <div class="input-group">
                                <form:label class="label" path="dienThoai">Điện thoại</form:label>
                                <form:input class="input--style-4" type="text" path="dienThoai" />
                            </div>
                        </div>
                        <div class="col-2">
                            <div class="input-group">
                                <form:label class="label" path="email">Email</form:label>
                                <form:input class="input--style-4" type="text" path="email" />
                            </div>
                        </div>
                    </div>

                    <div><b>Trong vòng 14 ngày qua, Anh/Chị có thấy xuất hiện dấu vết nào sau đây không?</b></div><br>
                    <div class="row row-space">
                        <table class="" style="width: 1200px;">
                            <tr style="
                            border: solid; height: 50px; text-align: center;
                        ">
                                <th scope="col">Triệu chứng</th>
                                <th scope="col">Có</th>
                                <th scope="col">Không</th>
                                <th scope="col">Triệu chứng</th>
                                <th scope="col">Có</th>
                                <th scope="col">Không</th>
                            </tr>
                            <tr style="
                            border: solid; height: 30px; text-align: center;
                        ">
                                <td>Sốt</td>
                                <td>
                                    <form:radiobutton class="form-check-input" path="sot" value="true"/>
                                </td>
                                <td>
                                    <form:radiobutton class="form-check-input" path="sot" value="false"/>
                                </td>
                                <td>Nôn/Buồn nôn</td>
                                <td>
                                    <form:radiobutton class="form-check-input" path="buonNon" value="true"/>
                                </td>
                                <td>
                                    <form:radiobutton class="form-check-input" path="buonNon" value="false" />
                                </td>
                            </tr>
                            <tr style="
                            border: solid; height: 30px; text-align: center;
                        ">
                                <td>Ho</td>
                                <td>
                                    <form:radiobutton class="form-check-input" path="ho" value="true"/>
                                </td>
                                <td>
                                    <form:radiobutton class="form-check-input" path="ho" value="false"/>
                                </td>
                                <td>Tiêu chảy</td>
                                <td>
                                    <form:radiobutton class="form-check-input" path="tieuChay" value="true"/>
                                </td>
                                <td>
                                    <form:radiobutton class="form-check-input" path="tieuChay" value="false"/>
                                </td>
                            </tr>
                            <tr style="
                            border: solid; height: 30px; text-align: center;
                        ">
                                <td>Khó thở</td>
                                <td>
                                    <form:radiobutton class="form-check-input" path="khoTho" value="true"/>
                                </td>
                                <td>
                                    <form:radiobutton class="form-check-input" path="khoTho" value="false"/>
                                </td>
                                <td>Xuất huyết ngoài da</td>
                                <td>
                                    <form:radiobutton class="form-check-input" path="xuatHuyetNgoaiDa" value="true"/>
                                </td>
                                <td>
                                    <form:radiobutton class="form-check-input" path="xuatHuyetNgoaiDa" value="false"/>
                                </td>
                            </tr>
                            <tr style="
                            border: solid; height: 30px; text-align: center;
                        ">
                                <td>Đau họng</td>
                                <td>
                                    <form:radiobutton class="form-check-input" path="dauHong" value="true"/>
                                </td>
                                <td>
                                    <form:radiobutton class="form-check-input" path="dauHong" value="false"/>
                                </td>
                                <td>Nổi ban ngoài da</td>
                                <td>
                                    <form:radiobutton class="form-check-input" path="noiBanNgoaiDa" value="true"/>
                                </td>
                                <td>
                                    <form:radiobutton class="form-check-input" path="noiBanNgoaiDa" value="false"/>
                                </td>
                            </tr>
                        </table>
                    </div>

                    <br><br>
                    <div><b>Lịch sử phơi nhiễm: Trong vòng 14 ngày qua, Anh/Chị có</b></div><br>
                    <div class="row row-space">
                        <table class="" style="width: 1200px;">
                            <tr style="
                             border: solid; height: 50px; text-align: center;
                         ">
                                <th scope="col"></th>
                                <th scope="col">Có</th>
                                <th scope="col">Không</th>
                            </tr>
                            <tr style="
                             border: solid; height: 30px; text-align: center;
                         ">
                                <td>Đến trang trại chăn nuôi / chợ buôn bán động vật sống / cơ sở giết mổ động vật / tiếp xúc động vật</td>
                                <td>
                                    <form:radiobutton class="form-check-input" path="tiepXucNgoai" value="true"/>
                                </td>
                                <td>
                                    <form:radiobutton class="form-check-input" path="tiepXucNgoai" value="false"/>
                                </td>
                            </tr>
                            <tr style="
                             border: solid; height: 30px; text-align: center;
                         ">
                                <td>Tiếp xúc gần với người mắc bệnh viêm đường hô hấp do nCoV</td>
                                <td>
                                    <form:radiobutton class="form-check-input" path="tiepXucNguoi" value="true"/>
                                </td>
                                <td>
                                    <form:radiobutton class="form-check-input" path="tiepXucNguoi" value="false"/>
                                </td>
                            </tr>
                        </table>
                    </div>
                    <br><br>
                    <div class="p-t-15" style="
                        text-align: center;
                    ">
                        <button class="btn btn--radius-2 btn--blue" type="submit">Submit</button>
                    </div>
                </form:form>
            </div>
        </div>
    </div>
</div>

<!-- Jquery JS-->
<link rel="stylesheet" href="<c:url value="/resources/vendor/jquery/jquery.min.js"/>">

<!-- Vendor JS-->
<link rel="stylesheet" href="<c:url value="/resources/vendor/select2/select2.min.js"/>">
<link rel="stylesheet" href="<c:url value="/resources/vendor/datepicker/moment.min.js"/>">
<link rel="stylesheet" href="<c:url value="/resources/vendor/datepicker/daterangepicker.js"/>">

<!-- Main JS-->
<link rel="stylesheet" href="<c:url value="/resources/js/global.js"/>">

</body><!-- This templates was made by Colorlib (https://colorlib.com) -->

<script>
    var Days = [31,28,31,30,31,30,31,31,30,31,30,31];// index => month [0-11]
    $(document).ready(function(){
        var d = new Date();
        var option = '<option value="year">year</option>';
        var selectedYear = "year";
        for (var i=1900;i <= d.getFullYear();i++){// years start i
            option += '<option value="'+ i + '">' + i + '</option>';
        }
        $('#year').append(option);
        $('#year').val(selectedYear);
    });
</script>
</html>
<!-- end document-->