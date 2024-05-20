package exercise9

import common.FileReader
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class EmployeeApiTest {
    private val employees = parseEmployees(FileReader.readFileInResources("exercise9/employees.csv"))
    private val employeeApi = newEmployeeApi(employees)

    @Test
    fun `test - find employee with the highest salary`() {
        assertEquals("Kevin Adams", employeeApi.findHighestPaidEmployee()?.name)
    }

    @Test
    fun `test - find employees by department`() {
        val departments = employees
            .map { it.department }
            .distinct()

        departments.forEach { department ->
            val actualEmployees = employeeApi.getEmployeesByDepartment(department)
                .sortedBy { it.name }
                .map { it.name }

            assertEquals(DEPARTMENT_TO_EMPLOYEE[department.name], actualEmployees)
        }
    }

    @Test
    fun `test - employees by salary range`() {
        val actualEmployees = employeeApi.getEmployeesBySalaryRange(60000..85000)
            .sortedBy { it.name }
            .map { it.name }

        assertEquals(EMPLOYEES_IN_SALARY_RANGE_60k_TO_85k.sorted(), actualEmployees)
    }

    @Test
    fun `test - calculate average salary by department`() {
        val departments = employees
            .map { it.department }
            .distinct()

        departments.forEach { department ->
            val actualAverageSalary = employeeApi.calculateAverageSalaryByDepartment(department)

            assertEquals(DEPARTMENT_NAME_TO_AVERAGE_SALARY[department.name], actualAverageSalary)
        }
    }

    @Test
    fun `test - find most common skill employees have`() {
        assertEquals("Kotlin", employeeApi.findMostCommonSkill())
    }

    companion object {
        private val DEPARTMENT_TO_EMPLOYEE = mapOf(
            "HR" to listOf(
                "Alice Rodriguez",
                "Alice Wilson",
                "Amy Adams",
                "Chris Adams",
                "David Taylor",
                "Emily Doe",
                "Emily Smith",
                "Emily Wang",
                "Jane Johnson",
                "Jane Lee",
                "Jane Rodriguez",
                "Jane Towns",
                "John Que",
                "Kevin Johnson",
                "Kevin Smith",
                "Sarah Lee",
                "Sarah Wilson"
            ),
            "Finance" to listOf(
                "Alice Lee",
                "Amy Doe",
                "Amy Wang",
                "Emily Adams",
                "Jane Davis",
                "Kevin Doe"
            ),
            "Engineering" to listOf(
                "Alice Adams",
                "Alice Doe",
                "Alice Smith",
                "Amy Rodriguez",
                "Amy Smith",
                "Amy Wilson",
                "Chris Rodriguez",
                "Chris Wang",
                "Chris Wilson",
                "David Doe",
                "David Smith",
                "David Wilson",
                "Emily Wilson",
                "John Wang",
                "Kevin Adams",
                "Kevin Lee",
                "Kevin Rodriguez",
                "Michael Johnson",
                "Michael Lee"
            ),
            "Marketing" to listOf(
                "Alice Brown",
                "Amy Taylor",
                "Chris Doe",
                "Chris Taylor",
                "Jane Brown",
                "Jane Doe",
                "John Smith",
                "Kevin Taylor"
            )
        )

        private val EMPLOYEES_IN_SALARY_RANGE_60k_TO_85k = listOf(
            "Kevin Adams",
            "Chris Wang",
            "Alice Smith",
            "Alice Adams",
            "Kevin Lee",
            "Amy Wang",
            "Alice Lee",
            "Jane Davis",
            "Amy Wilson",
            "David Doe",
            "David Wilson",
            "Chris Wilson",
            "Emily Adams",
            "Kevin Rodriguez",
            "Kevin Doe",
            "Michael Lee",
            "Emily Wilson",
            "David Smith",
            "Amy Rodriguez",
            "Chris Rodriguez",
            "Emily Doe",
            "Kevin Johnson",
            "John Wang",
        )

        private val DEPARTMENT_NAME_TO_AVERAGE_SALARY: Map<String, Double> = mapOf(
            "HR" to 54986.58823529412,
            "Finance" to 67160.33333333333,
            "Engineering" to 66920.73684210527,
            "Marketing" to 53745.125,
        )
    }
}