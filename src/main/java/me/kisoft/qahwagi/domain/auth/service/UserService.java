/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package me.kisoft.qahwagi.domain.auth.service;

import me.kisoft.qahwagi.domain.auth.entity.UserRole;
import me.kisoft.qahwagi.domain.auth.entity.User;

/**
 *
 * @author tareq
 */
public interface UserService {

  public boolean authenticate(User user);

  public boolean isUserInRole(UserRole role, User user);
}
