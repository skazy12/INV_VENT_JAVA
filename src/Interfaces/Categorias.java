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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;
import javax.swing.table.DefaultTableModel;

import Clases.Categoria;

import Conect.Conexion;

public class Categorias extends JFrame {

	private JPanel contentPane;
	private int xMouse, yMouse;
	private JLabel lblx, lbl_min;
	private JTextField tfcategoria;
	private JTable table;
	private JTextField tfFecha;
	
	private String[] titulos= {"COD", "CATEGORIA"};
	
	private JButton btnAgregar, btnEditar, btnEliminar, btnCancelar;
	DefaultTableModel modelo;
	private JTextField tfseleccionado;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Categorias frame = new Categorias();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Categorias() {
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
		
		
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon(empleados.class.getResource("/imgs/logo_pq.jpg")));
		lblNewLabel_1.setBounds(1216, 11, 158, 150);
		contentPane.add(lblNewLabel_1);
		
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
		tfFecha.setBounds(1074, 18, 116, 30);
		superior_panel.add(tfFecha);
		tfFecha.setFont(new Font("Yu Gothic UI Semilight", Font.PLAIN, 16));
		tfFecha.setColumns(10);
		
		JLabel lblFecha = new JLabel("FECHA");
		lblFecha.setFont(new Font("Yu Gothic UI Semilight", Font.PLAIN, 15));
		lblFecha.setBounds(1017, 23, 62, 21);
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
		btn_min.setBounds(56, 0, 53, 49);
		superior_panel.add(btn_min);
		
		lbl_min = new JLabel("-");
		lbl_min.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_min.setFont(new Font("Yu Gothic UI Semilight", Font.PLAIN, 35));
		lbl_min.setBounds(0, 0, 53, 49);
		btn_min.add(lbl_min);
		
		JPanel panel_menu = new JPanel();
		panel_menu.setBackground(new Color(41,52,98));
		panel_menu.setBounds(0, 49, 288, 781);
		contentPane.add(panel_menu);
		panel_menu.setLayout(null);
		
