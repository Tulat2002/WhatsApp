package com.devanktu.whatsappspring.repository;

import com.devanktu.whatsappspring.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    User findByEmail(String email);

    @Query("select u from User u where u.full_name Like %:query% or u.email Like %:query%")
    List<User> searchUser(@Param("query")String query);

}
