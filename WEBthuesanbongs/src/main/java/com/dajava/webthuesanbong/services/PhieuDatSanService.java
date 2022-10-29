package com.dajava.webthuesanbong.services;


import com.dajava.webthuesanbong.models.Phieudatsan;
import com.dajava.webthuesanbong.repositories.PhieuDatSanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class PhieuDatSanService {
    @Autowired
    private PhieuDatSanRepository phieuDatSanRepository;

    //hiển thị list KhuVuc
    public List<Phieudatsan> getPhieuDatSan(){
        return phieuDatSanRepository.findAll();
    }
}
