package pe.cajapaita.microservices.app.products.models.service;

import java.util.List;

import pe.cajapaita.microservices.app.products.models.entity.Product;

public interface ProductService {
	
	public List<Product> findAll();
	public Product findById(Long id);

}
