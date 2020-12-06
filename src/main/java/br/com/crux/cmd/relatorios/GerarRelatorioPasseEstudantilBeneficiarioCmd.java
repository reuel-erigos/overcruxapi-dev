package br.com.crux.cmd.relatorios;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.crux.builder.relatorios.FichaMatriculaTOBuilder;
import br.com.crux.dao.relatorios.PasseEstudantilBeneficiarioDao;
import br.com.crux.infra.relatorio.GeradorRelatorio;
import br.com.crux.to.relatorios.FichaMatriculaTO;

@Component
public class GerarRelatorioPasseEstudantilBeneficiarioCmd {

	@Autowired private GeradorRelatorio geradorRelatorio;
	@Autowired private PasseEstudantilBeneficiarioDao dao;
	@Autowired private FichaMatriculaTOBuilder builder;
	
	public byte[] gerar(List<Integer> listaIdsPessoaFisica,String mimeType) throws Exception  {
		String nomeRelatorio = "Passe_Estudantil";
		String[] path = {"casa_azul", "passe_estudantil"};
		
		Map<String, Object> parametros = new HashMap<>();
		
		List<FichaMatriculaTO> dados = builder.buildAll(dao.get(listaIdsPessoaFisica));
		
		return geradorRelatorio.gerar(parametros, dados, nomeRelatorio, path, mimeType);
	}
	
}
