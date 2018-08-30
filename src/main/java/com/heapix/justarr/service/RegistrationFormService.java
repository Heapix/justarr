package com.heapix.justarr.service;

import com.heapix.justarr.persistence.model.RegistrationForm;

public interface RegistrationFormService {
    RegistrationForm getRegistrationForm();
    RegistrationForm saveRegistrationForm(RegistrationForm form);
}
