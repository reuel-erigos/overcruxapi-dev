package br.com.crux.builder;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.crux.cmd.GetCargosCmd;
import br.com.crux.cmd.GetDepartamentoCmd;
import br.com.crux.cmd.GetEmpresaCmd;
import br.com.crux.cmd.GetUnidadeCmd;
import br.com.crux.dao.dto.FuncionarioDTO;
import br.com.crux.entity.Cargo;
import br.com.crux.entity.Departamentos;
import br.com.crux.entity.Empresa;
import br.com.crux.entity.Funcionario;
import br.com.crux.entity.Unidade;
import br.com.crux.enums.ConclusaoParecer;
import br.com.crux.enums.ParecerEntrevistador;
import br.com.crux.enums.TipoFuncionario;
import br.com.crux.to.ComboFuncionarioTO;
import br.com.crux.to.FuncionarioTO;

@Component
public class FuncionarioTOBuilder {

	@Autowired private CargosTOBuilder cargoTOBuilder;
	@Autowired private PessoaFisicaTOBuilder pessoaFisicaTOBuilder;
	@Autowired private UnidadeTOBuilder unidadeBuilder;
	@Autowired private EmpresaTOBuilder empresaTOBuilder;
	@Autowired private GetUnidadeCmd getUnidadeCmd;
	@Autowired private GetCargosCmd getCargosCmd;
	@Autowired private GetEmpresaCmd getEmpresaCmd;
	@Autowired private DepartamentoTOBuilder departamentoTOBuilder;
	@Autowired private DependentesTOBuilder dependentesTOBuilder;
	@Autowired private AlocacoesFuncionarioTOBuilder alocacoesFuncionarioTOBuilder;
	@Autowired private GetDepartamentoCmd getDepartamentoCmd;

	public Funcionario buildSemRelacionamentosCircular(FuncionarioTO to) {

		Funcionario retorno = new Funcionario();

		retorno.setId(to.getId());
		retorno.setMatricula(to.getMatricula());
		retorno.setDataAdmissao(to.getDataAdmissao());
		retorno.setDataDemissao(to.getDataDemissao());

		if (StringUtils.isEmpty(to.getHoraInicioJornada()) || to.getHoraInicioJornada()
				.length() < 5) {
			retorno.setHoraInicioJornada(null);
		} else {
			retorno.setHoraInicioJornada(getData(to.getHoraInicioJornada()));

		}

		if (StringUtils.isEmpty(to.getHoraFimJornada()) || to.getHoraFimJornada()
				.length() < 5) {
			retorno.setHoraFimJornada(null);
		} else {
			retorno.setHoraFimJornada(getData(to.getHoraFimJornada()));

		}

		Optional.ofNullable(to.getTipoFuncionario())
				.ifPresent(tp -> {
					retorno.setTipoFuncionario(TipoFuncionario.getPorTipo(to.getTipoFuncionario()));
				});

		retorno.setSalarioPretendido(to.getSalarioPretendido());

		Optional.ofNullable(to.getCargo())
				.ifPresent(c -> {
					Cargo cargo = getCargosCmd.getById(c.getId());
					retorno.setCargo(cargo);
				});

		Optional.ofNullable(to.getPessoasFisica())
				.ifPresent(pf -> {
					retorno.setPessoasFisica(pessoaFisicaTOBuilder.build(to.getPessoasFisica()));
				});

		Optional.ofNullable(to.getUnidade())
				.ifPresent(u -> {
					if (Objects.nonNull(u.getIdUnidade())) {
						Unidade unidade = getUnidadeCmd.getById(u.getIdUnidade());
						retorno.setUnidade(unidade);
					}
				});

		retorno.setDtHrEntrevista(to.getDtHrEntrevista());

		Optional.ofNullable(to.getParecerEntrevistador())
				.ifPresent(u -> {
					retorno.setParecerEntrevistador(ParecerEntrevistador.getPorTipo(to.getParecerEntrevistador()));
				});

		retorno.setDescricaoParecerEntrevistador(to.getDescricaoParecerEntrevistador());

		Optional.ofNullable(to.getConclusaoParecer())
				.ifPresent(u -> {
					retorno.setConclusaoParecer(ConclusaoParecer.getPorTipo(to.getConclusaoParecer()));
				});

		Optional.ofNullable(to.getEmpresaFuncionario())
				.ifPresent(ef -> {
					if (Objects.nonNull(ef.getId())) {
						Empresa empresa = getEmpresaCmd.getById(ef.getId());
						retorno.setEmpresaFuncionario(empresa);
					}
				});

		Optional.ofNullable(to.getFuncionarioEntrevistador())
				.ifPresent(fe -> {
					if (Objects.nonNull(fe.getId())) {
						retorno.setFuncionarioEntrevistador(getFuncionarioEntrevistador(to.getFuncionarioEntrevistador()));
					}
				});

		if (Objects.nonNull(to.getDescontaValeTransporte())) {
			retorno.setDescontaValeTransporte(to.getDescontaValeTransporte()
					.equalsIgnoreCase("S") ? true : false);
		} else {
			retorno.setDescontaValeTransporte(false);
		}

		Optional.ofNullable(to.getDepartamento())
				.ifPresent(d -> {
					if (Objects.nonNull(d.getIdDepartamento())) {
						Departamentos departamento = getDepartamentoCmd.getById(d.getIdDepartamento());
						retorno.setDepartamento(departamento);
					}
				});

		retorno.setUsuarioAlteracao(to.getUsuarioAlteracao());

		return retorno;
	}

