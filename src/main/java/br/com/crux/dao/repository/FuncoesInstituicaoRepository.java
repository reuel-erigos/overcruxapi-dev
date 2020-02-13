package br.com.crux.dao.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.crux.entity.FuncoesInstituicao;
import br.com.crux.entity.Instituicao;

@Repository
public interface FuncoesInstituicaoRepository extends JpaRepository<FuncoesInstituicao, Long> {

	public Optional<List<FuncoesInstituicao>> findByInstituicao(Instituicao instituicao);

}
