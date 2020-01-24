package pe.cajapaita.microservices.app.item.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

import pe.cajapaita.microservices.app.item.models.Item;
import pe.cajapaita.microservices.app.item.models.Product;
import pe.cajapaita.microservices.app.item.models.service.ItemService;

@RefreshScope
@RestController
@RequestMapping("/items")
public class ItemController {
	
	private static Logger log = LoggerFactory.getLogger(ItemController.class);
	
	@Autowired
	private Environment env;
	
	@Qualifier("serviceRestTemplate")
	@Autowired
	//@Qualifier("serviceFeign")
	private ItemService itemService;	
	
	@Value("${configuration.text}")
	private String text;
	
	@GetMapping
	public List<Item> findAll(){
		return this.itemService.findAll();
	}
	
	@HystrixCommand(fallbackMethod = "methodAlternative")
	@GetMapping("/{id}/quantity/{quantity}")
	public Item findById(@PathVariable Long id, @PathVariable Integer quantity) {
		return this.itemService.findById(id, quantity);
	}
	
	public Item methodAlternative(Long id, Integer quantity) {
		return Item.builder()
				.quantity(quantity)
				.product(Product.builder()
						.id(id)
						.name("Huawei P30 Pro")
						.price(3200.00)
						.build())
				.build();
	}
	
	@GetMapping("/get-config")
	public ResponseEntity<?> getConfig(@Value("${server.port}") String port) {
		
		log.info(text);
		
		Map<String, String> json = new HashMap<>();
		json.put("text", text);
		json.put("port", port);
		
		if(env.getActiveProfiles().length > 0 && env.getActiveProfiles()[0].equals("dev")) {
			json.put("autor.name", env.getProperty("configuration.autor.name"));
			json.put("autor.email", env.getProperty("configuration.autor.email"));
		}
		
		
		return new ResponseEntity<Map<String, String>>(json, HttpStatus.OK);
	}
	
	

}
