package com.dajava.webthuesanbong.controllers;

import com.dajava.webthuesanbong.models.SanBong;
import com.dajava.webthuesanbong.repositories.SanBongRepository;
import com.dajava.webthuesanbong.services.SanBongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/sanbong")
public class SanBongController {
	@Autowired
	private SanBongService sanBongService;
	@Autowired
	private SanBongRepository sanBongRepository;

	@GetMapping(value = "")
	public String getSanBong(Model model) {

		List<SanBong> sanBongs = sanBongService.getSanBong();
		model.addAttribute("sanbongs", sanBongs);
		return "sanbong/index";
	}
	@GetMapping("/create")
	public String create(){
		return "sanbong/create";
	}
	@PostMapping("/create")
	public String create(@ModelAttribute SanBong sanBong){
		sanBongRepository.save(sanBong);
		return "redirect:/sanbong";
	}
	@GetMapping("/delete")

	public String delete(Model model,@RequestParam("id") int id){
		SanBong sanBong = sanBongRepository.getById(id);
		model.addAttribute("sanBong", sanBong);
		return "sanbong/delete";
	}
	@PostMapping("/delete")
	public String delete(@RequestParam("id") int id){
		sanBongRepository.deleteById(id);
		return "redirect:/sanbong";
	}
	@GetMapping("/edit")

	public String edit(Model model,@RequestParam("id") int id){
		SanBong sanBong = sanBongRepository.getById(id);
		model.addAttribute("sanBong", sanBong);
		return "sanbong/edit";
	}
	@PostMapping("/edit")
	public String edit(@ModelAttribute("sanbong") SanBong sanBong){
		sanBongRepository.save(sanBong);
		return "redirect:/sanbong";
	}
}
