package com.ics.icsoauth2server.api.tag.repository;

import com.ics.icsoauth2server.domain.Tags;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TagRepository extends JpaRepository<Tags,Long> {

    Optional<Tags> findByTagName(String name);

}