	private LocalDateTime getData(String horaInicioJornada) {
		LocalDateTime data = LocalDateTime.parse("2001-01-01T" + horaInicioJornada + ":00");
		return data;
	}

	public FuncionarioTO buildTOSemRelacionamentosCircular(Funcionario entity) {
		FuncionarioTO retorno = new FuncionarioTO();

		if (Objects.isNull(entity)) {
			return retorno;
		}

		retorno.setId(entity.getId());
		retorno.setMatricula(entity.getMatricula());
		retorno.setDataAdmissao(entity.getDataAdmissao());
		retorno.setDataDemissao(entity.getDataDemissao());

		Optional.ofNullable(entity.getTipoFuncionario())
				.ifPresent(tf -> {
					retorno.setTipoFuncionario(tf.getTipo());
				});

		retorno.setSalarioPretendido(entity.getSalarioPretendido());

		Optional.ofNullable(entity.getCargo())
				.ifPresent(cargo -> {
					retorno.setCargo(cargoTOBuilder.buildTO(cargo));
				});

		Optional.ofNullable(entity.getPessoasFisica())
				.ifPresent(pf -> {
					retorno.setPessoasFisica(pessoaFisicaTOBuilder.buildTO(pf));
				});

		Optional.ofNullable(entity.getUnidade())
				.ifPresent(u -> {
					retorno.setUnidade(unidadeBuilder.buildTO(u));
				});

		retorno.setDtHrEntrevista(entity.getDtHrEntrevista());

		Optional.ofNullable(entity.getParecerEntrevistador())
				.ifPresent(pe -> {
					retorno.setParecerEntrevistador(pe.getTipo());
				});

		Optional.ofNullable(entity.getConclusaoParecer())
				.ifPresent(cp -> {
					retorno.setConclusaoParecer(cp.getTipo());
				});

		retorno.setDescricaoParecerEntrevistador(entity.getDescricaoParecerEntrevistador());
		retorno.setEmpresaFuncionario(empresaTOBuilder.buildTO(entity.getEmpresaFuncionario()));
		retorno.setFuncionarioEntrevistador(getFuncionarioEntrevistador(entity.getFuncionarioEntrevistador()));

		retorno.setDescontaValeTransporte(entity.getDescontaValeTransporte() ? "S" : "N");
		retorno.setDepartamento(departamentoTOBuilder.buildTO(entity.getDepartamento()));

		retorno.setUsuarioAlteracao(entity.getUsuarioAlteracao());

		Optional.ofNullable(entity.getHoraInicioJornada())
				.ifPresent(hora -> {
					String substring = hora.toString()
							.substring(11);
					retorno.setHoraInicioJornada(substring);
				});

		Optional.ofNullable(entity.getHoraFimJornada())
				.ifPresent(hora -> {
					String substring = hora.toString()
							.substring(11);
					retorno.setHoraFimJornada(substring);
				});

		return retorno;
	}

