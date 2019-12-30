package br.com.crux.builder;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.crux.cmd.GetCargosCmd;
import br.com.crux.cmd.GetDepartamentoCmd;
import br.com.crux.cmd.GetEmpresaCmd;
import br.com.crux.cmd.GetUnidadeCmd;
import br.com.crux.entity.Cargo;
import br.com.crux.entity.Departamentos;
import br.com.crux.entity.Empresa;
import br.com.crux.entity.Funcionario;
import br.com.crux.entity.Unidade;
import br.com.crux.enums.ConclusaoParecer;
import br.com.crux.enums.ParecerEntrevistador;
import br.com.crux.enums.TipoFuncionario;
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

		Optional.ofNullable(to.getTipoFuncionario()).ifPresent(tp -> {
			retorno.setTipoFuncionario(TipoFuncionario.getPorTipo(to.getTipoFuncionario()));
		});

		retorno.setSalarioPretendido(to.getSalarioPretendido());

		Optional.ofNullable(to.getCargo()).ifPresent(c -> {
			Cargo cargo = getCargosCmd.getById(c.getId());
			retorno.setCargo(cargo);
		});

		Optional.ofNullable(to.getPessoasFisica()).ifPresent(pf -> {
			retorno.setPessoasFisica(pessoaFisicaTOBuilder.build(to.getPessoasFisica()));
		});

		Optional.ofNullable(to.getUnidade()).ifPresent(u -> {
			if (Objects.nonNull(u.getIdUnidade())) {
				Unidade unidade = getUnidadeCmd.getById(u.getIdUnidade());
				retorno.setUnidade(unidade);
			}
		});

		retorno.setDtHrEntrevista(to.getDtHrEntrevista());

		Optional.ofNullable(to.getParecerEntrevistador()).ifPresent(u -> {
			retorno.setParecerEntrevistador(ParecerEntrevistador.getPorTipo(to.getParecerEntrevistador()));
		});

		retorno.setDescricaoParecerEntrevistador(to.getDescricaoParecerEntrevistador());

		Optional.ofNullable(to.getConclusaoParecer()).ifPresent(u -> {
			retorno.setConclusaoParecer(ConclusaoParecer.getPorTipo(to.getConclusaoParecer()));
		});

		Optional.ofNullable(to.getEmpresaFuncionario()).ifPresent(ef -> {
			if(Objects.nonNull(ef.getId())) {
				Empresa empresa = getEmpresaCmd.getById(ef.getId());
				retorno.setEmpresaFuncionario(empresa);
			}
		});

		Optional.ofNullable(to.getFuncionarioEntrevistador()).ifPresent(fe -> {
			if(Objects.nonNull(fe.getId())) {
				retorno.setFuncionarioEntrevistador(getFuncionarioEntrevistador(to.getFuncionarioEntrevistador()));	
			}
		});

		if(Objects.nonNull(to.getDescontaValeTransporte())) {
			retorno.setDescontaValeTransporte(to.getDescontaValeTransporte().equalsIgnoreCase("S") ? true : false);
		}else {
			retorno.setDescontaValeTransporte(false);
		}
		
		Optional.ofNullable(to.getDepartamento()).ifPresent( d -> {
			if(Objects.nonNull(d.getIdDepartamento())) {
				Departamentos departamento = getDepartamentoCmd.getById(d.getIdDepartamento());
				retorno.setDepartamento(departamento);
			}
		});

		
		retorno.setUsuarioAlteracao(to.getUsuarioAlteracao());

		return retorno;
	}
		
	public FuncionarioTO buildTOSemRelacionamentosCircular(Funcionario p) {
		FuncionarioTO retorno = new FuncionarioTO();

		if (Objects.isNull(p)) {
			return retorno;
		}

		retorno.setId(p.getId());
		retorno.setMatricula(p.getMatricula());
		retorno.setDataAdmissao(p.getDataAdmissao());
		retorno.setDataDemissao(p.getDataDemissao());

		Optional.ofNullable(p.getTipoFuncionario()).ifPresent(tf -> {
			retorno.setTipoFuncionario(tf.getTipo());
		});

		retorno.setSalarioPretendido(p.getSalarioPretendido());

		Optional.ofNullable(p.getCargo()).ifPresent(cargo -> {
			retorno.setCargo(cargoTOBuilder.buildTO(cargo));
		});

		Optional.ofNullable(p.getPessoasFisica()).ifPresent(pf -> {
			retorno.setPessoasFisica(pessoaFisicaTOBuilder.buildTO(pf));
		});

		Optional.ofNullable(p.getUnidade()).ifPresent(u -> {
			retorno.setUnidade(unidadeBuilder.buildTO(u));
		});

		retorno.setDtHrEntrevista(p.getDtHrEntrevista());

		Optional.ofNullable(p.getParecerEntrevistador()).ifPresent(pe -> {
			retorno.setParecerEntrevistador(pe.getTipo());
		});

		Optional.ofNullable(p.getConclusaoParecer()).ifPresent(cp -> {
			retorno.setConclusaoParecer(cp.getTipo());
		});

		retorno.setDescricaoParecerEntrevistador(p.getDescricaoParecerEntrevistador());
		retorno.setEmpresaFuncionario(empresaTOBuilder.buildTO(p.getEmpresaFuncionario()));
		retorno.setFuncionarioEntrevistador(getFuncionarioEntrevistador(p.getFuncionarioEntrevistador()));

		retorno.setDescontaValeTransporte(p.getDescontaValeTransporte() ? "S" : "N");
		retorno.setDepartamento(departamentoTOBuilder.buildTO(p.getDepartamento()));
		
		retorno.setUsuarioAlteracao(p.getUsuarioAlteracao());

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
		
		retorno = buildTOSemRelacionamentosCircular(p);
		
		if (Objects.nonNull(p.getDependentes())) {
			retorno.setDependentes(dependentesTOBuilder.buildAll(p.getDependentes()));
		}
		
		if (Objects.nonNull(p.getAlocacoesFuncionario())) {
			retorno.setAlocacoesFuncionario(alocacoesFuncionarioTOBuilder.buildAll(p.getAlocacoesFuncionario()));
		}		
		
		return retorno;
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

		Optional.ofNullable(p.getTipoFuncionario()).ifPresent(tf -> {
			retorno.setTipoFuncionario(tf.getTipo());
		});

		Optional.ofNullable(p.getTipoFuncionario()).ifPresent(tf -> {
			retorno.setTipoFuncionario(tf.getTipo());
		});
		retorno.setSalarioPretendido(p.getSalarioPretendido());

		retorno.setCargo(cargoTOBuilder.buildTO(p.getCargo()));

		retorno.setPessoasFisica(pessoaFisicaTOBuilder.buildTO(p.getPessoasFisica()));

		retorno.setUnidade(unidadeBuilder.buildTO(p.getUnidade()));

		retorno.setDtHrEntrevista(p.getDtHrEntrevista());

		Optional.ofNullable(p.getParecerEntrevistador()).ifPresent(pe -> {
			retorno.setParecerEntrevistador(pe.getTipo());
		});

		retorno.setDescricaoParecerEntrevistador(p.getDescricaoParecerEntrevistador());

		Optional.ofNullable(p.getConclusaoParecer()).ifPresent(cp -> {
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

		Optional.ofNullable(p.getCargo()).ifPresent(cargo -> {
			retorno.setCargo(cargoTOBuilder.build(cargo));
		});

		Optional.ofNullable(p.getPessoasFisica()).ifPresent(pessoa -> {
			retorno.setPessoasFisica(pessoaFisicaTOBuilder.build(pessoa));
		});

		Optional.ofNullable(p.getPessoasFisica()).ifPresent(pessoa -> {
			retorno.setPessoasFisica(pessoaFisicaTOBuilder.build(pessoa));
		});

		Optional.ofNullable(p.getUnidade()).ifPresent(unidade -> {
			retorno.setUnidade(unidadeBuilder.build(unidade));
		});

		Optional.ofNullable(p.getEmpresaFuncionario()).ifPresent(empresa -> {
			retorno.setEmpresaFuncionario(empresaTOBuilder.build(empresa));
		});

		retorno.setDtHrEntrevista(p.getDtHrEntrevista());

		Optional.ofNullable(p.getParecerEntrevistador()).ifPresent(pe -> {
			retorno.setParecerEntrevistador(ParecerEntrevistador.getPorTipo(pe));
		});

		retorno.setDescricaoParecerEntrevistador(p.getDescricaoParecerEntrevistador());

		Optional.ofNullable(p.getConclusaoParecer()).ifPresent(cp -> {
			retorno.setConclusaoParecer(ConclusaoParecer.getPorTipo(cp));
		});
		
		retorno.setDescontaValeTransporte(p.getDescontaValeTransporte().equalsIgnoreCase("S") ? true : false);
		
		Optional.ofNullable(p.getDepartamento()).ifPresent( d -> {
			if(Objects.nonNull(d.getIdDepartamento())) {
				Departamentos departamento = getDepartamentoCmd.getById(d.getIdDepartamento());
				retorno.setDepartamento(departamento);
			}
		});
	

		retorno.setUsuarioAlteracao(p.getUsuarioAlteracao());

		return retorno;
	}

	public List<FuncionarioTO> buildAll(List<Funcionario> dtos) {
		return dtos.stream().map(dto -> buildTO(dto)).collect(Collectors.toList());
	}

}
