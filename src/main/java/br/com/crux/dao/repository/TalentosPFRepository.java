package br.com.crux.dao.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.crux.entity.TalentosPf;

@Repository
public interface TalentosPFRepository extends JpaRepository<TalentosPf, Long> {

	@Query(value = "SELECT t FROM TalentosPf t inner join PessoaFisica pf on t.pessoasFisica = pf" + " where pf.id = ?1")
	public Optional<List<TalentosPf>> getByIdPessoaFisica(Long idPessoa);
	
	@Query(value = "SELECT t FROM TalentosPf t "
			+ " inner join PessoaFisica pf on t.pessoasFisica = pf"
			+ " inner join Funcionario f on f.pessoasFisica = pf"
			+ " inner join Unidade u on f.unidade = u"
			+ " where u.idUnidade = ?1")
	public Optional<List<TalentosPf>> findAllByUnidade(Long idUnidade);
	
}
