package dao;

import java.util.Collection;

import org.hibernate.Session;

import model.Professor;

public class ProfessorDAOImplementation implements ProfessorDAO {
	private static ProfessorDAOImplementation instance = null;
	private ProfessorDAOImplementation() {}
	public static ProfessorDAOImplementation getInstance() {
		if( null == instance )
			instance = new ProfessorDAOImplementation();
		return instance;
	}
	@Override
	public void create(Professor professor) {
		Session session = SessionFactoryService.get().openSession();
		try {
			session.beginTransaction();
			session.save( professor );
			session.getTransaction().commit();
		} catch (Exception e) {
			// manejar excepciones
		} finally {
			session.close();
		}
		
	}
	@Override
	public Professor read(String email) {
		Session session = SessionFactoryService.get().openSession();
		try {
			session.beginTransaction();
			Professor professor = session.get( Professor.class, email );
			session.getTransaction().commit();
			return professor;
		} catch (Exception e) {
			return null;
		} finally {
			session.close();
		}
	}
	@Override
	public void update(Professor professor) {
		Session session = SessionFactoryService.get().openSession();
		try {
			session.beginTransaction();
			session.saveOrUpdate( professor );
			session.getTransaction().commit();
		} catch (Exception e) {
			// manejar excepciones
		} finally {
			session.close();
		}
		
	}
	@Override
	public void delete(Professor professor) {
		Session session = SessionFactoryService.get().openSession();
		try {
			session.beginTransaction();
			session.delete( professor );
			session.getTransaction().commit();
		} catch (Exception e) {
			// manejar excepciones
		} finally {
			session.close();
		}
		
	}
	@SuppressWarnings("unchecked")
	@Override
	public Collection<Professor> readAll() {
		Session session = SessionFactoryService.get().openSession();
		try {
			session.beginTransaction();
			Collection<Professor> professors = session.createQuery( "from Professor" ).list();
			session.getTransaction().commit();
			return professors;
		} catch (Exception e) {
			return null;
		} finally {
			session.close();
		}
	}
}
