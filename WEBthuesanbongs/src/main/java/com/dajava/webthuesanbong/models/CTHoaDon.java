package com.dajava.webthuesanbong.models;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class CTHoaDon {
    @Id
    @GeneratedValue
    private int id;

    @Column(name = "GiaSan")
    private Double giaSan;

    @ManyToOne
    @JoinColumn(name = "san_id", nullable = false)
    private SanBong sanBong;

    @ManyToOne
    @JoinColumn(name = "hoadon_id", nullable = false)
    private HoaDon hoaDon;

    @OneToMany(mappedBy = "id")
    private List<NGK_CTHD> ngkcthds = new ArrayList<>();
}
