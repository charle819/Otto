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
import jakarta.persistence.UniqueConstraint;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "meeting_room_booking")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MeetingRoomBooking {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "meeting_room_booking_seq")
  @SequenceGenerator(name = "meeting_room_booking_seq", sequenceName = "meeting_room_booking_sequence", allocationSize = 1)
  @Column(name = "meeting_room_booking_id")
  private Long meetingRoomBookingId;

  @Column(name = "meeting_title", nullable = false)
  private String meetingTitle;

  @Column(name = "description")
  private String description;

  @Column(name = "start_time", nullable = false, columnDefinition = "TIMESTAMP")
  private LocalDateTime startTime;

  @Column(name = "end_time", nullable = false, columnDefinition = "TIMESTAMP")
  private LocalDateTime endTime;

  @Column(name = "attendee_count", nullable = false)
  private Integer attendeeCount;

  @Enumerated(EnumType.STRING)
  @Column(name = "booking_status", nullable = false)
  private BookingStatus bookingStatus;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "meeting_room_id", nullable = false)
  private MeetingRoom meetingRoom;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "employee_id", nullable = false)
  private Employee bookedBy;

  @Column(name = "created_on", updatable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
  @Temporal(TemporalType.TIMESTAMP)
  private LocalDateTime createdOn;

  @Column(name = "updated_on", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
  @Temporal(TemporalType.TIMESTAMP)
  private LocalDateTime updatedOn;

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
