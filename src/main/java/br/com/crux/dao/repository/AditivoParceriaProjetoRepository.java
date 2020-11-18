package br.com.crux.dao.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.crux.entity.AditivoParceriaProjeto;
import br.com.crux.entity.ParceriasProjeto;

@Repository
public interface AditivoParceriaProjetoRepository extends JpaRepository<AditivoParceriaProjeto, Long> {

	public Optional<List<AditivoParceriaProjeto>> findByParceriasProjeto(ParceriasProjeto ParceriasProjeto);

}
