package com.acme.otto.entity;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import java.util.List;

@Converter
public class AmenityConverter implements AttributeConverter<List<String>, String> {


  private static final ObjectMapper mapper = new ObjectMapper();

  /**
   * Converts the value stored in the entity attribute into the data representation to be stored in
   * the database.
   *
   * @param amenities the entity attribute value to be converted
   * @return the converted data to be stored in the database column
   */
  @Override
  public String convertToDatabaseColumn(List<String> amenities) {
    try {
      return mapper.writeValueAsString(amenities);
    } catch (JsonProcessingException e) {
      throw new RuntimeException("Failed to convert amenities to json ", e);
    }
  }

  /**
   * Converts the data stored in the database column into the value to be stored in the entity
   * attribute. Note that it is the responsibility of the converter writer to specify the correct
   * <code>dbData</code> type for the corresponding column for use by the JDBC driver: i.e.,
   * persistence providers are not expected to do such type conversion.
   *
   * @param dbData the data from the database column to be converted
   * @return the converted value to be stored in the entity attribute
   */
  @Override
  public List<String> convertToEntityAttribute(String dbData) {

    try {
      return mapper.readValue(dbData, new TypeReference<List<String>>() {});
    } catch (JsonProcessingException e) {
      throw new RuntimeException("Failed to covert json to amenities ", e);
    }
  }
}
