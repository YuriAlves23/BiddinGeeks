package br.com.syndesigroup.biddingeeks.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.syndesigroup.biddingeeks.models.Lance;
import br.com.syndesigroup.biddingeeks.models.Produto;

@Repository
public interface LanceRepository extends JpaRepository<Lance, Long> {

	List<Lance> findByProduto(Produto produto);
}
