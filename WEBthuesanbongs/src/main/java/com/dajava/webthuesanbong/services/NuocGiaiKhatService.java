package com.dajava.webthuesanbong.services;

import com.dajava.webthuesanbong.models.NuocGiaiKhat;
import com.dajava.webthuesanbong.repositories.NuocGiaiKhatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NuocGiaiKhatService {
    @Autowired
    private NuocGiaiKhatRepository nuocGiaiKhatRepository;

    public List<NuocGiaiKhat> getNuocgiaikhat(){
        return nuocGiaiKhatRepository.findAll();
    }

}
