
package br.com.crux.dao.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.crux.entity.ContasCentrosCusto;

@Repository
public interface ContasCentrosCustoRepository extends JpaRepository<ContasCentrosCusto, Long> {

	

	@Query(value = " select c.id_conta_centro_custo, c.id_conta_bancaria, c.id_usuario_apl, c.id_parceria_programa, c.id_parceria_projeto  "
			+ "   from contas_centros_custo c                                                                                         "
			+ "   inner join parcerias_programas pp on pp.id_parceria_programa = c.id_parceria_programa                               "
			+ "   inner join contas_bancarias cb on cb.id_conta_bancaria = c.id_conta_bancaria                                        "
			+ "   inner join unidades u on u.id_unidade = cb.id_unidade                                                               "
			+ "   inner join instituicoes i on i.id_instituicao = u.id_instituicao                                                    "
			+ "  where 1=1                                                                                                            "
			+ "    and i.id_instituicao        = ?1                                                                                   "
			+ "    and pp.id_parceria_programa = ?2                                                                                   "
			+ "   order by cb.nr_banco, cb.nm_banco, cb.nr_agencia, cb.nr_conta_bancaria                                              ", nativeQuery = true)
	public Optional<List<ContasCentrosCusto>> findByParceriasPrograma(Long idInstituicao, Long idParceriasPrograma);

	
	@Query(value = " select c.id_conta_centro_custo, c.id_conta_bancaria, c.id_usuario_apl, c.id_parceria_programa, c.id_parceria_projeto  "
			+ "   from contas_centros_custo c                                                                                         "
			+ "   inner join parcerias_projetos pp on pp.id_parceria_projeto = c.id_parceria_projeto                                  "
			+ "   inner join contas_bancarias cb on cb.id_conta_bancaria = c.id_conta_bancaria                                        "
			+ "   inner join unidades u on u.id_unidade = cb.id_unidade                                                               "
			+ "   inner join instituicoes i on i.id_instituicao = u.id_instituicao                                                    "
			+ "  where 1=1                                                                                                            "
			+ "    and i.id_instituicao        = ?1                                                                                   "
			+ "    and pp.id_parceria_projeto  = ?2                                                                                   "
			+ "   order by cb.nr_banco, cb.nm_banco, cb.nr_agencia, cb.nr_conta_bancaria                                              ", nativeQuery = true)
	public Optional<List<ContasCentrosCusto>> findByParceriasProjeto(Long idInstituicao, Long idParceriasProjeto);

}
