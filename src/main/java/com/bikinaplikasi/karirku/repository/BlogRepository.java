package com.bikinaplikasi.karirku.repository;

import com.bikinaplikasi.karirku.entity.Blog.Blog;
import com.bikinaplikasi.karirku.entity.Order.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BlogRepository extends JpaRepository<Blog, Integer> {
    int countByTitleAndAuthor(String title, Integer author);
}
