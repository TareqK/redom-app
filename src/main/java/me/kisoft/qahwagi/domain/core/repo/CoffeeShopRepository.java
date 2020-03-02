/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package me.kisoft.qahwagi.domain.core.repo;

import java.util.List;
import me.kisoft.qahwagi.domain.core.entity.CoffeeShop;
import me.kisoft.qahwagi.repo.CrudRepository;

/**
 *
 * @author tareq
 */
public interface CoffeeShopRepository extends CrudRepository<CoffeeShop> {

  List<CoffeeShop> getServingShopsNear(double longitude, double latitude);
}
