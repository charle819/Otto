package com.acme.otto.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.HashSet;
import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "permission")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PermissionEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "permission_seq")
  @SequenceGenerator(name = "permission_seq", sequenceName = "permission_sequence", allocationSize = 1)
  @Column(name = "permission_id")
  private Long permissionId;

  @Column(name = "name", unique = true, nullable = false)
  private String name;

  @Column(name = "description")
  private String description;

  @Column(name = "created_on", updatable = false, columnDefinition = "TIMESTAMP WITH TIMEZONE DEFAULT CURRENT_TIMESTAMP")
  @Temporal(TemporalType.TIMESTAMP)
  private OffsetDateTime createdOn;

  @Column(name = "updated_on", columnDefinition = "TIMESTAMP WITH TIMEZONE DEFAULT CURRENT_TIMESTAMP")
  @Temporal(TemporalType.TIMESTAMP)
  private OffsetDateTime updatedOn;

  @ManyToMany(mappedBy = "permissionEntities")
  private Set<RoleEntity> roleEntities = new HashSet<>();

  @PrePersist
  public void prePersist() {
    if (this.createdOn == null) {
      this.createdOn = OffsetDateTime.now(ZoneOffset.UTC);
    }
    this.updatedOn = OffsetDateTime.now(ZoneOffset.UTC);
  }

  @PreUpdate
  public void preUpdate() {
    this.updatedOn = OffsetDateTime.now(ZoneOffset.UTC);
  }
}
