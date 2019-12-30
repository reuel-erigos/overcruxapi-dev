package br.com.crux.dao.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.crux.entity.PrestacoesConta;
import br.com.crux.entity.Programa;

@Repository
public interface PrestacoesContaRepository extends JpaRepository<PrestacoesConta, Long> {

	Optional<List<PrestacoesConta>> findByPrograma(Programa programa);

}
