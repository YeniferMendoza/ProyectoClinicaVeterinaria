package es.ieslaarboleda.clinicaveterinaria.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import es.ieslaarboleda.clinicaveterinaria.dao.ClienteDAO;
import es.ieslaarboleda.clinicaveterinaria.dao.MascotaDAO;
import es.ieslaarboleda.clinicaveterinaria.dao.impl.ClienteDAOImpl;
import es.ieslaarboleda.clinicaveterinaria.dao.impl.MascotaDAOImpl;
import es.ieslaarboleda.clinicaveterinaria.model.Cliente;
import es.ieslaarboleda.clinicaveterinaria.model.Mascota;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class MascotasController implements Initializable {
	
	@FXML private TableView<Mascota> tableMascotas;
	@FXML private TableColumn<Mascota, Integer> colMascota;
	@FXML private TableColumn<Mascota, String> colNombre;
	@FXML private TableColumn<Mascota, String> colEspecie;
	@FXML private TableColumn<Mascota, String> colRaza;
	@FXML private TableColumn<Mascota, Integer> colEdad;
	@FXML private TableColumn<Mascota, Integer> colCliente;
	
	@FXML private TextField txtNombre;
	@FXML private TextField txtEspecie;
	@FXML private TextField txtRaza;
	@FXML private TextField txtEdad;
	@FXML private ComboBox<Cliente> comboSelecCliente;

	private Mascota mascotaSelecionada;
	private MascotaDAO dao = new MascotaDAOImpl();
	private ClienteDAO daoCliente = new ClienteDAOImpl();

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		colMascota.setCellValueFactory(new PropertyValueFactory<>("id"));
		colNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
		colEspecie.setCellValueFactory(new PropertyValueFactory<>("especie"));
		colRaza.setCellValueFactory(new PropertyValueFactory<>("raza"));
		colEdad.setCellValueFactory(new PropertyValueFactory<>("edad"));
		colCliente.setCellValueFactory(new PropertyValueFactory<>("cliente"));

		cargarDatos();
		cargarCombo();
		
		tableMascotas.getSelectionModel().selectedItemProperty().addListener((obs, oldSeleccionado, nuevoSeleccionado) -> {
			mascotaSelecionada = nuevoSeleccionado;
			if (mascotaSelecionada != null) {
				txtNombre.setText(mascotaSelecionada.getNombre());
				txtEspecie.setText(mascotaSelecionada.getEspecie());
				txtRaza.setText(mascotaSelecionada.getRaza());
				txtEdad.setText(String.valueOf(mascotaSelecionada.getEdad()));
				
				for(Cliente c : comboSelecCliente.getItems()) {
					if(c.getId() == mascotaSelecionada.getCliente()) {
						comboSelecCliente.setValue(c);
					}
				}
			}
		});
	}

	private void cargarDatos() {
		ObservableList<Mascota> mascotas = FXCollections.observableArrayList(dao.getMascotas());
		tableMascotas.setItems(mascotas);
	}
	
	private void cargarCombo() {
		ObservableList<Cliente> clientes = FXCollections.observableArrayList(daoCliente.getClientes());
		comboSelecCliente.setItems(clientes);
	}
	
	@FXML
	public void guardarMascota() {
		String nombre = txtNombre.getText();
		String especie = txtEspecie.getText();
		String raza = txtRaza.getText();
		int edad = Integer.valueOf(txtEdad.getText());
		int cliente = comboSelecCliente.getValue().getId();
		
		if(mascotaSelecionada != null) {
			mascotaSelecionada.setNombre(nombre);
			mascotaSelecionada.setEspecie(especie);
			mascotaSelecionada.setRaza(raza);
			mascotaSelecionada.setEdad(edad);
			mascotaSelecionada.setCliente(cliente);
			dao.updateMascota(mascotaSelecionada);
		}else {
			Mascota mascota = new Mascota(0, nombre, especie, raza, edad, cliente);
			dao.addMascota(mascota);
		}
		cargarDatos();
		limpiarCampos();
		
	}
	
	@FXML
	public void eliminarMascota() {
		if(mascotaSelecionada != null) {
			dao.deleteMascota(mascotaSelecionada.getId());
			cargarDatos();
			limpiarCampos();
		}
	}
	
	@FXML
	public void limpiarCampos() {
		txtNombre.clear();
		txtEspecie.clear();
		txtRaza.clear();
		txtEdad.clear();
		comboSelecCliente.getSelectionModel().clearSelection();
		mascotaSelecionada= null;
	}
	@FXML
	public void verClientes(ActionEvent evento) {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("/es/ieslaarboleda/clinicaveterinaria/view/Clientes.fxml"));
			Scene scn = new Scene(root);
			Stage st = (Stage)((Node) evento.getSource()).getScene().getWindow();
			
			st.setScene(scn);
			st.setTitle("Clinica Veterinaria");
			st.show();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
