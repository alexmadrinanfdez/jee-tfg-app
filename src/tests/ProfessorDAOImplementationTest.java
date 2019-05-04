package tests;

import static org.junit.Assert.*;

import java.util.Collection;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import dao.ProfessorDAO;
import dao.ProfessorDAOImplementation;
import model.Professor;

public class ProfessorDAOImplementationTest {
	ProfessorDAO pdao = ProfessorDAOImplementation.getInstance();
	Professor professor = new Professor();

	@Before
	public void setUp() throws Exception {
		professor.setEmail("email@mail.com");
		professor.setName("nombre");
		professor.setPassword("password");
	}

	@After
	public void tearDown() throws Exception {}

	@Test
	public void testCreate() {
		pdao.create( professor );
		
		assertNotNull(pdao);
		assertNotNull(professor.getEmail());
	}

	@Test
	public void testRead() {
		professor = pdao.read( "email@mail.com" );
		
		assertNotNull(professor);
		assertNotNull(professor.getName());
		assertNotNull(professor.getEmail());
		assertNotNull(professor.getPassword());
	}

	@Test
	public void testUpdate() {
		professor.setEmail("mail@mail.com");
		pdao.update( professor );
		
		Professor p = pdao.read("mail@mail.com");
		
		assertEquals(p.getName(), professor.getName());
		assertNotEquals(p.getEmail(), "mail@email.com");
		assertEquals(p.getEmail(), professor.getEmail());
	}

	@Test
	public void testDelete() {
		professor = pdao.read("email@mail.com");
		
		pdao.delete(professor);
	}

	@Test
	public void testReadAll() {
		Collection<Professor> ps = pdao.readAll();
		
		assertFalse(ps.isEmpty());
	}

}
