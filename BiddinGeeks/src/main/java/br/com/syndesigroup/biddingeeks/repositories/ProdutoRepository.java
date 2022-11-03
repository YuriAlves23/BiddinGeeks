package br.com.syndesigroup.biddingeeks.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.syndesigroup.biddingeeks.models.Produto;
import br.com.syndesigroup.biddingeeks.models.Usuario;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {

	@Query(value = "SELECT p FROM Produto p WHERE p.nome LIKE %?1%")
	List<Produto> buscarPorNome(String nome);
	
	List<Produto> findByUsuario(Usuario usuario);
}
