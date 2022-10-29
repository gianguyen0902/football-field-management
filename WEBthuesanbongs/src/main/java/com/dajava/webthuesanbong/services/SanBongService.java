package com.dajava.webthuesanbong.services;

import com.dajava.webthuesanbong.models.SanBong;
import com.dajava.webthuesanbong.repositories.SanBongRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SanBongService {
	@Autowired
	private SanBongRepository sanbongRepository;
//
	//hiển thị list KhuVuc
	public List<SanBong> getSanBong(){
		return sanbongRepository.findAll();
	}
//	//new sanbong
	public void save(SanBong sanbong) {
		sanbongRepository.save(sanbong);
	}
//
//	//get by id
	public Object findById(int id) {
		return sanbongRepository.findById(id);
	}
	//	delete
	public void delete (int id) {
		sanbongRepository.deleteById(id);
	}
}
