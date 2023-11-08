package tn.esprit.tiramisu.services;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.tiramisu.entities.Supplier;
import tn.esprit.tiramisu.entities.SupplierDTO;
import tn.esprit.tiramisu.repositories.SupplierRepository;
import tn.esprit.tiramisu.services.Iservices.ISupplierService;

import java.util.List;

@Service
@Slf4j
@AllArgsConstructor
public class SupplierServiceImpl implements ISupplierService {

	@Autowired
	SupplierRepository supplierRepository;

	@Override
	public List<Supplier> retrieveAllSuppliers() {
		return supplierRepository.findAll();
	}

	@Override
	public Supplier addSupplier(SupplierDTO supplierDTO) {
		return supplierRepository.save(new Supplier(supplierDTO.getCode() ,supplierDTO.getLabel(), supplierDTO.getCategory()));
	}

	@Override
	public Supplier updateSupplier(SupplierDTO supplierDTO) {
		Supplier supplier = supplierRepository.findSupplierByIdSupplier(supplierDTO.getIdSupplier());
		supplier.setCode(supplierDTO.getCode());
		supplier.setLabel(supplierDTO.getLabel());
		supplier.setCategory(supplierDTO.getCategory());
		return  supplierRepository.save(supplier);
	}

	@Override
	public void deleteSupplier(Long supplierId) {
		supplierRepository.deleteById(supplierId);

	}

	@Override
	public Supplier retrieveSupplier(Long supplierId) {

		return supplierRepository.findById(supplierId).orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + supplierId));
	}


}