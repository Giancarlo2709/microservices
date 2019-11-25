package pe.cajapaita.microservices.app.item.models.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import pe.cajapaita.microservices.app.item.client.ProductClientRest;
import pe.cajapaita.microservices.app.item.models.Item;

@Service("serviceFeign")
//@Primary
public class ItemServiceFeign implements ItemService {
	
	private ProductClientRest clientFeign;
	
	@Autowired
	public ItemServiceFeign(ProductClientRest clientFeign) {
		this.clientFeign = clientFeign;
	}

	@Override
	public List<Item> findAll() {
		return clientFeign.findAll()
				.stream()
				.map(p -> Item.builder().product(p).quantity(1).build())
				.collect(Collectors.toList());
	}

	@Override
	public Item findById(Long id, Integer quantity) {
		return Item.builder()
				.product(clientFeign.findById(id))
				.quantity(quantity)
				.build();
	}

}
