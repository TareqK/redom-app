/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package me.kisoft.qahwagi.infra.core.service.impl;

import lombok.SneakyThrows;
import me.kisoft.qahwagi.domain.core.entity.Barista;
import me.kisoft.qahwagi.domain.core.repo.BaristaRepository;
import me.kisoft.qahwagi.infra.core.factory.BaristaRepositoryFactory;
import me.kisoft.qahwagi.domain.core.service.BaristaService;

/**
 *
 * @author tareq
 */
public class BaristaServiceImpl implements BaristaService {

  @SneakyThrows
  @Override
  public Barista getBarista(String id) {
    try (BaristaRepository repo = BaristaRepositoryFactory.getInstance().get()) {
      return repo.findById(id);
    }
  }

  @SneakyThrows
  @Override
  public Barista updateBarista(Barista barista, String id) {
    try (BaristaRepository repo = BaristaRepositoryFactory.getInstance().get()) {
      return repo.update(barista, id);
    }
  }

}
