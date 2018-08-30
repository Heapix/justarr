package com.heapix.justarr.controller.converter;

import com.heapix.justarr.controller.dto.CreateNewsDto;
import com.heapix.justarr.persistence.model.News;
import org.springframework.stereotype.Component;

/**
 * @author mgergalov
 */
@Component
public class NewsConverterImpl implements NewsConverter {
    public NewsConverterImpl() {
    }

    public News toModel(CreateNewsDto dto) {
        if (dto == null) {
            return null;
        } else {
            News news = new News();
            news.setTitle(dto.getTitle());
            news.setBody(dto.getBody());
            news.setRole(dto.getRole());
            return news;
        }
    }
}
