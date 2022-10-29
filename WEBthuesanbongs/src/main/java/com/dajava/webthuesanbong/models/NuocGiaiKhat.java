package com.dajava.webthuesanbong.models;


import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "nuocgiaikhat")
public class NuocGiaiKhat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MaNuoc", nullable = false)
    private Integer id;

    @Column(name = "TenNuoc", nullable = false)
    private String tenNuoc;

    @Column(name = "DonGia")
    private Double donGia;

    @Column(name = "DVT", length = 20)
    private String dvt;

    @Lob
    @Column(name = "AnhNuoc", columnDefinition="LONGTEXT")
    private String anhNuoc;

    @OneToMany(mappedBy = "id")
    private List<ChiTietDichVu> chiTietDichVus = new ArrayList<>();

    @OneToMany(mappedBy = "id")
    private List<NGK_CTHD> ngk_cthds = new ArrayList<>();

}
