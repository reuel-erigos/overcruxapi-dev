package br.com.crux.builder;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.crux.cmd.GetAlunoCmd;
import br.com.crux.cmd.GetDiagnosticosCmd;
import br.com.crux.cmd.GetSolucoesCmd;
import br.com.crux.entity.Aluno;
import br.com.crux.entity.Atendimentos;
import br.com.crux.entity.Diagnosticos;
import br.com.crux.entity.Solucoes;
import br.com.crux.to.AtendimentosTO;

@Component
public class AtendimentosTOBuilder {

	@Autowired private AlunoTOBuilder alunoBuilder;
	@Autowired private SolucoesTOBuilder solucoesBuilder;
	@Autowired private DiagnosticosTOBuilder diagnosticoBuilder;
	@Autowired private GetAlunoCmd getAlunoCmd;
	@Autowired private GetDiagnosticosCmd getDiagnosticosCmd;
	@Autowired private GetSolucoesCmd getSolucoesCmd;

	public Atendimentos build(AtendimentosTO p) {
		Atendimentos retorno = new Atendimentos();

		retorno.setId(p.getId());
		retorno.setDescDiagnostico(p.getDescDiagnostico());
		retorno.setDescSolucao(p.getDescSolucao());
		retorno.setDataAtendimento(p.getDataAtendimento());

		Optional.ofNullable(p.getAluno()).ifPresent(a -> {
			Aluno aluno = getAlunoCmd.getById(a.getId());
			retorno.setAluno(aluno);
		});

		Optional.ofNullable(p.getDiagnostico()).ifPresent(d -> {
			Diagnosticos dia = getDiagnosticosCmd.getById(d.getId());
			retorno.setDiagnostico(dia);
		});

		Optional.ofNullable(p.getSolucoes()).ifPresent(s -> {
			Solucoes sol = getSolucoesCmd.getById(s.getId());
			retorno.setSolucoes(sol);
		});

		retorno.setUsuarioAlteracao(p.getUsuarioAlteracao());

		return retorno;
	}

	public AtendimentosTO buildTO(Atendimentos p) {
		AtendimentosTO retorno = new AtendimentosTO();

		if (Objects.isNull(p)) {
			return retorno;
		}

		retorno.setId(p.getId());
		retorno.setDescDiagnostico(p.getDescDiagnostico());
		retorno.setDescSolucao(p.getDescSolucao());
		retorno.setDataAtendimento(p.getDataAtendimento());
		retorno.setAluno(alunoBuilder.buildTO(p.getAluno()));
		retorno.setDiagnostico(diagnosticoBuilder.buildTO(p.getDiagnostico()));
		retorno.setSolucoes(solucoesBuilder.buildTO(p.getSolucoes()));
		retorno.setUsuarioAlteracao(p.getUsuarioAlteracao());

		return retorno;
	}

	public List<AtendimentosTO> buildAll(List<Atendimentos> dtos) {
		return dtos.stream().map(dto -> buildTO(dto)).collect(Collectors.toList());
	}

}
