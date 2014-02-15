package controller;

import java.util.List;

import model.Aluno;
import dao.AlunoDAO;

public class AlunoController {
	
	public void save(Aluno aluno){
		AlunoDAO alunoDAO = new AlunoDAO();
		alunoDAO.save(aluno);
	}
	
	public List<Aluno> list(){
		AlunoDAO alunoDAO = new AlunoDAO();
		return alunoDAO.list();
	}
	
}
