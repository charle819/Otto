package com.acme.otto.controller;

import com.acme.otto.model.Employee;
import com.acme.otto.model.LoginRequest;
import com.acme.otto.model.LoginResponse;
import com.acme.otto.model.LogoutRequest;
import com.acme.otto.model.PaginatedEmployeeResponse;
import com.acme.otto.service.EmployeeService;
import com.acme.otto.service.JwtService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/otto")
public class EmployeeController implements EmployeeApi {


  private final EmployeeService employeeService;
  private final AuthenticationManager authenticationManager;
  private final JwtService jwtService;

  /**
   * POST /employee/login : Login Employee
   *
   * @param loginRequest (required)
   * @return Employee login successfully (status code 200) or Unexpected error (status code 200)
   */
  @Override
  public ResponseEntity<LoginResponse> loginEmployee(LoginRequest loginRequest) {

    Authentication authentication = authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(loginRequest.getEmployeeCode(),
            loginRequest.getPassword()));

    UserDetails userDetails = (UserDetails) authentication.getPrincipal();
    String accessToken = jwtService.generateToken(userDetails);
    String refreshToken = jwtService.generateRefreshToken(userDetails);

    return ResponseEntity.ok(LoginResponse.builder()
        .jwtToken(accessToken)
        .refreshToken(refreshToken)
        .build());
  }


  /**
   * GET /employee/{employee_id} : Get Employee Get Employee
   *
   * @param employeeId Id of Employee (required)
   * @return Get employee (status code 200) or Unexpected error (status code 200)
   */
  @PreAuthorize("hasAuthority('ALL_EMPLOYEE_ACTION_ACCESS')")
  @Override
  public ResponseEntity<Employee> getEmployee(Long employeeId) {
    return ResponseEntity.ok(employeeService.getById(employeeId));
  }


  /**
   * POST /employee : Create Employee Create new Employee
   *
   * @param employee (optional)
   * @return New Created Employee (status code 201) or Unexpected error (status code 200)
   */
  @Override
  public ResponseEntity<Employee> createEmployee(Employee employee) {
    return ResponseEntity.status(HttpStatus.CREATED.value())
        .body(employeeService.create(employee));
  }

  /**
   * DELETE /employee/{employee_id} : Delete Employee Delete Employee
   *
   * @param employeeId Id of Employee (required)
   * @return Successful operation (status code 200) or Unexpected error (status code 200)
   */
  @Override
  public ResponseEntity<Void> deleteEmployee(Long employeeId) {
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
  public ResponseEntity<Employee> updateEmployee(Long employeeId, Employee employee) {
    return null;
  }
}
