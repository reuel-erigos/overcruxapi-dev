package br.com.crux.cmd.relatorios.gestao_pessoal;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.crux.builder.relatorios.AniversariantesTOBuilder;
import br.com.crux.dao.relatorios.AniversariantesDao;
import br.com.crux.infra.relatorio.GeradorRelatorio;
import br.com.crux.to.relatorios.gestao_pessoal.AniversariantesTO;

@Component
public class GerarRelatorioAniversariantesCmd {

	@Autowired private GeradorRelatorio geradorRelatorio;
	@Autowired private AniversariantesDao dao;
	@Autowired private AniversariantesTOBuilder builder;
	
	public byte[] gerar(List<Integer> listaIdsPessoaFisica,String mimeType) throws Exception  {
		String nomeRelatorio = "RelatorioAniversariantes";
		String[] path = {"casa_azul", "gestao_pessoal", "aniversariantes"};
		
		Map<String, Object> parametros = new HashMap<>();
		
		List<AniversariantesTO> dados = builder.buildAll(dao.get(listaIdsPessoaFisica));
		
		return geradorRelatorio.gerar(parametros, dados, nomeRelatorio, path, mimeType);
	}
	
}
