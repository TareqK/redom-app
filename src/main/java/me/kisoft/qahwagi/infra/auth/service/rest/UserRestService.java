/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package me.kisoft.qahwagi.infra.auth.service.rest;

import io.javalin.http.Context;

/**
 *
 * @author tareq
 */
public class UserRestService {

  public void signIn(Context context) {
    context.sessionAttribute("authenticated", true);
  }

  public void signUp(Context context) {

  }

}
