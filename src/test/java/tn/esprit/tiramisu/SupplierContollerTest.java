package tn.esprit.tiramisu;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import tn.esprit.tiramisu.controllers.SupplierController;
import tn.esprit.tiramisu.entities.*;
import tn.esprit.tiramisu.repositories.SupplierRepository;
import tn.esprit.tiramisu.services.Iservices.ISupplierService;
import tn.esprit.tiramisu.services.SupplierServiceImpl;
import java.util.*;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Slf4j
@ExtendWith(MockitoExtension.class)
@WebMvcTest(SupplierController.class)
public class SupplierContollerTest {

    private MockMvc mockMvc;

    static ObjectMapper objectMapper = new ObjectMapper();
    static ObjectWriter objectWriter = objectMapper.writer();
    public static String asJsonString(final Object obj) {
        try {
            objectMapper.registerModule(new JavaTimeModule());
            return objectMapper.writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Mock
    private Supplier sup;

    @Mock
    private Operator op;

    @Mock
    private ISupplierService supplierService;

    @Mock
    private SupplierRepository supplierRepository;

    @InjectMocks
    private SupplierServiceImpl supplierServiceImpl;

    @InjectMocks
    private SupplierController supplierController;


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
        this.mockMvc = MockMvcBuilders.standaloneSetup(supplierController).build();
    }

    @Test
    public void retrieveSuppliersTest() throws Exception {

        Mockito.when(supplierService.retrieveAllSuppliers()).thenReturn(records);
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/supplier")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$" , hasSize(3)))
                .andExpect(jsonPath("$[2].label" , is("fares")));
    }

    @Test
    public void createSupplierTest() throws Exception
    {

        Mockito.when(supplierService.addSupplier(supplierDTO)).thenReturn(supplier);
        mockMvc.perform(MockMvcRequestBuilders.post("/supplier")
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("utf-8")
                        .content(asJsonString(supplierDTO)))
                .andExpect(status().isCreated())
                .andReturn();
    }


    @Test
    public void deleteSupplierTest() throws Exception
    {
        Mockito.when(supplierRepository.findById(supplier.getIdSupplier())).thenReturn(Optional.ofNullable(supplier));
        mockMvc.perform(MockMvcRequestBuilders.delete("/supplier/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("utf-8")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    public void retrieveSupplierTest() throws Exception
    {
        Mockito.when(supplierService.retrieveSupplier(Mockito.anyLong())).thenReturn(supplier);
        mockMvc.perform(MockMvcRequestBuilders.get("/supplier/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("utf-8")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", notNullValue()))
                .andReturn();
    }

    @Test
    public void updateSupplierTest() throws Exception
    {
        Supplier updatedSupplier = Supplier.builder().idSupplier(1L).code("1234").label("jilani").build();

        Mockito.when(supplierService.updateSupplier(supplierDTO)).thenReturn(updatedSupplier);
        mockMvc.perform(MockMvcRequestBuilders.put("/supplier")
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("utf-8")
                        .content(asJsonString(supplierDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code" , is("1234")))
                .andReturn();
    }


}
