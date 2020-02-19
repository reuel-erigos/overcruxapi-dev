
package br.com.crux.dao.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.crux.entity.ItensMovimentacoesMateriais;
import br.com.crux.entity.MovimentacoesMateriais;

@Repository
public interface ItensMovimentacoesMateriaisRepository extends JpaRepository<ItensMovimentacoesMateriais, Long> {

	public Optional<List<ItensMovimentacoesMateriais>> findByMovimentacoesMateriais(MovimentacoesMateriais movimentacoesMateriais);

}
