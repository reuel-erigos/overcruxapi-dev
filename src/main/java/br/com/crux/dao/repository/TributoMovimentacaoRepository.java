package br.com.crux.dao.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.crux.entity.TributosMovimentacoes;

@Repository
public interface TributoMovimentacaoRepository extends JpaRepository<TributosMovimentacoes, Long>{


	@Query(value = "SELECT t FROM TributosMovimentacoes t          "
			+ " inner join Tributos tributo on tributo = t.tributo "
			+ " where t.idMovimentacao = ?1 "
			+ " order by tributo.descricao ")
	public Optional<List<TributosMovimentacoes>> findByIdMovimentacao(Long idMovimentacao);	

}

