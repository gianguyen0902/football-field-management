package com.dajava.webthuesanbong.repositories;

import com.dajava.webthuesanbong.models.CTHoaDon;
import com.dajava.webthuesanbong.models.NGK_CTHD;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface NGK_CTHD_Repository  extends JpaRepository<NGK_CTHD,Integer> {
    @Query("select a from NGK_CTHD a where a.ctHoaDon.id = :id")
    List<NGK_CTHD> findAllByCTHDid (@Param("id") int id) ;
    CTHoaDon findOneById(int id);
}
