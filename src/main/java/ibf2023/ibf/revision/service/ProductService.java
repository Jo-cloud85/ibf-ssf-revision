package ibf2023.ibf.revision.service;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ibf2023.ibf.revision.model.Product;
import ibf2023.ibf.revision.repository.ProductRepo;

@Service
public class ProductService {
    
    @Autowired
    private ProductRepo productRepo;

    @Autowired
    DataService dataService;

    public void saveExistingProductList(String key) throws JSONException, IOException {
        List<Product> productList = dataService.createListFromJsonStr();
        productRepo.saveProductListToRedis(key, productList);
    }

    public List<Product> getProductList() {
        return productRepo.getProductListFrRedis();
    }

    public Product getProductById(Integer id) {
        return productRepo.getProductById(id);
    }

    public void updateProduct(Product product) throws IOException {
        productRepo.updateProduct(product);
    }

    public void deleteProduct(Product product) {
        productRepo.deleteProduct(product);
    }
}
