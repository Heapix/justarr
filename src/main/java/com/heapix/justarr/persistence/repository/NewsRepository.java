package com.heapix.justarr.persistence.repository;

import com.heapix.justarr.persistence.model.News;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author mgergalov
 */
@Repository
public interface NewsRepository extends JpaRepository<News, Long> {

    List<News> findTop10ByOrderByCreatedDateAsc();
    List<News> findAllByRoleGreaterThanEqualOrderByCreatedDateDesc(Long role);

}
