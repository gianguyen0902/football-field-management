package com.dajava.webthuesanbong.models;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "chitietdichvu")
public class ChiTietDichVu {
    @Id
    @GeneratedValue
    private int id;

    @ManyToOne
    @JoinColumn(name = "nuoc_id", nullable = false)
    private NuocGiaiKhat nuocGiaiKhat;

    private int soLuong;

    private double tongTien;

    @ManyToOne
    @JoinColumn(name = "phieuDatSan_id")
    private Phieudatsan phieudatsan;

}
