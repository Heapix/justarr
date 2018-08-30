package com.heapix.justarr.controller;

import com.heapix.justarr.controller.dto.UpdateAboutPageDto;
import com.heapix.justarr.persistence.model.AboutPage;
import com.heapix.justarr.service.AboutPageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author mgergalov
 */
@RestController
@RequestMapping("about")
public class AboutPageController {

    @Autowired
    private AboutPageService aboutPageService;

    @GetMapping
    @PreAuthorize("permitAll()")
    public AboutPage getAboutPage() {
        return aboutPageService.getAboutPage();
    }

    @PutMapping
    @PreAuthorize("hasAnyAuthority('Super Administrator', 'Administrator')")
    public ResponseEntity updateAboutPage(@RequestBody UpdateAboutPageDto pageDto) {
        //impl
        aboutPageService.updateAboutPage(pageDto);
        return new ResponseEntity(HttpStatus.OK);
    }

}
