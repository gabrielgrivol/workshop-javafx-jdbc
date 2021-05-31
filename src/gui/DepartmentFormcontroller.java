package gui;

import java.net.URL;
import java.util.ResourceBundle;

import gui.util.Constraints;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import model.entities.Department;

public class DepartmentFormcontroller implements Initializable{

	private Department entity;
	
	@FXML
	private TextField txtId;
	
	@FXML
	private TextField txtName;
	
	@FXML
	private Label errorLabelName;
	
	@FXML
	private Button btSalvar;
	
	@FXML
	private Button btCancelar;
	
	public void setDepartment(Department entity) {
		this.entity = entity;
	}

	@FXML
	public void onBtSalvarAction() {
		System.out.println("botal salvar");
	}
	
	@FXML
	public void onBtCancelarAction() {
		System.out.println("botal canelar");
	}
	
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		initializeNodes();
	}
	
	private void initializeNodes() {
		Constraints.setTextFieldInteger(txtId);
		Constraints.setTextFieldMaxLength(txtName, 30);
	}
	
	public void updateFormData() {
		if (entity == null) {
			throw new IllegalStateException("Entity esta vazio");
		}
		txtId.setText(String.valueOf(entity.getId()));
		txtName.setText(entity.getName());
	}

	
}