	public Funcionario build(FuncionarioTO to) {
		Funcionario retorno = new Funcionario();

		retorno = buildSemRelacionamentosCircular(to);

		if (Objects.nonNull(to.getDependentes())) {
			retorno.setDependentes(dependentesTOBuilder.buildTOAll(to.getDependentes()));
		}

		if (Objects.nonNull(to.getAlocacoesFuncionario())) {
			retorno.setAlocacoesFuncionario(alocacoesFuncionarioTOBuilder.buildTOAll(to.getAlocacoesFuncionario()));
		}

		return retorno;
	}

	public FuncionarioTO buildTO(Funcionario p) {
		FuncionarioTO retorno = new FuncionarioTO();

		if (Objects.isNull(p)) {
			return retorno;
		}

		retorno = buildTOSemRelacionamentosCircular(p);

		if (Objects.nonNull(p.getDependentes())) {
			retorno.setDependentes(dependentesTOBuilder.buildAll(p.getDependentes()));
		}

		if (Objects.nonNull(p.getAlocacoesFuncionario())) {
			retorno.setAlocacoesFuncionario(alocacoesFuncionarioTOBuilder.buildAll(p.getAlocacoesFuncionario()));
		}

		return retorno;
	}

	public FuncionarioTO buildTOCombo(Funcionario p) {
		FuncionarioTO to = new FuncionarioTO();

		if (Objects.isNull(p)) {
			return to;
		}

		to.setId(p.getId());

		to.setPessoasFisica(pessoaFisicaTOBuilder.buildParaCombo(p.getPessoasFisica()));

		return to;
	}

	private FuncionarioTO getFuncionarioEntrevistador(Funcionario p) {
		FuncionarioTO retorno = new FuncionarioTO();

		if (Objects.isNull(p)) {
			return retorno;
		}

		retorno.setId(p.getId());
		retorno.setMatricula(p.getMatricula());
		retorno.setDataAdmissao(p.getDataAdmissao());
		retorno.setDataDemissao(p.getDataDemissao());

		Optional.ofNullable(p.getTipoFuncionario())
				.ifPresent(tf -> {
					retorno.setTipoFuncionario(tf.getTipo());
				});

		Optional.ofNullable(p.getTipoFuncionario())
				.ifPresent(tf -> {
					retorno.setTipoFuncionario(tf.getTipo());
				});
		retorno.setSalarioPretendido(p.getSalarioPretendido());

		retorno.setCargo(cargoTOBuilder.buildTO(p.getCargo()));

		retorno.setPessoasFisica(pessoaFisicaTOBuilder.buildTO(p.getPessoasFisica()));

		retorno.setUnidade(unidadeBuilder.buildTO(p.getUnidade()));

		retorno.setDtHrEntrevista(p.getDtHrEntrevista());

		Optional.ofNullable(p.getParecerEntrevistador())
				.ifPresent(pe -> {
					retorno.setParecerEntrevistador(pe.getTipo());
				});

		retorno.setDescricaoParecerEntrevistador(p.getDescricaoParecerEntrevistador());

		Optional.ofNullable(p.getConclusaoParecer())
				.ifPresent(cp -> {
					retorno.setConclusaoParecer(cp.getTipo());
				});

		retorno.setEmpresaFuncionario(empresaTOBuilder.buildTO(p.getEmpresaFuncionario()));

		retorno.setDescontaValeTransporte(p.getDescontaValeTransporte() ? "S" : "N");
		retorno.setDepartamento(departamentoTOBuilder.buildTO(p.getDepartamento()));

		retorno.setUsuarioAlteracao(p.getUsuarioAlteracao());

		return retorno;
	}

