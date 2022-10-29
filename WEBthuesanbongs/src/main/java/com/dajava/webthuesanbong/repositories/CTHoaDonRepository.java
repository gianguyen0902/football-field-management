package com.dajava.webthuesanbong.repositories;

import com.dajava.webthuesanbong.models.CTHoaDon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CTHoaDonRepository extends JpaRepository<CTHoaDon,Integer> {
    @Query("select a from CTHoaDon a where a.hoaDon.id = :id")
    List<CTHoaDon> findAllByHDid (@Param("id") int id) ;
    CTHoaDon findOneById(int id);

}
