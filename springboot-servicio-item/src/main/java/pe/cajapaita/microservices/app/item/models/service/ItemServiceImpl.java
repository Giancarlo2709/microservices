package pe.cajapaita.microservices.app.item.models.service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import pe.cajapaita.microservices.app.item.models.Item;
import pe.cajapaita.microservices.app.item.models.Product;

@Service("serviceRestTemplate")
public class ItemServiceImpl implements ItemService {

	private final RestTemplate clientRest;

	public ItemServiceImpl(RestTemplate clientRest) {
		this.clientRest = clientRest;
	}

	@Override
	public List<Item> findAll() {
		List<Product> products = Arrays
				.asList(clientRest.getForObject("http://service-products/products", Product[].class));
		
		return products.stream().map(p -> Item.builder().product(p).quantity(1).build()).collect(Collectors.toList());
	}

	@Override
	public Item findById(Long id, Integer quantity) {
		Map<String, String> pathVariables = new HashMap<>();
		pathVariables.put("id", id.toString());
		Product product = clientRest.getForObject("http://service-products/products/{id}", Product.class, pathVariables);

		return Item.builder().product(product).quantity(quantity).build();
	}

}
