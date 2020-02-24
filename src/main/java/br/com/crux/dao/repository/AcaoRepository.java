package br.com.crux.dao.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.crux.entity.Acoes;

@Repository
public interface AcaoRepository extends JpaRepository<Acoes, Long>{
	
	
	
	@Query(value = "SELECT acao FROM Acoes acao "
			+ " inner join Oficinas oficina on oficina = acao.oficina "
			+ " inner join Unidade uni on oficina.unidade = uni"
			+ " where 1 =1 "
			+ "   and (?1 is null or uni.idUnidade = ?1) "
			+ "   and (?2 is null or oficina.id = ?2) "
			+ "   and (?3 is null or acao.id =  ?3) ")
	public Optional<List<Acoes>> findByFilterSemTurma(Long idUnidade, 
					                                  Long idOficina, 
					                                  Long idAcao);
	

	
	@Query(value = "SELECT acao FROM Acoes acao "
			+ " inner join Oficinas oficina on oficina = acao.oficina "
			+ " inner join Turmas turma on turma.id = oficina.idTurma "
			+ " inner join Unidade uni on turma.unidade = uni"
			+ " where 1 =1 "
			+ "   and (?1 is null or uni.idUnidade = ?1) "
			+ "   and (?2 is null or turma.id = ?2) "
			+ "   and (?3 is null or oficina.id = ?3) "
			+ "   and (?4 is null or acao.id =  ?4) ")
	public Optional<List<Acoes>> findByFilterComTurma(Long idUnidade,
			                                          Long idTurma,
					                                  Long idOficina, 
					                                  Long idAcao);
	
	
	
	
	@Query(value = "SELECT a FROM Acoes a "
			+ " where 1 = 1 "
			+ "   and a.id = ?1 ")
	public Optional<List<Acoes>> findByAcao(Long idAcao);
	
	@Query(value = "SELECT acao FROM Acoes acao "
			+ " inner join Oficinas ofi on ofi = acao.oficina"
			+ " inner join Unidade uni on ofi.unidade = uni"
			+ " where uni.idUnidade = ?1")
	public Optional<List<Acoes>> findByUnidade(Long idUnidade);

	
	@Query(value = "SELECT acao FROM Acoes acao "
			+ " inner join Oficinas ofi on ofi = acao.oficina"
			+ " where ofi.id = ?1")
	public Optional<List<Acoes>> findByOficina(Long idOficina);	

	
	@Query(value = "SELECT a FROM Acoes a "
			+ " inner join Oficinas oficina on oficina = a.oficina "
			+ " inner join Turmas turma on turma.id = oficina.idTurma"
			+ " where 1 =1 "
			+ "   and turma.id = ?1 " )
	public Optional<List<Acoes>> findByTurma(Long idTurma);
	
	
	
}
