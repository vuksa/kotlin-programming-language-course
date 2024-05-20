package exercise9

/**
 * Interface for interacting with employee data.
 */
interface EmployeeApi {

    /**
     * Finds and returns the employee with the highest salary.
     * @return The employee with the highest salary, or null if the list of employees is empty.
     */
    fun findHighestPaidEmployee(): Employee?

    /**
     * Retrieves the list of employees working in a specific department.
     * @param department The department for which to retrieve employees.
     * @return A list of employees working in the specified department.
     */
    fun getEmployeesByDepartment(department: Department): List<Employee>

    /**
     * Retrieves the list of employees with salaries within a specified range.
     * @param salaryRange The salary in the range.
     * @return A list of employees with salaries within the specified range.
     */
    fun getEmployeesBySalaryRange(salaryRange: IntRange): List<Employee>

    /**
     * Calculates and returns the average salary for a given department.
     * @param department The department for which to calculate the average salary.
     * @return The average salary for the specified department.
     */
    fun calculateAverageSalaryByDepartment(department: Department): Double

    /**
     * Finds and returns the most common skill across all employees.
     * @return the most common skills.
     */
    fun findMostCommonSkill(): String
}
