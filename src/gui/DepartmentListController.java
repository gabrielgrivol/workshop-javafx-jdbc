package gui;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import application.Main;
import gui.util.Alerts;
import gui.util.Utils;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.entities.Department;
import model.services.DepartmentService;

public class DepartmentListController implements Initializable {
	
	private DepartmentService service;

	@FXML
	private TableView<Department> tableViewDepartment;

	@FXML
	private TableColumn<Department, Integer> tablecolumnId;

	@FXML
	private TableColumn<Department, String> tablecolumnName;

	@FXML
	private Button btNovo;
	
	private ObservableList<Department> obsList;

	@FXML
	public void onBtNovoAction(ActionEvent event) {
		Stage parentStage = Utils.currentStage(event);
		createDialogForm("/gui/DepartmentForm.fxml", parentStage);
		System.out.println("Botao Novo");
	}
	
	public void setDepartmentService(DepartmentService service) {
	this.service = service;
	}
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		initializeNodes();
	}

	private void initializeNodes() {
		tablecolumnId.setCellValueFactory(new PropertyValueFactory<>("id"));
		tablecolumnName.setCellValueFactory(new PropertyValueFactory<>("name"));
		
		//macete para o grid acompanhar a tela
        Stage stage = (Stage) Main.getMainScene().getWindow();
        tableViewDepartment.prefHeightProperty().bind(stage.heightProperty());
        
	}
	
	public void updateTableView() {
		if (service == null) {
			throw new IllegalStateException("Servi�o esta null");
		}
		List<Department> list = service.findAll();
		obsList = FXCollections.observableArrayList(list);
		tableViewDepartment.setItems(obsList);
	}
	
	private void createDialogForm(String absoluteName, Stage parentStage) {
		try{
			FXMLLoader loader = new FXMLLoader(getClass().getResource(absoluteName));	
			Pane pane = loader.load();
			
			Stage dialogStage = new Stage();
			dialogStage.setTitle("ENTRE COM OS DADOS DO DEPARTAMENTO");
			dialogStage.setScene(new Scene(pane));
			dialogStage.setResizable(false);
			dialogStage.initOwner(parentStage);
			dialogStage.initModality(Modality.WINDOW_MODAL);
			dialogStage.showAndWait();
			}
		catch (IOException e) {
			Alerts.showAlert("IO Exception","Erro carregando a View", e.getMessage(), AlertType.ERROR);
		}
	}

}
