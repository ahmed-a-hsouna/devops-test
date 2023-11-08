package tn.esprit.tiramisu.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.tiramisu.entities.Operator;

public interface OperatorRepository extends CrudRepository<Operator, Long> {

}
