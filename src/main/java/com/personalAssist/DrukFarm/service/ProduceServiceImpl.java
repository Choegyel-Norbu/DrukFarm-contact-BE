package com.personalAssist.DrukFarm.service;

import java.awt.print.Pageable;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.personalAssist.DrukFarm.Model.AppImage;
import com.personalAssist.DrukFarm.Model.FarmerDetail;
import com.personalAssist.DrukFarm.Model.Produce;
import com.personalAssist.DrukFarm.dto.ProduceDTO;
import com.personalAssist.DrukFarm.dto.ProducePaginatedDTO;
import com.personalAssist.DrukFarm.repository.AppImageRepository;
import com.personalAssist.DrukFarm.repository.FarmerDetailRepository;
import com.personalAssist.DrukFarm.repository.ProduceRepository;
import com.personalAssist.DrukFarm.util.AppConstants;
import com.personalAssist.DrukFarm.wrapper.UserWrapper;

import jakarta.servlet.http.HttpServletRequest;
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
			produce.setStatus(produceDTO.getStatus());

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
	public String updateProduceById(Long id, ProduceDTO updateProduce) {
		Produce produce = produceRepository.findById(id).orElse(null);
		if(produce != null) {
			produce.setName(updateProduce.getName());
			produce.setPricePerUnit(updateProduce.getPricePerUnit());
			produce.setQuantityAvailable(updateProduce.getQuantityAvailable());
			produce.setStorageAndShelfLife(updateProduce.getStorageAndShelfLife());
			produce.setStatus(updateProduce.getStatus());
		}
		if(produceRepository.save(produce) != null) {
			return "Success";
		}
		return "Failed";
	}

	@Override
	public void deleteProduceById(Long id) {
		produceRepository.deleteById(id);
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
		Produce produce = produceRepository.findById(productId).orElse(null);
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
			for (MultipartFile images : files) {
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

	@Override
	public Page<ProduceDTO> getPaginatedProduce(int page, int size, HttpServletRequest request) {
		PageRequest pageRequest = PageRequest.of(page, size);
		Page<ProducePaginatedDTO> produces = produceRepository.fetchPaginatedProduce(pageRequest);

		return produces.map(produce -> {
			ProduceDTO dto = UserWrapper.toProduceDTO(produce.getProduce());
			List<String> imageName = new ArrayList<>();
			List<String> imgURL = new ArrayList<>();
			List<AppImage> names = produce.getProduce().getImages();
			for (AppImage img : names) {
				String url = request.getRequestURL().toString().replace(request.getRequestURI(), "") + "/images/"
						+ img.getFileName();
				imageName.add(img.getFileName());
				imgURL.add(url);
			}
			dto.setUrl(imgURL);
			dto.setUserId(produce.getUserId());
			return dto;
		});
	}

	@Override
	public Page<ProduceDTO> searchProduceWithName(String keyword, int page, int size, HttpServletRequest request) {
		PageRequest pageRequest = PageRequest.of(page, size);

		Page<ProducePaginatedDTO> produces = produceRepository.searchWithName(keyword, pageRequest);

		return produces.map(produce -> {
			ProduceDTO dto = UserWrapper.toProduceDTO(produce.getProduce());
			List<String> imageName = new ArrayList<>();
			List<String> imgURL = new ArrayList<>();
			List<AppImage> image = produce.getProduce().getImages();
			for (AppImage img : image) {
				String url = request.getRequestURL().toString().replace(request.getRequestURI(), "") + "/images/"
						+ img.getFileName();
				imgURL.add(url);
			}
			dto.setUrl(imgURL);

			return dto;
		});
	}

	@Override
	public List<String> fetchOnlyImageName(Long id) {
		return appImageRepository.fetchOnlyImageName(id);
	}

	@Override
	public List<ProduceDTO> fetchProduceByFarmerId(Long userId, HttpServletRequest request) {
		List<Produce> produces = produceRepository.fetchProduceByFarmerId(userId);
		List<ProduceDTO> dto = new ArrayList<>();
		for(Produce produce : produces) {
			List<String> imageURL = new ArrayList<>();
			List<AppImage> image = produce.getImages();
			for(AppImage img : image) {
				String url = request.getRequestURL().toString().replace(request.getRequestURI(), "") + "/images/"
						+ img.getFileName();
				imageURL.add(url);
			}
			ProduceDTO produceDTO = UserWrapper.toProduceDTO(produce);
			produceDTO.setUrl(imageURL);
			dto.add(produceDTO);
		}
		return dto;
	}

	@Override
	public int getProduceByFarmerCount(Long userId) {
		return produceRepository.getProduceByFarmerCount(userId);
	}
}
