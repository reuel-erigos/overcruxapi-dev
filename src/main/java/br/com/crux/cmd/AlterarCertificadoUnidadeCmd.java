package br.com.crux.cmd;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.crux.builder.CertificadoUnidadeTOBuilder;
import br.com.crux.dao.repository.CertificadoUnidadeRepository;
import br.com.crux.entity.CertificadoUnidade;
import br.com.crux.entity.Unidade;
import br.com.crux.to.CertificadoUnidadeTO;

@Component
public class AlterarCertificadoUnidadeCmd extends AbstractAlterarListaCmd<CertificadoUnidade, CertificadoUnidadeTO, Unidade> {
	
	@Autowired private CertificadoUnidadeRepository repository;
	@Autowired private CertificadoUnidadeTOBuilder toBuilder;
	@Autowired private CadastrarCertificadoUnidadeCmd cadastrarCmd;
	@Autowired private ExcluirCertificadoUnidadeCmd excluirCmd;
	
	@Override
	protected CertificadoUnidadeTO getTO(CertificadoUnidade entity) {
		return toBuilder.buildTO(entity);
	}
	@Override
	protected List<CertificadoUnidadeTO> getTOListaBanco(
			List<CertificadoUnidade> lista) {
		return toBuilder.buildAll(lista);
	}
	@Override
	protected List<CertificadoUnidade> getListaBanco(Unidade pai) {
		return repository.findByUnidade(pai).orElse(new ArrayList<CertificadoUnidade>());
	}
	@Override
	protected Long getIdentificadorTO(CertificadoUnidadeTO to) {
		return to.getId();
	}
	@Override
	protected void cadastrar(CertificadoUnidadeTO to, Unidade p) {
		cadastrarCmd.cadastrar(p,to);
		
	}
	@Override
	protected void deletar(CertificadoUnidade registro) {
		excluirCmd.excluir(registro.getId());
	}
	
}
