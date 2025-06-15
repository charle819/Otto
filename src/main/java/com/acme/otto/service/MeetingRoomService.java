package com.acme.otto.service;

import com.acme.otto.entity.MeetingRoomEntity;
import com.acme.otto.mapper.MeetingRoomMapper;
import com.acme.otto.model.MeetingRoom;
import com.acme.otto.repository.MeetingRoomRepository;
import jakarta.transaction.Transactional;
import java.util.List;
import java.util.NoSuchElementException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MeetingRoomService {

  private final MeetingRoomRepository meetingRoomRepository;
  private final MeetingRoomMapper meetingRoomMapper;

  @Transactional
  public MeetingRoom create(MeetingRoom meetingRoom) {

    if (meetingRoomRepository.existsByNameOrRoomCode(meetingRoom.getName(),
        meetingRoom.getRoomCode())) {
      throw new RuntimeException(
          "Meeting room with either name : " + meetingRoom.getName() + " or roomCode : "
              + meetingRoom.getRoomCode() + " already exists");
    }

    MeetingRoomEntity meetingRoomEntity = meetingRoomMapper.toEntity(meetingRoom);
    meetingRoomEntity.setAmenity(List.of("Whiteboard", "TV"));
    meetingRoomEntity = meetingRoomRepository.save(meetingRoomEntity);
    return meetingRoomMapper.fromEntity(meetingRoomEntity);
  }

  public MeetingRoom getById(Long meetingRoomId) {

    MeetingRoomEntity meetingRoomEntity = meetingRoomRepository.findById(meetingRoomId)
        .orElseThrow(() -> new NoSuchElementException(
            "No Meeting room with Id : " + meetingRoomId + " found"));

    return meetingRoomMapper.fromEntity(meetingRoomEntity);
  }

}
