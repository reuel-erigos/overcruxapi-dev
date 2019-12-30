package br.com.crux.builder;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.crux.cmd.GetColaboradoresProjetoCmd;
import br.com.crux.cmd.GetComposicaoRhProjetoCmd;
import br.com.crux.cmd.GetParceriasProjetoCmd;
import br.com.crux.cmd.GetProjetosUnidadeCmd;
import br.com.crux.entity.Projeto;
import br.com.crux.to.ProjetoTO;

@Component
public class ProjetoTOBuilder {

	@Autowired private GetProjetosUnidadeCmd getProjetosUnidadeCmd;
	@Autowired private GetColaboradoresProjetoCmd getColaboradoresProjetoCmd;
	@Autowired private GetParceriasProjetoCmd getParceriasProjetoCmd;
	@Autowired private GetComposicaoRhProjetoCmd getComposicaoRhProjetoCmd;

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

		retorno.setDataPrevisaoInicio(p.getDataPrevisaoInicio());
		retorno.setDataPrevisaoTermino(p.getDataPrevisaoTermino());

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

		retorno.setDataPrevisaoInicio(p.getDataPrevisaoInicio());
		retorno.setDataPrevisaoTermino(p.getDataPrevisaoTermino());

		retorno.setUnidades(getProjetosUnidadeCmd.getUnidadesTOByIdProjeto(p.getId()));
		retorno.setColaboradoresProjeto((getColaboradoresProjetoCmd.getColaboradoresProjetoTOByProjeto(p)));
		retorno.setParceriasProjeto(getParceriasProjetoCmd.getParceriasProjetoTOByProjeto(p));
		retorno.setComposicaoRhProjeto(getComposicaoRhProjetoCmd.getComposicaoRhProjetoByProjeto(p));

		return retorno;
	}

	public List<ProjetoTO> buildAll(List<Projeto> dtos) {
		return dtos.stream().map(dto -> buildTO(dto)).collect(Collectors.toList());
	}

}
