## Employee Portal Task

An entry CSV file [employees.csv](https://github.com/vuksa/kotlin-programming-language-course/blob/main/src/main/resources/exercise9/employees.csv) contains the list of employees with the following columns:

- Employee ID - the id of an employee
- Name - name of an employee
- Department - department in which employee works
- Salary - salary of an employee
- Skills - skill sets of an employee

### Task 1: Create entity classes. 
#### Points: 10

In [exercise9/models.kt](models.kt) file create the following classes:

- Create `Department` **data** class which has `departmentName: String` as a primary constructor property.
- Create `EmployeeID` **inline value** class which has `id: String` as a primary constructor property.
- Create `Employee` **data** class which has only a primary constructor with following properties:
  
  - `employeeID` of type `EmployeeID`
  - `name` of type `String`
  - `department` of type `Department`
  - `salary` of type `Int`
  - `skills` of type `List<String>`

### Task 2: Parse the entry CSV file [employees.csv](https://github.com/vuksa/kotlin-programming-language-course/blob/main/src/main/resources/exercise9/employees.csv)
#### Points: 10

Implement `exercise9.EmployeesRunnerKt.parseEmployees` function to parse the entry CSV file [employees.csv](https://github.com/vuksa/kotlin-programming-language-course/blob/main/src/main/resources/exercise9/employees.csv).
As a result `exercise9.EmployeesRunnerKt.parseEmployees` function should return the list of employees (`List<Employee>`) listed in CSV file
with their corresponding ids, names, departments, salaries and skills.

Columns in CSV file are split by comma character (`,`), and skills in skills colum are split by pipe character (`|`).

### Task 3: Create class `EmployeesPortal` and implement functions of `EmployeesApi` interface
#### Total points: 40

### Task 3.1
#### Points: 4
Create class `EmployeesPortal` which has primary constructor with a `employees: List<Employee>` parameter.
Class `EmployeesPortal` should implement `EmployeesApi` interface.
To complete tasks Task 3.2 - Task 3.5, implement overridden `EmployeesApi` interface methods 
in the `EmployeesPortal` class. 
***Note***: Leverage Kotlin APIs on Collections such as `map`, `filter`, `flatmap`, etc to implement your solution.
Implementing the solutions by not using these APIs will be accepted, but it will result in points deduction penalty.

### Task 3.2 Implement `EmployeeApi.getEmployeesByDepartment` function
#### Points: 4

`exercise9.EmployeeApi.getEmployeesByDepartment` function should return all employees that 
work for the provided `Department`. 

### Task 3.3 Implement `EmployeeApi.findHighestPaidEmployee` function
#### Points: 8

`exercise9.EmployeeApi.findHighestPaidEmployee` function should return the employee with the highest salary.


### Task 3.4 Implement `EmployeeApi.getEmployeesBySalaryRange` function
#### Points: 8

`exercise9.EmployeeApi.getEmployeesBySalaryRange` function should return all employees that have salary withing provided salary range.


### Task 3.5 Implement `EmployeeApi.calculateAverageSalaryByDepartment` function
#### Points: 8

`exercise9.EmployeeApi.calculateAverageSalaryByDepartment` should return an average salary in the department.
Average salary can be calculated by dividing total department's salary expense with the number of employees in the department.


### Task 3.5 Implement `EmployeeApi.findMostCommonSkills` function
#### Points: 8

`exercise9.EmployeeApi.findMostCommonSkills` should most common skill employees have.
The Most common skill is the skill mentioned the most times in the [employees.csv](https://github.com/vuksa/kotlin-programming-language-course/blob/main/src/main/resources/exercise9/employees.csv) file.

## Verifying results
To verify the correctness of the implementation, run the `Exercise 9/Run All Tests` run configuration from a drop-down menu
![Run tests via Exercise9/Run All Tests run config.](https://github.com/vuksa/kotlin-programming-language-course/blob/main/doc/verify-implementation-by-running-tests.png)