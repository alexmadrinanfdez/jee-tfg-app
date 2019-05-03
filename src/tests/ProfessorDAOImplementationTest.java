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
	Professor p = new Professor();

	@Before
	public void setUp() throws Exception {
		professor.setEmail("email@mail.com");
		professor.setName("nombre");
		professor.setPassword("password");
		
		p.setEmail("mail@email.com");
		p.setName("nombre");
		p.setPassword("password");
	}

	@After
	public void tearDown() throws Exception {}

	@Test
	public void testCreate() {
		pdao.create( professor );
		pdao.create( p );
		
		assertNotNull(pdao);
		assertNotNull(p.getEmail());
	}

	@Test
	public void testRead() {
		professor = pdao.read( "email@mail.com" );
		
		assertNotNull(professor);
		assertEquals(p.getName(), professor.getName());
		assertNotEquals(p.getEmail(), professor.getEmail());
		assertEquals(p.getPassword(), professor.getPassword());
	}

	@Test
	public void testUpdate() {
		p.setEmail("mail@mail.com");
		pdao.update( p );
		
		p = pdao.read("mail@mail.com");
		
		assertEquals(p.getName(), professor.getName());
		assertNotEquals(p.getEmail(), "mail@email.com");
		assertNotEquals(p.getEmail(), professor.getEmail());
	}

	@Test
	public void testDelete() {
		professor = pdao.read("email@mail.com");
		p = pdao.read("mail@mail.com");
		
		pdao.delete(professor);
		pdao.delete(p);
	}

	@Test
	public void testReadAll() {
		Collection<Professor> ps = pdao.readAll();
		
		assertTrue(ps.isEmpty());
	}

}
