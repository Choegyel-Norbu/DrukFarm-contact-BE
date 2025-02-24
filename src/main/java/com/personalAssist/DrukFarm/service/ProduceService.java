package com.personalAssist.DrukFarm.service;

import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.personalAssist.DrukFarm.Model.AppImage;
import com.personalAssist.DrukFarm.Model.Produce;
import com.personalAssist.DrukFarm.dto.ProduceDTO;

@Component
public interface ProduceService {
	
	public String addProduce(Long userId, ProduceDTO produceDTO);
	public List<ProduceDTO> getAllProduce();
	public List<Produce> getProduceByFarmer(String email);
	public String updateProduceById(Long produceId, ProduceDTO produceDTO);
	public String deleteProduceById(Long produceId);
	public List<ProduceDTO> searchByCategory(String category);
	
	public String uploadImage(Long productId, MultipartFile file);
	public List<AppImage> fetchImages(Long productId);
	public void deleteImageById(Long id);
	public List<String> uploadMulImages(Long produceId, List<MultipartFile> files);
}
