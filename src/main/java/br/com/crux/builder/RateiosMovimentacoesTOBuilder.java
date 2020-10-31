package br.com.crux.builder;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.crux.cmd.GetProgramaCmd;
import br.com.crux.cmd.GetProjetoCmd;
import br.com.crux.cmd.GetUsuarioLogadoCmd;
import br.com.crux.entity.RateiosMovimentacoes;
import br.com.crux.to.RateiosMovimentacoesTO;

@Component
public class RateiosMovimentacoesTOBuilder {

	@Autowired private GetUsuarioLogadoCmd getUsuarioLogadoCmd;
	@Autowired private GetProgramaCmd getProgramaCmd;
	@Autowired private GetProjetoCmd getProjetoCmd;
	@Autowired private ProgramaTOBuilder programaTOBuilder;
	@Autowired private ProjetoTOBuilder projetoTOBuilder;
	
	
	

	public RateiosMovimentacoesTO buildTO(RateiosMovimentacoes m) {
		RateiosMovimentacoesTO to = new RateiosMovimentacoesTO();

		if (Objects.isNull(m)) {
			return to;
		}

		BeanUtils.copyProperties(m, to);
		
		to.setPrograma(programaTOBuilder.buildTOEnxuto(m.getPrograma()));
		to.setProjeto(projetoTOBuilder.buildTOEnxuto(m.getProjeto()));

		return to;
	}

	public List<RateiosMovimentacoesTO> buildAllTO(List<RateiosMovimentacoes> list) {
		return list.stream()
				.map(this::buildTO)
				.collect(Collectors.toList());
	}
	
	public List<RateiosMovimentacoes> buildAll(List<RateiosMovimentacoesTO> list) {
		return list.stream()
				.map(this::build)
				.collect(Collectors.toList());
	}

	public RateiosMovimentacoes build(RateiosMovimentacoesTO to) {
		RateiosMovimentacoes p = new RateiosMovimentacoes();

		BeanUtils.copyProperties(to, p);

		if (Objects.nonNull(to.getPrograma()) && Objects.nonNull(to.getPrograma().getId())) {
			p.setPrograma(getProgramaCmd.getById(to.getPrograma().getId()));
		}

		if (Objects.nonNull(to.getProjeto()) && Objects.nonNull(to.getProjeto().getId())) {
			p.setProjeto(getProjetoCmd.getById(to.getProjeto().getId()));
		}

		p.setUsuarioAlteracao(getUsuarioLogadoCmd.getUsuarioLogado().getIdUsuario());
		return p;
	}

}

