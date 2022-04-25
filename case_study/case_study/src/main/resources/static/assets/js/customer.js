let idUpdate = 0;

function sendIdUpdate(id) {
    idUpdate = id;
    $.ajax({
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        type: "GET", //GET, DELETE, PUT...
        url: "http://localhost:8080/customerRestful/detail/" + idUpdate,
        success: function (resultSuccess) {
            let customer = resultSuccess.data;
            $('#customerCodeUpdate').val(customer.customerCode);

            $('#customerNameUpdate').val(customer.customerName);

            $('#customerBirthdayUpdate').val(customer.customerBirthday);

            if (customer.customerGender == 0) {
                $("#femaleUpdate").prop("checked", true);
            } else {
                $("#maleUpdate").prop("checked", true);
            }

            $('#customerIdCardUpdate').val(customer.customerIdCard);

            $('#customerPhoneUpdate').val(customer.customerPhone);

            $('#customerEmailUpdate').val(customer.customerEmail);

            $('#customerAddressUpdate').val(customer.customerAddress);

            $('#customerTypeIdUpdate').val(customer.customerType.customerTypeId);
        },
        error: function (resultError) {
            alert("Not found")
        }
    });
}


let idDelete = 0;

function sendIdDelete(id) {
    idDelete = id;
    $.ajax({
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        type: "GET", //GET, DELETE, PUT...
        url: "http://localhost:8080/customerRestful/detail/" + idDelete,
        success: function (resultSuccess) {
            let customer = resultSuccess.data;
            $('#customerCodeDelete').val(customer.customerCode);

            $('#customerNameDelete').val(customer.customerName);
        },
        error: function (resultError) {
            alert("Not found")
        }
    });
}

function submitSortForm() {
    document.getElementById("sortForm").submit();
}

