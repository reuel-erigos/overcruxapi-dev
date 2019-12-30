package br.com.crux.cmd;

import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.crux.builder.PessoaFisicaTOBuilder;
import br.com.crux.dao.repository.CursosFormacaoPFRepository;
import br.com.crux.dao.repository.PessoaFisicaRepository;
import br.com.crux.entity.CursosFormacaoPf;
import br.com.crux.entity.PessoaFisica;
import br.com.crux.exception.NotFoundException;
import br.com.crux.rule.CamposObrigatoriosCursosFormacaoPFRule;
import br.com.crux.to.CursosFormacaoPfTO;
import br.com.crux.to.UsuarioLogadoTO;

@Component
public class CadastrarCursosFormacaoPFCmd {

	@Autowired private CursosFormacaoPFRepository repository;
	
	@Autowired private GetUsuarioLogadoCmd getUsuarioLogadoCmd;
	@Autowired private CamposObrigatoriosCursosFormacaoPFRule camposObrigatoriosRule;
	
	@Autowired private PessoaFisicaTOBuilder pessoaFisicaBuilder;
	@Autowired private PessoaFisicaRepository pessoaFisicaRepository;
	
	public void cadastrar(CursosFormacaoPfTO to) {
		if(Objects.isNull(to.getPessoaFisica())) {
			throw new NotFoundException("Pessoa Física não informada.");
		}		
		
		camposObrigatoriosRule.verificar(to.getNome(), to.getPessoaFisica().getId());
		
		Optional<PessoaFisica> pessoaFisica = pessoaFisicaRepository.findById(to.getPessoaFisica().getId());
		if(!pessoaFisica.isPresent() ) {
			throw new NotFoundException("Pessoa Física informada não existe.");
		}
		
		CursosFormacaoPf entity = new CursosFormacaoPf();

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
