package br.com.crux.cmd;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.crux.builder.EscolaTOBuilder;
import br.com.crux.dao.repository.EscolaRepository;
import br.com.crux.entity.Escola;
import br.com.crux.entity.Instituicao;
import br.com.crux.entity.UsuariosSistema;
import br.com.crux.to.EscolaTO;

@Component
public class CadastrarEscolaCmd {

	@Autowired
	private EscolaRepository repository;
	
	@Autowired
	private EscolaTOBuilder materialTOBuilder;
	
	@Autowired 
	private GetUsuarioLogadoCmd getUsuarioLogadoCmd;

	@Autowired 
	private GetUnidadeLogadaCmd getUnidadeLogadaCmd;
	
	public void cadastrar(EscolaTO param) {
		Escola entity = materialTOBuilder.build(param);
		entity.setUsuarioSistema(new UsuariosSistema());
		entity.getUsuarioSistema().setIdUsuario(getUsuarioLogadoCmd.getUsuarioLogado().getIdUsuario());
		entity.setInstituicao(new Instituicao());
		entity.getInstituicao().setId(getUnidadeLogadaCmd.getUnidadeTOSimplificado().getInstituicao().getId());
		repository.save(entity);
	}
}
