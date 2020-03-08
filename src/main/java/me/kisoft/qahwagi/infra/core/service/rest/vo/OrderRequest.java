/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package me.kisoft.qahwagi.infra.core.service.rest.vo;

import java.util.ArrayList;
import java.util.List;
import lombok.Data;
import me.kisoft.qahwagi.domain.core.entity.CoffeeShop;
import me.kisoft.qahwagi.domain.core.entity.Customer;

/**
 *
 * @author tareq
 */
@Data
public class OrderRequest {

  private CoffeeShop coffeeShop;
  private Customer customer;
  private List<String> orderedItems = new ArrayList<>();
  private double latitude;
  private double longitude;
  private String notes;
}
