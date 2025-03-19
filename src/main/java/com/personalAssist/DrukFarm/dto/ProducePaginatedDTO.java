package com.personalAssist.DrukFarm.dto;

import java.util.List;

import com.personalAssist.DrukFarm.Model.Produce;

public class ProducePaginatedDTO {

	private Produce produce;
	private Long userId;

	public ProducePaginatedDTO() {
		super();
	}

	public ProducePaginatedDTO(Produce produce, Long userId) {
		super();
		this.produce = produce;
		this.userId = userId;
	}

	public Produce getProduce() {
		return produce;
	}

	public void setProduce(Produce produce) {
		this.produce = produce;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

}