$(document).ready(function () {

    // CREATE MODAL
    $('#btn-create').click(function () {

        let customerCode = $('#customerCode').val();

        let customerName = $('#customerName').val();

        let customerBirthday = $('#customerBirthday').val();

        let customerGender = $("input[type='radio'][name='customerGender']:checked").val();

        let customerIdCard = $('#customerIdCard').val();

        let customerPhone = $('#customerPhone').val();

        let customerEmail = $('#customerEmail').val();

        let customerAddress = $('#customerAddress').val();

        let customerType = $('#customerTypeId').val();

        let customerObj = {
            customerCode: customerCode,
            customerName: customerName,
            customerBirthday: customerBirthday,
            customerGender: customerGender,
            customerIdCard: customerIdCard,
            customerPhone: customerPhone,
            customerEmail: customerEmail,
            customerAddress: customerAddress,
            customerType: {
                customerTypeId: customerType
            }
        }
        console.log(customerObj);

        $.ajax({
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            type: "POST", //GET, DELETE, PUT...
            url: "http://localhost:8080/customerRestful/create",
            data: JSON.stringify(customerObj),
            success: function (resultSuccess) {
                $('#createForm').submit();
                $('#msg').text('Create successfully!');
                $('#createModal').modal('hide');
            },
            error: function (resultError) {
                console.log(resultError.responseJSON.errorMap);
                let customerCodeError = "";
                let customerNameError = "";
                let customerBirthdayError = "";
                let customerIdCardError = "";
                let customerPhoneError = "";
                let customerEmailError = "";
                let customerAddressError = "";

                if (resultError.responseJSON.errorMap.customerCode) {
                    customerCodeError = resultError.responseJSON.errorMap.customerCode;
                }
                if (resultError.responseJSON.errorMap.customerName) {
                    customerNameError = resultError.responseJSON.errorMap.customerName;
                }
                if (resultError.responseJSON.errorMap.customerBirthday) {
                    customerBirthdayError = resultError.responseJSON.errorMap.customerBirthday
                }
                if (resultError.responseJSON.errorMap.customerIdCard) {
                    customerIdCardError = resultError.responseJSON.errorMap.customerIdCard
                }
                if (resultError.responseJSON.errorMap.customerPhone) {
                    customerPhoneError = resultError.responseJSON.errorMap.customerPhone
                }
                if (resultError.responseJSON.errorMap.customerEmail) {
                    customerEmailError = resultError.responseJSON.errorMap.customerEmail
                }
                if (resultError.responseJSON.errorMap.customerAddress) {
                    customerAddressError = resultError.responseJSON.errorMap.customerAddress
                }
                $('#customerCodeError').text(customerCodeError);
                $('#customerNameError').text(customerNameError);
                $('#customerBirthdayError').text(customerBirthdayError);
                $('#customerIdCardError').text(customerIdCardError);
                $('#customerPhoneError').text(customerPhoneError);
                $('#customerEmailError').text(customerEmailError);
                $('#customerAddressError').text(customerAddressError);
            }
        });
    });

    // UPDATE MODAL
    $('#btn-update').click(function () {

        let customerCode = $('#customerCodeUpdate').val();

        let customerName = $('#customerNameUpdate').val();

        let customerBirthday = $('#customerBirthdayUpdate').val();

        let customerGender = $("input[type='radio'][name='customerGenderUpdate']:checked").val();

        let customerIdCard = $('#customerIdCardUpdate').val();

        let customerPhone = $('#customerPhoneUpdate').val();

        let customerEmail = $('#customerEmailUpdate').val();

        let customerAddress = $('#customerAddressUpdate').val();

        let customerType = $('#customerTypeIdUpdate').val();

        let customerObj = {
            customerCode: customerCode,
            customerName: customerName,
            customerBirthday: customerBirthday,
            customerGender: customerGender,
            customerIdCard: customerIdCard,
            customerPhone: customerPhone,
            customerEmail: customerEmail,
            customerAddress: customerAddress,
            customerType: {
                customerTypeId: customerType
            }
        }

        $.ajax({
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            type: "PATCH", //GET, DELETE, PUT...
            data: JSON.stringify(customerObj),
            url: "http://localhost:8080/customerRestful/update/" + idUpdate,
            success: function (resultSuccess) {
                $('#btn-update').submit();
                $('#msg').text('Update successfully!');

                //CHU Y: phai dat link import jquery, proper truoc boostrap js, truoc boostrap css.
                $('#updateModal').modal('hide');
                window.location.reload();
            },
            error: function (resultError) {
                console.log(resultError.responseJSON.errorMap);
                let customerCodeError = "";
                let customerNameError = "";
                let customerBirthdayError = "";
                let customerIdCardError = "";
                let customerPhoneError = "";
                let customerEmailError = "";
                let customerAddressError = "";

                if (resultError.responseJSON.errorMap.customerCode) {
                    customerCodeError = resultError.responseJSON.errorMap.customerCode;
                }
                if (resultError.responseJSON.errorMap.customerName) {
                    customerNameError = resultError.responseJSON.errorMap.customerName;
                }
                if (resultError.responseJSON.errorMap.customerBirthday) {
                    customerBirthdayError = resultError.responseJSON.errorMap.customerBirthday
                }
                if (resultError.responseJSON.errorMap.customerIdCard) {
                    customerIdCardError = resultError.responseJSON.errorMap.customerIdCard
                }
                if (resultError.responseJSON.errorMap.customerPhone) {
                    customerPhoneError = resultError.responseJSON.errorMap.customerPhone
                }
                if (resultError.responseJSON.errorMap.customerEmail) {
                    customerEmailError = resultError.responseJSON.errorMap.customerEmail
                }
                if (resultError.responseJSON.errorMap.customerAddress) {
                    customerAddressError = resultError.responseJSON.errorMap.customerAddress
                }
                $('#customerCodeUpdateError').text(customerCodeError);
                $('#customerNameUpdateError').text(customerNameError);
                $('#customerBirthdayUpdateError').text(customerBirthdayError);
                $('#customerIdCardUpdateError').text(customerIdCardError);
                $('#customerPhoneUpdateError').text(customerPhoneError);
                $('#customerEmailUpdateError').text(customerEmailError);
                $('#customerAddressUpdateError').text(customerAddressError);
            }
        });
    });

    // DELETE MODAL
    $('#btn-delete').click(function () {
        $.ajax({
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            type: "PATCH", //GET, DELETE, PUT...
            data: JSON.stringify({}),
            url: "http://localhost:8080/customerRestful/delete/" + idDelete,
            success: function (resultSuccess) {
                $('#msg').text('Delete successfully!');

                //CHU Y: phai dat link import jquery, proper truoc boostrap js, truoc boostrap css.
                $('#deleteModal').modal('hide');
                window.location.reload();
            },
            error: function (resultError) {
                alert("He thong dang bao tri");
            }
        });
    });

});