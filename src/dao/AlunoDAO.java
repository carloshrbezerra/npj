package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Aluno;
import util.ConnectionFactory;

public class AlunoDAO {
	private Connection con;
	
	public AlunoDAO(){
		try {
			con = ConnectionFactory.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void save(Aluno aluno){
		
		try {
			String sql = "insert into aluno(matricula,nome,email)"
					+ "values(?,?,?)";
			
			PreparedStatement ps = (PreparedStatement) con.prepareStatement(sql);
			ps.setInt(1, aluno.getMatricula());
			ps.setString(2, aluno.getNome());
			ps.setString(3, aluno.getEmail());
			
			ps.execute();
			ps.close();
			con.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}	
	}
	
	public List<Aluno> list(){
		
		List<Aluno> alunos = new ArrayList<Aluno>();
		
		try {
			String sql = "select * from aluno";
			
			PreparedStatement ps = (PreparedStatement) con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				Aluno a = new Aluno();
				a.setMatricula(rs.getInt("matricula"));
				a.setNome(rs.getString("nome"));
				a.setEmail(rs.getString("email"));
				alunos.add(a);
			}
			
			ps.close();
			con.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}	
		
		return alunos;
	}
}
