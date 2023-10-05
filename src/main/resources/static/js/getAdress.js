document.getElementById("all_emp_adress").addEventListener("click", function () {
    window.location.href = "/emp/adress";
});

$(document).ready(function () {
    $('#acc_emp_adress').click(function () {
        var dept_num = 10; // '10'은 회계 부서를 나타내며, 1은 페이지 번호입니다.
        var pageNo = 1;

        $.ajax({
            type: 'GET',
            url: '/emp/adress?dept_num=' + dept_num + '&pageNo=' + pageNo,
            success: function (data) {
                if (data && data.empAdressDtos && data.empAdressDtos.length) {
                    let tbody = $('#employeeTable tbody');
                    tbody.empty();
                    for (let i = 0; i < data.empAdressDtos.length; i++) {
                        let employee = data.empAdressDtos[i];
                        let row = $('<tr>');
                        row.append($('<td>').text(employee.empName));
                        row.append($('<td>').text(employee.gradeName));
                        row.append($('<td>').text(employee.deptName));
                        row.append($('<td>').text(employee.empEmail));
                        tbody.append(row);
                    }
                    console.log(data);

                    // 페이지 네비게이션을 변경하지 않고 데이터만 업데이트합니다.
                } else {
                    console.error('데이터가 유효하지 않습니다.');
                }
            },
            error: function () {
                alert('데이터를 불러오는 중 에러가 발생했습니다.');
            }
        });
    });
});

