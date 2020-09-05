package br.com.crux.dao.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.crux.entity.Programa;

@Repository
public interface ProgramaRepository extends JpaRepository<Programa, Long>{

	@Query(value = "SELECT p FROM Programa p"
			+ " inner join ProgramasUnidade pu on pu.programa = p "
			+ " where pu.unidade.idUnidade = :idUnidade"
			+ " order by p.nome ")
	public Optional<List<Programa>> findByIdUnidade(Long idUnidade);
	

	@Query(value = "SELECT p FROM Programa p "
			+ " inner join ProgramasUnidade pu on pu.programa = p "
			+ " inner join Unidade uni on uni = pu.unidade "
			+ " inner join Instituicao ins on ins = uni.instituicao "
			+ " where ins.id = ?1 "
			+ "   and uni.id = ?2 "
			+ " order by p.nome ")
	public Optional<List<Programa>> findByIdInstituicaoAndIdUnidade(Long idInstituicao, Long idUnidade);	

}

