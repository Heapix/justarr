package com.heapix.justarr.persistence.repository;

import com.heapix.justarr.persistence.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author mgergalov
 */
@Repository
public interface EventRepository extends JpaRepository<Event, Long> {
    List<Event> findByRoleGreaterThanEqual(Long role);
}

