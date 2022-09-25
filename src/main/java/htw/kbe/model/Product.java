package htw.kbe.model;

import lombok.AllArgsConstructor;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
@Document(collection = "Products")
public class Product {
	@Id
	int id;
	String name;
	String size;
	String dough;
	String fill;
	String glasur;
	String extras;
	String description;
	String userName;
}
