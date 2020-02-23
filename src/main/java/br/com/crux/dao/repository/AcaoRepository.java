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
			+ " inner join Oficinas ofi on ofi = acao.oficina"
			+ " inner join Unidade uni on ofi.unidade = uni"
			+ " where uni.idUnidade = ?1")
	public Optional<List<Acoes>> findByUnidade(Long idUnidade);
	
	@Query(value = "SELECT acao FROM Acoes acao "
			+ " inner join Oficinas ofi on ofi = acao.oficina"
			+ " where ofi.id = ?1")
	public Optional<List<Acoes>> findAllByOficina(Long idOficina);	

	
}
