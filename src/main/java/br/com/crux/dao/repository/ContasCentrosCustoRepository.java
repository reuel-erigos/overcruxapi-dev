
package br.com.crux.dao.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.crux.entity.ContasCentrosCusto;

@Repository
public interface ContasCentrosCustoRepository extends JpaRepository<ContasCentrosCusto, Long> {

	
	@Query(value = "select distinct ccc from ContasCentrosCusto ccc "
			+ " inner join ParceriasPrograma pp on pp = ccc.parceriasPrograma "
			+ " inner join ContasBancaria cb on cb = ccc.contasBancaria " 
            + " inner join Unidade u on u = cb.unidade "
            + " inner join Instituicao i on i = u.instituicao " 
		      + " where i.id = ?1  "
		      + "   and pp.id = ?2 "
		      + " order by cb.numeroBanco, cb.nomeBanco, cb.numeroAgencia, cb.numeroContaBancaria ")
	public Optional<List<ContasCentrosCusto>> findByParceriasPrograma(Long idInstituicao, Long idParceriasPrograma);

	
	@Query(value = "select distinct ccc from ContasCentrosCusto ccc "
			+ " inner join ParceriasProjeto pp on pp = ccc.parceriasProjeto "
			+ " inner join ContasBancaria cb on cb = ccc.contasBancaria " 
            + " inner join Unidade u on u = cb.unidade "
            + " inner join Instituicao i on i = u.instituicao " 
		      + " where i.id = ?1  "
		      + "   and pp.id = ?2 "
		      + " order by cb.numeroBanco, cb.nomeBanco, cb.numeroAgencia, cb.numeroContaBancaria ")
	public Optional<List<ContasCentrosCusto>> findByParceriasProjeto(Long idInstituicao, Long idParceriasProjeto);

}
