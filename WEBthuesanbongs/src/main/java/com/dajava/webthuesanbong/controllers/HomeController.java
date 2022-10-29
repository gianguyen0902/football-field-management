package com.dajava.webthuesanbong.controllers;

import com.dajava.webthuesanbong.appuser.AppUser;
import com.dajava.webthuesanbong.models.*;
import com.dajava.webthuesanbong.repositories.*;
import com.dajava.webthuesanbong.services.NuocGiaiKhatService;
import com.dajava.webthuesanbong.services.SanBongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.time.Duration;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping(value = "")
public class HomeController {
    @Autowired
    SanBongService sanBongService;
    @Autowired
    SanBongRepository sanBongRepository;
    @Autowired
    PhieuDatSanRepository phieuDatSanRepository;
    @Autowired
    NuocGiaiKhatService nuocGiaiKhatService;
    @Autowired
    NuocGiaiKhatRepository nuocGiaiKhatRepository;
    @Autowired
    PhieuDatNuocRepository phieuDatNuocRepository;
    @Autowired
    CTHoaDonRepository ctHoaDonRepository;
    @Autowired
    NGK_CTHD_Repository ngk_cthd_repository;

    @Autowired
    HoaDonRepository hoaDonRepository;

    @GetMapping(value = "")
    public String index(Model model) {
        AppUser userDetails = (AppUser) SecurityContextHolder.getContext().getAuthentication()
                .getPrincipal();
        model.addAttribute("user", userDetails);
        return "index";
    }

    @GetMapping(value = "/DanhSachSan")
    public String lstSanBong(Model model, @RequestParam("page")Optional<Integer> page, @RequestParam(value = "name", required = false)String name) {
        Pageable pageable = PageRequest.of(page.orElse(0),6);

        Page<SanBong> sanBongs;
        if (name==null|| name.equals("")){
            sanBongs = sanBongRepository.findAll(pageable);
            model.addAttribute("sanBongs", sanBongs);
            return "sanbong";
        }
        else {
            sanBongs = sanBongRepository.getSanBongsByName(pageable,name);
            model.addAttribute("sanBongs", sanBongs);
            model.addAttribute("name", name);
            return "sanbong";
        }
    }

    @GetMapping(value = "/ChiTietSan")
    public String chiTietSanBong(Model model, @RequestParam("id") int id) {
        SanBong sanBong = sanBongRepository.getById(id);
        model.addAttribute("sanBong", sanBong);

        return "chitietsan";
    }
    @GetMapping(value = "/user/deleteCartItem")
    public String deleteCartItem(Model model, @RequestParam("id") int id) {


        AppUser userDetails = (AppUser) SecurityContextHolder.getContext().getAuthentication()
                .getPrincipal();

        Phieudatsan phieu = phieuDatSanRepository.findOneById(id);

        phieu.setDeleted(true);

        phieuDatSanRepository.save(phieu);

        return "redirect:/gioHang";
    }



    @GetMapping(value = "/DanhSachNuoc")
    public String lstNuoc(Model model, @RequestParam("page")Optional<Integer> page, @RequestParam int idPhieuDatSan) {
        Pageable pageable = PageRequest.of(page.orElse(0),6);
        Page<NuocGiaiKhat> nuocGiaiKhats = nuocGiaiKhatRepository.findAll(pageable);
        model.addAttribute("nuocGiaiKhats", nuocGiaiKhats);
        model.addAttribute("idPhieuDatSan", idPhieuDatSan);
        return "nuocgiaikhat";
    }
    @GetMapping(value = "/ChiTietNuoc")
    public String chiTietNuoc (Model model,@RequestParam int id,@RequestParam int idPhieuDatSan){
        NuocGiaiKhat nuocgiaikhat = nuocGiaiKhatRepository.getById(id);
        model.addAttribute("nuocgiaikhat", nuocgiaikhat);
        model.addAttribute("idPhieuDatSan",idPhieuDatSan);
        return "chitietnuoc";
    }

