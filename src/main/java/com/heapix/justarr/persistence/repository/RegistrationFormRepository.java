package com.heapix.justarr.persistence.repository;

import com.heapix.justarr.persistence.model.RegistrationForm;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RegistrationFormRepository extends JpaRepository<RegistrationForm, Long> {
}
