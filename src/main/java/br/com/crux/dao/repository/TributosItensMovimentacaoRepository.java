package br.com.crux.dao.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.crux.entity.TributosItensMovimentacoes;

@Repository
public interface TributosItensMovimentacaoRepository extends JpaRepository<TributosItensMovimentacoes, Long>{


	@Query(value = "SELECT t FROM TributosItensMovimentacoes t     "
			+ " inner join Tributos tributo on tributo = t.tributo "
			+ " where t.idItemMovimentacao = ?1                    "
			+ " order by tributo.descricao                         ")
	public Optional<List<TributosItensMovimentacoes>> findAllByIdItemMovimentacao(Long idItenMovimentacao);	

	
	@Query(value = "SELECT t FROM TributosItensMovimentacoes t                "
			+ " inner join Movimentacoes mov on mov.id = t.idItemMovimentacao "
			+ " inner join Tributos tributo on tributo = t.tributo            "
			+ " where mov.id = ?1                                             "
			+ " order by tributo.descricao                                    ")
	public Optional<List<TributosItensMovimentacoes>> findAllByIdMovimentacao(Long idMovimentacao);		

	
	@Query(value = "SELECT t FROM TributosItensMovimentacoes t     "
			+ " inner join Tributos tributo on tributo = t.tributo "
			+ " where t.idItemMovimentacao = ?1                    "
			+ "   and tributo.id           = ?2                    "
			+ " order by tributo.descricao                         ")
	public Optional<TributosItensMovimentacoes> findByIdItemMovimentacaoAndIdTributo(Long idItemMovimentacao, Long idTributo);	
}

