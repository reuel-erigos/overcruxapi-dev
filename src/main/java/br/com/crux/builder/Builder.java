package br.com.crux.builder;

import java.util.List;
import java.util.stream.Collectors;

public abstract class Builder<E,TO> {

	public List<TO> buildTO(List<E> lista) {
		return lista.stream().map(this::buildTO).collect(Collectors.toList());
	}
	
	public abstract TO buildTO(E e);
}
