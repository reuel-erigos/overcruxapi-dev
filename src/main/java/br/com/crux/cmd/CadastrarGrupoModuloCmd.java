package br.com.crux.cmd;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.crux.builder.GrupoModuloTOBuilder;
import br.com.crux.dao.repository.GrupoModuloRepository;
import br.com.crux.dao.repository.ModuloRepository;
import br.com.crux.dao.repository.PerfilAcessoRepository;
import br.com.crux.dao.repository.UnidadeRepository;
import br.com.crux.entity.GruposModulo;
import br.com.crux.entity.Modulo;
import br.com.crux.entity.PerfilAcesso;
import br.com.crux.entity.Unidade;
import br.com.crux.exception.NotFoundException;
import br.com.crux.exception.PerfilAcessoException;
import br.com.crux.rule.CamposObrigatoriosGrupoModuloRule;
import br.com.crux.to.GrupoModuloTO;

@Component
public class CadastrarGrupoModuloCmd {

	@Autowired private GrupoModuloRepository repository;
	@Autowired private CamposObrigatoriosGrupoModuloRule camposObrigatoriosRule;
	@Autowired private GetUsuarioLogadoCmd getUsuarioLogadoCmd;
	@Autowired private GrupoModuloTOBuilder toBuilder;

	@Autowired private GrupoModuloRepository grupoModuloRepository;
	@Autowired private PerfilAcessoRepository perfilAcessoRepository;
	@Autowired private ModuloRepository moduloRepository;
	@Autowired private UnidadeRepository unidadeRepository;
	
	
	public void cadastrarAll(List<GrupoModuloTO> listaTO) {
		Optional.ofNullable(listaTO).ifPresent(lista -> {
			lista.forEach(to -> cadastrar(to));
		});
	}
	
	
	public void cadastrar(GrupoModuloTO to) {
		camposObrigatoriosRule.verificar(to);
		
		to.setUsuarioAlteracao(getUsuarioLogadoCmd.getUsuarioLogado().getIdUsuario());
		GruposModulo entity = toBuilder.buildTO(to);

		// Cadastrar o grupo do modulo pai, caso não exista.
		addGrupoModuloPai(to.getUnidade().getIdUnidade(), to.getModulo().getId());
		
		repository.save(entity);
	}
	
	private void addGrupoModuloPai(Long idUnidade, Long idModulo) {
		Optional<Modulo> moduloPai = moduloRepository.findById(idModulo);
		
		if(Objects.isNull(moduloPai.get().getModuloPai())) {
			return;
		}
		
		// Cadastrar o grupo do modulo pai, caso não exista.
		cadastrarGrupoModuloPai(idUnidade, moduloPai.get().getModuloPai().getId());
		
		addGrupoModuloPai(idUnidade, moduloPai.get().getModuloPai().getId());
		
	}
	
	public GruposModulo cadastrarGrupoModuloPai(Long idUnidade, Long idModulo) {
		
		Optional<Modulo> modulo = moduloRepository.findById(idModulo);
		if(!modulo.isPresent()) {
			throw new NotFoundException("Modulo informado não existe.");
		}
		
		Optional<Unidade> unidade = unidadeRepository.findById(idUnidade);
		if(!unidade.isPresent()) {
			throw new NotFoundException("Unidade informada não existe.");
		}
		
		Optional<PerfilAcesso> perfilApenasConsulta = perfilAcessoRepository.getPerfilApenasConsulta();
		if(!perfilApenasConsulta.isPresent()) {
			throw new PerfilAcessoException("Não foi possível atribuir permissão ao modulo pai.");
		}
		
		Optional<GruposModulo> grupoModuloPaiOptional = grupoModuloRepository.findByIdModuloAndIdPerfilAcessoAndIdUnidade(modulo.get().getId(), 
																									                      perfilApenasConsulta.get().getId(), 
																									                      unidade.get().getIdUnidade());
		
		GruposModulo grupoModuloPai = null;
		if(!grupoModuloPaiOptional.isPresent()) {
			grupoModuloPai = new GruposModulo();
			grupoModuloPai.setModulo(modulo.get());
			grupoModuloPai.setNome("C");
			grupoModuloPai.setPerfilAcesso(perfilApenasConsulta.get());
			grupoModuloPai.setDescricao("Grupo de perfil de consultar");
			grupoModuloPai.setUnidade(unidade.get());
			
			grupoModuloPai = grupoModuloRepository.save(grupoModuloPai);
		} else {
			grupoModuloPai = grupoModuloPaiOptional.get();
		}
	
		return grupoModuloPai;
	}
}
