package tn.esprit.tiramisu.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.tiramisu.entities.Stock;


public interface StockRepository extends JpaRepository<Stock, Long> {}

