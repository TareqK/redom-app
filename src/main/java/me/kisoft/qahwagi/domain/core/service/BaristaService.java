/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package me.kisoft.qahwagi.domain.core.service;

import me.kisoft.qahwagi.domain.core.entity.Barista;

/**
 *
 * @author tareq
 */
public interface BaristaService {

  Barista getBarista(String id);

  Barista updateBarista(Barista barista, String id);

}
