package br.com.crux.builder;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.crux.cmd.GetEmpresaCmd;
import br.com.crux.cmd.GetPessoaFisicaCmd;
import br.com.crux.cmd.GetUsuarioLogadoCmd;
import br.com.crux.entity.Fornecedor;
import br.com.crux.to.FornecedorTO;

@Component
public class FornecedoresTOBuilder {

	@Autowired private GetUsuarioLogadoCmd getUsuarioLogadoCmd;
	
	@Autowired private GetEmpresaCmd getEmpresaCmd;
	@Autowired private EmpresaTOBuilder empresaTOBuilder;
	@Autowired private GetPessoaFisicaCmd getPessoaFisicaCmd;
	@Autowired private PessoaFisicaTOBuilder pessoaFisicaTOBuilder;

	public FornecedorTO buildTO(Fornecedor entity) {
		FornecedorTO to = new FornecedorTO();
		if (Objects.isNull(entity)) {
			return to;
		}
		
		BeanUtils.copyProperties(entity, to);
		
		to.setEmpresa(empresaTOBuilder.buildTOCombo(entity.getEmpresa()));
		to.setPessoasFisica(pessoaFisicaTOBuilder.buildParaCombo(entity.getPessoasFisica()));
		
		return to;
	}

	public Fornecedor build(FornecedorTO to) {
		Fornecedor entity = new Fornecedor();

		BeanUtils.copyProperties(to, entity);
		
		if (Objects.nonNull(to.getEmpresa()) && Objects.nonNull(to.getEmpresa().getId())) {
			entity.setEmpresa(getEmpresaCmd.getById(to.getEmpresa().getId()));
		}

		
		if (Objects.nonNull(to.getPessoasFisica()) && Objects.nonNull(to.getPessoasFisica().getId())) {
			entity.setPessoasFisica(getPessoaFisicaCmd.getById(to.getPessoasFisica().getId()));
		}
		
		entity.setUsuarioAlteracao(getUsuarioLogadoCmd.getUsuarioLogado().getIdUsuario());

		return entity;
	}
	
	public List<FornecedorTO> buildAll(List<Fornecedor> lista) {
		return lista.stream()
				.map(this::buildTO)
				.collect(Collectors.toList());
	}


}
