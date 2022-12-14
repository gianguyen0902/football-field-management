package com.dajava.webthuesanbong.appuser;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
@Transactional(readOnly = true)
public interface AppUserRepository
        extends JpaRepository<AppUser,Integer> {
    Optional<AppUser> findByEmail(String email);
    @Modifying
    @Query("UPDATE AppUser a SET a.enable = 1 WHERE a.email = ?1")

    int enableAppUser(String email);
}
