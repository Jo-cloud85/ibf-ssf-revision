package ibf2023.ibf.revision;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import ibf2023.ibf.revision.service.ProductService;
import ibf2023.ibf.revision.util.Util;

@SpringBootApplication
public class RevisionApplication implements CommandLineRunner {

	@Autowired
	ProductService productService;

	public static void main(String[] args) {
		SpringApplication.run(RevisionApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		productService.saveExistingProductList(Util.KEY_PRODUCTS);
		productService.getProductList();
	}

}
