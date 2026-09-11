package es.ieslaarboleda.clinicaveterinaria.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import es.ieslaarboleda.clinicaveterinaria.dao.ClienteDAO;
import es.ieslaarboleda.clinicaveterinaria.dao.impl.ClienteDAOImpl;
import es.ieslaarboleda.clinicaveterinaria.model.Cliente;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.Node;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class ClientesController implements Initializable{
	
	@FXML private TableView<Cliente> tableClientes;
	@FXML private TableColumn<Cliente, Integer> colCliente;
	@FXML private TableColumn<Cliente, String> colNombre;
	@FXML private TableColumn<Cliente, String> colTelefono;
	@FXML private TableColumn<Cliente, Integer> colEmail;
	
	@FXML private TextField txtNombre;
	@FXML private TextField txtTelefono;
	@FXML private TextField txtEmail;
	private Cliente clienteSeleccionado;
	

	private	ClienteDAO dao = new ClienteDAOImpl();
	
	public void cargarDatos() {
		ObservableList<Cliente> clientes = FXCollections.observableArrayList(dao.getClientes());
		tableClientes.setItems(clientes);
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		colCliente.setCellValueFactory( new PropertyValueFactory<>("id"));
		colNombre.setCellValueFactory( new PropertyValueFactory<>("nombre"));
		colTelefono.setCellValueFactory( new PropertyValueFactory<>("telefono"));
		colEmail.setCellValueFactory( new PropertyValueFactory<>("email"));
		
		cargarDatos();
		
		tableClientes.getSelectionModel().selectedItemProperty().addListener((obs, oldSeleccionado, nuevoSeleccionado) -> {
			clienteSeleccionado = nuevoSeleccionado;
			if (clienteSeleccionado != null) {
				txtNombre.setText(clienteSeleccionado.getNombre());
				txtTelefono.setText(clienteSeleccionado.getTelefono());
				txtEmail.setText(clienteSeleccionado.getEmail());
			}
		});
		
	}
	
	@FXML
	public void guardarCliente() {
		String nombre = txtNombre.getText();
		String telefono = txtTelefono.getText();
		String email = txtEmail.getText();

		if(clienteSeleccionado != null) {
			clienteSeleccionado.setNombre(nombre);
			clienteSeleccionado.setTelefono(telefono);
			clienteSeleccionado.setEmail(email);
			dao.updateCliente(clienteSeleccionado);
		}else {
			
			Cliente nuevoCliente = new Cliente(0, nombre, telefono, email);
			dao.addCliente(nuevoCliente);
		}
		limpiarCampos();
		cargarDatos();

	}
	
	@FXML
	public void eliminarCliente() {
		if (clienteSeleccionado != null) {
			dao.deleteCliente(clienteSeleccionado.getId());
			cargarDatos();
			limpiarCampos();
		}
	}
	
	@FXML
	public void verMascotas(ActionEvent evento) {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("/es/ieslaarboleda/clinicaveterinaria/view/Mascotas.fxml"));
			Scene scn = new Scene(root);
			Stage st = (Stage)((Node) evento.getSource()).getScene().getWindow();
			
			st.setScene(scn);
			st.setTitle("Clinica Veterinaria");
			st.show();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	@FXML
	public void limpiarCampos() {
		txtNombre.clear();
		txtTelefono.clear();
		txtEmail.clear();
		
		clienteSeleccionado = null;
	}
}
