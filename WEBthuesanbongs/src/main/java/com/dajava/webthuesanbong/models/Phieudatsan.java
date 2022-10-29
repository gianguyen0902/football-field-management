package com.dajava.webthuesanbong.models;

import com.dajava.webthuesanbong.appuser.AppUser;
import lombok.Data;

import javax.persistence.*;
import java.time.Instant;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class Phieudatsan {

    @Id
    @GeneratedValue
    private int id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private AppUser user;

    @ManyToOne
    @JoinColumn(name = "sanBong_id")
    private SanBong sanBong;

    @OneToMany(mappedBy = "id")
    private List<ChiTietDichVu> chiTietDichVus = new ArrayList<>();

    private LocalDate ngayDat;

    @Column(name = "GioBatDau", nullable = false)
    private Instant gioBatDau;

    @Column(name = "GioKetThuc")
    private Instant gioKetThuc;

    @Column(name = "DonGia", nullable = false)
    private Double donGia;

    @OneToMany(mappedBy = "id")
    private List<CTHoaDon> ctHoaDons = new ArrayList<>();

    private boolean deleted;

}
