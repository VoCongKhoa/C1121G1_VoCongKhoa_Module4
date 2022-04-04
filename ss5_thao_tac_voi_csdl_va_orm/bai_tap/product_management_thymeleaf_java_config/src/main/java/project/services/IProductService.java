package project.services;

import project.models.Product;

import java.util.List;

public interface IProductService {
    List<Product> findAll();

    void save(Product product);

    Product findById(int id);

    void update(Product product);

    void remove(Product product);

    List<Product> searchByName(String nameSearch);
}
