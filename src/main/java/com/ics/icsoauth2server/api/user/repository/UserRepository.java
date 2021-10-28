package com.ics.icsoauth2server.api.user.repository;

import com.ics.icsoauth2server.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    Optional<User> findByUsername(String username);
    Optional<User> findByEmailId(String emailId);
    Optional<User> findByMobileNo(String mobileNo);
    //    @Query(value = "SELECT  * from User where username:?1 OR email_Id:?2 OR mobile_no:?3",nativeQuery = true)
    Optional<User> findByUsernameOrEmailIdOrMobileNo(String username,String emailId, String mobileNo);
}
