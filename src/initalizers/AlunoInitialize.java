package initalizers;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import javax.swing.JOptionPane;

import model.Aluno;
import controller.AlunoController;


public class AlunoInitialize implements Initializable{
	
	
	@FXML
	private TextField tfMatricula;
	@FXML
	private TextField tfNome;
	@FXML
	private TextField tfEmail;
	@FXML
	private Button btCadastrar;
	
	@FXML
	private Button btnNovo;
	
	@FXML
	private TableView<Aluno> tabelaAluno;
	@FXML
	private TableColumn tabelaalunoMatricula;
	@FXML
	private TableColumn tabelaalunoNome;
	
	private ObservableList<Aluno> data;
	
	private AlunoController ac = new AlunoController();
	
	@Override
	public void initialize(URL url, ResourceBundle resource) {
		
		//Preenche a tabela dos alunos
		tableData(ac.list());
		
		
		btCadastrar.setOnAction(new EventHandler<ActionEvent>(){
			
			@Override
			public void handle(ActionEvent event) {

				Aluno aluno = new Aluno();
				aluno.setMatricula(Integer.parseInt(tfMatricula.getText()));
				aluno.setNome(tfNome.getText());
				aluno.setEmail(tfEmail.getText());
				
					
				ac.save(aluno);
				limpaDados();
				mensagem("Aluno Cadastrado com Sucesso");
			}
		});
		
		
		btnNovo.setOnAction(new EventHandler<ActionEvent>(){
			
			@Override
			public void handle(ActionEvent event) {
				Aluno a = new Aluno();
				limpaDados();
			}
		});
		
		tabelaAluno.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Aluno>() {

			@Override
			public void changed(ObservableValue<? extends Aluno> observable,
					Aluno oldValue, Aluno newValue) {
				preencheTela(newValue);
				
			}	
		});
	}
	
	
	/**
	 * Método responsável por limpar a tela e exibir a mensagem de cadastro
	 */
	private void limpaDados(){
		tfMatricula.setText("");
		tfNome.setText("");
		tfEmail.setText(""); 

		tableData(ac.list());
	}
	
	/**
	 * Método responsável por popular(preencher) a tabela
	 * @param alunos
	 */
	private void tableData(List<Aluno> alunos){
		
		tabelaalunoMatricula.setCellValueFactory(new PropertyValueFactory<Aluno, Integer>("matricula"));
		tabelaalunoNome.setCellValueFactory(new PropertyValueFactory<Aluno, String>("nome"));
		
		data = FXCollections.observableArrayList(alunos);
		tabelaAluno.setItems(data);
		
	}
	
	/**
	 * Método Responsavel por preencher a tela com o dado clicado da tabela
	 * @param aluno
	 */
	private void preencheTela(Aluno aluno){
		if(aluno != null){
			tfMatricula.setText(aluno.getMatricula().toString());
			tfNome.setText(aluno.getNome());
			tfEmail.setText(aluno.getEmail()); 
		}
	}
	
	
	private void mensagem(String msg){
		JOptionPane.showMessageDialog(null,msg);
	}

}
