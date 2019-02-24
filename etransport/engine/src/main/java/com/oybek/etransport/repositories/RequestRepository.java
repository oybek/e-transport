package com.oybek.etransport.repositories;

import com.oybek.etransport.entities.Request;
import org.springframework.data.repository.CrudRepository;

public interface RequestRepository extends CrudRepository<Request, Long> {
}
