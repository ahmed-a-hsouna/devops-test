package tn.esprit.tiramisu.services.Iservices;

import tn.esprit.tiramisu.entities.Stock;

import java.util.List;

public interface IStockService {

    Stock addStock(Stock stock);
    Stock retrieveStock(Long id);
    List<Stock> retrieveAllStock();

}
