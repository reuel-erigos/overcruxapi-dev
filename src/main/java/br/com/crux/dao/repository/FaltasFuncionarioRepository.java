package br.com.crux.dao.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.crux.entity.FaltasFuncionario;

@Repository
public interface FaltasFuncionarioRepository extends JpaRepository<FaltasFuncionario, Long>{
	
	
	@Query(value = "SELECT ff FROM FaltasFuncionario ff "
			+ " inner join Funcionario func on func = ff.funcionarioFaltou"
			+ " inner join Unidade uni on func.unidade = uni"
			+ " where uni.idUnidade = ?1")
	public Optional<List<FaltasFuncionario>> findByIdUnidade(Long idUnidade);

	@Query(value = "SELECT ff FROM FaltasFuncionario ff "
			+ " inner join Funcionario func on func = ff.funcionarioFaltou"
			+ " where func.id = ?1")
	public Optional<List<FaltasFuncionario>> getPorFuncionario(Long idFuncionario);

	
}
