package pe.cajapaita.microservices.app.item.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Item {
	
	private Product product;
	private Integer quantity;
	
	public Double getTotal() {
		return product.getPrice() * quantity.doubleValue();
	}

}
