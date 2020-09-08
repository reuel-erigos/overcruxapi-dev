package br.com.crux.dao.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.crux.entity.Movimentacoes;

@Repository
public interface MovimentacoesRepository extends JpaRepository<Movimentacoes, Long> {

	@Query(value = "select distinct m                                                           "
			+ "       from Movimentacoes m                                                      "
			+ "         inner join Unidade u on u = m.unidade                                   "
			+ "         inner join Instituicao inst on inst = u.instituicao                     "
			+ "         left join Empresa empresa on empresa = m.empresa                        "
			+ "         left join RateiosMovimentacoes rateio on rateio.idMovimentacao = m.id   "
			+ "         left join Programa programa on programa = rateio.programa               "
			+ "         left join Projeto projeto on projeto = rateio.projeto                   "
			+ "         left join Fatura f on f.idMovimentacao = m.id                           "
			+ "         left join PagamentosFatura pf on pf.idFatura = f.id                     "
			+ "   where 1=1                                                                     "
			+ "     and m.stTipoMovimentacao != 'T'                                             "
			+ "     and inst.id = ?1                                                            "
			+ "     and (?2 is null or empresa.id = ?2)                                         "
			+ "     and (?3 is null or programa.id = ?3)                                        "
			+ "     and (?4 is null or projeto.id = ?4)                                         "
			+ "     and (?5 is null or m.valorMovimentacao = ?5)                                "
			+ "     and (coalesce(?6, null) is null or f.dataVencimento.toLocalDate() = ?6)     ")		
	public Optional<List<Movimentacoes>> findByFilterOrigem(Long idInstituicao, 
			                                                Long idEmpresa, 
			                                                Long idPrograma, 
			                                                Long idProjeto, 
			                                                Double valor,
			                                                LocalDate dataVencimento);
	
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
	
	
	@Query(value = "select f                                                "
			+ "       from Movimentacoes f                                  "
			+ "         inner join Doadores doador on doador = f.doador     "
			+ "         inner join Unidade u on u = f.unidade               "
			+ "         inner join Instituicao inst on inst = u.instituicao "
			+ "   where 1=1                                                 "
			+ "     and inst.id = ?1                                        "
			+ "     and doador.id = ?2                                      ")
	public Optional<List<Movimentacoes>> getAllByIdDoador(Long idInstituicao, Long idDoador);
	

}
