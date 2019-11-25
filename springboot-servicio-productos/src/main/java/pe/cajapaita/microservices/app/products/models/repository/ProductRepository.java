package pe.cajapaita.microservices.app.products.models.repository;

import org.springframework.data.repository.CrudRepository;

import pe.cajapaita.microservices.app.products.models.entity.Product;

public interface ProductRepository extends CrudRepository<Product, Long> {

}
