package com.heapix.justarr.controller.converter;

import com.heapix.justarr.controller.dto.CreateNewsDto;
import com.heapix.justarr.persistence.model.News;

/**
 * @author mgergalov
 */
public interface NewsConverter {

    News toModel(CreateNewsDto dto);

}
