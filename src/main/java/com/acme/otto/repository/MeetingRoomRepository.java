package com.acme.otto.repository;

import com.acme.otto.entity.MeetingRoomEntity;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MeetingRoomRepository extends JpaRepository<MeetingRoomEntity, Long> {

  boolean existsByNameOrRoomCode(String name , String roomCode);
}
