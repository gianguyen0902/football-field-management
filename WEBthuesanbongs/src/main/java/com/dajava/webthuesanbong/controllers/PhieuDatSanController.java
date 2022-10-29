package com.dajava.webthuesanbong.controllers;

import com.dajava.webthuesanbong.models.HoaDon;
import com.dajava.webthuesanbong.repositories.HoaDonRepository;
import com.dajava.webthuesanbong.repositories.PhieuDatSanRepository;
import com.dajava.webthuesanbong.services.PhieuDatSanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping(value = "/phieudatsan")
public class PhieuDatSanController {
    @Autowired
    private PhieuDatSanService phieuDatSanService;

    @Autowired
    private PhieuDatSanRepository phieuDatSanRepository;

    @Autowired
    private HoaDonRepository hoaDonRepository;

    @GetMapping(value ="")
    public String getPhieuDatSan (Model model){
        List<HoaDon> hoaDons = hoaDonRepository.findAll();
        model.addAttribute("hoadons", hoaDons);
        return "phieudatsan/index";
    }

//    @GetMapping(value = "/chitietdatsan")
//    public String getChiTietDatSan (Model model, @RequestParam("id") int id){
//       // List<Chitietphieudat> chitietphieudats = phieuDatSanRepository.getChiTietPhieuDat(id);
//       // model.addAttribute("chitietphieudats", chitietphieudats);
//        return "phieudatsan/chitietdatsan";
//    }
//    @GetMapping(value = "/chitietdatnuoc")
//    public String getChiTietDichVu (Model model, @RequestParam("id") int id){
//       // List<Chitietdichvu> chitietdichvus = phieuDatSanRepository.getChiTietDatNuoc(id);
//       // model.addAttribute("chitietdichvus", chitietdichvus);
//        return "phieudatsan/chitietdatnuoc";
//    }

}
