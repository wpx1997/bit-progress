package com.wpx.repository.user;

import com.wpx.model.user.UserEs;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * @author 不会飞的小鹏
 */
public interface UserRepository extends ElasticsearchRepository<UserEs, Long> {
}