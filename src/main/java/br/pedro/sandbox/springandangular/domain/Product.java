package br.pedro.sandbox.springandangular.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="product")
public class Product {

	@Id
	@SequenceGenerator(name="product_seq", sequenceName="product_seq", initialValue=101)
	@GeneratedValue(generator="product_seq")
	private Long id;
	
	@NotBlank(message="Please, give a name to your product.")
	private String name;
	
	@NotNull(message="Please, give a price to your product.")
	@Min(message="Please, give a price equal or bigger than 1.0.", value=1L)
	private double price;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}
}
