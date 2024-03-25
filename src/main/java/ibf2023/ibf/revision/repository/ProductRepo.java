package ibf2023.ibf.revision.repository;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import ibf2023.ibf.revision.model.Product;
import ibf2023.ibf.revision.service.DataService;
import ibf2023.ibf.revision.util.Util;

@Repository
public class ProductRepo {

    @Autowired
    @Qualifier(Util.KEY_PRODUCTS)
    RedisTemplate<String, String> template;

    HashOperations<String, String, String> hashOps;

    @Autowired
    DataService dataService;

    public void saveProductListToRedis(String key, List<Product> productList) throws IOException {
        hashOps = template.opsForHash();
        for (Product product : productList) {
            String productJsonString = dataService.convertProductToJsonStr(product);
            hashOps.putIfAbsent(Util.KEY_PRODUCTS, product.getId().toString(), productJsonString);
        }
    }

    // Get all records (from Redis Map)
    public List<Product> getProductListFrRedis() {
        hashOps = template.opsForHash();
        List<Product> productList = new LinkedList<>();
        Map<String, String> productMap = hashOps.entries(Util.KEY_PRODUCTS);

        for (Map.Entry<String, String> entry : productMap.entrySet()) {
            String jsonObjectStr = entry.getValue();
            Product product = dataService.convertJsonStrToProduct(jsonObjectStr);
            productList.add(product);
        }

        return productList;
    }

    // Create single record (to Redis Map)
    public void saveNewProductToRedis(Product product) throws IOException {
        hashOps = template.opsForHash(); 
        String productJsonStr = dataService.convertProductToJsonStr(product);
        hashOps.putIfAbsent(Util.KEY_PRODUCTS, product.getId().toString(), productJsonStr);
    }

    // Read a specific record (from Redis Map)
    public Product getProductById(Integer id) {
        hashOps = template.opsForHash(); 
        Map<String, String> productMap = hashOps.entries(Util.KEY_PRODUCTS); //each string is a json str
    
        Optional<Product> productOptional = productMap.values().stream()
                                            .map(jsonStr -> dataService.convertJsonStrToProduct(jsonStr))
                                            .filter(product -> product.getId().equals(id))
                                            .findFirst();
        
        return productOptional.orElse(null);
    }

    // Update a specific record - make sure id no change
    public void updateProduct(Product product) throws IOException {
        hashOps = template.opsForHash(); 
        String productJsonStr = dataService.convertProductToJsonStr(product);
        hashOps.put(Util.KEY_PRODUCTS, product.getId().toString(), productJsonStr);
    }

    // Delete a specific record (in Redis Map)
    public void deleteProduct(Product product) {
        hashOps = template.opsForHash(); 
        hashOps.delete(Util.KEY_PRODUCTS, product.getId());
    }
}
