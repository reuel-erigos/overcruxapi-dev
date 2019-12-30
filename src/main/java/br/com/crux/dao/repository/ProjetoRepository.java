package br.com.crux.dao.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.crux.entity.Projeto;

@Repository
public interface ProjetoRepository extends JpaRepository<Projeto, Long>{

	@Query(value = "SELECT p FROM Projeto p"
			+ " inner join ProjetosUnidade pu on pu.projeto = p "
			+ " where pu.unidade.idUnidade = :idUnidade")	
	public Optional<List<Projeto>> findByIdUnidade(Long idUnidade);
	
	
	@Query(value = "SELECT p FROM Projeto pj "
			+ " inner join Programa p on p.id = pj.programa.id "
			+ " inner join Objetivo ob on ob.idObjetivo = p.objetivo.idObjetivo "
			+ " inner join Perspectiva pe on pe.idPerspectiva = ob.perspectiva.idPerspectiva"
			+ " inner join Unidade uni on uni.idUnidade = pe.unidade.idUnidade "
			+ " inner join Instituicao ins on ins.id = uni.instituicao.id"
			+ " where ins.id = ?1")
	public Optional<List<Projeto>> findByIdInstituicao(Long idInstituicao);	
	
	
	
	@Query(value = "SELECT p FROM Projeto pj "
			+ " inner join Programa p on p.id = pj.programa.id "
			+ " where p.id = ?1")
	public Optional<List<Projeto>> findByIdPrograma(Long idPrograma);	
}
