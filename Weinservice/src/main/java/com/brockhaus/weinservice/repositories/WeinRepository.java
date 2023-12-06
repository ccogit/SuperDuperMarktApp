package com.brockhaus.weinservice.repositories;

import com.brockhaus.weinservice.model.Wein;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WeinRepository extends JpaRepository<Wein, Long> {
}