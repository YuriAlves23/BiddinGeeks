package br.com.syndesigroup.biddingeeks.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.syndesigroup.biddingeeks.models.Lance;

@Repository
public interface LanceRepository extends JpaRepository<Lance, Long> {

}
