package quinielasocial.lab.controller;


import java.util.List;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.*;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zul.*;

import quinielasocial.lab.entity.Torneo;
import quinielasocial.lab.services.torneoServicio;

public class ControladorListaQuinielas extends SelectorComposer<Component> {

	private static final long serialVersionUID = 1L;
	private torneoServicio torneoData = new torneoServicio();

	public ControladorListaQuinielas() {
		super();
	}


	@Wire
	private Textbox txtPalabraClave;
	@Wire
	private Listbox lstListaQuinielas;
	@Wire
	private Label lblModelo;
	@Wire
	private Label lblMarca;
	@Wire
	private Label lblPrecio;
	@Wire
	private Label lblDescripcion;
	@Wire
	private Image imgImagenPrevia;
	
	public torneoServicio torneos = new torneoServicio();

//	private IServicio servicioCarro = new Servicio();
	

	
	@Listen("onClick = #btnBuscar")
	public void search(){
		Clients.showNotification("Lo sentimos :( La B�squeda No Produjo Resultados","ERROR",null,null,1500);

	}

	
}