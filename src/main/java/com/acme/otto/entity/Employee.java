package com.acme.otto.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.List;
import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "employee")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Employee {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "employee_seq")
  @SequenceGenerator(name = "employee_seq", sequenceName = "employee_sequence", allocationSize = 1)
  @Column(name = "employee_id")
  private Long employeeId;

  @Column(name = "employee_code", nullable = false, unique = true)
  private String employeeCode;

  @Column(name = "password", nullable = false)
  private String password;

  @Column(name = "full_name", nullable = false)
  private String fullName;

  @Column(name = "email", nullable = false)
  private String email;

  @Column(name = "phone_number")
  private Integer phoneNumber;

  @Column(name = "is_active")
  private Boolean isActive = Boolean.TRUE;

  @Column(name = "created_on", updatable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
  @Temporal(TemporalType.TIMESTAMP)
  private LocalDateTime createdOn;

  @Column(name = "updated_on", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
  @Temporal(TemporalType.TIMESTAMP)
  private LocalDateTime updatedOn;

  @ManyToMany
  @JoinTable(
      name = "employee_roles",
      joinColumns = @JoinColumn(name = "employee_id"),
      inverseJoinColumns = @JoinColumn(name = "role_id")
  )
  private Set<Role> roles;

  @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<EmployeeSession> employeeSessions;

  @PrePersist
  public void prePersist() {
    if (this.createdOn == null) {
      this.createdOn = Instant.now().atZone(ZoneOffset.UTC).toLocalDateTime();
    }
    this.updatedOn = Instant.now().atZone(ZoneOffset.UTC).toLocalDateTime();
  }

  @PreUpdate
  public void preUpdate() {
    this.updatedOn = Instant.now().atZone(ZoneOffset.UTC).toLocalDateTime();
  }


}
