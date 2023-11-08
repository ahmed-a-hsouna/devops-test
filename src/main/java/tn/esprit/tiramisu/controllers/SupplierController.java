package tn.esprit.tiramisu.controllers;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.tiramisu.entities.Supplier;
import tn.esprit.tiramisu.entities.SupplierDTO;
import tn.esprit.tiramisu.services.Iservices.ISupplierService;

import java.util.List;


@RestController
@AllArgsConstructor
@CrossOrigin(origins = "*")
public class SupplierController {

	@Autowired
	ISupplierService supplierService;

	@GetMapping("/supplier")
	@CrossOrigin(origins = "*")
	public List<Supplier> getSuppliers() {
		return supplierService.retrieveAllSuppliers();
	}

	@GetMapping("/supplier/{supplierId}")
		@CrossOrigin(origins = "*")

	public Supplier retrieveSupplier(@PathVariable Long supplierId) {
		return supplierService.retrieveSupplier(supplierId);
	}

	@PostMapping("/supplier")
		@CrossOrigin(origins = "*")

	public ResponseEntity<Supplier> addSupplier(@RequestBody SupplierDTO supplierDTO) {
		return new ResponseEntity<>(supplierService.addSupplier(supplierDTO) , HttpStatus.CREATED);
	}

	@DeleteMapping("/supplier/{supplierId}")
		@CrossOrigin(origins = "*")

	public void removeSupplier(@PathVariable Long supplierId) {
		supplierService.deleteSupplier(supplierId);
	}

	@PutMapping("/supplier")
		@CrossOrigin(origins = "*")

	public Supplier updateSupplier(@RequestBody SupplierDTO supplierDTO) {
		return supplierService.updateSupplier(supplierDTO);
	}

}
