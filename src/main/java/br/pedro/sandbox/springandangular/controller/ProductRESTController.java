package br.pedro.sandbox.springandangular.controller;

import java.net.URI;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.pedro.sandbox.springandangular.domain.Product;
import br.pedro.sandbox.springandangular.repository.ProductRepository;

@RestController
@CrossOrigin(origins="http://localhost:4200")
public class ProductRESTController {

	@Autowired
	private ProductRepository productRepository;
	
	@GetMapping("/products")
	public Iterable<Product> getAllProducts() {
		return this.productRepository.findAll();
	}
	
	@PostMapping("/products")
	public ResponseEntity<Object> createProduct(@RequestBody @Valid Product product) {
		this.productRepository.save(product);
		
		URI location = ServletUriComponentsBuilder
				.fromCurrentRequest()
				.path("/products")
				.buildAndExpand().toUri();
		
		return ResponseEntity.created(location).build();
	}
}
