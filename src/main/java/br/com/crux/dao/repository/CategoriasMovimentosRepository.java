
package br.com.crux.dao.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.crux.entity.CategoriasMovimentos;

@Repository
public interface CategoriasMovimentosRepository extends JpaRepository<CategoriasMovimentos, Long> {

	@Query(value = "SELECT cm FROM CategoriasMovimentos cm              "
			+ " inner join Movimentacoes m  on m.id = cm.idMovimentacao "
			+ " where m.id = :id                                        ")
	public Optional<List<CategoriasMovimentos>> findByIdMovimentacao(Long id);

}
