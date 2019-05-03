package dao;

import java.util.Collection;

import org.hibernate.Session;

import model.TFG;

public class TFGDAOImplementation implements TFGDAO {
	private static TFGDAOImplementation instance = null;
	private TFGDAOImplementation() {}
	public static TFGDAOImplementation getInstance() {
		if( null == instance ) 
			instance = new TFGDAOImplementation();
		return instance;
	}
	@Override
	public void create(TFG tfg) {
		Session session = SessionFactoryService.get().openSession();
		try {
			session.beginTransaction();
			session.save( tfg );
			session.getTransaction().commit();
		} catch (Exception e) {
			// manejar excepciones
		} finally {
			session.close();
		}
		
	}
	@Override
	public TFG read(String email) {
		Session session = SessionFactoryService.get().openSession();
		try {
			session.beginTransaction();
			TFG tfg = session.get( TFG.class, email );
			session.getTransaction().commit();
			return tfg;
		} catch (Exception e) {
			return null;
		} finally {
			session.close();
		}
	}
	@Override
	public void update(TFG tfg) {
		Session session = SessionFactoryService.get().openSession();
		try {
			session.beginTransaction();
			session.saveOrUpdate( tfg );
			session.getTransaction().commit();
		} catch (Exception e) {
			// manejar excepciones
		} finally {
			session.close();
		}		
	}
	@Override
	public void delete(TFG tfg) {
		Session session = SessionFactoryService.get().openSession();
		try {
			session.beginTransaction();
			session.delete( tfg );
			session.getTransaction().commit();
		} catch (Exception e) {
			// manejar excepciones
		} finally {
			session.close();
		}
		
	}
	@SuppressWarnings("unchecked")
	@Override
	public Collection<TFG> readAll() {
		Session session = SessionFactoryService.get().openSession();
		try {
			session.beginTransaction();
			Collection<TFG> tfgs = session.createQuery( "from TFG" ).list();
			session.getTransaction().commit();
			return tfgs;
		} catch (Exception e) {
			return null;
		} finally {
			session.close();
		}
	}
}
