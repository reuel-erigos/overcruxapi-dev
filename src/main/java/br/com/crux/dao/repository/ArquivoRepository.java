package br.com.crux.dao.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.crux.entity.Arquivo;

@Repository
public interface ArquivoRepository extends JpaRepository<Arquivo, Long> {

	@Query(value = "SELECT a FROM Arquivo a inner join PessoaFisica pf on pf.idArquivo = a.idArquivo"
			+ " where pf.id = ?1")
	public Optional<Arquivo> findByIdPessoaFisica(Long idPessoaFisica);
	
	@Query(value = "SELECT a FROM Arquivo a inner join Unidade u on u.idArquivo = a.idArquivo"
			+ " where u.id = ?1")
	public Optional<Arquivo> findByIdUnidade(Long idUnidade);
	
	@Query(value = "SELECT a FROM Arquivo a inner join Instituicao i on i.idArquivo = a.idArquivo"
			+ " where i.id = ?1")
	public Optional<Arquivo> findByIdInstituicao(Long idInstituicao);
	

}
