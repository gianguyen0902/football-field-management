package com.dajava.webthuesanbong.controllers;

import com.dajava.webthuesanbong.models.KhuVuc;
import com.dajava.webthuesanbong.repositories.KhuVucRepository;
import com.dajava.webthuesanbong.services.KhuVucService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/khuvuc")
public class KhuVucController {

	@Autowired
	private KhuVucService khuvucService;
	@Autowired
	private KhuVucRepository khuVucRepository;

	@GetMapping(value = "")
	public String getKhuVuc(Model model) {

		List<KhuVuc> khuvucList = khuvucService.getKhuVuc();
		model.addAttribute("khuvucs", khuvucList);
		return "khuvuc/index";
	}
	@GetMapping("/create")
	public String create(){
		return "khuvuc/create";
	}
	@PostMapping("/create")
	public String create(@ModelAttribute KhuVuc khuVuc){
		khuVucRepository.save(khuVuc);
		return "redirect:/khuvuc";
	}
	@GetMapping("/delete")

	public String delete(Model model,@RequestParam("id") int id){
		KhuVuc khuVuc = khuVucRepository.getById(id);
		model.addAttribute("khuVuc", khuVuc);
		return "khuvuc/delete";
	}
	@PostMapping("/delete")
	public String delete(@ModelAttribute("khuVuc") KhuVuc khuVuc){
		khuVucRepository.deleteById(khuVuc.getId());
		return "redirect:/khuvuc";
	}
	@GetMapping("/edit")

	public String edit(Model model,@RequestParam("id") int id){
		KhuVuc khuVuc = khuVucRepository.getById(id);
		model.addAttribute("khuVuc", khuVuc);
		return "khuvuc/edit";
	}
	@PostMapping("/edit")
	public String edit(@ModelAttribute("khuVuc") KhuVuc khuVuc){
		khuVucRepository.save(khuVuc);
		return "redirect:/khuvuc";
	}

}
