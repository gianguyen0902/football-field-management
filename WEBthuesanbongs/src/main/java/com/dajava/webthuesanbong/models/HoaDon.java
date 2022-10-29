package com.dajava.webthuesanbong.models;

import com.dajava.webthuesanbong.appuser.AppUser;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class HoaDon {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MaHoaDon", nullable = false)
    private Integer id;

    private LocalDate ngayDat;

    @Column(name = "TongTien")
    private Double tongTien;

    @OneToMany(mappedBy = "id")
    private List<CTHoaDon> ctHoaDons = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "user_id")
    private AppUser user;
}
