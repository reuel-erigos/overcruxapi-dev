package br.com.crux.dao.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.crux.entity.UsuariosSistema;

@Repository
public interface UsuarioSistemaRepository extends JpaRepository<UsuariosSistema, Long>{
	
	
	public Optional<UsuariosSistema> findByUsername(String username);

	@Query(value = "SELECT u FROM UsuariosSistema u "
			+ " order by u.username  ")
	public Optional<List<UsuariosSistema>> findByUnidade(Long idUnidade);


	
}
