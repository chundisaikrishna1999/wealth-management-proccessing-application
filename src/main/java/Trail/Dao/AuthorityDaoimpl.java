package Trail.Dao;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import Trail.model.Authority;

@Service
public class AuthorityDaoimpl {

	@Autowired
	private SessionFactory sessionFactory;

	@Transactional
	public void update(Authority authority) {
		sessionFactory.getCurrentSession().update(authority);
	}

	@Transactional
	public void save(Authority authority) {
		sessionFactory.getCurrentSession().save(authority);
	}
}
