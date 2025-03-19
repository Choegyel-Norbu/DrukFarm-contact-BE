package com.personalAssist.DrukFarm.controller;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.personalAssist.DrukFarm.Model.AppImage;
import com.personalAssist.DrukFarm.Model.Produce;
import com.personalAssist.DrukFarm.dto.AppImageDTO;
import com.personalAssist.DrukFarm.dto.LombokCheccDTO;
import com.personalAssist.DrukFarm.dto.ProduceDTO;
import com.personalAssist.DrukFarm.service.ProduceService;
import com.personalAssist.DrukFarm.util.AppConstants;
import com.personalAssist.DrukFarm.wrapper.UserWrapper;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api")
public class ProduceController {

	@Autowired
	ProduceService produceService;

	@PostMapping("/addProduce/{id}")
	public ResponseEntity<?> addProduce(@PathVariable Long id, @RequestBody ProduceDTO produceDTO) {
		String produce = produceService.addProduce(id, produceDTO);

		if (produce != null) {
			return ResponseEntity.ok(produce);
		} else {
			return ResponseEntity.status(HttpStatus.SC_CONFLICT).body("Error adding produce");
		}

	}

	@GetMapping("/getProduces")
	public ResponseEntity<List<ProduceDTO>> getAllProduce() {
		List<ProduceDTO> list = produceService.getAllProduce();

		return ResponseEntity.ok(list);
	}

	@GetMapping("/getPaginatedProduce/{page}/{size}")
	public ResponseEntity<Page<ProduceDTO>> getPaginatedProduce(@PathVariable int page, @PathVariable int size,
			HttpServletRequest request) {
		Page<ProduceDTO> paginated = produceService.getPaginatedProduce(page, size, request);

		if (!paginated.isEmpty()) {
			return ResponseEntity.ok(paginated);
		} else {
			return ResponseEntity.status(HttpStatus.SC_CONFLICT).body(null);
		}
	}

	@GetMapping("/serchProduce/{keyword}/{page}/{size}")
	public ResponseEntity<?> searchByProduceName(@PathVariable String keyword, @PathVariable int page,
			@PathVariable int size, HttpServletRequest request) {
		Page<ProduceDTO> searchResult = produceService.searchProduceWithName(keyword, page, size, request);
		List<ProduceDTO> result = searchResult.getContent();
		List<ProduceDTO> responseDTO = new ArrayList<>();

		for (ProduceDTO produce : result) {
			responseDTO.add(produce);
		}
		if (!responseDTO.isEmpty()) {
			return ResponseEntity.ok(responseDTO);
		} else {
			return ResponseEntity.noContent().build();
		}
	}
	
	@GetMapping("/getProduceForFarmer/{userId}")
	public ResponseEntity<List<ProduceDTO>> fetchProduceByFarmerId(@PathVariable Long userId, HttpServletRequest request) {
		List<ProduceDTO> produceDTO = produceService.fetchProduceByFarmerId(userId, request);
		if(!produceDTO.isEmpty()) {
			return ResponseEntity.ok(produceDTO);
		}else {
			return ResponseEntity.status(HttpStatus.SC_CONFLICT).body(null);

		}
	}
	
	@GetMapping("/productCount/{userId}")
	public int getProduceByFarmerCount(@PathVariable Long userId) {
		return produceService.getProduceByFarmerCount(userId);
	}

	@DeleteMapping("/deleteProduct/{prod_id}")
	public void deleteProduceById(@PathVariable Long prod_id) {
		produceService.deleteProduceById(prod_id);
	}
	
	@PatchMapping("/updateProduce/{prod_id}")
	public ResponseEntity<String> updateProduceById(@PathVariable Long prod_id, @RequestBody ProduceDTO produceDTO){
		String update = produceService.updateProduceById(prod_id, produceDTO);
		if(update.equalsIgnoreCase("Success")) {
			return ResponseEntity.ok(update);
		}
		return ResponseEntity.status(HttpStatus.SC_CONFLICT).body(update);
	}
	
	@PostMapping("/uploadImage/{produceID}")
	public ResponseEntity<?> uploadImages(@PathVariable Long produceID, @RequestParam("file") MultipartFile imageFile,
			HttpServletRequest request) {
		String fileName = produceService.uploadImage(produceID, imageFile);

		try {
			Path filePath = Paths.get(AppConstants.IMAGE_UPLOAD_DIR + fileName).toAbsolutePath().normalize();
			String serverURL = request.getRequestURL().toString().replace(request.getRequestURI(), "");
			String imageURL = serverURL + "/images/" + fileName;

			File file = filePath.toFile();
			if (file.exists() && file.isFile()) {
				return ResponseEntity.ok().body(imageURL);
			} else {
				return ResponseEntity.notFound().build();

			}
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.SC_INTERNAL_SERVER_ERROR).build();

		}
	}

	@GetMapping("/getImages/{id}")
	public ResponseEntity<List<AppImageDTO>> fetchImages(@PathVariable Long id, HttpServletRequest request) {
		List<AppImage> appImage = produceService.fetchImages(id);
		List<AppImageDTO> imageDTO = new ArrayList<>();
		try {
			String serverURL = request.getRequestURL().toString().replace(request.getRequestURI(), "");

			for (AppImage image : appImage) {
				Path file = Paths.get(AppConstants.IMAGE_UPLOAD_DIR + image.getFileName()).toAbsolutePath().normalize();
				String imageURL = serverURL + "/images/" + image.getFileName();

				AppImageDTO obj = UserWrapper.toAppImageDTO(image);
				obj.setUrl(imageURL);
				imageDTO.add(obj);
			}
			return ResponseEntity.ok(imageDTO);
		} catch (Exception e) {
			return ResponseEntity.notFound().build();

		}
	}

	@PostMapping("/uploadMulImages/{id}")
	public ResponseEntity<String> uploadMulImage(@PathVariable Long id,
			@RequestParam("file") List<MultipartFile> file) {
		if (produceService.uploadMulImages(id, file).size() != 0) {
			return ResponseEntity.ok("Images uploaded successfully");
		} else {
			return ResponseEntity.status(HttpStatus.SC_INTERNAL_SERVER_ERROR).body("Error uploading images");
		}
	}

	@DeleteMapping("/deleteImage/{id}")
	public void deleteImage(@PathVariable Long id) {
		LombokCheccDTO dto = new LombokCheccDTO();

		produceService.deleteImageById(id);
	}
}
