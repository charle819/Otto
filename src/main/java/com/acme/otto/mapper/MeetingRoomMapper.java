package com.acme.otto.mapper;

import com.acme.otto.entity.MeetingRoomEntity;
import com.acme.otto.entity.converter.StringToJsonConverter;
import com.acme.otto.model.MeetingRoom;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants.ComponentModel;
import org.mapstruct.Named;

@Mapper(componentModel = ComponentModel.SPRING)
public abstract class MeetingRoomMapper {

  @Mapping(target = "amenity", source = "amenities")
  public abstract MeetingRoomEntity toEntity(MeetingRoom meetingRoom);

  @Mapping(target = "amenities", source = "amenity")
  public abstract MeetingRoom fromEntity(MeetingRoomEntity meetingRoomEntity);

}
