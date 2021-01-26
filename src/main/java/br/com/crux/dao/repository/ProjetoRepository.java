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
			+ " where pu.unidade.idUnidade = :idUnidade"
			+ " order by pj.nome ")	
	public Optional<List<Projeto>> findByIdUnidade(Long idUnidade);
	
	
	@Query(value = "SELECT distinct pj FROM Projeto pj "
			+ " inner join ProjetosUnidade pu on pu.projeto = pj "
			+ " inner join Unidade uni on uni = pu.unidade "
			+ " inner join Instituicao ins on ins = uni.instituicao"
			+ " where ins.id = ?1"
			+ " order by pj.nome ")
	public Optional<List<Projeto>> findByIdInstituicao(Long idInstituicao);	
	
	
	
	@Query(value = "SELECT pj FROM Projeto pj "
			+ " inner join Programa p on p.id = pj.programa.id "
			+ " where p.id = ?1"
			+ " order by pj.nome ")
	public Optional<List<Projeto>> findByIdPrograma(Long idPrograma);	
}
