package br.com.crux.dao.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.crux.entity.AlocacoesFuncionario;

@Repository
public interface AlocacaoFuncionarioRepository extends JpaRepository<AlocacoesFuncionario, Long>{
	
	@Query("select d from AlocacoesFuncionario d "
			+ " inner join Funcionario f on f.id = d.funcionario.id "
			+ " where f.id = ?1")
	Optional<List<AlocacoesFuncionario>> findAllByFuncionario(Long idFuncionario);
}
