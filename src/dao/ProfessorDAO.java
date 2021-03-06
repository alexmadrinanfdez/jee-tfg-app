package dao;

import java.util.Collection;

import model.Professor;

public interface ProfessorDAO {
	
	public void create(Professor professor);
	public Professor read(String email);
	public void update(Professor professor);
	public void delete(Professor professor);
	public Collection<Professor> readAll();
	
}
