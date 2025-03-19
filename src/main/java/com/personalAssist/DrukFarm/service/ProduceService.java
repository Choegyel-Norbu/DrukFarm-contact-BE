package com.personalAssist.DrukFarm.service;

import java.awt.print.Pageable;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.personalAssist.DrukFarm.Model.AppImage;
import com.personalAssist.DrukFarm.Model.Produce;
import com.personalAssist.DrukFarm.dto.ProduceDTO;
import com.personalAssist.DrukFarm.dto.ProducePaginatedDTO;

import jakarta.servlet.http.HttpServletRequest;

@Component
public interface ProduceService {
	
	public String addProduce(Long userId, ProduceDTO produceDTO);
	public List<ProduceDTO> getAllProduce();
	public Page<ProduceDTO> getPaginatedProduce(int page, int size, HttpServletRequest request);
	
//	public Page<ProduceDTO> getPaginatedProduce(int page, int size, HttpServletRequest request);

	public Page<ProduceDTO> searchProduceWithName(String keyword, int page, int size, HttpServletRequest request);
	public List<Produce> getProduceByFarmer(String email);
	public int getProduceByFarmerCount(Long userId);
	
	
	public String updateProduceById(Long produceId, ProduceDTO produceDTO);
	public List<ProduceDTO> fetchProduceByFarmerId(Long id, HttpServletRequest request);
	public void deleteProduceById(Long produceId);
	public List<ProduceDTO> searchByCategory(String category);
	public List<String> fetchOnlyImageName(Long id);
	
	public String uploadImage(Long productId, MultipartFile file);
	public List<AppImage> fetchImages(Long productId);
	public void deleteImageById(Long id);
	public List<String> uploadMulImages(Long produceId, List<MultipartFile> files);
}
