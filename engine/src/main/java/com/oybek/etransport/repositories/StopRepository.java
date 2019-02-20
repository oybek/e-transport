package com.oybek.etransport.repositories;

import com.oybek.etransport.entities.Stop;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface StopRepository extends CrudRepository<Stop, String> {

    List<Stop> findAllByType(String type);
}