		JButton btnEmpleado = new JButton("EMPLEADOS");
		btnEmpleado.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnEmpleado.setBackground(new Color(242,76,76));
				btnEmpleado.setForeground(Color.black);
				
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				btnEmpleado.setBackground(new Color(41,52,98));
				btnEmpleado.setForeground(Color.white);
			}
		});
		btnEmpleado.setRolloverSelectedIcon(new ImageIcon(Categorias.class.getResource("/imgs/empleado.png")));
		btnEmpleado.setRolloverIcon(new ImageIcon(Categorias.class.getResource("/imgs/empleado.png")));
		btnEmpleado.setIcon(new ImageIcon(Categorias.class.getResource("/imgs/empleado2.png")));
		btnEmpleado.setFocusPainted(false);
		btnEmpleado.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

		btnEmpleado.setFont(new Font("Yu Gothic UI Semilight", Font.BOLD, 24));
		btnEmpleado.setForeground(Color.WHITE);
		btnEmpleado.setBackground(new Color(41,52,98));
		btnEmpleado.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				callempleados();
			}
		});
		btnEmpleado.setBorder(null);
		btnEmpleado.setBounds(0, 80, 287, 47);
		panel_menu.add(btnEmpleado);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(0, 76, 287, 2);
		panel_menu.add(separator);
		
		JButton btnVentas = new JButton("VENTAS");
		btnVentas.setRolloverIcon(new ImageIcon(Categorias.class.getResource("/imgs/compras.png")));
		btnVentas.setRolloverSelectedIcon(new ImageIcon(Categorias.class.getResource("/imgs/compras.png")));
		btnVentas.setIcon(new ImageIcon(Categorias.class.getResource("/imgs/compras2.png")));
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
			}
		});
		btnCategoria.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnCategoria.setRolloverSelectedIcon(new ImageIcon(Categorias.class.getResource("/imgs/categorias.png")));
		btnCategoria.setRolloverIcon(new ImageIcon(Categorias.class.getResource("/imgs/categorias.png")));
		btnCategoria.setIcon(new ImageIcon(Categorias.class.getResource("/imgs/categorias.png")));

		btnCategoria.setForeground(Color.BLACK);
		btnCategoria.setFont(new Font("Yu Gothic UI Semilight", Font.BOLD, 24));
		btnCategoria.setFocusPainted(false);
		btnCategoria.setBorder(null);
		btnCategoria.setBackground(new Color(242,76,76));
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

		btnMenu.setRolloverSelectedIcon(new ImageIcon(Categorias.class.getResource("/imgs/piezas-de-cubiertos.png")));
		btnMenu.setRolloverIcon(new ImageIcon(Categorias.class.getResource("/imgs/piezas-de-cubiertos.png")));
		btnMenu.setIcon(new ImageIcon(Categorias.class.getResource("/imgs/piezas-de-cubiertos2.png")));
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
				
				JLabel lblTitulo = new JLabel("CATEGORIAS");
				lblTitulo.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 21));
				lblTitulo.setBounds(735, 73, 145, 38);
				contentPane.add(lblTitulo);
				
				tfcategoria = new JTextField();
				tfcategoria.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 15));
				tfcategoria.setBounds(313, 278, 464, 30);
				contentPane.add(tfcategoria);
				tfcategoria.setColumns(10);
				
				JLabel lblNewLabel = new JLabel("CATEGORIA");
				lblNewLabel.setFont(new Font("Yu Gothic UI Semilight", Font.PLAIN, 15));
				lblNewLabel.setBounds(313, 246, 198, 21);
				contentPane.add(lblNewLabel);
				
				btnAgregar = new JButton("Agregar");
				btnAgregar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {

						Categoria obj=new Categoria();
						Conexion con=new Conexion();
						if(ComprobarCampos()) {
							obj=RecuperarDatos();
							String sentencia=String.format("Insert into categoria (nombrecategoria) "
									+ "values('%s')", obj.getNombreCategoria());
							int res=con.ejecutarSentenciaSql(sentencia);
							if (res==1) {
								limpiarCampos();
								resetBotones();
								mostrarDatos(titulos);
							
							}

							
							
						}
					}
				});
				btnAgregar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

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
				btnAgregar.setBounds(313, 468, 138, 62);
				contentPane.add(btnAgregar);
				
				btnEditar = new JButton("Editar");
				btnEditar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						Conexion con=new Conexion();
						Categoria obj= new Categoria();
						if(ComprobarCampos()) {
							obj=RecuperarDatos();
							String sentencia=String.format("Update categoria set nombrecategoria='%s' "
									+ "where idcategoria='%s'"
									, obj.getNombreCategoria(), tfseleccionado.getText());
							int res=con.ejecutarSentenciaSql(sentencia);
							if (res ==1) {
								limpiarCampos();
								resetBotones();
								mostrarDatos(titulos);
								JOptionPane.showMessageDialog(null,"Categoría Editada Exitosamente","Exito",JOptionPane.INFORMATION_MESSAGE);
							}
							
						}
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
				btnEditar.setBounds(473, 468, 138, 62);
				contentPane.add(btnEditar);
				
				btnEliminar = new JButton("Eliminar");
				btnEliminar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						Conexion con=new Conexion();
						int dialogButton = JOptionPane.YES_NO_OPTION;
						int dialogResult = JOptionPane.showConfirmDialog (null, "¿Seguro que desea Eliminar la categoría "+tfcategoria.getText()+"?","Advertencia",dialogButton,JOptionPane.WARNING_MESSAGE);
						if(!(tfseleccionado.getText().isEmpty())&& dialogResult == JOptionPane.YES_OPTION) {
							
							String sentencia=String.format("Delete from categoria where idcategoria='%s'"
									,tfseleccionado.getText());

							int res1=con.ejecutarSentenciaSql(sentencia);
							if(res1==1) {
								limpiarCampos();
								resetBotones();
								mostrarDatos(titulos);
								JOptionPane.showMessageDialog(null,"Categoría Borrada Exitosamente","Exito",JOptionPane.INFORMATION_MESSAGE);
							}

							
						}
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
				btnEliminar.setBounds(639, 468, 138, 62);
				contentPane.add(btnEliminar);
				
				JScrollPane scrollPane = new JScrollPane();
				scrollPane.setBounds(787, 182, 587, 522);
				contentPane.add(scrollPane);
				
				table = new JTable();
				table.setBackground(new Color(250, 240, 230));
				table.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 15));
				modelo= new DefaultTableModel(null, titulos);
				table.setModel(modelo);
			
					
				scrollPane.setViewportView(table);

				
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
				btnCancelar.setBounds(639, 350, 138, 62);
				contentPane.add(btnCancelar);
				
				JSeparator separator_4 = new JSeparator();
				separator_4.setBounds(313, 109, 893, 2);
				contentPane.add(separator_4);
				
				JLabel lblCodSeleccionado = new JLabel("COD Seleccionado");
				lblCodSeleccionado.setFont(new Font("Yu Gothic UI Semilight", Font.PLAIN, 15));
				lblCodSeleccionado.setBounds(787, 734, 151, 21);
				contentPane.add(lblCodSeleccionado);
				
				tfseleccionado = new JTextField();
				tfseleccionado.setFont(new Font("Yu Gothic UI Semilight", Font.PLAIN, 15));
				tfseleccionado.setEditable(false);
				tfseleccionado.setColumns(10);
				tfseleccionado.setBounds(931, 730, 255, 30);
				contentPane.add(tfseleccionado);
		

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
					tfcategoria.setText(receptor.getModel().getValueAt(receptor.getSelectedRow(), 1).toString());
					tfseleccionado.setText(receptor.getModel().getValueAt(receptor.getSelectedRow(), 0).toString());
					String cod=tfseleccionado.getText();
					Seleccion(cod);
	
					
				}
				btnAgregar.setEnabled(false);
				btnEditar.setEnabled(true);
				btnEliminar.setEnabled(true);


				
			}
		});
		mostrarDatos(titulos);
		fechaActual();
		resetBotones();
		setIconImage(getIconImage());

	}
	public Categoria RecuperarDatos() {
		Categoria obj=new Categoria();
		obj.setNombreCategoria(tfcategoria.getText());
		
		return obj;
		
		
	}
	public boolean ComprobarCampos() {
		if(tfcategoria.getText().isEmpty()) {

			
			JOptionPane.showMessageDialog(null,"Todos los Campos son obligatorios","Advertencia",JOptionPane.WARNING_MESSAGE);
			return false;
		}else {
			return true;
			
		}
		
	}
	public void limpiarCampos() {
		tfcategoria.setText("");
		tfseleccionado.setText("");
		
	}
	public void resetBotones() {
		btnAgregar.setEnabled(true);
		btnEditar.setEnabled(false);
		btnEliminar.setEnabled(false);

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
			ResultSet resultado=con.consultarSql("Select * from categoria");
			while(resultado.next()) {
				Object[] obj= {resultado.getInt("idcategoria"), resultado.getString("nombrecategoria")};
				
				modelo.addRow(obj);
			}
			table.getColumnModel().getColumn(0).setPreferredWidth(20);
			table.getColumnModel().getColumn(1).setPreferredWidth(400);

			table.setRowHeight(30);
			
		} catch (Exception e) {
			System.out.println("Error "+e);
		}
		
		
	}
	public void Seleccion(String cod) {
		Conexion con=new Conexion();
		try {
			ResultSet resultado=con.consultarSql("Select * from categoria where idcategoria="+"'"+cod+"'");
			if(resultado.next()) {
				tfcategoria.setText(resultado.getString("nombrecategoria"));
			}
			
		} catch (Exception e2) {
			JOptionPane.showMessageDialog(null,"Error en la conexion","Error",JOptionPane.ERROR_MESSAGE);
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
	public void callventas() {
		Ventas y=new Ventas();
		this.setVisible(false);
		y.setLocationRelativeTo(null);
		y.setVisible(true);
	}
	public void callempleados() {
		empleados y=new empleados();
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
