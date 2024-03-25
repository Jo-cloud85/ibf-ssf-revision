package ibf2023.ibf.revision.service;

import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.TimeZone;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import ibf2023.ibf.revision.model.Product;
import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;

@Service
public class DataService {
    private String jsonFilename = "src/main/resources/static/products.json";

    public String getJsonStrFromFile() throws IOException {
        String jsonStr = new String(Files.readAllBytes(Paths.get(jsonFilename)));
        return jsonStr;
    }


    public List<Product> createListFromJsonStr() throws JSONException, IOException {
        JSONArray jsonArray = new JSONArray(getJsonStrFromFile());

        List<Product> productList = new LinkedList<>();

        for (int i=0; i<jsonArray.length(); i++) {
            JSONObject jsonObject = jsonArray.getJSONObject(i);
            Product product = new Product();
            product.setId(jsonObject.getInt("id"));
            product.setTitle(jsonObject.getString("title"));
            product.setDescription(jsonObject.getString("description"));
            product.setPrice(jsonObject.getInt("price"));
            product.setDiscountPercentage(jsonObject.getDouble("discountPercentage"));
            product.setRating(jsonObject.getDouble("rating"));
            product.setStock(jsonObject.getInt("stock"));
            product.setBrand(jsonObject.getString("brand"));
            product.setCategory(jsonObject.getString("category"));
            Long datedLong = jsonObject.getLong("dated");
            Date date = new Date(datedLong);
            product.setDated(date);
            product.setBuyCount(jsonObject.getInt("buy"));
            productList.add(product);
        }
        // System.out.println(productList);
        return productList;
    }

    public String convertProductToJsonStr(Product product) throws IOException {
        JsonObject jsonObject = Json.createObjectBuilder()
                                    .add("id", product.getId())
                                    .add("title", product.getTitle())
                                    .add("description", product.getDescription())
                                    .add("price", product.getPrice())
                                    .add("discountPercentage", product.getDiscountPercentage())
                                    .add("rating", product.getRating())
                                    .add("stock", product.getStock())
                                    .add("brand", product.getBrand())
                                    .add("category", product.getCategory())
                                    .add("dated", product.getDated().getTime())
                                    .add("buyCount", product.getBuyCount())
                                    .build();

        String productJsonString;

        try(Writer writer = new StringWriter()) {
            Json.createWriter(writer).write(jsonObject);
            productJsonString = writer.toString();
        }
        return productJsonString;
    }

    public Product convertJsonStrToProduct(String jsonStr) {
        JsonReader reader = Json.createReader(new StringReader(jsonStr));
        JsonObject jsonObject = reader.readObject();

        Product product = new Product();
        
        product.setId(jsonObject.getInt("id"));
        product.setTitle(jsonObject.getString("title"));
        product.setDescription(jsonObject.getString("description"));
        product.setPrice(jsonObject.getInt("price"));
        product.setDiscountPercentage(jsonObject.getJsonNumber("discountPercentage").doubleValue());
        product.setRating(jsonObject.getJsonNumber("rating").doubleValue());
        product.setStock(jsonObject.getInt("stock"));
        product.setBrand(jsonObject.getString("brand"));
        product.setCategory(jsonObject.getString("category"));
        Long datedLong = jsonObject.getJsonNumber("dated").longValue();
        Date date = new Date(datedLong);
    
        product.setDated(date);
        product.setBuyCount(jsonObject.getInt("buyCount"));

        return product;
    }
}
