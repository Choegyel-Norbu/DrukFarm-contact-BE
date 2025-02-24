package com.personalAssist.DrukFarm.Model;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "app_image_tbl")
public class AppImage {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String fileName;
	private String filePath;
	
	@ManyToOne
    @JoinColumn(name = "produce_id", nullable = false)
    private Produce produce;

	public AppImage(Long id, String fileName, String filePath, Produce produce) {
		super();
		this.id = id;
		this.fileName = fileName;
		this.filePath = filePath;
		this.produce = produce;
	}

	public AppImage() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public Produce getProduce() {
		return produce;
	}

	public void setProduce(Produce produce) {
		this.produce = produce;
	}	

}
