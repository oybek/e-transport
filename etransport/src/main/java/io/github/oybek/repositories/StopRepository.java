package io.github.oybek.repositories;

import io.github.oybek.entities.Stop;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface StopRepository extends CrudRepository<Stop, String> {

    List<Stop> findAllByType(String type);
}
