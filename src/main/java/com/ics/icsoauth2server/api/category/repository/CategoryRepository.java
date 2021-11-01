package com.ics.icsoauth2server.api.category.repository;

import com.ics.icsoauth2server.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category,Long> {

}
