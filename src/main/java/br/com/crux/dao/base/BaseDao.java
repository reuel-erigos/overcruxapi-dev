package br.com.crux.dao.base;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class BaseDao {
	
	@Autowired protected EntityManager em;

	private static final ThreadLocal<EntityManager> SESSION = new ThreadLocal<EntityManager>();
	
	protected Session getSession() {
		return currentEntityManager().unwrap(Session.class);
	}

	protected EntityManager currentEntityManager() {
		EntityManager manager = (EntityManager) SESSION.get();
		if (manager == null) {
			manager = em;
			SESSION.set(manager);
		}
		return manager;
	}

	protected void closeEntityManager() {
		EntityManager manager = (EntityManager) SESSION.get();
		if (manager != null) {
			manager.close();
		}
		SESSION.set(null);
	}

}
