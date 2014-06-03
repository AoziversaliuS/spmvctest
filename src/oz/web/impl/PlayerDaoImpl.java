package oz.web.impl;

import javax.annotation.Resource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Component;

import oz.web.dao.IPlayerDao;
import oz.web.pojo.Player;

@Component
public class PlayerDaoImpl implements IPlayerDao {

	@Resource
	private SessionFactory sessionFactory;
	
	@Override
	public void add(Player p) {
		System.out.println("PlayerDaoImpl.add()");
		Session session = sessionFactory.getCurrentSession();
		session.beginTransaction();
		session.save(p);
		session.getTransaction().commit();
	}

	@Override
	public void update(Player p) {
		System.out.println("PlayerDaoImpl��update����");
	}

	@Override
	public void delete(Player p) {
		System.out.println("PlayerDaoImpl��delete����");
	}

	@Override
	public Player get(int id) {
		System.out.println("PlayerDaoImpl��get����");
		return null;
	}

}