	public Funcionario getFuncionarioEntrevistador(FuncionarioTO p) {
		Funcionario retorno = new Funcionario();

		retorno.setId(p.getId());
		retorno.setMatricula(p.getMatricula());
		retorno.setDataAdmissao(p.getDataAdmissao());
		retorno.setDataDemissao(p.getDataDemissao());
		retorno.setTipoFuncionario(TipoFuncionario.getPorTipo(p.getTipoFuncionario()));
		retorno.setSalarioPretendido(p.getSalarioPretendido());

		Optional.ofNullable(p.getCargo())
				.ifPresent(cargo -> {
					retorno.setCargo(cargoTOBuilder.build(cargo));
				});

		Optional.ofNullable(p.getPessoasFisica())
				.ifPresent(pessoa -> {
					retorno.setPessoasFisica(pessoaFisicaTOBuilder.build(pessoa));
				});

		Optional.ofNullable(p.getPessoasFisica())
				.ifPresent(pessoa -> {
					retorno.setPessoasFisica(pessoaFisicaTOBuilder.build(pessoa));
				});

		Optional.ofNullable(p.getUnidade())
				.ifPresent(unidade -> {
					retorno.setUnidade(unidadeBuilder.build(unidade));
				});

		Optional.ofNullable(p.getEmpresaFuncionario())
				.ifPresent(empresa -> {
					retorno.setEmpresaFuncionario(empresaTOBuilder.build(empresa));
				});

		retorno.setDtHrEntrevista(p.getDtHrEntrevista());

		Optional.ofNullable(p.getParecerEntrevistador())
				.ifPresent(pe -> {
					retorno.setParecerEntrevistador(ParecerEntrevistador.getPorTipo(pe));
				});

		retorno.setDescricaoParecerEntrevistador(p.getDescricaoParecerEntrevistador());

		Optional.ofNullable(p.getConclusaoParecer())
				.ifPresent(cp -> {
					retorno.setConclusaoParecer(ConclusaoParecer.getPorTipo(cp));
				});

		retorno.setDescontaValeTransporte(p.getDescontaValeTransporte()
				.equalsIgnoreCase("S") ? true : false);

		Optional.ofNullable(p.getDepartamento())
				.ifPresent(d -> {
					if (Objects.nonNull(d.getIdDepartamento())) {
						Departamentos departamento = getDepartamentoCmd.getById(d.getIdDepartamento());
						retorno.setDepartamento(departamento);
					}
				});

		retorno.setUsuarioAlteracao(p.getUsuarioAlteracao());

		return retorno;
	}

	public List<FuncionarioTO> buildAll(List<Funcionario> dtos) {
		return dtos.stream()
				.map(dto -> buildTO(dto))
				.collect(Collectors.toList());
	}

	public List<FuncionarioTO> buildAllTOCombo(List<Funcionario> dtos) {
		return dtos.stream()
				.map(dto -> buildTOCombo(dto))
				.collect(Collectors.toList());
	}
	
	
	public ComboFuncionarioTO buildComboTO(FuncionarioDTO p) {
		ComboFuncionarioTO retorno = new ComboFuncionarioTO();
		
		if(Objects.isNull(p)) {
			return retorno;
		}
		
		retorno.setId(p.getId());
		retorno.setNome(p.getNome());
		return retorno;
	}
	
	public List<ComboFuncionarioTO> buildAllDTO(List<FuncionarioDTO> dtos) {
		return dtos.stream().map(dto -> buildComboTO(dto)).collect(Collectors.toList());
	}

}
