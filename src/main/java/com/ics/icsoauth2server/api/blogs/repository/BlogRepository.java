package com.ics.icsoauth2server.api.blogs.repository;

import com.ics.icsoauth2server.domain.Blog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BlogRepository extends JpaRepository<Blog,Long>, PagingAndSortingRepository<Blog,Long> {

    Boolean existsByTitle(String topic);

    Optional<Blog> findByTitle(String topic);

    Optional<Blog> findByTitleAndCreatedBy(String title,String username);

    List<Blog> findAllByCreatedByAndAndIsPublished(String username,Boolean isPublished);

    Optional<Blog> findByTitleAndIsPublishedAndIsDeleted(String title,Boolean isPublished,Boolean isDeleted);

    Page<Blog> findAllByIsPublished(Boolean isPublished, Pageable pageable);

    Page<Blog> findAllByIsPublishedAndIsDeleted(Boolean isPublished, Boolean isDeleted, Pageable pageable);

    Page<Blog> findAllByIsPublishedAndIsDeletedAndCreatedBy(Boolean isPublished, Boolean isDeleted,String username, Pageable pageable);

}
