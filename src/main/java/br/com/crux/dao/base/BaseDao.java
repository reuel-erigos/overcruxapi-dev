package br.com.crux.dao.base;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;

public abstract class BaseDao {

	@Autowired 
	protected EntityManager em;
	
	
}
