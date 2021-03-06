package quinielasocial.lab.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.persistence.criteria.Predicate;

import org.springframework.util.CollectionUtils;
import org.zkoss.bind.annotation.AfterCompose;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.Init;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Session;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.WrongValueException;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zkplus.spring.SpringUtil;
import org.zkoss.zul.Button;
import org.zkoss.zul.Datebox;
import org.zkoss.zul.Div;
import org.zkoss.zul.Label;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Textbox;

import javassist.bytecode.stackmap.BasicBlock.Catch;
import quinielasocial.lab.business.services.CRUDService;
import quinielasocial.lab.domain.entity.Equipo;
import quinielasocial.lab.domain.entity.Jugador;
import quinielasocial.lab.domain.entity.Persona;
import quinielasocial.lab.domain.entity.Rol;
import quinielasocial.lab.domain.entity.Usuario;


public class ControladorUsuario extends SelectorComposer<Component> {
	private static final long serialVersionUID = 1L;
	
	@WireVariable
	protected CRUDService serviciopersona = (CRUDService) SpringUtil.getBean("CRUDService");
	@WireVariable
	protected CRUDService serviciojugador = (CRUDService) SpringUtil.getBean("CRUDService");
	@WireVariable
	protected CRUDService serviciousuario = (CRUDService) SpringUtil.getBean("CRUDService");
	@WireVariable
	protected CRUDService serviciorol = (CRUDService) SpringUtil.getBean("CRUDService");
	
	private List<Persona> personas;
	private List<Jugador> jugadores;
	private List<Usuario> usuarios;

	private List<Rol> roles;
	private Usuario unusuario;
	Persona personau = new Persona();
	Jugador jugadoru = new Jugador();
	private Div modificarPerfil; 
	

	
	//wire components registrar
	@Wire
	Textbox cedula;
	@Wire
	Textbox nombre;
	@Wire
	Textbox apellido;
	@Wire
	Textbox correo;
	@Wire
	Datebox fechanacimiento;
	@Wire
	Textbox clave;
	@Wire
	Textbox confcontrasena;
	@Wire
	Button urlfoto;
	//wire components login
		@Wire
		Textbox usuario;
		@Wire
		Textbox password;
	//Mensajes
	@Wire
	Label message;
	
		
		public ControladorUsuario(){
			super();
//			Persona personh = new Persona("19849215", (long)1, "Gerardo", "Cordero", new Date(), "/me.jpg", 
//					true, "administrador@gmail.com");
//			serviciopersona.Save(personh);
//			Rol rolh = new Rol((long)1, "administrador");
//			serviciorol.Save(rolh);
//			Rol rolh2 = new Rol((long)2, "jugador");
//			serviciorol.Save(rolh2);
//			Usuario usuarioh = new Usuario((long)1, "1234", new Date(), true, (long)1, "administrador@gmail.com");
//			serviciousuario.Save(usuarioh);
//			Jugador jugadorh = new Jugador((long)1, (float)0, new Date(), "19849215");
//			serviciojugador.Save(jugadorh);
			//Se llenan con un try/catch cuando las tablas estan vacias
			personas = serviciopersona.getAll(Persona.class);
			usuarios = serviciousuario.getAll(Usuario.class);
			jugadores = serviciojugador.getAll(Jugador.class);
		
						
		}
		
		
		
		@Listen("onClick=#login; onOK=#loginWin")
		public void doLogin(){
			
			//Login
			String txtUser = usuario.getValue().toString();
			String txtPass = password.getValue().toString();
			
			Session miSession = Sessions.getCurrent();
			
			//Paso 1 Buscar usuario con ese correo: Hay dos formas, 1. con una consulta SQL, 2. En los servicios.
			//Paso 2 si existe validad que la contraseña sea la correcta.
			//Paso 3 setear variable de sesion
			//Paso 4 si es valido redireccionar
			
			Boolean s=false;
					
			for (int i=0;i<usuarios.size();i++){
			
				//Paso 1 Buscar usuario con ese correo
				if(txtUser.equals(usuarios.get(i).getCorreo())) {

					//Paso 2 si existe validad que la contraseña sea la correcta.
					if (txtPass.equals(usuarios.get(i).getContrasena()) && usuarios.get(i).getIdrol() == 1 ){ //si es jugador
						//Paso 3 setear variable de sesion
						miSession.setAttribute("usuario", usuarios.get(i));	
						//Paso 4 si es valido redireccionar
						Executions.sendRedirect("/administradorPagina/indexSesionAdmin.zul");					
						s=true;
					}
					else
					if (txtPass.equals(usuarios.get(i).getContrasena()) && usuarios.get(i).getIdrol() == 2){ //si es admin
	
						miSession.setAttribute("usuario", usuarios.get(i));					
						Executions.sendRedirect("/indexSesion.zul");		
						s=true;
					}
						
				}
								
			}	
			if (s==false){
				 Messagebox.show("Clave o Usuario Incorrecto, por favor verifique.", "Error", Messagebox.OK, Messagebox.ERROR);
			}	
		}//fin login
		
		@Listen("onClick=#saved; onOK=#registrarWin")
		public void saved(){
			//Registrar
			String txtCedula = cedula.getValue().toString();
			String txtNombre= nombre.getValue().toString();
			String txtApellido = apellido.getValue().toString();
			String txtCorreo = correo.getValue().toString();
			String txtClave =clave.getValue().toString();
			String txtConfcontrasena = confcontrasena.getValue().toString();
			
			@SuppressWarnings("deprecation")
			String txtUrlfoto = urlfoto.getUpload();
			
			//Tomando la fecha de nacimiento y dandole formato correcto
			SimpleDateFormat smFechanacimiento = new SimpleDateFormat("dd/MM/yyyy");
			Date txtFechanacimiento = new Date();
			try {
				txtFechanacimiento = smFechanacimiento.parse(fechanacimiento.getText().toString());
			} catch (WrongValueException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ParseException e) {
				// TODO Auto-generated catch valueblock
				e.printStackTrace();
			}
			
			//Messagebox.show("URL foto, fecha nacimiento"+txtUrlfoto+" "+txtFechanacimiento.toGMTString().toString());
			
			//Paso 1 validad campos no vacios
			//Paso 2 Valida contraseñas iguales
			if(txtClave.equals(txtConfcontrasena) ){
				//Paso 3 si todo el formulario esta completo guardar
				Persona personac = new Persona(txtCedula, (long)personas.size()+1, txtNombre, txtApellido,txtFechanacimiento, txtUrlfoto, true, txtCorreo);
				serviciopersona.Save(personac);
				Usuario usuarioc = new Usuario((long)usuarios.size()+1, txtClave, new Date(), true, 2, txtCorreo);
				serviciousuario.Save(usuarioc);
				Jugador jugadorc = new Jugador((long)jugadores.size()+1, (float)0, new Date(), txtCedula);
				serviciojugador.Save(jugadorc);
				personas.clear();
				personas = serviciopersona.getAll(Persona.class);
				usuarios.clear();
				usuarios = serviciousuario.getAll(Usuario.class);
				jugadores.clear();
				jugadores = serviciojugador.getAll(Jugador.class);
			}else{
				 Messagebox.show("Contreñas no son iguales, por favor verifique.", "Error", Messagebox.OK, Messagebox.ERROR);
			}
		}//fin guardar
		
}
