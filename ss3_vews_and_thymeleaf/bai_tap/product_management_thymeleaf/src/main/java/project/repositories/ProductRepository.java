package project.repositories;

import org.springframework.stereotype.Repository;
import project.models.Product;

import java.util.*;

@Repository
public class ProductRepository implements IProductRepository {

    private static final Map<Integer, Product> productMap;
    private static int autoIncrementId = 1;
    static {

        productMap = new TreeMap<>();
        productMap.put(1, new Product(autoIncrementId++, "iPhone 13 Pro Max 256GB",36990000, "Gold, White, new", "iPhone"));
        productMap.put(2, new Product(autoIncrementId++, "OPPO Reno7 Z 5G",10490000, "Black, White, old", "OPPO"));
        productMap.put(3, new Product(autoIncrementId++, "Samsung Galaxy S22 Ultra 5G 256GB",33990000, "Black, White, new", "Samsung"));
        productMap.put(4, new Product(autoIncrementId++, "iPhone 11 64GB",16290000, "Black, White, new", "iPhone"));
        productMap.put(5, new Product(autoIncrementId++, "Samsung Galaxy Tab S8",20990000, "Black, Gold, new", "Samsung"));
        productMap.put(6, new Product(autoIncrementId++, "Asus TUF Gaming FX517ZC i5",27000000, "Black, White, new", "Asus"));
    }

    @Override
    public List<Product> findAll() {
        return new ArrayList<>(productMap.values());
    }

    @Override
    public void save(Product product) {
        product.setId(productMap.size()+1);
        productMap.put(product.getId(), product);
    }

    @Override
    public Product findById(int id) {
        return productMap.get(id);
    }

    @Override
    public void update(int id, Product product) {
        productMap.put(id, product);

    }

    @Override
    public void remove(int id) {
        productMap.remove(id);
    }

    @Override
    public List<Product> searchByName(String nameSearch) {
        List<Product> result = new ArrayList<>();
        for (Product product: productMap.values()) {
            if (product.getName().toLowerCase(Locale.ROOT).contains(nameSearch.trim().toLowerCase(Locale.ROOT))){
                result.add(product);
            }
        }
        return result;
    }
}
