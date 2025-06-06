package com.acme.otto.entity;

import com.acme.otto.entity.converter.StringToJsonConverter;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

@Entity
@Table(name = "meeting_room")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MeetingRoomEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "meeting_room_seq")
  @SequenceGenerator(name = "meeting_room_seq", sequenceName = "meeting_room_sequence", allocationSize = 1)
  @Column(name = "meeting_room_id")
  private Long meetingRoomId;

  @Column(name = "name", unique = true, nullable = false)
  private String name;

  @Column(name = "room_code", unique = true, nullable = false)
  private String roomCode;

  @Column(name = "description")
  private String description;

  @Column(name = "capacity", nullable = false)
  private Integer capacity;

  @Column(name = "location", nullable = false)
  private String location;

  @Column(name = "floor_number", nullable = false)
  private String floorNumber;

  @Column(name = "amenity", columnDefinition = "jsonb")
  @Convert(converter = StringToJsonConverter.class)
  @JdbcTypeCode(SqlTypes.JSON) // Hibernate specific conversion
  private List<String> amenity;

  @Column(name = "is_active")
  private Boolean isActive = Boolean.TRUE;

  @Column(name = "created_on", updatable = false, columnDefinition = "TIMESTAMP WITH TIMEZONE DEFAULT CURRENT_TIMESTAMP")
  @Temporal(TemporalType.TIMESTAMP)
  private OffsetDateTime createdOn;

  @Column(name = "updated_on", columnDefinition = "TIMESTAMP WITH TIMEZONE DEFAULT CURRENT_TIMESTAMP")
  @Temporal(TemporalType.TIMESTAMP)
  private OffsetDateTime updatedOn;

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
