package br.com.crux.dao.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.crux.entity.RateiosCategoriasMovimentos;

@Repository
public interface RateiosCategoriasMovimentosRepository extends JpaRepository<RateiosCategoriasMovimentos, Long> {

	@Query(value = "select rm from RateiosCategoriasMovimentos rm                       "
			+ " inner join CategoriasMovimentos cm on cm.id = rm.idCategoriaMovimento   "
			+ " inner join Movimentacoes m on m.id = cm.idMovimento                     "
			+ " where m.id = ?1                                                         ")
	public Optional<List<RateiosCategoriasMovimentos>> findByIdMovimento(Long idMovimento);

	
	@Query(value = "select rm from RateiosCategoriasMovimentos rm                       "
			+ " inner join CategoriasMovimentos cm on cm.id = rm.idCategoriaMovimento   "
			+ " where cm.id = ?1                                                        ")
	public Optional<List<RateiosCategoriasMovimentos>> findByIdCategoria(Long idCategoria);

}
