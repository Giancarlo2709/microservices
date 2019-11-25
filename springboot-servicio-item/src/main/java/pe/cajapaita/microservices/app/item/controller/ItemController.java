package pe.cajapaita.microservices.app.item.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pe.cajapaita.microservices.app.item.models.Item;
import pe.cajapaita.microservices.app.item.models.service.ItemService;

@RestController
@RequestMapping("/items")
public class ItemController {
	
	@Qualifier("serviceRestTemplate")
	@Autowired
	//@Qualifier("serviceFeign")
	private ItemService itemService;	
	
	@GetMapping
	public List<Item> findAll(){
		return this.itemService.findAll();
	}
	
	@GetMapping("/{id}/quantity/{quantity}")
	public Item findById(@PathVariable Long id, @PathVariable Integer quantity) {
		return this.itemService.findById(id, quantity);
	}
	
	

}
