/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package me.kisoft.qahwagi.infra.hibernate.covnerter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.AttributeConverter;

/**
 *
 * @author tareq
 */
public class ListConverter implements AttributeConverter<List<Object>, String> {

  ObjectMapper mapper = new ObjectMapper();

  @Override
  public String convertToDatabaseColumn(List<Object> attribute) {
    try {
      return mapper.writeValueAsString(attribute);
    } catch (JsonProcessingException ex) {
      return null;
    }
  }

  @Override
  public List<Object> convertToEntityAttribute(String dbData) {
    try {
      return mapper.readValue(dbData, List.class);
    } catch (JsonProcessingException ex) {
      Logger.getLogger(ListConverter.class.getName()).log(Level.SEVERE, null, ex);
    }
    return new ArrayList();
  }

}
