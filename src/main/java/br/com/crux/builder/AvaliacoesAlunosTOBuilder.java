package br.com.crux.builder;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.crux.cmd.GetAtividadesAlunoCmd;
import br.com.crux.cmd.GetAvaliacoesCmd;
import br.com.crux.entity.AtividadesAluno;
import br.com.crux.entity.Avaliacoes;
import br.com.crux.entity.AvaliacoesAlunos;
import br.com.crux.enums.NotaAvaliacao;
import br.com.crux.to.AvaliacoesAlunosTO;

@Component
public class AvaliacoesAlunosTOBuilder {

	@Autowired private AvaliacoesTOBuilder avaliacoesBuilder;
	@Autowired private AtividadesAlunoTOBuilder atividadesBuilder;
	@Autowired private GetAvaliacoesCmd getAvaliacoesCmd;
	@Autowired private GetAtividadesAlunoCmd getAtividadesAlunoCmd;

	public AvaliacoesAlunos build(AvaliacoesAlunosTO p) {
		AvaliacoesAlunos retorno = new AvaliacoesAlunos();

		retorno.setId(p.getId());
		retorno.setDescriaoAvaliacao(p.getDescriaoAvaliacao());
		retorno.setDataAvaliacao(p.getDataAvaliacao());

		Optional.ofNullable(p.getAtividadesAluno()).ifPresent(aa -> {
			AtividadesAluno atiAluno = getAtividadesAlunoCmd.getById(aa.getId());
			retorno.setAtividadesAluno(atiAluno);
		});

		Optional.ofNullable(p.getAvaliacoes()).ifPresent(aa -> {
			Avaliacoes avaliacoes = getAvaliacoesCmd.getById(aa.getId());
			retorno.setAvaliacoes(avaliacoes);
		});

		Optional.ofNullable(p.getNotaAvaliacao()).ifPresent(nota -> {
			retorno.setNotaAvaliacao(NotaAvaliacao.getPorTipo(nota));
		});
		
		retorno.setUsuarioAlteracao(p.getUsuarioAlteracao());

		return retorno;
	}

	public AvaliacoesAlunosTO buildTO(AvaliacoesAlunos p) {
		AvaliacoesAlunosTO retorno = new AvaliacoesAlunosTO();

		if (Objects.isNull(p)) {
			return retorno;
		}

		retorno.setId(p.getId());
		retorno.setDescriaoAvaliacao(p.getDescriaoAvaliacao());
		retorno.setDataAvaliacao(p.getDataAvaliacao());
		retorno.setAtividadesAluno(atividadesBuilder.buildTO(p.getAtividadesAluno()));
		retorno.setAvaliacoes(avaliacoesBuilder.buildTO(p.getAvaliacoes()));
		Optional.ofNullable(p.getNotaAvaliacao()).ifPresent(nota -> {
			retorno.setNotaAvaliacao(nota.getTipo());
		});
		retorno.setUsuarioAlteracao(p.getUsuarioAlteracao());

		return retorno;
	}

	public List<AvaliacoesAlunosTO> buildAll(List<AvaliacoesAlunos> dtos) {
		return dtos.stream().map(dto -> buildTO(dto)).collect(Collectors.toList());
	}

}
