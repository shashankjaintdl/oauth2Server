package com.ics.icsoauth2server.api.blogs.repository;

import com.ics.icsoauth2server.domain.Blog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BlogRepository extends JpaRepository<Blog,Long> {

}
