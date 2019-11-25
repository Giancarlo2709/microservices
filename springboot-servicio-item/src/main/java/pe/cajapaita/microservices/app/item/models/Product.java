package pe.cajapaita.microservices.app.item.models;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Product {
	
	private Long id;
	private String name;
	private Double price;
	private LocalDate createdAt;
	private Integer port;

}
