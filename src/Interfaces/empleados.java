package Interfaces;


import java.awt.Color;
import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.sql.ResultSet;

import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;
import javax.swing.table.DefaultTableModel;


import Clases.Usuario;
import Conect.Conexion;

import javax.swing.JTextField;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;


public class empleados extends JFrame {

	private JPanel contentPane;
	private int xMouse, yMouse;
	private JLabel lblx, lbl_min;
	private JTextField tfci;
	private JTextField tfNombre;
	private JTextField tfApellido;
	private JTextField tfClave;
	private JTable table;
	private JTextField tfTelefono;

	private JTextField tfFecha;
	private JTextField ciActual;
	private JComboBox cboxTipo;
	
	private String[] titulos= {"CI", "Nombres", "Apellidos", "Telefono", "Fecha Registro"};
	
	private JButton btnAgregar, btnEditar, btnEliminar, btnCancelar;
	DefaultTableModel modelo;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					empleados frame = new empleados();
					frame.setVisible(true);
					frame.setLocationRelativeTo(null);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public empleados() {
		setLocationByPlatform(true);
		setUndecorated(true);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1384, 830);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel_menu = new JPanel();
		panel_menu.setBackground(new Color(41,52,98));
		panel_menu.setBounds(0, 49, 288, 781);
		contentPane.add(panel_menu);
		panel_menu.setLayout(null);
		
		JButton btnEmpleado = new JButton("EMPLEADOS");
		btnEmpleado.setRolloverSelectedIcon(new ImageIcon(empleados.class.getResource("/imgs/empleado.png")));
		btnEmpleado.setRolloverIcon(new ImageIcon(empleados.class.getResource("/imgs/empleado.png")));
		btnEmpleado.setIcon(new ImageIcon(empleados.class.getResource("/imgs/empleado.png")));
		btnEmpleado.setFocusPainted(false);
		btnEmpleado.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

		btnEmpleado.setFont(new Font("Yu Gothic UI Semilight", Font.BOLD, 24));
		btnEmpleado.setForeground(Color.black);
		btnEmpleado.setBackground(new Color(242,76,76));
		btnEmpleado.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnEmpleado.setBorder(null);
		btnEmpleado.setBounds(0, 80, 287, 47);
		panel_menu.add(btnEmpleado);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(0, 76, 287, 2);
		panel_menu.add(separator);
		
