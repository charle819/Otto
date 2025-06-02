package com.acme.otto.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "employee_session")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeSession {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "employee_session_seq")
  @SequenceGenerator(name = "employee_session_seq", sequenceName = "employee_session_sequence", allocationSize = 1)
  @Column(name = "employee_session_id")
  private Long employeeSessionId;

  @Column(name = "created_on", updatable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
  @Temporal(TemporalType.TIMESTAMP)
  private LocalDateTime createdOn;

  @Column(name = "updated_on", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
  @Temporal(TemporalType.TIMESTAMP)
  private LocalDateTime updatedOn;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "employee_id", nullable = false)
  private Employee employee;

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
