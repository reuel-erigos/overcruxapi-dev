package br.com.crux.dao.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.crux.entity.MovimentacoesMateriais;

@Repository
public interface MovimentacoesMateriaisRepository extends JpaRepository<MovimentacoesMateriais, Long> {

	@Query(value = "select f from MovimentacoesMateriais f" + 
	" inner join Unidade u on u = f.unidade " + "where u.idUnidade = ?1")
	public Optional<List<MovimentacoesMateriais>> findByIdUnidade(Long idUnidade);

}
