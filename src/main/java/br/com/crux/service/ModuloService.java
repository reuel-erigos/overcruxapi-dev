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
	
	@GetMapping(path = "/instituicao", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<ModuloTO> getModulosPorInstituicaoLogada() {
		return getModulosCmd.getModulosPorInstituicaoLogada();
	}
	
	@GetMapping(path = "/instituicao/{idInstituicao}", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<ModuloTO> getModulosPorUnidade(@PathVariable(name = "idInstituicao") Long idInstituicao) {
		return getModulosCmd.getModulosPorInstituicao(idInstituicao);
	}	

	@GetMapping(path = "/{idModulo}/instituicao/{idInstituicao}", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<GrupoModuloTO> getGrupoModulo(@PathVariable(name = "idInstituicao") Long idInstituicao, 
			                                  @PathVariable(name = "idModulo") Long idModulo) {
		return getGruposModulosCmd.getGrupoModulo(idInstituicao, idModulo);
	}
	
}
