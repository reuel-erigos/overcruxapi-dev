package br.com.crux.dao.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.crux.entity.CadastroReservaAtividade;

@Repository
public interface CadastroReservaAtividadeRepository extends JpaRepository<CadastroReservaAtividade, Long> {

	@Query(value = "SELECT p FROM CadastroReservaAtividade p " + " inner join Atividades ati on ati = p.atividade" + " where ati.id = ?1")
	Optional<List<CadastroReservaAtividade>> getPorAtividade(Long id);

}
