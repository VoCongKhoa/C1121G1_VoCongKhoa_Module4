package project.repositories;

import org.springframework.stereotype.Repository;
import project.configurations.AppConfiguration;
import project.models.Product;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.*;

@Transactional
@Repository
public class ProductRepository implements IProductRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Product> findAll() {
        return entityManager.createQuery("select p from product p",Product.class).getResultList();
    }

    @Override
    public void save(Product product) {
        entityManager.persist(product);
    }

    @Override
    public Product findById(int id) {
        return entityManager.find(Product.class,id);
    }

    @Override
    public void update(Product product) {
        entityManager.merge(product);

    }

    @Override
    public void remove(Product product) {
        entityManager.remove(findById(product.getId()));
    }

    @Override
    public List<Product> searchByName(String nameSearch) {
        List<Product> result = new ArrayList<>();
        for (Product product: findAll()) {
            if (product.getName().toLowerCase(Locale.ROOT).contains(nameSearch.trim().toLowerCase(Locale.ROOT))){
                result.add(product);
            }
        }
        return result;
    }
}
