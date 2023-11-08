package tn.esprit.tiramisu.services.Iservices;

import tn.esprit.tiramisu.entities.Supplier;
import tn.esprit.tiramisu.entities.SupplierDTO;

import java.util.List;

public interface ISupplierService {

	List<Supplier> retrieveAllSuppliers();

	Supplier addSupplier(SupplierDTO supplierDTO);

	void deleteSupplier(Long id);

	Supplier updateSupplier(SupplierDTO supplierDTO);

	Supplier retrieveSupplier(Long id);

}
