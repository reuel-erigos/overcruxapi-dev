package br.com.crux.dao.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.crux.entity.ContasBancaria;

@Repository
public interface ContasBancariaRepository extends JpaRepository<ContasBancaria, Long> {

	@Query(value = "select f from ContasBancaria f " + " inner join Unidade u on u = f.unidade " + " where u.idUnidade = ?1")
	public Optional<List<ContasBancaria>> findByIdUnidade(Long idUnidade);

	
	@Query(value = "select f from ContasBancaria f " 
	             + " inner join Unidade u on u = f.unidade "
	             + " inner join Instituicao i on i = u.instituicao " 
			      + " where i.id = ?1"
			      + " order by f.numeroBanco, f.nomeBanco, f.numeroAgencia, f.numeroContaBancaria ")
	public Optional<List<ContasBancaria>> findByIdInstituicao(Long idInstituicao);

	
	@Query(value = 	  " select distinct cb.*                                                                                                                 "
			+ "   from contas_bancarias cb                                                                                                                   "
			+ "     inner join (select ccc.id_conta_bancaria                                                                                                 "
			+ "                   from contas_centros_custo ccc                                                                                              "
			+ "                     inner join parcerias_programas	pp on pp.id_parceria_programa = ccc.id_parceria_programa                                 "
			+ "                 union                                                                                                                        "
			+ "                 select ccc.id_conta_bancaria                                                                                                 "
			+ "                   from contas_centros_custo ccc                                                                                              "
			+ "                     inner join parcerias_projetos	ppj on ppj.id_parceria_projeto = ccc.id_parceria_projeto                                 "
			+ "                 where ?1                                                                                                                     "
			+ "                        between DATE_TRUNC('DAY', dt_inicio_parceria)                                                                         "
			+ "                            and coalesce( DATE_TRUNC('DAY',dt_fim_parceria) , ?1  )                                                           "
			+ "      ) ccc on ccc.id_conta_bancaria = cb.id_conta_bancaria                                                                                   "
		      + " order by cb.nr_banco, cb.nm_banco, cb.nr_agencia, cb.nr_conta_bancaria ", nativeQuery = true)
	public Optional<List<ContasBancaria>> findAllContasCentroCustos(Long idInstituicao, String dataReembolso);

	
	
}
