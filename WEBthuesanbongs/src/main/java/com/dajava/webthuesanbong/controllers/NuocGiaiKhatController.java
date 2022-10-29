package com.dajava.webthuesanbong.controllers;

import com.dajava.webthuesanbong.models.NuocGiaiKhat;
import com.dajava.webthuesanbong.repositories.NuocGiaiKhatRepository;
import com.dajava.webthuesanbong.services.NuocGiaiKhatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(value = "/nuocgiaikhat")
public class NuocGiaiKhatController {
    @Autowired
    private NuocGiaiKhatService nuocGiaiKhatService;
    @Autowired
    private  NuocGiaiKhatRepository nuocGiaiKhatRepository;
    @GetMapping(value = "")
	public String getNuocGiaiKhat(Model model) {

		List<NuocGiaiKhat> nuocGiaiKhats = nuocGiaiKhatService.getNuocgiaikhat();
		model.addAttribute("nuocGiaiKhats", nuocGiaiKhats);
		return "nuocgiaikhat/index";
	}
	@GetMapping("/create")
	public String create(){
		return "nuocgiaikhat/create";
	}
	@PostMapping("/create")
	public String create(@ModelAttribute NuocGiaiKhatAddReq req){

		NuocGiaiKhat ngk = new NuocGiaiKhat();
		ngk.setTenNuoc(req.getTenNuoc());
		ngk.setDonGia(req.getDonGia());
		ngk.setDvt(req.getDvt());
		ngk.setAnhNuoc(req.getAnhNuoc());


		nuocGiaiKhatRepository.save(ngk);
		return "redirect:/nuocgiaikhat";
	}
	@GetMapping("/delete")

	public String delete(Model model,@RequestParam("id") int id){
		NuocGiaiKhat nuocgiaikhat = nuocGiaiKhatRepository.getById(id);
		model.addAttribute("nuocgiaikhat", nuocgiaikhat);
		return "nuocgiaikhat/delete";
	}
	@PostMapping("/delete")
	public String delete(@RequestParam("id") int id){
		nuocGiaiKhatRepository.deleteById(id);
		return "redirect:/nuocgiaikhat";
	}
	@GetMapping("/edit")

	public String edit(Model model,@RequestParam("id") int id){
		NuocGiaiKhat nuocgiaikhat = nuocGiaiKhatRepository.getById(id);
		model.addAttribute("nuocgiaikhat", nuocgiaikhat);
		return "nuocgiaikhat/edit";
	}
	@PostMapping("/edit")
	public String edit(@ModelAttribute("nuocgiaikhat") NuocGiaiKhat nuocgiaikhat){
		nuocGiaiKhatRepository.save(nuocgiaikhat);
		return "redirect:/nuocgiaikhat";
	}

}
