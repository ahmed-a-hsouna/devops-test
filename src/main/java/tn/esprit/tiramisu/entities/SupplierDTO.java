package tn.esprit.tiramisu.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@Data
@FieldDefaults(level = AccessLevel.PUBLIC)
public class SupplierDTO implements Serializable {

    Long idSupplier;
    String code;
    String label;
    String category;
    @OneToMany(mappedBy="supplier")
    @JsonIgnore
    private Set<Invoice> invoices;

    public SupplierDTO( String code , String label){
        this.code = code;
        this.label = label;
    }

    public SupplierDTO( String code , String label, String category){
        this.code = code;
        this.label = label;
        this.category = category;
    }



}