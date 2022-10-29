package com.dajava.webthuesanbong.repositories;

import com.dajava.webthuesanbong.models.SanBong;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface SanBongRepository extends JpaRepository<SanBong, Integer> {

    SanBong findOneById(int idSan);
    @Query("select u from SanBong u where u.tenSanBong like %:name%")
    public Page<SanBong> getSanBongsByName(Pageable pageable, @Param("name") String name);
}
