package pe.cajapaita.microservices.app.item.client;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import pe.cajapaita.microservices.app.item.models.Product;

@FeignClient(name = "service-products")
public interface ProductClientRest {
	
	@GetMapping("/products")
	public List<Product> findAll();
	
	@GetMapping("/products/{id}")
	public Product findById(@PathVariable Long id);

}
