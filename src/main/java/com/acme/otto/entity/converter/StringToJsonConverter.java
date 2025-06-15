package com.acme.otto.entity.converter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import java.util.ArrayList;
import java.util.List;

@Converter
public class StringToJsonConverter implements AttributeConverter<List<String>, String> {

  private static final ObjectMapper mapper = new ObjectMapper();

  @Override
  public String convertToDatabaseColumn(List<String> amenities) {
    try {
      return amenities == null ? "[]" : mapper.writeValueAsString(amenities);
    } catch (JsonProcessingException e) {
      throw new RuntimeException("Failed to convert amenities to json ", e);
    }
  }

  @Override
  public List<String> convertToEntityAttribute(String dbData) {

    try {
      return dbData == null ? new ArrayList<>()
          : mapper.readValue(dbData, new TypeReference<List<String>>() {});
    } catch (JsonProcessingException e) {
      throw new RuntimeException("Failed to covert json to amenities ", e);
    }
  }
}
