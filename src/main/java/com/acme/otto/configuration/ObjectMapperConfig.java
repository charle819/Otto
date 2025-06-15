package com.acme.otto.configuration;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import java.time.OffsetDateTime;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ObjectMapperConfig {

  @Bean
  public ObjectMapper getObjectMapper() {

    ObjectMapper mapper = new ObjectMapper();

    // 1. Register JavaTimeModule to handle java.time.* types
    mapper.registerModule(new JavaTimeModule());

    // 2. Disable writing dates as timestamps (already default for JavaTimeModule, but good practice)
    mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

    // 3. Optional: Exclude null values from JSON output globally
    mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);

    // 4. Crucial: Configure OffsetDateTime serialization/deserialization globally
    //    This uses the pattern "yyyy-MM-dd'T'HH:mm:ssXXX"
    //    'XXX' is for the offset (e.g., +05:30, Z)
    //    We specify the shape as STRING to ensure it's not a timestamp (which we disabled above too)
    mapper.configOverride(OffsetDateTime.class).setFormat(
        JsonFormat.Value.forPattern("yyyy-MM-dd'T'HH:mm:ssXXX")
    );
    // Note: For deserialization, Jackson is usually quite flexible with ISO 8601 variations,
    // but this format pattern also helps ensure consistency for parsing.

    return mapper;

  }
}
