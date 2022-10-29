package com.dajava.webthuesanbong.repositories;

import com.dajava.webthuesanbong.models.HoaDon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HoaDonRepository extends JpaRepository<HoaDon,Integer> {


    @Query("select a from HoaDon a where a.user.id = :id")
    List<HoaDon> findAllByUser (@Param ("id") int id) ;
    HoaDon findOneById(int id);
}