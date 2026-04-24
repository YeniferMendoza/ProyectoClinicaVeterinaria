module clinicaveterinaria {
	requires javafx.base;
	requires javafx.fxml;
	requires javafx.controls;
	requires javafx.graphics;
	requires java.sql;	
	
	opens es.ieslaarboleda.clinicaveterinaria.main to javafx.graphics;
	opens es.ieslaarboleda.clinicaveterinaria.model to javafx.base;
	opens es.ieslaarboleda.clinicaveterinaria.controller to javafx.fxml;
	
	exports es.ieslaarboleda.clinicaveterinaria.main;
	exports es.ieslaarboleda.clinicaveterinaria.model;
	exports es.ieslaarboleda.clinicaveterinaria.controller;
}
