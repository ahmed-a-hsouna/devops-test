package tn.esprit.tiramisu;

import lombok.extern.slf4j.Slf4j;
import org.junit.After;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import tn.esprit.tiramisu.entities.Supplier;
import tn.esprit.tiramisu.repositories.SupplierRepository;
import tn.esprit.tiramisu.repositories.OperatorRepository;
import tn.esprit.tiramisu.services.Iservices.ISupplierService;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import java.util.List;


@ExtendWith(SpringExtension.class)
@SpringBootTest
@Slf4j
@TestMethodOrder(OrderAnnotation.class)
public class SupplierServiceImplTest {

    @Autowired
    ISupplierService supplierService;
    @Autowired
    SupplierRepository supplierRepository;
    @Autowired
    OperatorRepository operatorRepository;




    @Test
    @Order(1)
    public void addSupplierTest() {

        Supplier supplier1 = new Supplier("123", "jilani");
        Supplier supplier2 = new Supplier("1234", "jilani");
        Supplier supplier = supplierRepository.save(supplier1);
        supplierRepository.save(supplier2);
        Assertions.assertEquals(supplier1 , supplier);

    }
    @Test
    @Order(2)
    public void updateSupplierTest() {

        Supplier supplier1 = supplierRepository.findSupplierByIdSupplier(49L);
        supplier1.setCode("12345");
        Supplier supplier = supplierRepository.save(supplier1);
        Assertions.assertEquals(supplier.getCode() , "12345");

    }

    @Test
    @Order(3)
    public void retrieveSupplierTest() {
        Supplier supplier = supplierService.retrieveSupplier(49L);
        Assertions.assertEquals(supplier.getCode(), "12345");
    }

    @Test
    @Order(3)
    public void retrieveAllSuppliersTest() {
        List<Supplier> listProduits = supplierService.retrieveAllSuppliers();
        Assertions.assertEquals(2, listProduits.size());
    }

    @Test
    @After
    public void delete() {
        supplierRepository.deleteAll();
    }





}