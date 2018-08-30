package com.heapix.justarr.service;

import com.heapix.justarr.controller.bo.CreateResponseBo;
import com.heapix.justarr.controller.bo.UpdateResponseBo;
import com.heapix.justarr.controller.dto.UpdateNewsDto;
import com.heapix.justarr.persistence.model.News;

import java.util.List;

/**
 * @author mgergalov
 */
public interface NewsService {

    CreateResponseBo createNews(News news, Long userId);
    List<News> getAll();
    News findNews(Long id);
    List<News> getByRole(Long id);
    UpdateResponseBo update(UpdateNewsDto news, Long newsId);
    void delete(Long id);

}
