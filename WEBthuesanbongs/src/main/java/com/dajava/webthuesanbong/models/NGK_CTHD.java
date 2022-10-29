package com.dajava.webthuesanbong.models;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class NGK_CTHD {
    @Id
    @GeneratedValue
    private Integer id;


    @Column(name = "GiaNuoc")
    private Double giaNuoc;


    @ManyToOne
    @JoinColumn(name = "cthd_id")
    private CTHoaDon ctHoaDon;

    @ManyToOne
    @JoinColumn(name = "ngk_id")
    private NuocGiaiKhat nuocGiaiKhat;

}
