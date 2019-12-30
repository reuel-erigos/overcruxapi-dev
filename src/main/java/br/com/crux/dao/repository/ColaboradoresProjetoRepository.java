package br.com.crux.dao.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.crux.entity.ColaboradoresProjeto;
import br.com.crux.entity.Projeto;

@Repository
public interface ColaboradoresProjetoRepository extends JpaRepository<ColaboradoresProjeto, Long> {

	//	@Query(value = "SELECT c FROM ColaboradoresProjeto c"
	//			+ " inner join Projeto p on c.projeto = p"
	//			+ " inner join Unidade u on p.unidade = u"
	//			+ " where u.idUnidade = :idUnidade")
//	public Optional<List<ColaboradoresProjeto>> findByIdUnidade(Long idUnidade);

	public Optional<List<ColaboradoresProjeto>> findByProjeto(Projeto projeto);

}
