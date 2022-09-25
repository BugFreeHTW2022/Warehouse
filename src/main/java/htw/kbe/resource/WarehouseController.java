package htw.kbe.resource;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import htw.kbe.model.Component;
import htw.kbe.repository.ComponentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import htw.kbe.model.Product;
import htw.kbe.repository.ProductRepository;

@RestController
public class WarehouseController {

	@Autowired
	private ProductRepository repository;
	@Autowired
	private ComponentRepository componentRepository;

	@PostMapping("/addProduct")
	public int saveProduct(@RequestBody Product book) {
		book.setId(generateId());
		Product product = new Product(book.getId(),book.getName(),book.getSize(),book.getDough(),book.getFill(),book.getGlasur(), book.getExtras(), book.getDescription(), book.getUserName());
		repository.save(product);
		return  book.getId();
	}
	@PostMapping("/updateProduct")
	public int updateProduct(@RequestBody Product book) {
		if(repository.existsById(book.getId())){
			repository.deleteById(book.getId());
			repository.save(book);
			return 1;
		}
		return  -1;
	}

	@GetMapping("/findAllProducts")
	public List<Product> getProducts() {

		return repository.findAll();
	}

	@GetMapping("/findAllComponents")
	public List<Component> getComponents() {

		return componentRepository.findAll();
	}

	@GetMapping("/findProductsById/{id}")
	public Optional<Product> getBook(@PathVariable int id) {
		return repository.findById(id);
	}


	@GetMapping("/findUserProducts/{username}")
	public List<Product> getUserProducts(@PathVariable String username) {
		List<Product> list = repository.findAll();
		List<Product> userProduct = new ArrayList<>();
		Stream<Product> personsOver18 = list.stream().filter(p -> p.getUserName().equals(username));
		personsOver18.forEach(userProduct::add);
		return userProduct;
	}
	@DeleteMapping("/delete/{id}")
	public String deleteProduct(@PathVariable int id) {
		repository.deleteById(id);
		return "Product deleted with id : " + id;
	}

	public int generateId(){
		List<Product> list = repository.findAll();
		int i = 0;
		for (Product p: list) {
			if(p.getId() > i) {
				i = p.getId();
			}
		}
		return i+1;
	}

}
