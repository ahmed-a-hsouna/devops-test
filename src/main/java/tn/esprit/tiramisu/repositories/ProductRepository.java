package tn.esprit.tiramisu.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.tiramisu.entities.Product;
import tn.esprit.tiramisu.entities.ProductCategory;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findByCategory(ProductCategory category);
    List<Product> findByStockIdStock(Long idStock);
}
