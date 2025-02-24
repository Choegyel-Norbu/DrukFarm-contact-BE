package com.personalAssist.DrukFarm.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.personalAssist.DrukFarm.Model.AppImage;
import com.personalAssist.DrukFarm.Model.FarmerDetail;
import com.personalAssist.DrukFarm.Model.Produce;
import com.personalAssist.DrukFarm.dto.ProduceDTO;
import com.personalAssist.DrukFarm.repository.AppImageRepository;
import com.personalAssist.DrukFarm.repository.FarmerDetailRepository;
import com.personalAssist.DrukFarm.repository.ProduceRepository;
import com.personalAssist.DrukFarm.util.AppConstants;
import com.personalAssist.DrukFarm.wrapper.UserWrapper;

import jakarta.transaction.Transactional;

@Service
public class ProduceServiceImpl implements ProduceService {

	@Autowired
	FarmerDetailRepository farmerDetailRepository;

	@Autowired
	ProduceRepository produceRepository;

	@Autowired
	AppImageRepository appImageRepository;

	@Override
	public String addProduce(Long useId, ProduceDTO produceDTO) {
		FarmerDetail farmerDetail = farmerDetailRepository.fetchFarmerDetailByUserID(useId);
		Produce produce = new Produce();

		try {
			produce.setAnimalSource(produceDTO.getAnimalSource());
			produce.setCategory(produceDTO.getCategory());
			produce.setCultivationMethod(produceDTO.getCultivationMethod());
			produce.setFarmerDetail(farmerDetail);
			produce.setHarvestDate(produceDTO.getHarvestDate());
			produce.setName(produceDTO.getName());
			produce.setPackagingType(produceDTO.getPackagingType());
			produce.setPricePerUnit(produceDTO.getPricePerUnit());
			produce.setQualityGrade(produceDTO.getQualityGrade());
			produce.setQuantityAvailable(produceDTO.getQuantityAvailable());
			produce.setRipeNessLevel(produceDTO.getRipeNessLevel());
			produce.setStorageAndShelfLife(produceDTO.getStorageAndShelfLife());
			produce.setDelivery(produceDTO.isDelivery());

			produceRepository.save(produce);

		} catch (Exception e) {
			System.out.println(e);
			return null;
		}

		return "Success";
	}

	@Override
	public List<ProduceDTO> getAllProduce() {
		List<Produce> list = produceRepository.findAll();
		List<ProduceDTO> produceDtoList = new ArrayList<>();
		for (Produce produceList : list) {
			ProduceDTO produce = new ProduceDTO();
			produce = UserWrapper.toProduceDTO(produceList);
			produceDtoList.add(produce);
		}
		return produceDtoList;
	}

	@Override
	public List<Produce> getProduceByFarmer(String email) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String updateProduceById(Long id, ProduceDTO produceDTO) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String deleteProduceById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ProduceDTO> searchByCategory(String category) {
		List<Produce> searchResult = produceRepository.searchByCategory(category);
		List<ProduceDTO> produceDtoResult = new ArrayList<>();

		for (Produce produce : searchResult) {
			ProduceDTO produceDTO = new ProduceDTO();
			produceDtoResult.add(UserWrapper.toProduceDTO(produce));
		}
		return produceDtoResult;
	}

	@Transactional
	@Override
	public String uploadImage(Long productId, MultipartFile file) {
		Produce produce = produceRepository.getById(productId);
		Path imagePath = Paths.get(AppConstants.IMAGE_UPLOAD_DIR);
		String fileName = null;

		if (!Files.exists(imagePath)) {
			try {
				Files.createDirectories(imagePath);

			} catch (IOException e) {
				return " Could not create directory";
			}
		} else {
			fileName = UUID.randomUUID().toString() + file.getOriginalFilename().replace(" ", "");
			Path filePath = imagePath.resolve(fileName);

			try {
				AppImage appImage = new AppImage();
				appImage.setProduce(produce);
				appImage.setFileName(fileName);
				appImage.setFilePath(filePath.toString());
				appImageRepository.save(appImage);

				Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
			} catch (IOException e) {
				return "Error uploading file";
			}
		}
		return fileName;
	}

	@Override
	public List<String> uploadMulImages(Long produceId, List<MultipartFile> files) {
		Produce produce = produceRepository.getById(produceId);
		
		Path imagePath = Paths.get(AppConstants.IMAGE_UPLOAD_DIR);
		List<String> multiFileName = new ArrayList<>();

		if (!Files.exists(imagePath)) {
			try {
				Files.createDirectories(imagePath);

			} catch (IOException e) {
				return null;
			}
		} else {
			for(MultipartFile images : files) {
				String fileName = UUID.randomUUID().toString() + images.getOriginalFilename().replace(" ", "");
				Path filePath = imagePath.resolve(fileName);

				try {
					AppImage appImage = new AppImage();
					appImage.setProduce(produce);
					appImage.setFileName(fileName);
					appImage.setFilePath(filePath.toString());
					appImageRepository.save(appImage);

					Files.copy(images.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
				} catch (IOException e) {
					return null;
				}
				multiFileName.add(fileName);
			}
			
		}
		return multiFileName;
	}

	@Override
	public List<AppImage> fetchImages(Long productId) {
		return appImageRepository.fetchImageName(productId);
	}

	@Override
	public void deleteImageById(Long imageId) {
		appImageRepository.deleteById(imageId);
	}

}
