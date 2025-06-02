package com.acme.otto.controller;

import com.acme.otto.model.BookMeetingRoom;
import com.acme.otto.model.MeetingRoom;
import com.acme.otto.model.PaginatedMeetingRoomResponse;
import java.time.OffsetDateTime;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class MeetingRoomController implements MeetingRoomApi{

  /**
   * POST /meeting-room/{meeting_room_id}/book : Book a meeting room
   *
   * @param meetingRoomId   Id of meeting room (required)
   * @param bookMeetingRoom (optional)
   * @return Successful operation (status code 200) or Unexpected error (status code 200)
   */
  @Override
  public ResponseEntity<Void> bookMeetingRoom(String meetingRoomId,
      BookMeetingRoom bookMeetingRoom) {
    return null;
  }

  /**
   * POST /meeting-room : Create Meeting Room
   *
   * @param meetingRoom (optional)
   * @return New created meeting room (status code 201) or Unexpected error (status code 200)
   */
  @Override
  public ResponseEntity<MeetingRoom> createMeetingRoom(MeetingRoom meetingRoom) {
    return null;
  }

  /**
   * DELETE /meeting-room/{meeting_room_id} : Delete Meeting room
   *
   * @param meetingRoomId Id of meeting room (required)
   * @return Successful operation (status code 200) or Unexpected error (status code 200)
   */
  @Override
  public ResponseEntity<Void> deleteMeetingRoom(String meetingRoomId) {
    return null;
  }

  /**
   * GET /meeting-room/{meeting_room_id} : Get Meeting room
   *
   * @param meetingRoomId Id of meeting room (required)
   * @return Get meeting room (status code 201) or Unexpected error (status code 200)
   */
  @Override
  public ResponseEntity<MeetingRoom> getMeetingRoom(String meetingRoomId) {
    return null;
  }

  /**
   * GET /meeting-room : Search Meeting Room
   *
   * @param offset     Pagination offset (optional, default to 0)
   * @param limit      Number of records per page (optional, default to 30)
   * @param roomStatus Filter by room status (e.g., available, occupied) (optional)
   * @param amenities  Filter by amenities (comma-separated list) (optional)
   * @param startTime  Filter by booking start time (optional)
   * @param endTime    Filter by booking end time (optional)
   * @param employeeId Filter by employee who booked (optional)
   * @return Paginated Meeting room list (status code 200) or Unexpected error (status code 200)
   */
  @Override
  public ResponseEntity<PaginatedMeetingRoomResponse> searchMeetingRoom(Integer offset,
      Integer limit, String roomStatus, List<String> amenities, OffsetDateTime startTime,
      OffsetDateTime endTime, Integer employeeId) {
    return null;
  }

  /**
   * PATCH /meeting-room/{meeting_room_id}/book : Update a booked meeting room
   *
   * @param meetingRoomId   Id of meeting room (required)
   * @param bookMeetingRoom (optional)
   * @return Successful operation (status code 200) or Unexpected error (status code 200)
   */
  @Override
  public ResponseEntity<Void> updateBookedMeetingRoom(String meetingRoomId,
      BookMeetingRoom bookMeetingRoom) {
    return null;
  }

  /**
   * PUT /meeting-room/{meeting_room_id} : Update Meeting room
   *
   * @param meetingRoomId Id of meeting room (required)
   * @param meetingRoom   (optional)
   * @return New created meeting room (status code 201) or Unexpected error (status code 200)
   */
  @Override
  public ResponseEntity<MeetingRoom> updateMeetingRoom(String meetingRoomId,
      MeetingRoom meetingRoom) {
    return null;
  }
}