    @PostMapping(value = "/user/datsan")
    public String datSan(Model model, @ModelAttribute DatSanReq req) {

        AppUser userDetails = (AppUser) SecurityContextHolder.getContext().getAuthentication()
                .getPrincipal();
        SanBong sanBong = sanBongRepository.findOneById(req.getId());

        Phieudatsan phieuDatSan = new Phieudatsan();
        phieuDatSan.setUser(userDetails);
        phieuDatSan.setSanBong(sanBong);
        phieuDatSan.setNgayDat(LocalDate.now());
        Duration duration = Duration.between(req.getStart(), req.getEnd());

        double dongia = sanBong.getDonGia() * (duration.getSeconds() * 1.0 / 3600);

        phieuDatSan.setDonGia(dongia);
    phieuDatSan.setGioBatDau(req.getStart().toInstant(ZoneOffset.UTC));
    phieuDatSan.setGioKetThuc(req.getEnd().toInstant(ZoneOffset.UTC));


        phieuDatSanRepository.save(phieuDatSan);

        model.addAttribute("sanBong", sanBong);
        return "redirect:/gioHang";
    }
    //Dat nuoc
    @PostMapping(value = "/user/datnuoc")
    public String datNuoc(Model model, @ModelAttribute DatNuocReq req) {
//
//        AppUser userDetails = (AppUser) SecurityContextHolder.getContext().getAuthentication()
//                .getPrincipal();
        NuocGiaiKhat nuocGiaiKhat = nuocGiaiKhatRepository.findOneById(req.getIdNuoc());
        Phieudatsan phieudatsan = phieuDatSanRepository.findOneById(req.getIdPhieuDatSan());
//
        ChiTietDichVu chiTietDichVu = new ChiTietDichVu();
        chiTietDichVu.setNuocGiaiKhat(nuocGiaiKhat);
        chiTietDichVu.setSoLuong(req.getSoLuong());
        double tongtien = nuocGiaiKhat.getDonGia() * req.getSoLuong() ;
        chiTietDichVu.setTongTien(tongtien);
        chiTietDichVu.setPhieudatsan(phieudatsan);

        phieuDatNuocRepository.save(chiTietDichVu);
        return "redirect:/gioHang";
    }

