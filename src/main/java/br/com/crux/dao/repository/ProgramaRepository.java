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
			+ " where pu.unidade.idUnidade = :idUnidade")
	public Optional<List<Programa>> findByIdUnidade(Long idUnidade);
	

	@Query(value = "SELECT p FROM Programa p "
			+ " inner join Objetivo ob on ob.idObjetivo = p.objetivo.idObjetivo "
			+ " inner join Perspectiva pe on pe.idPerspectiva = ob.perspectiva.idPerspectiva"
			+ " inner join Unidade uni on uni.idUnidade = pe.unidade.idUnidade "
			+ " inner join Instituicao ins on ins.id = uni.instituicao.id"
			+ " where ins.id = ?1")
	public Optional<List<Programa>> findByIdInstituicao(Long idInstituicao);	

}

