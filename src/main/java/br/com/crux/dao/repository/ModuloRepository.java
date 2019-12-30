package br.com.crux.dao.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.crux.entity.Modulo;

@Repository
public interface ModuloRepository extends JpaRepository<Modulo, Long>{
	
	public Optional<Modulo> findByNome(String nomeModulo);
	
	public List<Modulo> findAllByOrderByDescricaoAsc();

}
