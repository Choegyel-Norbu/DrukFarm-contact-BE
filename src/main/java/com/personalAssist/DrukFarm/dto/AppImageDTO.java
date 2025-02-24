package com.personalAssist.DrukFarm.dto;

import com.personalAssist.DrukFarm.Model.Produce;

public class AppImageDTO {

	private Long id;
	private String fileName;
	private String filePath;
	private Produce produce;
	private String url;

	public AppImageDTO() {
		super();
	}

	public AppImageDTO(Long id) {
		super();
		this.id = id;
	}

	public Long getId() {
		return id;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
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
