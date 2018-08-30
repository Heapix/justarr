package com.heapix.justarr.persistence.repository;

import com.heapix.justarr.persistence.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author mgergalov
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long>{

    User findByEmail(String email);
    List<User> findByRoleEquals(Long role);
    List<User> findByRoleIsNot(Long role);

}
