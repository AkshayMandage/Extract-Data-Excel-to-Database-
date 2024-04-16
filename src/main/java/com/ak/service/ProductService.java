/**
 * 
 */
package com.ak.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.ak.entity.Product;
import com.ak.helper.ExcelHelper;
import com.ak.repo.ProductRepository;

/**
 * @author Akshay Mandage
 *
 */
@Service
public class ProductService {

	@Autowired
	private ProductRepository prodRepo;

	public void save(MultipartFile file) {
		try {
			List<Product> product = ExcelHelper.converExcelToListOfProduct(file.getInputStream());
			this.prodRepo.saveAll(product);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public List<Product> getAllProduct() {
		return this.prodRepo.findAll();

	}
}
