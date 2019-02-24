package com.oybek.etransport.repositories;

import com.oybek.etransport.entities.Reach;
import com.oybek.etransport.entities.Stop;
import org.springframework.data.repository.CrudRepository;

public interface ReachRepository extends CrudRepository<Reach, Long> {
    void deleteByStop(Stop stop);
}
