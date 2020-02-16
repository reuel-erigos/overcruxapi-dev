
package br.com.crux.dao.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.crux.entity.ItensMovimentacoes;
import br.com.crux.entity.Movimentacoes;

@Repository
public interface ItensMovimentacoesRepository extends JpaRepository<ItensMovimentacoes, Long> {

	public Optional<List<ItensMovimentacoes>> findByMovimentacao(Movimentacoes movimentacao);

}
