define(function () {
    return {
        view: "datatable",
        columns: [
            {id: "employeeId"},
            {id: "FirstName"},
            {id: "lastName"},
            {id: "departmentId"},
            {id: "jobTitle"},
            {id: "gender"},
            {id: "dateOfBirth"}
        ],
        uri: '/api/employees',
        autoheight: true,
        autowidth: true
    }
})