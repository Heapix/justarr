package com.heapix.justarr.service;

import com.heapix.justarr.controller.bo.CreateResponseBo;
import com.heapix.justarr.controller.bo.UpdateResponseBo;
import com.heapix.justarr.controller.converter.NewsConverter;
import com.heapix.justarr.controller.dto.UpdateNewsDto;
import com.heapix.justarr.persistence.model.News;
import com.heapix.justarr.persistence.repository.NewsRepository;
import com.heapix.justarr.persistence.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import javax.transaction.Transactional;

/**
 * @author mgergalov
 */
@Service
@Transactional
public class NewsServiceImpl implements NewsService {

    @Autowired
    private NewsRepository newsRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private NewsConverter newsConverter;

    @Override
    public CreateResponseBo createNews(News news, Long userId) {
        news.setCreatedDate(new Date());
        //check is user exist
        news.setUserId(userId);
        News response = newsRepository.save(news);
        return new CreateResponseBo(response.getId());
    }

    @Override
    public List<News> getAll() {
        return newsRepository.findTop10ByOrderByCreatedDateAsc();
    }

    @Override
    public News findNews(Long id) {
        return newsRepository.getOne(id);
    }

    @Override
    public List<News> getByRole(Long role) {
//        return newsRepository.findAllByRoleGreaterThanEqual(role);
        return newsRepository.findAllByRoleGreaterThanEqualOrderByCreatedDateDesc(role);
    }

    @Override
    public UpdateResponseBo update(UpdateNewsDto newsDto, Long newsId) {
        News news = newsRepository.getOne(newsId);
        news.setBody(newsDto.getBody());
        news.setRole(newsDto.getRole());
        news.setCreatedDate(new Date());
        return new UpdateResponseBo(newsRepository.save(news).getId());
    }

    @Override
    public void delete(Long id) {
        newsRepository.deleteById(id);
    }
}
