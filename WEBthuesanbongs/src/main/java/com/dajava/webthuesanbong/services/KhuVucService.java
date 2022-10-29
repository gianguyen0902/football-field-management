package com.dajava.webthuesanbong.services;

import com.dajava.webthuesanbong.models.KhuVuc;
import com.dajava.webthuesanbong.repositories.KhuVucRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class KhuVucService {
	
	@Autowired
	private KhuVucRepository khuvucRepository;
	
	//hiển thị list KhuVuc
	public List<KhuVuc> getKhuVuc(){
		return khuvucRepository.findAll();
	}

	//new khuvuc
	public void save(KhuVuc khuvuc) {
		khuvucRepository.save(khuvuc);
	}
	//
//	//get by id
	public Optional<KhuVuc> findById(int id) {
		return khuvucRepository.findById(id);
	}
	//	delete
	public void delete (int id) {
		khuvucRepository.deleteById(id);
	}
}
