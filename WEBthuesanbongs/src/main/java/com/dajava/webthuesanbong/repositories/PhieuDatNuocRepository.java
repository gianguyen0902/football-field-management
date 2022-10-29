package com.dajava.webthuesanbong.repositories;

import com.dajava.webthuesanbong.models.ChiTietDichVu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PhieuDatNuocRepository extends JpaRepository<ChiTietDichVu,Integer> {

    @Query("SELECT COALESCE(SUM(a.tongTien), 0) FROM ChiTietDichVu a where a.phieudatsan.id in :ids")
    double tinhTongTienNuoc(@Param("ids") List<Integer> ids);

    @Query("SELECT a FROM ChiTietDichVu a where a.phieudatsan.id in :ids")
    List<ChiTietDichVu> timNuocDaDat(@Param("ids") List<Integer> ids);

    List<ChiTietDichVu> findAllByPhieudatsanId(int id);
}
