package com.example.systemofconferences.blog;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BlogRepository extends JpaRepository<Blog, Long> {
    @Query("select p from Blog p where concat(p.post_id, '', p.post_name, '', p.publish_date, '', p.text, '', p.client_name) like %?1%")
    List<Blog> search(String keyword);
}
