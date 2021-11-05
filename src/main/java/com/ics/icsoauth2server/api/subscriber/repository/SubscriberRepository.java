package com.ics.icsoauth2server.api.subscriber.repository;

import com.ics.icsoauth2server.domain.Subscriber;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubscriberRepository extends JpaRepository<Subscriber,Long> {

}
