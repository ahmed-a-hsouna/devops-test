package tn.esprit.tiramisu;

import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import tn.esprit.tiramisu.entities.Supplier;
import tn.esprit.tiramisu.entities.SupplierDTO;
import tn.esprit.tiramisu.entities.Operator;
import tn.esprit.tiramisu.entities.Supplier;
import tn.esprit.tiramisu.repositories.SupplierRepository;
import tn.esprit.tiramisu.repositories.OperatorRepository;
import tn.esprit.tiramisu.repositories.SupplierRepository;
import tn.esprit.tiramisu.services.SupplierServiceImpl;

import java.time.Instant;
import java.util.*;

@Slf4j
@ExtendWith(MockitoExtension.class)
@WebMvcTest(SupplierServiceImpl.class)
public class SupplierServiceTest {

    @Mock
    private Supplier inv;

    @Mock
    private Supplier sup;

    @Mock
    private Operator op;
    @Mock
    private SupplierRepository supplierRepository;


    @Mock
    private OperatorRepository operatorRepository;

    @InjectMocks
    private SupplierServiceImpl supplierServiceImpl;

    Supplier supplier = Supplier.builder().idSupplier(1L).code("123").label("jilani").build();

    SupplierDTO supplierDTO = SupplierDTO.builder().idSupplier(1L).code("123").label("jilani").build();

    List<Supplier> records = new ArrayList<Supplier>() {
        {
            add(
                    Supplier.builder().idSupplier(1L).code("123").label("jilani").build()
            );
            add(
                    Supplier.builder().idSupplier(2L).code("12345").label("yassine").build()
            );
            add(
                    Supplier.builder().idSupplier(3L).code("1234567").label("fares").build()
            );
        }
    };


    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(supplierServiceImpl).build();
    }


    @Test
    public void getAllSuppliersServiceTest() {
        Mockito.when(supplierRepository.findAll()).thenReturn(records);
        List<Supplier> supplierList = supplierServiceImpl.retrieveAllSuppliers();
        Assertions.assertEquals("jilani", supplierList.get(0).getLabel());
    }

    @Test
    public void getOneSupplierServiceTest() {
        Mockito.when(supplierRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(supplier));
        Supplier supplier1 = supplierServiceImpl.retrieveSupplier(1L);
        Assert.assertNotNull(supplier1);
        log.info("get ===> " + supplier1.toString());
        Mockito.verify(supplierRepository).findById(Mockito.anyLong());
    }

    @Test
    public void addSupplierServiceTest() {
        log.info("first : " + supplier.getIdSupplier());
        Mockito.when(supplierRepository.save(Mockito.any())).thenReturn(supplier);
        Supplier supplier1 = supplierServiceImpl.addSupplier(supplierDTO);
        log.info("second : " + supplier1.toString());
        Assert.assertNotNull(supplier1);
    }

    @Test
    public void deleteSupplierServiceTest() {
        supplierServiceImpl.deleteSupplier(supplier.getIdSupplier());
        Mockito.verify(supplierRepository).deleteById(supplier.getIdSupplier());
    }


    @Test
    public void getSuppliersBySupplierServiceTest() {
        Mockito.when(supplierRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(supplier));
        Optional<Supplier> supplier1 = supplierRepository.findById(supplier.getIdSupplier());
        log.info("first : " + supplier1.isPresent());
        Assert.assertNotNull(supplier1.toString());
    }




}
