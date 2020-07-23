package br.com.crux.cmd;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.BiPredicate;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.crux.builder.UsuariosUnidadeTOBuilder;
import br.com.crux.dao.repository.UsuariosUnidadeRepository;
import br.com.crux.entity.UsuariosUnidade;
import br.com.crux.rule.CamposObrigatoriosUsuariosUnidadeRule;
import br.com.crux.to.UsuariosUnidadesTO;

@Component
public class AlterarUsuariosUnidadeCmd {

	@Autowired private CamposObrigatoriosUsuariosUnidadeRule camposObrigatoriosUsuariosUnidadeRule;
	@Autowired private UsuariosUnidadeTOBuilder usuarioUnidadeTOBuilder;
	@Autowired private UsuariosUnidadeRepository usuariosUnidadeRepository;
	@Autowired private GetUsuarioLogadoCmd getUsuarioLogadoCmd;

	private void alterar(UsuariosUnidadesTO usuarioUnidade) {
		camposObrigatoriosUsuariosUnidadeRule.verificar(usuarioUnidade);
		usuarioUnidade.setUsuarioAlteracao(getUsuarioLogadoCmd.getUsuarioLogado().getIdUsuario());
		
		UsuariosUnidade entity = usuarioUnidadeTOBuilder.build(usuarioUnidade);
		usuariosUnidadeRepository.save(entity);
	}

	public void alterarAll(List<UsuariosUnidadesTO> usuariosUnidadesTO, Long idUsuarioSistema) {
		// Lista da unidades que o usuário tem acesso.
		List<UsuariosUnidade> usuariosUnidades = usuariosUnidadeRepository.findByUsuarioSistema(idUsuarioSistema)
				.orElse(new ArrayList<UsuariosUnidade>());

		BiPredicate<UsuariosUnidadesTO, List<UsuariosUnidadesTO>> contemNaLista = (parte, lista) -> lista.stream()
				.anyMatch(parteNova -> Objects.nonNull(parteNova.getId()) && parteNova.getId().equals(parte.getId()));

		// Remove da lista todos os registros que não contém no Banco de Dados
		usuariosUnidades.removeIf(registro -> {
			if (!contemNaLista.test(usuarioUnidadeTOBuilder.buildTO(registro), usuariosUnidadesTO)) {
				usuariosUnidadeRepository.delete(registro);
				return true;
			}
			return false;
		});

		// Adiciona os novos registros
		List<UsuariosUnidadesTO> novos = usuariosUnidadesTO.stream()
				.filter(registro -> Objects.isNull(registro.getId())).collect(Collectors.toList());

		if (Objects.nonNull(novos)) {
			novos.forEach(novo -> alterar(novo));
		}

	}

}
