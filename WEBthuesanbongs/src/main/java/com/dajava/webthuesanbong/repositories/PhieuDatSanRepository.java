package com.dajava.webthuesanbong.repositories;

import com.dajava.webthuesanbong.models.Phieudatsan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PhieuDatSanRepository extends JpaRepository<Phieudatsan, Integer> {
    List<Phieudatsan> findAllByUserIdAndDeletedFalse(int id);


    @Query("SELECT SUM(a.donGia) FROM Phieudatsan a where a.deleted = false ")

    double tinhTongTienSan(int id);
    Phieudatsan findOneById(int id);
//    @Query("select u from Chitietphieudat u where u.maPhieuDat. = :id")
//    public List<Chitietphieudat> getChiTietPhieuDat(@Param("id") int id);

//    @Query("select u from Chitietdichvu u where u.maPhieuDat.key = :id")
//    public List<Chitietdichvu> getChiTietDatNuoc(@Param("id") int id);
}
