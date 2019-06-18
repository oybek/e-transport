package io.github.oybek.repositories;

import io.github.oybek.entities.Reach;
import io.github.oybek.entities.Stop;
import org.springframework.data.repository.CrudRepository;

public interface ReachRepository extends CrudRepository<Reach, Long> {
    void deleteByStop(Stop stop);
}
