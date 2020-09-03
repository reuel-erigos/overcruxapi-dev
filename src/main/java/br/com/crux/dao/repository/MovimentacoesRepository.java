package br.com.crux.dao.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.crux.entity.Movimentacoes;

@Repository
public interface MovimentacoesRepository extends JpaRepository<Movimentacoes, Long> {

	
	@Query(value = "select f                                                "
			+ "       from Movimentacoes f                                  "
			+ "         inner join Unidade u on u = f.unidade               "
			+ "         inner join Instituicao inst on inst = u.instituicao "
			+ "   where 1=1                                                 "
			+ "     and inst.id = ?1                                    "
			+ "     and f.stTipoMovimentacao = 'T'                          ")
	public Optional<List<Movimentacoes>> getAllTipoMovimentoDestino(Long idInstituicao);
	
	@Query(value = "select f                                                "
			+ "       from Movimentacoes f                                  "
			+ "         inner join Unidade u on u = f.unidade               "
			+ "         inner join Instituicao inst on inst = u.instituicao "
			+ "   where 1=1                                                 "
			+ "     and inst.id = ?1                                    "
			+ "     and f.stTipoMovimentacao != 'T'                          ")
	public Optional<List<Movimentacoes>> getAllTipoMovimentoOrigem(Long idInstituicao);
	

}
