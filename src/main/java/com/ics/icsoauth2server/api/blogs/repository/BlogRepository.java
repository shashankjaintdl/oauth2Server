package com.ics.icsoauth2server.api.blogs.repository;

import com.ics.icsoauth2server.domain.Blog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BlogRepository extends JpaRepository<Blog,Long> {

    Boolean existsByTitle(String topic);

    Optional<Blog> findByTitle(String topic);

    Optional<Blog> findByTitleAndCreatedBy(String title,String username);

    List<Blog> findAllByCreatedByAndAndIsPublished(String username,Boolean isPublished);
}
