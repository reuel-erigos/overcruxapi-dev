package br.com.crux.cmd;

import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.crux.builder.PessoaFisicaTOBuilder;
import br.com.crux.dao.repository.CursosFormacaoPFRepository;
import br.com.crux.entity.CursosFormacaoPf;
import br.com.crux.exception.NotFoundException;
import br.com.crux.rule.CamposObrigatoriosCursosFormacaoPFRule;
import br.com.crux.to.CursosFormacaoPfTO;
import br.com.crux.to.UsuarioLogadoTO;

@Component
public class AlterarCursosFormacaoPFCmd {

	@Autowired private CursosFormacaoPFRepository repository;
	
	@Autowired private GetUsuarioLogadoCmd getUsuarioLogadoCmd;
	@Autowired private PessoaFisicaTOBuilder pessoaFisicaBuilder;
	@Autowired private CamposObrigatoriosCursosFormacaoPFRule camposObrigatoriosRule;
	
	
	public void alterar(CursosFormacaoPfTO to) {
		Optional<CursosFormacaoPf> entityOptional = repository.findById(to.getId());
		if(!entityOptional.isPresent()) {
			throw new NotFoundException("Curso formação informado não existe.");
		}
		
		if(Objects.isNull(to.getPessoaFisica())) {
			throw new NotFoundException("Pessoa Física não informada.");
		}
		
		camposObrigatoriosRule.verificar(to.getNome(), to.getPessoaFisica().getId());
		
		CursosFormacaoPf entity = entityOptional.get();

		entity.setNome(to.getNome());
		entity.setNomeInstuicao(to.getNomeInstuicao());
		entity.setDataInicio(to.getDataInicio());
		entity.setDataFim(to.getDataFim());
		entity.setPessoaFisica(pessoaFisicaBuilder.build(to.getPessoaFisica()));
		
		UsuarioLogadoTO usuarioLogado = getUsuarioLogadoCmd.getUsuarioLogado();
		entity.setUsuarioAlteracao(usuarioLogado.getIdUsuario());
		
		repository.save(entity);
		
	}
}
