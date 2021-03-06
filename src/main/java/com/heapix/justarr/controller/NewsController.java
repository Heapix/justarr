package com.heapix.justarr.controller;

import com.heapix.justarr.config.security.UserAuth;
import com.heapix.justarr.controller.bo.CreateResponseBo;
import com.heapix.justarr.controller.converter.NewsConverter;
import com.heapix.justarr.controller.dto.CreateNewsDto;
import com.heapix.justarr.controller.dto.UpdateNewsDto;
import com.heapix.justarr.persistence.model.News;
import com.heapix.justarr.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.List;

import static com.heapix.justarr.controller.EventUtils.getUserRole;

/**
 * @author mgergalov
 */
@RestController
@RequestMapping("news")
public class NewsController {

    @Autowired
    private NewsService newsService;
    @Autowired
    private NewsConverter newsConverter;


    @GetMapping
    @PreAuthorize("permitAll()")
    public List<News> getAllNews() {
        return newsService.getByRole(getUserRole().getId());
    }

    @GetMapping("/{id}")
    @PreAuthorize("permitAll()")
    public News getNews(@NotNull @PathVariable("id") long id) throws Exception {
        News news = newsService.findNews(id);
        if(news != null && news.getRole() < getUserRole().getId()) throw new Exception("access denied");
        return news;
    }

    @PostMapping
    @PreAuthorize("hasAnyAuthority('Super Administrator','Administrator', 'Moderator')")
    public ResponseEntity addNews(@RequestBody CreateNewsDto newsDto) {

        UserAuth currUser = (UserAuth) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        CreateResponseBo response = newsService.createNews(newsConverter.toModel(newsDto), currUser.getId());
        return new ResponseEntity(response, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('Super Administrator', 'Administrator', 'Moderator')")
    public ResponseEntity updateNews(@RequestBody UpdateNewsDto news, @PathVariable long id) {
        //impl
        return new ResponseEntity(newsService.update(news, id), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('Super Administrator', 'Administrator', 'Moderator')")
    public ResponseEntity removeNews(@PathVariable long id) {
        //impl
        newsService.delete(id);
        return new ResponseEntity(HttpStatus.OK);
    }


}
