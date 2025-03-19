package com.personalAssist.DrukFarm.dto;

import java.util.List;

import com.personalAssist.DrukFarm.Model.Produce;

public class CartItemDTO {

	private Produce produce;
	private Long cartItemId;
	
	public CartItemDTO() {
		super();
	}
	public CartItemDTO(Produce produces, Long cartItemId) {
		super();
		this.produce = produces;
		this.cartItemId = cartItemId;
	}
	public Produce getProduce() {
		return produce;
	}
	public void setProduces(Produce produces) {
		this.produce = produces;
	}
	public Long getCartItemId() {
		return cartItemId;
	}
	public void setCartItemId(Long cartItemId) {
		this.cartItemId = cartItemId;
	}
	
	
}
