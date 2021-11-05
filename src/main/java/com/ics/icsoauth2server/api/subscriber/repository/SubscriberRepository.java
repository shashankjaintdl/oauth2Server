package com.ics.icsoauth2server.api.subscriber.repository;

import com.ics.icsoauth2server.domain.Subscriber;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SubscriberRepository extends JpaRepository<Subscriber,Long> {

    Optional<Subscriber> findByEmailId(String emailId);

    Page<Subscriber> findAll(Pageable pageable);
}
