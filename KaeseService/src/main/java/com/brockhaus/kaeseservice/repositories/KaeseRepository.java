package com.brockhaus.kaeseservice.repositories;

import com.brockhaus.kaeseservice.model.Kaese;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KaeseRepository extends JpaRepository<Kaese, Long> {
}