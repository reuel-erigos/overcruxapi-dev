package br.com.crux.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.crux.cmd.GetGruposModulosCmd;
import br.com.crux.cmd.GetModulosCmd;
import br.com.crux.to.GrupoModuloTO;
import br.com.crux.to.ModuloTO;

@RestController
@RequestMapping(value = "modulo")
public class ModuloService {
	
	@Autowired private GetModulosCmd getModulosCmd; 
	@Autowired private GetGruposModulosCmd getGruposModulosCmd; 
	
	@GetMapping(path = "", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<ModuloTO> getAll() {
		return getModulosCmd.getAll();
	}
	
	@GetMapping(path = "/unidade", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<ModuloTO> getModulosPorUnidadeLogada() {
		return getModulosCmd.getModulosPorUnidadeLogada();
	}
	
	@GetMapping(path = "/unidade/{idUnidade}", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<ModuloTO> getModulosPorUnidade(@PathVariable(name = "idUnidade") Long idUnidade) {
		return getModulosCmd.getModulosPorUnidade(idUnidade);
	}	

	@GetMapping(path = "/{idModulo}/unidade/{idUnidade}", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<GrupoModuloTO> getGrupoModulo(@PathVariable(name = "idUnidade") Long idUnidade, 
			                                  @PathVariable(name = "idModulo") Long idModulo) {
		return getGruposModulosCmd.getGrupoModulo(idUnidade, idModulo);
	}
	
}
