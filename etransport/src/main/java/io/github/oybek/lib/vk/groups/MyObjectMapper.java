
 package io.github.oybek.lib.vk.groups;

 import com.fasterxml.jackson.annotation.JsonInclude;
 import com.fasterxml.jackson.core.JsonProcessingException;
 import com.fasterxml.jackson.core.type.TypeReference;
 import com.fasterxml.jackson.databind.DeserializationFeature;
 import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
 import com.mashape.unirest.http.ObjectMapper;

 import java.io.IOException;

 public class MyObjectMapper implements ObjectMapper {
     private com.fasterxml.jackson.databind.ObjectMapper jacksonObjectMapper
             = new com.fasterxml.jackson.databind.ObjectMapper();

     public MyObjectMapper() {
         jacksonObjectMapper.registerModule(new Jdk8Module());
         jacksonObjectMapper.setSerializationInclusion(JsonInclude.Include.NON_EMPTY);
         jacksonObjectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
     }

     public <T> T readValue(String value, Class<T> valueType) {
         try {
             return jacksonObjectMapper.readValue(value, valueType);
         } catch (IOException e) {
             throw new RuntimeException(e);
         }
     }

     public String writeValue(Object value) {
         try {
             return jacksonObjectMapper.writeValueAsString(value);
         } catch (JsonProcessingException e) {
             throw new RuntimeException(e);
         }
     }

     public <T> T convertValue(Object fromValue, TypeReference<?> toValueTypeRef)
             throws IllegalArgumentException
     {
         return jacksonObjectMapper.convertValue(fromValue, toValueTypeRef);
     }
 }
         