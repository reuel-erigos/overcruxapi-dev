package br.com.crux.builder;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.crux.cmd.GetDepartamentoCmd;
import br.com.crux.cmd.GetEmpresaCmd;
import br.com.crux.cmd.GetItensMovimentacoesMateriaisCmd;
import br.com.crux.cmd.GetProgramaCmd;
import br.com.crux.cmd.GetProjetoCmd;
import br.com.crux.cmd.GetUnidadeCmd;
import br.com.crux.cmd.GetUsuarioLogadoCmd;
import br.com.crux.entity.Departamentos;
import br.com.crux.entity.Empresa;
import br.com.crux.entity.MovimentacoesMateriais;
import br.com.crux.entity.Programa;
import br.com.crux.entity.Projeto;
import br.com.crux.entity.Unidade;
import br.com.crux.to.MovimentacoesMateriaisTO;

@Component
public class MovimentacoesMateriaisTOBuilder {

	@Autowired private UnidadeTOBuilder unidadeBuilder;
	@Autowired private EmpresaTOBuilder empresaTOBuilder;
	@Autowired private ProjetoTOBuilder projetoTOBuilder;
	@Autowired private ProgramaTOBuilder programaTOBuilder;
	@Autowired private DepartamentoTOBuilder departamentoTOBuilder;
	@Autowired private GetUnidadeCmd getUnidadeCmd;
	@Autowired private GetEmpresaCmd getEmpresaCmd;
	@Autowired private GetProjetoCmd getProjetoCmd;
	@Autowired private GetProgramaCmd getProgramaCmd;
	@Autowired private GetDepartamentoCmd getDepartamentoCmd;
	@Autowired private GetItensMovimentacoesMateriaisCmd getItensMovimentacoesMateriaisCmd;

	@Autowired private GetUsuarioLogadoCmd getUsuarioLogadoCmd;

	public MovimentacoesMateriais build(MovimentacoesMateriaisTO to) {
		MovimentacoesMateriais entity = new MovimentacoesMateriais();

		BeanUtils.copyProperties(to, entity);

		if (Objects.nonNull(to.getDepartamento()) && Objects.nonNull(to.getDepartamento()
				.getIdDepartamento())) {
			Departamentos departamentos = getDepartamentoCmd.getById(to.getDepartamento()
					.getIdDepartamento());
			entity.setDepartamento(departamentos);
		}

		if (Objects.nonNull(to.getEmpresa()) && Objects.nonNull(to.getEmpresa()
				.getId())) {
			Empresa empresa = getEmpresaCmd.getById(to.getEmpresa()
					.getId());
			entity.setEmpresa(empresa);
		}

		if (Objects.nonNull(to.getPrograma()) && Objects.nonNull(to.getPrograma()
				.getId())) {
			Programa programa = getProgramaCmd.getById(to.getPrograma()
					.getId());
			entity.setPrograma(programa);
		}

		if (Objects.nonNull(to.getProjeto()) && Objects.nonNull(to.getProjeto()
				.getId())) {
			Projeto projeto = getProjetoCmd.getById(to.getProjeto()
					.getId());
			entity.setProjeto(projeto);
		}

		if (Objects.nonNull(to.getUnidade()) && Objects.nonNull(to.getUnidade()
				.getIdUnidade())) {
			Unidade unidade = getUnidadeCmd.getById(to.getUnidade()
					.getIdUnidade());
			entity.setUnidade(unidade);
		}

		entity.setUsuarioAlteracao(getUsuarioLogadoCmd.getUsuarioLogado()
				.getIdUsuario());

		return entity;
	}

	public MovimentacoesMateriaisTO buildTO(MovimentacoesMateriais e) {
		MovimentacoesMateriaisTO to = new MovimentacoesMateriaisTO();

		if (Objects.isNull(e)) {
			return to;
		}

		BeanUtils.copyProperties(e, to);

		to.setUnidade(unidadeBuilder.buildTOCombo(e.getUnidade()));
		to.setEmpresa(empresaTOBuilder.buildTOCombo(e.getEmpresa()));
		to.setPrograma(programaTOBuilder.buildTOEnxuto(e.getPrograma()));
		to.setProjeto(projetoTOBuilder.buildTOEnxuto(e.getProjeto()));
		to.setDepartamento(departamentoTOBuilder.buildTOCombo(e.getDepartamento()));
		
		to.setItensMovimentacoesMateriais(getItensMovimentacoesMateriaisCmd.getItensMovimentacoesMateriaisTOByMovimentacaoMateriais(e));

		return to;
	}

	public List<MovimentacoesMateriaisTO> buildAll(List<MovimentacoesMateriais> list) {
		return list.stream()
				.map(dto -> buildTO(dto))
				.collect(Collectors.toList());
	}

	public MovimentacoesMateriaisTO buildTOCombo(MovimentacoesMateriais e) {
		MovimentacoesMateriaisTO to = new MovimentacoesMateriaisTO();

		if (Objects.isNull(e)) {
			return to;
		}

		to.setId(e.getId());
		to.setDescricaoMovimentacaoMaterial(e.getDescricaoMovimentacaoMaterial());

		return to;
	}

}
