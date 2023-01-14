package com.keMoleseng.bookapiv2.repository;

import com.keMoleseng.bookapiv2.model.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
}
