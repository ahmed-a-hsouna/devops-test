package tn.esprit.tiramisu;

import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.internal.matchers.Equals;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import tn.esprit.tiramisu.entities.Supplier;
import tn.esprit.tiramisu.entities.SupplierDTO;
import static org.hamcrest.Matchers.*;
import org.junit.jupiter.api.Assertions;

@Slf4j
@ExtendWith(MockitoExtension.class)
@WebMvcTest(Supplier.class)
public class SupplierEntityTest {


    @Mock
    private Supplier supplier;

    @Mock
    private SupplierDTO supplierDTO;

    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(supplier).build();

    }

    @Test
    public void SupplierSetterTest() {
        Supplier supplier1 = new Supplier();
        supplier1.setLabel("7qlouf kbir");
        Assertions.assertEquals("7qlouf kbir" , supplier1.getLabel());

    }
}
