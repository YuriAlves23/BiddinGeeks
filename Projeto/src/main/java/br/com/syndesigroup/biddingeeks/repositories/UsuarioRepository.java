package br.com.syndesigroup.biddingeeks.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.syndesigroup.biddingeeks.models.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

	@Query(value = "SELECT u FROM Usuario u WHERE u.nome LIKE %?1%")
	List<Usuario> buscarPorNome(String nome);
	
	@Query(value = "SELECT * FROM Usuario WHERE email = :email AND password = :password", nativeQuery = true)
	Usuario fazerLogin(@Param("email") String email, @Param("password")String password);
}
