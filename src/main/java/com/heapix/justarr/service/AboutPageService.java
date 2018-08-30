package com.heapix.justarr.service;

import com.heapix.justarr.controller.dto.UpdateAboutPageDto;
import com.heapix.justarr.persistence.model.AboutPage;

/**
 * @author mgergalov
 */
public interface AboutPageService {

    AboutPage getAboutPage();
    void updateAboutPage(UpdateAboutPageDto page);

}
