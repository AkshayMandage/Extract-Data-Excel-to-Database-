/**
 * 
 */
package com.ak.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ak.entity.Product;
import com.ak.helper.ExcelHelper;
import com.ak.service.ProductService;

/**
 * @author Akshay Mandage
 *
 */
@RestController
public class ProductControllerr {

	@Autowired
	private ProductService pService;
	
	@PostMapping("/upload")
	public ResponseEntity<?> uploadfile(@RequestParam("file") MultipartFile file){
		
			if(ExcelHelper.checkExcelFormat(file)) {
				this.pService.save(file);
				return ResponseEntity.ok(Map.of("Message" ,"file uploaded SuccessFully..!!"));
			}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Please enter excel file");
	}
	
	@GetMapping("/getProduct")
	public List<Product> getAllProduct(){
		return this.pService.getAllProduct();
	}
	
}
