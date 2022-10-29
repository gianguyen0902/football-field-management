package com.dajava.webthuesanbong.models;

import java.util.List;

import javax.persistence.*;

import javax.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "khuvuc")
public class KhuVuc {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "MaKhuVuc", nullable = false)
	private Integer id;

	@Column(name = "TenKhuVuc")
	private String tenKhuVuc;

	@Column(name = "DiaChi")
	private String diaChi;

	@OneToMany(mappedBy="khuvuc")
	private List<SanBong> sanbongs;


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTenKhuVuc() {
		return tenKhuVuc;
	}

	public void setTenKhuVuc(String tenKhuVuc) {
		this.tenKhuVuc = tenKhuVuc;
	}

	public String getDiaChi() {
		return diaChi;
	}

	public void setDiaChi(String diaChi) {
		this.diaChi = diaChi;
	}



}
