/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package me.kisoft.qahwagi.infra.auth.service.rest;

import io.javalin.http.Context;
import me.kisoft.qahwagi.domain.auth.entity.User;
import me.kisoft.qahwagi.domain.auth.repo.UserRepository;
import me.kisoft.qahwagi.domain.auth.service.UserService;
import me.kisoft.qahwagi.infra.auth.factory.UserRepositoryFactory;
import me.kisoft.qahwagi.infra.auth.factory.UserServiceFactory;
import me.kisoft.qahwagi.infra.auth.service.rest.vo.SignInRequest;
import org.apache.commons.lang3.StringUtils;

/**
 *
 * @author tareq
 */
public class UserRestService {

  UserService userService = UserServiceFactory.getInstance().get();

  public void signIn(Context ctx) throws Exception {
    SignInRequest request = ctx.bodyAsClass(SignInRequest.class);
    User foundUser = userService.signIn(request.getUsername(), request.getPassword());
    if (foundUser != null) {
      if (StringUtils.equals(request.getPassword(), foundUser.getPassword())) {
        ctx.res.setStatus(200);
        ctx.sessionAttribute("authenticated", true);
        ctx.sessionAttribute("user", foundUser);
        return;
      }
      ctx.res.setStatus(403);
    }

  }

  public void signUp(Context ctx) throws Exception {
    try (UserRepository repo = UserRepositoryFactory.getInstance().get()) {
      User toCreate = ctx.bodyAsClass(User.class);
      if (repo.getUserByUsername(toCreate.getUsername()) == null) {
        userService.signUp(toCreate);
        return;
      }
      ctx.res.setStatus(400);
    }
  }

}
