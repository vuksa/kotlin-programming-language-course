package exercise9

import common.FileReader

fun parseEmployees(employeesCSVLines: List<String>): List<Employee> {
    TODO("Implement parsing of employees")
}

fun newEmployeeApi(employees: List<Employee>): EmployeeApi {
    TODO("Instantiate EmployeeApi")
}

fun main() {
    val employeesCSVLines = FileReader.readFileInResources("exercise9/employees.csv")
    val employees = parseEmployees(employeesCSVLines)

    val employeeApi : EmployeeApi = newEmployeeApi(employees)
}
