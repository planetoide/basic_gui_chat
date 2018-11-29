package com.chat;

/**
* The HelloWorld program implements an application that
* simply displays "Hello World!" to the standard output.
*
* @author  Elvis Martinez Flores
* @version 1.0
* @since   2018-08-05 
*/

import java.awt.EventQueue;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class chat {

	private JFrame frame;
	private JTextField mensaje;
	//propiedad para recuperar un archivo
	public InputStream input;
	//propiedad para leer el archivo recuperado
	public DataInputStream inst;
	
	public FileOutputStream file;
	public DataOutputStream data;
	
	public int count;
	public String cadenauno = "";
	public String cadenados = "";

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					chat window = new chat();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Constructor de la aplicacion
	 */
	public chat() {
		initialize();
	}

	/**
	 * Metodo para inciar el dibujado de la interfaz grafica
	 */
	private void initialize() {
		/*creacion del panel principal*/
		frame = new JFrame();
		frame.setTitle("Mockup Chat");
		frame.setBounds(100, 100, 587, 419);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		/*Creacion del area de texto*/
		JTextArea textArea = new JTextArea();
		textArea.setBounds(35, 36, 505, 244);
		frame.getContentPane().add(textArea);
		
		/*Creacion de la entrada de texto*/
		mensaje = new JTextField();
		mensaje.setBounds(35, 305, 360, 45);
		frame.getContentPane().add(mensaje); 
		mensaje.setColumns(10);
		
		/*boton para el evento de enviar el mensaje*/
		JButton btnEnviarMensaje = new JButton("Enviar mensaje");
		btnEnviarMensaje.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				cadenados = mensaje.getText();
				textArea.append("\n"+cadenados);
				System.out.println(cadenados);
				mensaje.setText("");
				try {
					file = new FileOutputStream("chat.txt");
					data = new DataOutputStream(file);
					data.writeBytes(textArea.getText());
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		btnEnviarMensaje.setBounds(407, 315, 133, 25);
		frame.getContentPane().add(btnEnviarMensaje);
		
		JLabel lblMensajes = new JLabel("Mensajes");
		lblMensajes.setBounds(35, 13, 56, 16);
		frame.getContentPane().add(lblMensajes);
		
		//escribir texto en la caja de texto
		//pedir el archivo
		try {
			input = new FileInputStream("chat.txt");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		//leer el archivo
		inst = new DataInputStream(input);  
		
		try {
			count = input.available();
			byte[] ary = new byte[count];  
		    inst.read(ary);  
		    for (byte bt : ary) {  
		      char k = (char) bt; 
		      cadenauno = cadenauno	+ k;	      
		      System.out.print(k); 
		    } 
		    System.out.println(cadenauno);
		    textArea.setText(cadenauno);
		    
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
	     
		
	}
}
