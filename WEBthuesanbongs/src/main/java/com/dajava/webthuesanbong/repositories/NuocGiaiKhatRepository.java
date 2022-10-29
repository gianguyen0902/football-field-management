package com.dajava.webthuesanbong.repositories;

import com.dajava.webthuesanbong.models.NuocGiaiKhat;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NuocGiaiKhatRepository extends JpaRepository<NuocGiaiKhat, Integer> {
    NuocGiaiKhat findOneById(int idNuoc);
}
