package com.acme.otto.controller;

import com.acme.otto.model.Employee;
import com.acme.otto.model.LoginRequest;
import com.acme.otto.model.LoginResponse;
import com.acme.otto.model.LogoutRequest;
import com.acme.otto.model.PaginatedEmployeeResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class EmployeeController implements EmployeeApi {

  /**
   * POST /employee : Create Employee Create new Employee
   *
   * @param employee (optional)
   * @return New Created Employee (status code 200) or Unexpected error (status code 200)
   */
  @Override
  public ResponseEntity<Employee> createEmployee(Employee employee) {
    return null;
  }

  /**
   * DELETE /employee/{employee_id} : Delete Employee Delete Employee
   *
   * @param employeeId Id of Employee (required)
   * @return Successful operation (status code 200) or Unexpected error (status code 200)
   */
  @Override
  public ResponseEntity<Void> deleteEmployee(String employeeId) {
    return null;
  }

  /**
   * POST /employee/logout : Logout Employee
   *
   * @param logoutRequest (required)
   * @return Logout successful (status code 200) or Unexpected error (status code 200)
   */
  @Override
  public ResponseEntity<Void> employeeLogoutPost(LogoutRequest logoutRequest) {
    return null;
  }

  /**
   * GET /employee/{employee_id} : Get Employee Get Employee
   *
   * @param employeeId Id of Employee (required)
   * @return Get employee (status code 200) or Unexpected error (status code 200)
   */
  @Override
  public ResponseEntity<Employee> getEmployee(String employeeId) {
    return null;
  }

  /**
   * POST /employee/login : Login Employee Login Employee
   *
   * @param loginRequest (required)
   * @return Employee login successfull (status code 200) or Unexpected error (status code 200)
   */
  @Override
  public ResponseEntity<LoginResponse> loginEmployee(LoginRequest loginRequest) {
    return null;
  }

  /**
   * GET /employee : Search Employees Search Employees
   *
   * @param offset       Pagination offset (optional, default to 0)
   * @param limit        Pagination limit (optional, default to 30)
   * @param baseLocation Filter by base location (optional)
   * @param name         Filter by employee name (optional)
   * @param email        Filter by email (optional)
   * @param isActive     Filter by active status (true/false) (optional)
   * @return Paginated employee list (status code 200) or Unexpected error (status code 200)
   */
  @Override
  public ResponseEntity<PaginatedEmployeeResponse> searchEmployee(Integer offset, Integer limit,
      String baseLocation, String name, String email, Boolean isActive) {
    return null;
  }

  /**
   * PUT /employee/{employee_id} : Update Employee Update Employee
   *
   * @param employeeId Id of Employee (required)
   * @param employee   (optional)
   * @return Updated Employee (status code 200) or Unexpected error (status code 200)
   */
  @Override
  public ResponseEntity<Employee> updateEmployee(String employeeId, Employee employee) {
    return null;
  }
}
