package com.heapix.justarr.persistence.repository;

import com.heapix.justarr.persistence.model.EventRegistration;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

/**
 * @author mgergalov
 */
public interface EventRegistrationRepository extends JpaRepository<EventRegistration, Long> {

    Optional<EventRegistration> findByUserIdAndEventId(Long userId, Long eventId);
    List<EventRegistration> findByEventId(Long eventId);
    void deleteAllByUserId(Long userId);
}
