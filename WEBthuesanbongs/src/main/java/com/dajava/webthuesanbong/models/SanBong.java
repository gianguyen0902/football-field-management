package com.dajava.webthuesanbong.models;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class SanBong {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "MaSanBong", nullable = false)
	private Integer id;

	@Column(name = "TenSanBong", nullable = false)
	private String tenSanBong;
	@Lob
	@Column(name = "AnhSan", columnDefinition="LONGTEXT")
	private String anhSan;

	@Column(name = "DiaChi")
	private String diaChi;

	@Column(name = "ChieuRong")
	private Integer chieuRong;

	@Column(name = "ChieuDai")
	private Integer chieuDai;

	@Column(name = "DonGia", nullable = false)
	private Double donGia;

	@ManyToOne
	@JoinColumn(name = "MaKhuVuc", insertable=false, updatable=false)
	private KhuVuc khuvuc;

//	@OneToMany(mappedBy = "key.sanBong")
//	private List<Phieudatsan> phieudatsans = new ArrayList<>();
	private int MaKhuVuc;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTenSanBong() {
		return tenSanBong;
	}

	public void setTenSanBong(String tenSanBong) {
		this.tenSanBong = tenSanBong;
	}

	public String getAnhSan() {
		return anhSan;
	}

	public void setAnhSan(String anhSan) {
		this.anhSan = anhSan;
	}

	public String getDiaChi() {
		return diaChi;
	}

	public void setDiaChi(String diaChi) {
		this.diaChi = diaChi;
	}

	public Integer getChieuRong() {
		return chieuRong;
	}

	public void setChieuRong(Integer chieuRong) {
		this.chieuRong = chieuRong;
	}

	public Integer getChieuDai() {
		return chieuDai;
	}

	public void setChieuDai(Integer chieuDai) {
		this.chieuDai = chieuDai;
	}

	public Double getDonGia() {
		return donGia;
	}

	public void setDonGia(Double donGia) {
		this.donGia = donGia;
	}

	public int getMaKhuVuc() {
		return MaKhuVuc;
	}

	public void setMaKhuVuc(int makhu) {
		this.MaKhuVuc = makhu;
	}

	
}
