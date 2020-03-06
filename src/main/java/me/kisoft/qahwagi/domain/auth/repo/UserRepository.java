/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package me.kisoft.qahwagi.domain.auth.repo;

import me.kisoft.qahwagi.domain.auth.entity.User;
import me.kisoft.qahwagi.domain.repo.CrudRepository;

/**
 *
 * @author tareq
 */
public interface UserRepository extends CrudRepository<User> {

  User getUserByUsername(String username);
}
