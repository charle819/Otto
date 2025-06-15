package com.acme.otto.entity;

import com.acme.otto.enums.BookingStatus;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "meeting_room_booking")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MeetingRoomBookingEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "meeting_room_booking_seq")
  @SequenceGenerator(name = "meeting_room_booking_seq", sequenceName = "meeting_room_booking_sequence", allocationSize = 1)
  @Column(name = "meeting_room_booking_id")
  private Long meetingRoomBookingId;

  @Column(name = "meeting_title", nullable = false)
  private String meetingTitle;

  @Column(name = "description")
  private String description;

  @Column(name = "start_time", nullable = false, columnDefinition = "TIMESTAMP WITH TIMEZONE")
  private OffsetDateTime startTime;

  @Column(name = "end_time", nullable = false, columnDefinition = "TIMESTAMP WITH TIMEZONE")
  private OffsetDateTime endTime;

  @Column(name = "attendee_count", nullable = false)
  private Integer attendeeCount;

  @Enumerated(EnumType.STRING)
  @Column(name = "booking_status", nullable = false)
  private BookingStatus bookingStatus;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "meeting_room_id", nullable = false)
  private MeetingRoomEntity meetingRoomEntity;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "employee_id", nullable = false)
  private EmployeeEntity bookedBy;

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
