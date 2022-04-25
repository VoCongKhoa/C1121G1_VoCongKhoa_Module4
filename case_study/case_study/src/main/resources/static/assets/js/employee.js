let idUpdate = 0;

function sendIdUpdate(id) {
    idUpdate = id;
    $.ajax({
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        type: "GET", //GET, DELETE, PUT...
        url: "http://localhost:8080/employeeRestful/detail/" + idUpdate,
        success: function (resultSuccess) {
            let employee = resultSuccess.data;

            $('#employeeNameUpdate').val(employee.employeeName);

            $('#employeeBirthdayUpdate').val(employee.employeeBirthday);

            $('#employeeSalaryUpdate').val(employee.employeeSalary);

            $('#employeeIdCardUpdate').val(employee.employeeIdCard);

            $('#employeePhoneUpdate').val(employee.employeePhone);

            $('#employeeEmailUpdate').val(employee.employeeEmail);

            $('#employeeAddressUpdate').val(employee.employeeAddress);

            $('#positionUpdate').val(employee.position.positionId);

            $('#divisionUpdate').val(employee.division.divisionId);

            $('#educationDegreeUpdate').val(employee.educationDegree.educationDegreeId);
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
        url: "http://localhost:8080/employeeRestful/detail/" + idDelete,
        success: function (resultSuccess) {
            let employee = resultSuccess.data;
            $('#employeeNameDelete').val(employee.employeeName);
            $('#employeeBirthdayDelete').val(employee.employeeBirthday);
            $('#employeeEmailDelete').val(employee.employeeEmail);
            $('#divisionDelete').val(employee.division.divisionName);
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

        let employeeName = $('#employeeNameCreate').val();

        let employeeBirthday = $('#employeeBirthdayCreate').val();

        let employeeIdCard = $('#employeeIdCardCreate').val();

        let employeeSalary = $('#employeeSalaryCreate').val();

        let employeePhone = $('#employeePhoneCreate').val();

        let employeeEmail = $('#employeeEmailCreate').val();

        let employeeAddress = $('#employeeAddressCreate').val();

        let positionId = $('#positionCreate').val();

        let divisionId = $('#divisionCreate').val();

        let educationDegreeId = $('#educationDegreeCreate').val();

        let employeeObj = {
            employeeName: employeeName,
            employeeBirthday: employeeBirthday,
            employeeIdCard: employeeIdCard,
            employeeSalary: employeeSalary,
            employeePhone: employeePhone,
            employeeEmail: employeeEmail,
            employeeAddress: employeeAddress,
            position: {
                positionId: positionId
            },
            educationDegree: {
                educationDegreeId: educationDegreeId
            },
            division: {
                divisionId: divisionId
            },
        }
        console.log(employeeObj);

        $.ajax({
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            type: "POST", //GET, DELETE, PUT...
            url: "http://localhost:8080/employeeRestful/create",
            data: JSON.stringify(employeeObj),
            success: function (resultSuccess) {
                $('#createForm').submit();
                $('#msg').text('Create successfully!');
                $('#createModal').modal('hide');
            },
            error: function (resultError) {
                let employeeNameError = "";
                let employeeBirthdayError = "";
                let employeeIdCardError = "";
                let employeeSalaryError = "";
                let employeePhoneError = "";
                let employeeEmailError = "";

                if (resultError.responseJSON.errorMap.employeeSalary) {
                    employeeSalaryError = resultError.responseJSON.errorMap.employeeSalary;
                }
                if (resultError.responseJSON.errorMap.employeeName) {
                    employeeNameError = resultError.responseJSON.errorMap.employeeName;
                }
                if (resultError.responseJSON.errorMap.employeeBirthday) {
                    employeeBirthdayError = resultError.responseJSON.errorMap.employeeBirthday
                }
                if (resultError.responseJSON.errorMap.employeeIdCard) {
                    employeeIdCardError = resultError.responseJSON.errorMap.employeeIdCard
                }
                if (resultError.responseJSON.errorMap.employeePhone) {
                    employeePhoneError = resultError.responseJSON.errorMap.employeePhone
                }
                if (resultError.responseJSON.errorMap.employeeEmail) {
                    employeeEmailError = resultError.responseJSON.errorMap.employeeEmail
                }

                $('#employeeNameCreateError').text(employeeNameError);
                $('#employeeBirthdayCreateError').text(employeeBirthdayError);
                $('#employeeIdCardCreateError').text(employeeIdCardError);
                $('#employeePhoneCreateError').text(employeePhoneError);
                $('#employeeEmailCreateError').text(employeeEmailError);
                $('#employeeSalaryCreateError').text(employeeSalaryError);
            }
        });
    });

    // UPDATE MODAL
    $('#btn-update').click(function () {

        let employeeName = $('#employeeNameUpdate').val();

        let employeeBirthday = $('#employeeBirthdayUpdate').val();

        let employeeIdCard = $('#employeeIdCardUpdate').val();

        let employeeSalary = $('#employeeSalaryUpdate').val();

        let employeePhone = $('#employeePhoneUpdate').val();

        let employeeEmail = $('#employeeEmailUpdate').val();

        let employeeAddress = $('#employeeAddressUpdate').val();

        let positionId = $('#positionUpdate').val();

        let divisionId = $('#divisionUpdate').val();

        let educationDegreeId = $('#educationDegreeUpdate').val();

        let employeeObj = {
            employeeName: employeeName,
            employeeBirthday: employeeBirthday,
            employeeIdCard: employeeIdCard,
            employeeSalary: employeeSalary,
            employeePhone: employeePhone,
            employeeEmail: employeeEmail,
            employeeAddress: employeeAddress,
            position: {
                positionId: positionId
            },
            educationDegree: {
                educationDegreeId: educationDegreeId
            },
            division: {
                divisionId: divisionId
            },
        }
        console.log(employeeObj);

        $.ajax({
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            type: "PATCH", //GET, DELETE, PUT...
            data: JSON.stringify(employeeObj),
            url: "http://localhost:8080/employeeRestful/update/" + idUpdate,
            success: function (resultSuccess) {
                $('#btn-update').submit();
                $('#msg').text('Update successfully!');

                //CHU Y: phai dat link import jquery, proper truoc boostrap js, truoc boostrap css.
                $('#updateModal').modal('hide');
                window.location.reload();
            },
            error: function (resultError) {
                let employeeNameError = "";
                let employeeBirthdayError = "";
                let employeeIdCardError = "";
                let employeeSalaryError = "";
                let employeePhoneError = "";
                let employeeEmailError = "";

                console.log(resultError.responseJSON)
                if (resultError.responseJSON.errorMap.employeeSalary) {
                    employeeSalaryError = resultError.responseJSON.errorMap.employeeSalary;
                }
                if (resultError.responseJSON.errorMap.employeeName) {
                    employeeNameError = resultError.responseJSON.errorMap.employeeName;
                }
                if (resultError.responseJSON.errorMap.employeeBirthday) {
                    employeeBirthdayError = resultError.responseJSON.errorMap.employeeBirthday
                }
                if (resultError.responseJSON.errorMap.employeeIdCard) {
                    employeeIdCardError = resultError.responseJSON.errorMap.employeeIdCard
                }
                if (resultError.responseJSON.errorMap.employeePhone) {
                    employeePhoneError = resultError.responseJSON.errorMap.employeePhone
                }
                if (resultError.responseJSON.errorMap.employeeEmail) {
                    employeeEmailError = resultError.responseJSON.errorMap.employeeEmail
                }

                $('#employeeNameUpdateError').text(employeeNameError);
                $('#employeeBirthdayUpdateError').text(employeeBirthdayError);
                $('#employeeIdCardUpdateError').text(employeeIdCardError);
                $('#employeePhoneUpdateError').text(employeePhoneError);
                $('#employeeEmailUpdateError').text(employeeEmailError);
                $('#employeeSalaryUpdateError').text(employeeSalaryError);
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
            url: "http://localhost:8080/employeeRestful/delete/" + idDelete,
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