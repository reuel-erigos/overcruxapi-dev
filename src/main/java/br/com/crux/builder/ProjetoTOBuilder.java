package br.com.crux.builder;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.crux.cmd.GetColaboradoresProjetoCmd;
import br.com.crux.cmd.GetComposicaoRhProjetoCmd;
import br.com.crux.cmd.GetContasCentrosCustoCmd;
import br.com.crux.cmd.GetParceriasProjetoCmd;
import br.com.crux.cmd.GetProgramaCmd;
import br.com.crux.cmd.GetProjetosUnidadeCmd;
import br.com.crux.entity.Programa;
import br.com.crux.entity.Projeto;
import br.com.crux.to.ProjetoTO;

@Component
public class ProjetoTOBuilder {

	@Autowired private GetProjetosUnidadeCmd getProjetosUnidadeCmd;
	@Autowired private GetColaboradoresProjetoCmd getColaboradoresProjetoCmd;
	@Autowired private GetParceriasProjetoCmd getParceriasProjetoCmd;
	@Autowired private GetComposicaoRhProjetoCmd getComposicaoRhProjetoCmd;
	@Autowired private GetContasCentrosCustoCmd getContasCentrosCustoCmd;
	@Autowired private GetProgramaCmd getProgramaCmd;
	@Autowired private ProgramaTOBuilder programaTOBuilder;

	public Projeto build(ProjetoTO p) {
		Projeto retorno = new Projeto();

		retorno.setId(p.getId());
		retorno.setNome(p.getNome());
		retorno.setDescricao(p.getDescricao());

		retorno.setDataInicio(p.getDataInicio());
		retorno.setDataFim(p.getDataFim());
		retorno.setUsuarioAlteracao(p.getUsuarioAlteracao());
		retorno.setPublicoAlvo(p.getPublicoAlvo());
		retorno.setJustificativa(p.getJustificativa());
		retorno.setObjetivoGeral(p.getObjetivoGeral());
		retorno.setIdCoordenador(p.getIdCoordenador());
		
		retorno.setDataPrevisaoInicio(p.getDataPrevisaoInicio());
		retorno.setDataPrevisaoTermino(p.getDataPrevisaoTermino());
		
		Optional.ofNullable(p.getPrograma()).ifPresent(programa -> {
			if (Objects.nonNull(programa.getId())) {
				Programa entity = getProgramaCmd.getById(programa.getId());
				retorno.setPrograma(entity);
			}
		});

		return retorno;
	}

	public ProjetoTO buildTO(Projeto p) {
		ProjetoTO retorno = new ProjetoTO();

		if (Objects.isNull(p)) {
			return retorno;
		}

		retorno.setId(p.getId());
		retorno.setNome(p.getNome());
		retorno.setDescricao(p.getDescricao());

		retorno.setDataInicio(p.getDataInicio());
		retorno.setDataFim(p.getDataFim());
		retorno.setUsuarioAlteracao(p.getUsuarioAlteracao());
		retorno.setPublicoAlvo(p.getPublicoAlvo());
		retorno.setJustificativa(p.getJustificativa());
		retorno.setObjetivoGeral(p.getObjetivoGeral());
		retorno.setIdCoordenador(p.getIdCoordenador());
		
		retorno.setDataPrevisaoInicio(p.getDataPrevisaoInicio());
		retorno.setDataPrevisaoTermino(p.getDataPrevisaoTermino());

		retorno.setUnidades(getProjetosUnidadeCmd.getUnidadesTOByIdProjeto(p.getId()));
		retorno.setColaboradoresProjeto((getColaboradoresProjetoCmd.getColaboradoresProjetoTOByProjeto(p)));
		retorno.setParceriasProjeto(getParceriasProjetoCmd.getParceriasProjetoTOByProjeto(p));
		retorno.setComposicaoRhProjeto(getComposicaoRhProjetoCmd.getComposicaoRhProjetoByProjeto(p));
		retorno.setContasCentrosCusto(getContasCentrosCustoCmd.getTOPorProjeto(p));

		Optional.ofNullable(p.getPrograma()).ifPresent(programa -> {
			retorno.setPrograma(programaTOBuilder.buildTO(programa));
		});
		
		return retorno;
	}

	public List<ProjetoTO> buildAll(List<Projeto> dtos) {
		return dtos.stream()
				.map(dto -> buildTO(dto))
				.collect(Collectors.toList());
	}

	public List<ProjetoTO> buildAllCombo(List<Projeto> dtos) {
		return dtos.stream()
				.map(dto -> buildTOCombo(dto))
				.collect(Collectors.toList());
	}

	public ProjetoTO buildTOCombo(Projeto p) {
		ProjetoTO retorno = new ProjetoTO();

		if (Objects.isNull(p)) {
			return retorno;
		}

		retorno.setId(p.getId());
		retorno.setNome(p.getNome());
		retorno.setDescricao(p.getDescricao());
		
		return retorno;

	}

}
