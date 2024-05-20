package exercise9

import common.FileReader

fun parseEmployees(employeesCSVLines: List<String>): List<Employee> {
    return employeesCSVLines.drop(1)
        .map { line ->
            val components = line.trim().split(",")
            Employee(
                employeeID = EmployeeID(components.first().trim()),
                name = components[1].trim(),
                department = Department(components[2].trim()),
                salary = components[3].trim().toInt(),
                skills = components[4].trim().split("|").map { it.trim() }
            )
        }
}

fun newEmployeeApi(employees: List<Employee>): EmployeeApi {
    return EmployeePortal(employees)
}

fun main() {
    val employeesCSVLines = FileReader.readFileInResources("exercise9/employees.csv")
    val employees = parseEmployees(employeesCSVLines)

    val employeeApi: EmployeeApi = newEmployeeApi(employees)
}