		JButton btnVentas = new JButton("VENTAS");
		btnVentas.setRolloverIcon(new ImageIcon(empleados.class.getResource("/imgs/compras.png")));
		btnVentas.setRolloverSelectedIcon(new ImageIcon(empleados.class.getResource("/imgs/compras.png")));
		btnVentas.setIcon(new ImageIcon(empleados.class.getResource("/imgs/compras2.png")));
		btnVentas.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnVentas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				callventas();
			}
		});
		btnVentas.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnVentas.setBackground(new Color(242,76,76));
				btnVentas.setForeground(Color.black);
				
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				btnVentas.setBackground(new Color(41,52,98));
				btnVentas.setForeground(Color.white);
			}
		});
		btnVentas.setForeground(Color.WHITE);
		btnVentas.setFont(new Font("Yu Gothic UI Semilight", Font.BOLD, 24));
		btnVentas.setFocusPainted(false);
		btnVentas.setBorder(null);
		btnVentas.setBackground(new Color(41, 52, 98));
		btnVentas.setBounds(0, 29, 287, 47);
		panel_menu.add(btnVentas);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(0, 129, 287, 2);
		panel_menu.add(separator_1);
		
		JButton btnCategoria = new JButton("CATEGORIAS");
		btnCategoria.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				callcategorias();
			}
		});
		btnCategoria.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnCategoria.setRolloverSelectedIcon(new ImageIcon(empleados.class.getResource("/imgs/categorias.png")));
		btnCategoria.setRolloverIcon(new ImageIcon(empleados.class.getResource("/imgs/categorias.png")));
		btnCategoria.setIcon(new ImageIcon(empleados.class.getResource("/imgs/categorias2.png")));
		btnCategoria.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnCategoria.setBackground(new Color(242,76,76));
				btnCategoria.setForeground(Color.black);
				
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				btnCategoria.setBackground(new Color(41,52,98));
				btnCategoria.setForeground(Color.white);
			}
		});
		btnCategoria.setForeground(Color.WHITE);
		btnCategoria.setFont(new Font("Yu Gothic UI Semilight", Font.BOLD, 24));
		btnCategoria.setFocusPainted(false);
		btnCategoria.setBorder(null);
		btnCategoria.setBackground(new Color(41, 52, 98));
		btnCategoria.setBounds(0, 131, 287, 47);
		panel_menu.add(btnCategoria);
		
		JSeparator separator_2 = new JSeparator();
		separator_2.setBounds(0, 177, 287, 2);
		panel_menu.add(separator_2);
		
		JButton btnMenu = new JButton("MENU");
		btnMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				callmenu();
			}
		});
		btnMenu.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

		btnMenu.setRolloverSelectedIcon(new ImageIcon(empleados.class.getResource("/imgs/piezas-de-cubiertos.png")));
		btnMenu.setRolloverIcon(new ImageIcon(empleados.class.getResource("/imgs/piezas-de-cubiertos.png")));
		btnMenu.setIcon(new ImageIcon(empleados.class.getResource("/imgs/piezas-de-cubiertos2.png")));
		btnMenu.setForeground(Color.WHITE);
		btnMenu.setFont(new Font("Yu Gothic UI Semilight", Font.BOLD, 24));
		btnMenu.setFocusPainted(false);
		btnMenu.setBorder(null);
		btnMenu.setBackground(new Color(41, 52, 98));
		btnMenu.setBounds(0, 181, 287, 47);
		btnMenu.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnMenu.setBackground(new Color(242,76,76));
				btnMenu.setForeground(Color.black);
				
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				btnMenu.setBackground(new Color(41,52,98));
				btnMenu.setForeground(Color.white);
			}
		});
		panel_menu.add(btnMenu);
		
		JSeparator separator_3 = new JSeparator();
		separator_3.setBounds(0, 227, 287, 2);
		panel_menu.add(separator_3);
		
		JPanel btnCerrarSesion = new JPanel();
		btnCerrarSesion.setBounds(0, 714, 287, 56);
		panel_menu.add(btnCerrarSesion);
		btnCerrarSesion.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		
				btnCerrarSesion.setBackground(new Color(236, 155, 59));
				btnCerrarSesion.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseEntered(MouseEvent e) {
						btnCerrarSesion.setBackground(new Color(247, 215, 22));
					}
					@Override
					public void mouseExited(MouseEvent e) {
						btnCerrarSesion.setBackground(new Color(236, 155, 59));
					}
					@Override
					public void mouseClicked(MouseEvent e) {
						calllogin();
					}
				});
				btnCerrarSesion.setLayout(null);
				
				JLabel lblNewLabel_3 = new JLabel("CERRAR SESION");
				lblNewLabel_3.setBounds(0, 0, 287, 56);
				btnCerrarSesion.add(lblNewLabel_3);
				lblNewLabel_3.setFont(new Font("Dubai Light", Font.BOLD, 24));
				lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
				
				JLabel lblTitulo = new JLabel("EMPLEADOS");
				lblTitulo.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 21));
				lblTitulo.setBounds(704, 72, 129, 38);
				contentPane.add(lblTitulo);
				
				tfci = new JTextField();
				tfci.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 15));
				tfci.setBounds(305, 156, 402, 30);
				contentPane.add(tfci);
				tfci.setColumns(10);
				
				JLabel lblNewLabel = new JLabel("Numero de Carnet (CI)");
				lblNewLabel.setFont(new Font("Yu Gothic UI Semilight", Font.PLAIN, 15));
				lblNewLabel.setBounds(305, 131, 198, 21);
				contentPane.add(lblNewLabel);
				
				tfNombre = new JTextField();
				tfNombre.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 15));
				tfNombre.setColumns(10);
				tfNombre.setBounds(305, 222, 402, 30);
				contentPane.add(tfNombre);
				
				JLabel lblNombres = new JLabel("Nombres");
				lblNombres.setFont(new Font("Yu Gothic UI Semilight", Font.PLAIN, 15));
				lblNombres.setBounds(305, 197, 198, 21);
				contentPane.add(lblNombres);
				
				tfApellido = new JTextField();
				tfApellido.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 15));
				tfApellido.setColumns(10);
				tfApellido.setBounds(305, 288, 402, 30);
				contentPane.add(tfApellido);
				
				JLabel lblApellidos = new JLabel("Apellidos");
				lblApellidos.setFont(new Font("Yu Gothic UI Semilight", Font.PLAIN, 15));
				lblApellidos.setBounds(305, 263, 198, 21);
				contentPane.add(lblApellidos);
				
				tfClave = new JTextField();
				tfClave.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 15));
				tfClave.setColumns(10);
				tfClave.setBounds(308, 438, 399, 30);
				contentPane.add(tfClave);
				
				JLabel lblClave = new JLabel("Clave de Acceso al sistema");
				lblClave.setFont(new Font("Yu Gothic UI Semilight", Font.PLAIN, 15));
				lblClave.setBounds(305, 406, 198, 21);
				contentPane.add(lblClave);
				
				btnAgregar = new JButton("Agregar");
				btnAgregar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
				btnAgregar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						String tipo= cboxTipo.getSelectedItem().toString();
						Usuario obj=new Usuario();
						Conexion con=new Conexion();
						if(ComprobarCampos()) {
							obj=RecuperarDatos();
							String sentencia=String.format("Insert into usuario (ci, nombre, apellido, clave, telefono, fecha, tipo_usuario) "
									+ "values('%s', '%s', '%s', '%s', '%s', '%s', '%s')", obj.getCi(), obj.getNombre(), obj.getApellido(), obj.getClave(), obj.getTelefono(), tfFecha.getText(), obj.getTipo());
							int res=con.ejecutarSentenciaSql(sentencia);
							if (res==1) {
								limpiarCampos();
								resetBotones();
								mostrarDatos(titulos);
								if(tipo.equals("Empleado")) {

									JOptionPane.showMessageDialog(null,"Empleado Registrado exitosamente","Exito",JOptionPane.INFORMATION_MESSAGE);
								}else {

									JOptionPane.showMessageDialog(null,"Administrador Registrado exitosamente","Exito",JOptionPane.INFORMATION_MESSAGE);
								}
								
							}

							
							
						}
						
					}
				});
				btnAgregar.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseEntered(MouseEvent e) {
						btnAgregar.setBackground(new Color(242,76,76));
						btnAgregar.setForeground(Color.black);
						
					}
					
					@Override
					public void mouseExited(MouseEvent e) {
						btnAgregar.setBackground(new Color(41,52,98));
						btnAgregar.setForeground(Color.white);
					}
				});
				btnAgregar.setSelectedIcon(new ImageIcon(empleados.class.getResource("/imgs/agregar.png")));
				btnAgregar.setRolloverSelectedIcon(new ImageIcon(empleados.class.getResource("/imgs/agregar.png")));
				btnAgregar.setRolloverIcon(new ImageIcon(empleados.class.getResource("/imgs/agregar.png")));
				btnAgregar.setIcon(new ImageIcon(empleados.class.getResource("/imgs/agregar2.png")));
				btnAgregar.setForeground(Color.WHITE);
				btnAgregar.setFont(new Font("Yu Gothic UI Semilight", Font.BOLD, 18));
				btnAgregar.setFocusPainted(false);
				btnAgregar.setBorder(null);
				btnAgregar.setBackground(new Color(41, 52, 98));
				btnAgregar.setBounds(298, 615, 138, 62);
				contentPane.add(btnAgregar);
				
				btnEditar = new JButton("Editar");
				btnEditar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						editar();
					}
				});
				btnEditar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
				btnEditar.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseEntered(MouseEvent e) {
						btnEditar.setBackground(new Color(242,76,76));
						btnEditar.setForeground(Color.black);
						
					}
					
					@Override
					public void mouseExited(MouseEvent e) {
						btnEditar.setBackground(new Color(41,52,98));
						btnEditar.setForeground(Color.white);
					}
				});
				btnEditar.setSelectedIcon(new ImageIcon(empleados.class.getResource("/imgs/editar1.png")));
				btnEditar.setRolloverSelectedIcon(new ImageIcon(empleados.class.getResource("/imgs/editar1.png")));
				btnEditar.setRolloverIcon(new ImageIcon(empleados.class.getResource("/imgs/editar1.png")));
				btnEditar.setIcon(new ImageIcon(empleados.class.getResource("/imgs/editar2.png")));
				btnEditar.setForeground(Color.WHITE);
				btnEditar.setFont(new Font("Yu Gothic UI Semilight", Font.BOLD, 18));
				btnEditar.setBorder(null);
				btnEditar.setBackground(new Color(41, 52, 98));
				btnEditar.setBounds(446, 615, 138, 62);
				contentPane.add(btnEditar);
				
				btnEliminar = new JButton("Eliminar");
				btnEliminar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						eliminar();
					}
				});
				btnEliminar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
				btnEliminar.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseEntered(MouseEvent e) {
						btnEliminar.setBackground(new Color(242,76,76));
						btnEliminar.setForeground(Color.black);
						
					}
					
					@Override
					public void mouseExited(MouseEvent e) {
						btnEliminar.setBackground(new Color(41,52,98));
						btnEliminar.setForeground(Color.white);
					}
				});
				btnEliminar.setSelectedIcon(new ImageIcon(empleados.class.getResource("/imgs/eliminar1.png")));
				btnEliminar.setRolloverSelectedIcon(new ImageIcon(empleados.class.getResource("/imgs/eliminar1.png")));
				btnEliminar.setRolloverIcon(new ImageIcon(empleados.class.getResource("/imgs/eliminar1.png")));
				btnEliminar.setIcon(new ImageIcon(empleados.class.getResource("/imgs/eliminar2.png")));
				btnEliminar.setForeground(Color.WHITE);
				btnEliminar.setFont(new Font("Yu Gothic UI Semilight", Font.BOLD, 18));
				btnEliminar.setFocusPainted(false);
				btnEliminar.setBorder(null);
				btnEliminar.setBackground(new Color(41, 52, 98));
				btnEliminar.setBounds(613, 615, 138, 62);
				contentPane.add(btnEliminar);
				
				JScrollPane scrollPane = new JScrollPane();
				scrollPane.setBounds(774, 188, 600, 489);
				contentPane.add(scrollPane);
				
				table = new JTable();
				table.setBackground(new Color(250, 240, 230));
				table.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 15));
				modelo= new DefaultTableModel(null, titulos);
				table.setModel(modelo);
			
					
				scrollPane.setViewportView(table);
				mostrarDatos(titulos);
				
				btnCancelar = new JButton("Cancelar");
				btnCancelar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						limpiarCampos();
						resetBotones();
						mostrarDatos(titulos);
					}
				});
				btnCancelar.addMouseListener(new MouseAdapter() {


					@Override
					public void mouseEntered(MouseEvent e) {
						btnCancelar.setBackground(new Color(242,76,76));
						btnCancelar.setForeground(Color.black);
						
					}
					
					@Override
					public void mouseExited(MouseEvent e) {
						btnCancelar.setBackground(new Color(41,52,98));
						btnCancelar.setForeground(Color.white);
					}
				});
				btnCancelar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
				btnCancelar.setSelectedIcon(new ImageIcon(empleados.class.getResource("/imgs/cancelar1.png")));
				btnCancelar.setRolloverSelectedIcon(new ImageIcon(empleados.class.getResource("/imgs/cancelar1.png")));
				btnCancelar.setRolloverIcon(new ImageIcon(empleados.class.getResource("/imgs/cancelar1.png")));
				btnCancelar.setIcon(new ImageIcon(empleados.class.getResource("/imgs/cancelar2.png")));
				btnCancelar.setForeground(Color.WHITE);
				btnCancelar.setFont(new Font("Yu Gothic UI Semilight", Font.BOLD, 18));
				btnCancelar.setFocusPainted(false);
				btnCancelar.setBorder(null);
				btnCancelar.setBackground(new Color(41, 52, 98));
				btnCancelar.setBounds(613, 522, 138, 62);
				contentPane.add(btnCancelar);
				
				JLabel lblTipoDeUsuario = new JLabel("Tipo de Usuario:");
				lblTipoDeUsuario.setFont(new Font("Yu Gothic UI Semilight", Font.PLAIN, 15));
				lblTipoDeUsuario.setBounds(298, 506, 198, 21);
				contentPane.add(lblTipoDeUsuario);
				
				cboxTipo = new JComboBox();
				cboxTipo.setBackground(new Color(135, 206, 250));
				cboxTipo.setFont(new Font("Yu Gothic UI Semilight", Font.PLAIN, 18));
				cboxTipo.setModel(new DefaultComboBoxModel(new String[] {"Empleado", "Administrador"}));
				cboxTipo.setBounds(308, 538, 224, 30);
				contentPane.add(cboxTipo);
				
				JLabel lblTelefono = new JLabel("Telefono");
				lblTelefono.setFont(new Font("Yu Gothic UI Semilight", Font.PLAIN, 15));
				lblTelefono.setBounds(305, 329, 198, 21);
				contentPane.add(lblTelefono);
				
				tfTelefono = new JTextField();
				tfTelefono.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 15));
				tfTelefono.setColumns(10);
				tfTelefono.setBounds(305, 354, 402, 30);
				contentPane.add(tfTelefono);
				
				JSeparator separator_4 = new JSeparator();
				separator_4.setBounds(313, 109, 900, 2);
				contentPane.add(separator_4);
				
				ciActual = new JTextField();
				ciActual.setEditable(false);
				ciActual.setFont(new Font("Yu Gothic UI Semilight", Font.PLAIN, 15));
				ciActual.setColumns(10);
				ciActual.setBounds(925, 156, 255, 30);
				contentPane.add(ciActual);
				
				JLabel lblCiActual = new JLabel("CI SELECCIONADO");
				lblCiActual.setFont(new Font("Yu Gothic UI Semilight", Font.PLAIN, 15));
				lblCiActual.setBounds(789, 160, 151, 21);
				contentPane.add(lblCiActual);
				
				JSeparator separator_5 = new JSeparator();
				separator_5.setOrientation(SwingConstants.VERTICAL);
				separator_5.setBounds(767, 112, 12, 718);
				contentPane.add(separator_5);
				
				JLabel lblNewLabel_1 = new JLabel("");
				lblNewLabel_1.setBounds(1223, 11, 151, 150);
				contentPane.add(lblNewLabel_1);
				lblNewLabel_1.setIcon(new ImageIcon(empleados.class.getResource("/imgs/logo_pq.jpg")));
				
				JPanel superior_panel = new JPanel();
				superior_panel.setBorder(new MatteBorder(1, 1, 0, 1, (Color) new Color(0, 0, 0)));
				
						superior_panel.setBackground(Color.WHITE);
						superior_panel.setBounds(0, 0, 1384, 49);
						contentPane.add(superior_panel);
						superior_panel.setLayout(null);
						
						JPanel btn_exit = new JPanel();
						btn_exit.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
						btn_exit.setBorder(new LineBorder(new Color(0, 0, 0)));
						btn_exit.setBackground(Color.WHITE);
						btn_exit.addMouseListener(new MouseAdapter() {
							@Override
							public void mouseClicked(MouseEvent e) {
								System.exit(0);
							}
							@Override
							public void mouseEntered(MouseEvent e) {
								btn_exit.setBackground(Color.red);
								lblx.setForeground(Color.white);
							}
							@Override
							public void mouseExited(MouseEvent e) {
								btn_exit.setBackground(Color.white);
								lblx.setForeground(Color.black);
							}
						});
						btn_exit.setBounds(0, 0, 53, 49);
						superior_panel.add(btn_exit);
						btn_exit.setLayout(null);
						
						lblx = new JLabel("X");
						lblx.setHorizontalAlignment(SwingConstants.CENTER);
						lblx.setBounds(0, 0, 53, 49);
						btn_exit.add(lblx);
						lblx.setFont(new Font("Yu Gothic UI Semilight", Font.PLAIN, 35));
						
						tfFecha = new JTextField();
						tfFecha.setEditable(false);
						tfFecha.setBounds(970, 13, 155, 30);
						superior_panel.add(tfFecha);
						tfFecha.setFont(new Font("Yu Gothic UI Semilight", Font.PLAIN, 16));
						tfFecha.setColumns(10);
						
						JLabel lblFecha = new JLabel("FECHA");
						lblFecha.setFont(new Font("Yu Gothic UI Semilight", Font.PLAIN, 15));
						lblFecha.setBounds(918, 19, 62, 19);
						superior_panel.add(lblFecha);
						
						JPanel btn_min = new JPanel();
						btn_min.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
						btn_min.addMouseListener(new MouseAdapter() {
							@Override
							public void mouseClicked(MouseEvent e) {
								setExtendedState(ICONIFIED);
							}
							@Override
							public void mouseEntered(MouseEvent e) {
								btn_min.setBackground(Color.ORANGE);
								lbl_min.setForeground(Color.white);
							}
							@Override
							public void mouseExited(MouseEvent e) {
								btn_min.setBackground(Color.white);
								lbl_min.setForeground(Color.black);
							}
						});
						btn_min.setLayout(null);
						btn_min.setBorder(new LineBorder(new Color(0, 0, 0)));
						btn_min.setBackground(Color.WHITE);
						btn_min.setBounds(55, 0, 53, 49);
						superior_panel.add(btn_min);
						
						lbl_min = new JLabel("-");
						lbl_min.setHorizontalAlignment(SwingConstants.CENTER);
						lbl_min.setFont(new Font("Yu Gothic UI Semilight", Font.PLAIN, 35));
						lbl_min.setBounds(0, 0, 53, 49);
						btn_min.add(lbl_min);
		

		superior_panel.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				xMouse=e.getX();
				yMouse=e.getY();
			}
		});
		
		superior_panel.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				int x=e.getXOnScreen();
				int y=e.getYOnScreen();
				
				setLocation(x-xMouse, y-yMouse);
			}
		});
		
		//otras funciones
		
		table.addMouseListener(new MouseAdapter() {
			


			
			@Override
			public void mouseClicked(MouseEvent e) {
				if(e.getClickCount()==1) {
					JTable receptor=(JTable) e.getSource();
					tfci.setText(receptor.getModel().getValueAt(receptor.getSelectedRow(), 0).toString());
					String ci=tfci.getText();
					ciActual.setText(ci);
					Seleccion(ci);

					
				}
				btnAgregar.setEnabled(false);
				btnEditar.setEnabled(true);
				btnEliminar.setEnabled(true);


				
			}
		});
		
		fechaActual();
		resetBotones();
		setIconImage(getIconImage());
	}
	public Usuario RecuperarDatos() {
		Usuario obj=new Usuario();
		obj.setCi(tfci.getText());
		obj.setNombre(tfNombre.getText());
		obj.setApellido(tfApellido.getText());
		obj.setClave(tfClave.getText());
		obj.setTelefono(tfTelefono.getText());
		obj.setTipo(cboxTipo.getSelectedItem().toString());
		
		return obj;
		
		
	}
	public boolean ComprobarCampos() {
		if(tfci.getText().isEmpty() || tfApellido.getText().isEmpty() || tfNombre.getText().isEmpty() 
			|| tfClave.getText().isEmpty()) {

			
			JOptionPane.showMessageDialog(null,"Todos los Campos son obligatorios","Advertencia",JOptionPane.WARNING_MESSAGE);
			return false;
		}else {
			boolean cicorrecto=tfci.getText().matches("[0-9]*");
			boolean tefcorrecto=tfTelefono.getText().matches("[0-9]*");
			if(cicorrecto && tefcorrecto) {
				return true;
			}else {
				JOptionPane.showMessageDialog(null,"Ingrese datos numericos correctos en el CI o Telefono","Advertencia",JOptionPane.WARNING_MESSAGE);
				return false;
				
			}
			
		}
		
	}
	public void fechaActual() {
		String f="";
		Calendar fecha=new GregorianCalendar();
        int annio = fecha.get(Calendar.YEAR);
        int mes = fecha.get(Calendar.MONTH);
        int dia = fecha.get(Calendar.DAY_OF_MONTH);
        
        f=dia+"-"+(mes+1)+"-"+annio;
        tfFecha.setText(f);
	}
	public void mostrarDatos(String[] titulos) {
		modelo= new DefaultTableModel(null, titulos) {
			@Override 
			public boolean isCellEditable(int row, int column) {  
				return false; 
			}

		};

		
		table.setModel(modelo);

		try {
			Conexion con=new Conexion();
			ResultSet resultado=con.consultarSql("Select * from usuario");
			while(resultado.next()) {
				Object[] emp= {resultado.getInt("ci"), resultado.getString("nombre"), resultado.getString("apellido"),
						resultado.getString("telefono"), resultado.getDate("fecha")};
				
				modelo.addRow(emp);
			}
			table.getColumnModel().getColumn(0).setPreferredWidth(70);
			table.getColumnModel().getColumn(1).setPreferredWidth(120);
			table.getColumnModel().getColumn(2).setPreferredWidth(120);
			table.getColumnModel().getColumn(3).setPreferredWidth(100);
			table.getColumnModel().getColumn(4).setPreferredWidth(75);
			table.setRowHeight(30);
		
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null,"Error en la conexion "+e,"Error",JOptionPane.ERROR_MESSAGE);
		}
		
	}
	public void limpiarCampos() {
		tfci.setText("");
		tfApellido.setText("");
		tfNombre.setText("");
		tfTelefono.setText("");
		tfClave.setText("");
		ciActual.setText("");
		
		
	}
	public void Seleccion(String ci) {
			Conexion con=new Conexion();
			try {
				ResultSet resultado=con.consultarSql("Select * from usuario where ci="+"'"+ci+"'");

				if(resultado.next()) {
					tfci.setText(ci);
					tfNombre.setText(resultado.getString("nombre"));
					tfApellido.setText(resultado.getString("apellido"));
					tfTelefono.setText(resultado.getString("telefono"));
					tfClave.setText(resultado.getString("clave"));
					cboxTipo.setSelectedItem(resultado.getString("tipo_usuario"));

				}
				
			} catch (Exception e2) {
				JOptionPane.showMessageDialog(null,"Error en la conexion","Error",JOptionPane.ERROR_MESSAGE);
			}
		
	}
	public void resetBotones() {
		btnAgregar.setEnabled(true);
		btnEditar.setEnabled(false);
		btnEliminar.setEnabled(false);

	}
	public void eliminar() {
		int dialogButton = JOptionPane.YES_NO_OPTION;
		int dialogResult = JOptionPane.showConfirmDialog (null, "¿Seguro que desea Eliminar al usuario "+tfNombre.getText()+" "+tfApellido.getText()+"?","Advertencia",dialogButton,JOptionPane.WARNING_MESSAGE);
		Conexion con=new Conexion();
		if(ciActual.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null,"Seleccione un Usuario","Exito",JOptionPane.WARNING_MESSAGE);
		}
		if(!(ciActual.getText().isEmpty())&& dialogResult == JOptionPane.YES_OPTION) {
			
			String sentencia=String.format("Delete from usuario where ci='%s'"
					,ciActual.getText());

			int res1=con.ejecutarSentenciaSql(sentencia);
			if(res1==1) {
				limpiarCampos();
				resetBotones();
				mostrarDatos(titulos);
				JOptionPane.showMessageDialog(null,"Usuario Borrado Exitosamente","Exito",JOptionPane.INFORMATION_MESSAGE);
			}

			
		}
	}
	public void editar() {

		String ciac=ciActual.getText();
		Usuario obj=new Usuario();
		Conexion con=new Conexion();
		if(ComprobarCampos()) {
			obj=RecuperarDatos();
			String sentencia=String.format("update usuario set ci='%s', nombre='%s', apellido='%s', telefono='%s', clave='%s', tipo_usuario='%s' "
					+ "where ci='%s'"
					, obj.getCi(), obj.getNombre(), obj.getApellido(), obj.getTelefono(), obj.getClave(), obj.getTipo(), ciac);
			int res1=con.ejecutarSentenciaSql(sentencia);
			if(res1==1) {
				limpiarCampos();
				resetBotones();
				mostrarDatos(titulos);
				JOptionPane.showMessageDialog(null,"Usuario Editado Exitosamente","Exito",JOptionPane.INFORMATION_MESSAGE);
			}	
					

			
		}
	}
	public void callventas() {
		Ventas y=new Ventas();
		this.setVisible(false);
		y.setLocationRelativeTo(null);
		y.setVisible(true);
	}
	public void callcategorias() {
		Categorias y=new Categorias();
		this.setVisible(false);
		y.setLocationRelativeTo(null);
		y.setVisible(true);
	}
	public void callmenu() {
		menu y=new menu();
		this.setVisible(false);
		y.setLocationRelativeTo(null);
		y.setVisible(true);
	}
	public void calllogin() {
		login y=new login();
		this.setVisible(false);
		y.setLocationRelativeTo(null);
		y.setVisible(true);
	}
	@Override
	public Image getIconImage(){
		 Image retValue=Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("imgs/logo_pq.jpg"));
		 return retValue;
	}
}
