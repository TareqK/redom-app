/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package me.kisoft.qahwagi.infra.core.service.rest;

import me.kisoft.qahwagi.domain.core.service.BaristaService;
import me.kisoft.qahwagi.infra.core.factory.BaristaServiceFactory;

/**
 *
 * @author tareq
 */
public class BaristaRestService {

  private BaristaService service = BaristaServiceFactory.getInstance().get();

}