    //Tao hoa don
    @PostMapping(value = "/user/taoHoaDon")
    public String taoHoaDon(Model model, @ModelAttribute HoaDonReq req) {
        AppUser userDetails = (AppUser) SecurityContextHolder.getContext().getAuthentication()
                .getPrincipal();
        HoaDon hoaDon = new HoaDon();
        hoaDon.setNgayDat(LocalDate.now());
        List<Phieudatsan> dsPDS = phieuDatSanRepository.findAllByUserIdAndDeletedFalse(userDetails.getId());
        List<Integer> dsId = new ArrayList<>();

        double tongTienSan =0;
        for (Phieudatsan i : dsPDS){
            dsId.add(i.getId());
            tongTienSan += i.getDonGia();
        }
        double tongTienNuoc = phieuDatNuocRepository.tinhTongTienNuoc(dsId);
        double tongTien = tongTienSan+ tongTienNuoc;

        hoaDon.setTongTien(tongTien);
        hoaDon.setUser(userDetails);
        HoaDon HDsaved = hoaDonRepository.save(hoaDon);

        /// luu chi tiet nuoc



        for (Phieudatsan i : dsPDS){
           CTHoaDon ctHoaDon = new CTHoaDon();
           ctHoaDon.setGiaSan(i.getDonGia());
           ctHoaDon.setSanBong(i.getSanBong());
           ctHoaDon.setHoaDon(HDsaved);

            CTHoaDon save = ctHoaDonRepository.save(ctHoaDon);

            List<ChiTietDichVu> chiTietDichVus = phieuDatNuocRepository.findAllByPhieudatsanId(i.getId());

            List<NGK_CTHD> ngk_cthds = new ArrayList<>();

            for (ChiTietDichVu tietDichVus : chiTietDichVus) {
                NGK_CTHD ngk_cthd = new NGK_CTHD();
                ngk_cthd.setGiaNuoc(tietDichVus.getNuocGiaiKhat().getDonGia() * tietDichVus.getSoLuong());
                ngk_cthd.setCtHoaDon(save);
                ngk_cthd.setNuocGiaiKhat(tietDichVus.getNuocGiaiKhat());
                ngk_cthds.add(ngk_cthd);
            }

            ngk_cthd_repository.saveAll(ngk_cthds);

        }







        return "redirect:/donHangDaDat  ";
    }
    @GetMapping(value = "/hoadon")
    public String hoadon() {
        return "hoadon";
    }
    @GetMapping(value = "/gioHang")
    public String gioHang(Model model) {

        AppUser userDetails = (AppUser) SecurityContextHolder.getContext().getAuthentication()
                .getPrincipal();
        List<Phieudatsan> pdss = phieuDatSanRepository.findAllByUserIdAndDeletedFalse(userDetails.getId());
        model.addAttribute("phieuDatSans", pdss);

        List<Phieudatsan> dsPDS = phieuDatSanRepository.findAllByUserIdAndDeletedFalse(userDetails.getId());
        List<Integer> dsId = new ArrayList<>();

        double tongTienSan =0;
        for (Phieudatsan i : dsPDS){
            dsId.add(i.getId());
            tongTienSan += i.getDonGia();
        }
        double tongTienNuoc = phieuDatNuocRepository.tinhTongTienNuoc(dsId);
        double tongTien = tongTienSan+ tongTienNuoc;
        model.addAttribute("tongtiensan",tongTienSan);
        model.addAttribute("tongtiennuoc",tongTienNuoc);
        model.addAttribute("tongtien",tongTien);
        model.addAttribute("user", userDetails);
        return "shopping-cart";
    }

    @GetMapping(value = "/donHangDaDat")
    public String hoaDonDaDat(Model model) {

        AppUser userDetails = (AppUser) SecurityContextHolder.getContext().getAuthentication()
                .getPrincipal();
        List<HoaDon> hd = hoaDonRepository.findAllByUser(userDetails.getId());
        model.addAttribute("hoaDons", hd);
        model.addAttribute("user", userDetails);
        return "checkout";
    }
    @GetMapping(value = "/chitietHd")
    public String ctHd(Model model ,@RequestParam("id") int id) {
        AppUser userDetails = (AppUser) SecurityContextHolder.getContext().getAuthentication()
                .getPrincipal();
//      HoaDon hd = hoaDonRepository.findAllByUser(userDetails.getId());
        List<CTHoaDon> ctHoaDons = ctHoaDonRepository.findAllByHDid(id);
        model.addAttribute("ctHoaDons", ctHoaDons);

        model.addAttribute("user", userDetails);
        return "/chitiethoadon";
    }

    @GetMapping(value = "/chitetNuoc_cthd")
    public String ctNGK_ctHD(Model model ,@RequestParam("id") int id) {
        AppUser userDetails = (AppUser) SecurityContextHolder.getContext().getAuthentication()
                .getPrincipal();
        List<NGK_CTHD> ngk_cthd = ngk_cthd_repository.findAllByCTHDid(id);
        model.addAttribute("ngk_cthds", ngk_cthd);
        model.addAttribute("user", userDetails);
        return "/chitiet_ngk_hoadon";
    }
    @GetMapping(value = "/registers")
    public String register() {
        return "pages-register";
    }

    @GetMapping(value = "/loginpage")
    public String login() {
        return "pages-login";
    }

    @GetMapping(value = "/chiduong")
    public ModelAndView chiduong(@RequestParam("diaChi") String diaChi) {
        ModelAndView mv = new ModelAndView("chiduongdi-page");
        mv.addObject("destination", diaChi);
        return mv;
    }
}
