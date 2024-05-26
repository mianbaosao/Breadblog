package com.itmianbao.mapper;

import com.itmianbao.pojo.BlogData;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * @Description: demo
 * @Author:bread
 * @Date: 2024-04-29 16:59
 */

public interface BlogRepository extends ElasticsearchRepository<BlogData, Long> {

}