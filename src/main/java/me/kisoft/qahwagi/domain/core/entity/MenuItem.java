/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package me.kisoft.qahwagi.domain.core.entity;

import lombok.Data;

/**
 *
 * @author tareq
 */
@Data
public class MenuItem {

  private String id;
  private String name;
  private String description;
  private double price;
  private boolean available;
}
