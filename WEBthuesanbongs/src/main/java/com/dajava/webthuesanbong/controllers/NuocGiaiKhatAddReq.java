package com.dajava.webthuesanbong.controllers;

import lombok.Data;

@Data
public class NuocGiaiKhatAddReq {
    private String tenNuoc;
    private String anhNuoc;
    private Double donGia;
    private String dvt;
}
