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
import java.awt.image.BufferedImage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;

import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.imageio.ImageIO;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
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


import Clases.Producto;
import Clases.Render;

import Conect.Conexion;
import javax.swing.JTextArea;

import javax.swing.JCheckBox;

public class menu extends JFrame {
	private FileInputStream fis;
	private int longitudBytes;

	private JPanel contentPane;
	private int xMouse, yMouse;
	private JLabel lblx, lblfoto, lbl_min;
	private JTextField tfproducto;
	private JTextField tfprecio;
	private JTable table;
	private JComboBox cboxCategoria, cboxCategoria2;
	private JTextArea taDescripcion;
	
	private String[] titulos= {"COD", "PRODUCTO", "PRECIO Bs.", "IMAGEN"};
	private JButton btnAgregar, btnEditar, btnEliminar, btnCancelar, btnImagen;
	DefaultTableModel modelo;
	private JTextField tfseleccionado;
	private JTextField tffecha;
	private JCheckBox cboxActivo;
	
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					menu frame = new menu();
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
	public menu() {
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
		lblNewLabel_1.setBounds(1220, 11, 158, 150);
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
		
		JLabel lblFecha = new JLabel("FECHA");
		lblFecha.setFont(new Font("Yu Gothic UI Semilight", Font.PLAIN, 15));
		lblFecha.setBounds(928, 16, 62, 21);
		superior_panel.add(lblFecha);
		
		tffecha = new JTextField();
		tffecha.setText("9-7-2022");
		tffecha.setFont(new Font("Yu Gothic UI Semilight", Font.PLAIN, 16));
		tffecha.setEditable(false);
		tffecha.setColumns(10);
		tffecha.setBounds(1000, 11, 155, 30);
		superior_panel.add(tffecha);
		
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
		btnEmpleado.setSelectedIcon(new ImageIcon(menu.class.getResource("/imgs/empleado.png")));
		btnEmpleado.setRolloverSelectedIcon(new ImageIcon(menu.class.getResource("/imgs/empleado.png")));
		btnEmpleado.setRolloverIcon(new ImageIcon(menu.class.getResource("/imgs/empleado.png")));
		btnEmpleado.setIcon(new ImageIcon(menu.class.getResource("/imgs/empleado2.png")));
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
		btnVentas.setRolloverIcon(new ImageIcon(menu.class.getResource("/imgs/compras.png")));
		btnVentas.setRolloverSelectedIcon(new ImageIcon(menu.class.getResource("/imgs/compras.png")));
		btnVentas.setIcon(new ImageIcon(menu.class.getResource("/imgs/compras2.png")));
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
				callcategoria();
			}
		});
		btnCategoria.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnCategoria.setRolloverSelectedIcon(new ImageIcon(menu.class.getResource("/imgs/categorias.png")));
		btnCategoria.setRolloverIcon(new ImageIcon(menu.class.getResource("/imgs/categorias.png")));
		btnCategoria.setIcon(new ImageIcon(menu.class.getResource("/imgs/categorias2.png")));
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
			}
		});
		btnMenu.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

		btnMenu.setRolloverSelectedIcon(new ImageIcon(menu.class.getResource("/imgs/piezas-de-cubiertos.png")));
		btnMenu.setRolloverIcon(new ImageIcon(menu.class.getResource("/imgs/piezas-de-cubiertos.png")));
		btnMenu.setIcon(new ImageIcon(menu.class.getResource("/imgs/piezas-de-cubiertos.png")));
		btnMenu.setForeground(Color.BLACK);
		btnMenu.setFont(new Font("Yu Gothic UI Semilight", Font.BOLD, 24));
		btnMenu.setFocusPainted(false);
		btnMenu.setBorder(null);
		btnMenu.setBackground(new Color(242,76,76));
		btnMenu.setBounds(0, 181, 287, 47);

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
				
				JLabel lblTitulo = new JLabel("MENU");
				lblTitulo.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 21));
				lblTitulo.setBounds(759, 73, 129, 38);
				contentPane.add(lblTitulo);
				
				tfproducto = new JTextField();
				tfproducto.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 15));
				tfproducto.setBounds(308, 174, 423, 30);
				contentPane.add(tfproducto);
				tfproducto.setColumns(10);
				
				JLabel lblNewLabel = new JLabel("Producto");
				lblNewLabel.setFont(new Font("Yu Gothic UI Semilight", Font.PLAIN, 15));
				lblNewLabel.setBounds(308, 140, 198, 21);
				contentPane.add(lblNewLabel);
				
				tfprecio = new JTextField();
				tfprecio.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 15));
				tfprecio.setColumns(10);
				tfprecio.setBounds(308, 361, 198, 30);
				contentPane.add(tfprecio);
				
				JLabel lblNombres = new JLabel("Precio Bs.");
				lblNombres.setFont(new Font("Yu Gothic UI Semilight", Font.PLAIN, 15));
				lblNombres.setBounds(308, 329, 198, 21);
				contentPane.add(lblNombres);
				
				JLabel lblApellidos = new JLabel("Categor\u00EDa:");
				lblApellidos.setFont(new Font("Yu Gothic UI Semilight", Font.PLAIN, 15));
				lblApellidos.setBounds(298, 412, 102, 21);
				contentPane.add(lblApellidos);
				
				JLabel lblBuscar = new JLabel("Buscar Producto Por Categor\u00EDa:");
				lblBuscar.setFont(new Font("Yu Gothic UI Semilight", Font.PLAIN, 15));
				lblBuscar.setBounds(759, 122, 255, 21);
				contentPane.add(lblBuscar);
				
				btnAgregar = new JButton("Agregar");
				btnAgregar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
				btnAgregar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if(ComprobarCampos()) {
							Conexion con=new Conexion();
							Producto obj=new Producto();
							
							try {
								obj=RecuperarDatos();
								String sql="INSERT INTO producto (nombreproducto, descripcion, precio, idcategoria, img, estado) VALUES (?,?,?,?,?,?)";
								PreparedStatement ps=con.ejecutarSentencia(sql);
								ps.setString(1, obj.getNombreProducto());
								ps.setString(2, obj.getDesc());
								ps.setDouble(3, obj.getPrecio());
								ps.setInt(4, obj.getIdCategoria());
								
								ps.setBinaryStream(5,obj.getImg(),obj.getLon());
								ps.setBoolean(6, obj.isEstado());
								ps.execute();
								ps.close();
								limpiarCampos();
								mostrarDatos(titulos);
								JOptionPane.showMessageDialog(null,"Producto Registrado Exitosamente","Exito",JOptionPane.INFORMATION_MESSAGE);
								
								
								
								
							} catch (Exception e2) {
								JOptionPane.showMessageDialog(null, e2);
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
				btnAgregar.setBounds(298, 735, 138, 62);
				contentPane.add(btnAgregar);
				
				btnEditar = new JButton("Editar");
				btnEditar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if(ComprobarCampos()) {
							Conexion con=new Conexion();
							Producto obj=new Producto();
							
							try {
								if(fis!=null && longitudBytes!=0) {
									obj=RecuperarDatos();
									String sql="update producto set nombreproducto= ?, descripcion=?, precio=?, idcategoria=?, img=?, estado=? "
											+ "where idproducto=?;";
									PreparedStatement ps=con.ejecutarSentencia(sql);
									ps.setString(1, obj.getNombreProducto());
									ps.setString(2, obj.getDesc());
									ps.setDouble(3, obj.getPrecio());
									ps.setInt(4, obj.getIdCategoria());
									 
									ps.setBinaryStream(5,obj.getImg(),obj.getLon());
									ps.setBoolean(6, obj.isEstado());
								
									ps.setInt(7, Integer.parseInt(tfseleccionado.getText()));
									ps.executeUpdate();
									ps.close();
									limpiarCampos();
									mostrarDatos(titulos);
									JOptionPane.showMessageDialog(null,"Producto Editado Exitosamente","Exito",JOptionPane.INFORMATION_MESSAGE);
								}else {
									obj=RecuperarDatos();
									String sql="update producto set nombreproducto=?, descripcion=?, precio=?, idcategoria=?, estado=? "
											+ "where idproducto=?;";
									PreparedStatement ps=con.ejecutarSentencia(sql);
									ps.setString(1, obj.getNombreProducto());
									ps.setString(2, obj.getDesc());
									ps.setDouble(3, obj.getPrecio());
									ps.setInt(4, obj.getIdCategoria());
									ps.setBoolean(5, obj.isEstado());
									 
									ps.setInt(6, Integer.parseInt(tfseleccionado.getText()));
									ps.executeUpdate();
									ps.close();
									limpiarCampos();
									mostrarDatos(titulos);
									JOptionPane.showMessageDialog(null,"Producto Editado Exitosamente","Exito",JOptionPane.INFORMATION_MESSAGE);
								}
								
								
								
							} catch (Exception e2) {
								JOptionPane.showMessageDialog(null,"Error en la Actualizacion "+e2,"Error",JOptionPane.INFORMATION_MESSAGE);
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
				btnEditar.setBounds(446, 735, 138, 62);
				contentPane.add(btnEditar);
				
				btnEliminar = new JButton("Eliminar");
				btnEliminar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						int dialogButton = JOptionPane.YES_NO_OPTION;
						int dialogResult = JOptionPane.showConfirmDialog (null, "¿Seguro que desea Eliminar el producto "+tfproducto.getText()+"?","Advertencia",dialogButton,JOptionPane.WARNING_MESSAGE);
						
						if(Integer.parseInt(tfseleccionado.getText())>0 && dialogResult == JOptionPane.YES_OPTION) {
							Conexion con=new Conexion();
							Producto obj=new Producto();
							
							try {
								obj=RecuperarDatos();
								String sql="Delete from producto where idproducto='"+Integer.parseInt(tfseleccionado.getText())+"'";
								PreparedStatement ps=con.ejecutarSentencia(sql);
	
								ps.execute();
								ps.close();
								limpiarCampos();
								mostrarDatos(titulos);
								JOptionPane.showMessageDialog(null,"Producto Eliminado Exitosamente","Exito",JOptionPane.INFORMATION_MESSAGE);
								
								
								
								
							} catch (Exception e2) {
								JOptionPane.showMessageDialog(null, e2);
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
				btnEliminar.setBounds(594, 735, 138, 62);
				contentPane.add(btnEliminar);
				
				JScrollPane scrollPane = new JScrollPane();
				scrollPane.setBounds(750, 242, 624, 555);
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
				btnCancelar.setBounds(593, 664, 138, 62);
				contentPane.add(btnCancelar);
				
				JLabel lblTipoDeUsuario = new JLabel("IMAGEN (Opcional)");
				lblTipoDeUsuario.setFont(new Font("Yu Gothic UI Semilight", Font.PLAIN, 15));
				lblTipoDeUsuario.setBounds(298, 453, 147, 21);
				contentPane.add(lblTipoDeUsuario);
				
				cboxCategoria = new JComboBox();
				cboxCategoria.setBackground(new Color(0, 191, 255));
				cboxCategoria.setFont(new Font("Yu Gothic UI Semilight", Font.PLAIN, 18));
				cboxCategoria.setBounds(410, 406, 271, 30);
				contentPane.add(cboxCategoria);
				
				JSeparator separator_4 = new JSeparator();
				separator_4.setBounds(313, 109, 897, 2);
				contentPane.add(separator_4);
				
				btnImagen = new JButton("Seleccionar");
				btnImagen.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
				btnImagen.setSelectedIcon(new ImageIcon(menu.class.getResource("/imgs/insimagen2.png")));
				btnImagen.setRolloverSelectedIcon(new ImageIcon(menu.class.getResource("/imgs/insimagen2.png")));
				btnImagen.setRolloverIcon(new ImageIcon(menu.class.getResource("/imgs/insimagen2.png")));
				btnImagen.setIcon(new ImageIcon(menu.class.getResource("/imgs/insimagen1.png")));
				btnImagen.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseEntered(MouseEvent e) {
						btnImagen.setBackground(new Color(242,76,76));
						btnImagen.setForeground(Color.black);
						
					}
					
					@Override
					public void mouseExited(MouseEvent e) {
						btnImagen.setBackground(new Color(41,52,98));
						btnImagen.setForeground(Color.white);
					}
				});
				btnImagen.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						lblfoto.setIcon(null);
						JFileChooser j = new JFileChooser();
						j.setFileSelectionMode(JFileChooser.FILES_ONLY);// solo archivosyno carpetas
						int estado=j.showOpenDialog(null);
						if(estado == JFileChooser.APPROVE_OPTION){
						   try{
						       fis=new FileInputStream(j.getSelectedFile());
						       // necesitamos saber la cantidad de bytes
						       longitudBytes=(int)j.getSelectedFile().length();

						       try{
						            
						            Image icono=ImageIO. read(j.getSelectedFile()).getScaledInstance(lblfoto.getWidth(),lblfoto.getHeight(),Image.SCALE_DEFAULT);
						            lblfoto.setIcon(new ImageIcon(icono));
						            lblfoto.updateUI();
						                              
						       }catch(IOException ex){
						            JOptionPane.showMessageDialog(rootPane,"imagen:"+ex);
						       }
						   }catch(FileNotFoundException ex){
						       ex.printStackTrace();
						        
					}
				}
				
					
					}
				});

				btnImagen.setForeground(Color.WHITE);
				btnImagen.setFont(new Font("Yu Gothic UI Semilight", Font.BOLD, 18));
				btnImagen.setFocusPainted(false);
				btnImagen.setBorder(null);
				btnImagen.setBackground(new Color(41, 52, 98));
				btnImagen.setBounds(298, 485, 138, 62);
				contentPane.add(btnImagen);
				
				lblfoto= new JLabel("");
				lblfoto.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
				lblfoto.setBackground(Color.LIGHT_GRAY);
				lblfoto.setBounds(446, 467, 198, 192);
				contentPane.add(lblfoto);
				
				taDescripcion = new JTextArea();
				taDescripcion.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 15));
				taDescripcion.setLineWrap(true);
				taDescripcion.setWrapStyleWord(true);
				taDescripcion.setBorder(new LineBorder(new Color(0, 0, 0)));
				taDescripcion.setBounds(308, 242, 423, 76);
				contentPane.add(taDescripcion);
				
				JLabel lblDescripcin = new JLabel("Descripci\u00F3n");
				lblDescripcin.setFont(new Font("Yu Gothic UI Semilight", Font.PLAIN, 15));
				lblDescripcin.setBounds(308, 215, 198, 21);
				contentPane.add(lblDescripcin);
				
				JLabel lblCodSeleccionado = new JLabel("COD Seleccionado");
				lblCodSeleccionado.setFont(new Font("Yu Gothic UI Semilight", Font.PLAIN, 15));
				lblCodSeleccionado.setBounds(839, 206, 151, 21);
				contentPane.add(lblCodSeleccionado);
				
				tfseleccionado = new JTextField();
				tfseleccionado.setFont(new Font("Yu Gothic UI Semilight", Font.PLAIN, 15));
				tfseleccionado.setEditable(false);
				tfseleccionado.setColumns(10);
				tfseleccionado.setBounds(987, 202, 255, 30);
				contentPane.add(tfseleccionado);
				
				cboxCategoria2 = new JComboBox();
				cboxCategoria2.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						mostrarDatos(titulos);
						
					}
				});
				cboxCategoria2.setFont(new Font("Yu Gothic UI Semilight", Font.PLAIN, 18));
				cboxCategoria2.setBackground(new Color(0, 191, 255));
				cboxCategoria2.setBounds(769, 154, 271, 30);
				contentPane.add(cboxCategoria2);
				
				JSeparator separator_5 = new JSeparator();
				separator_5.setOrientation(SwingConstants.VERTICAL);
				separator_5.setBounds(741, 115, 12, 715);
				contentPane.add(separator_5);
				
				cboxActivo = new JCheckBox("ACTIVO");
				cboxActivo.setBackground(Color.WHITE);
				cboxActivo.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 17));
				cboxActivo.setBounds(594, 139, 97, 23);
				contentPane.add(cboxActivo);
		

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
					tfseleccionado.setText(receptor.getModel().getValueAt(receptor.getSelectedRow(), 0).toString());
					String cod=tfseleccionado.getText();

					Seleccion(cod);

					
				}
				btnAgregar.setEnabled(false);
				btnEditar.setEnabled(true);
				btnEliminar.setEnabled(true);
				



				
			}
		});
		cargarcategorias();
		mostrarDatos(titulos);
		fechaActual();
		resetBotones();
		setIconImage(getIconImage());
			
	}	
					
	public Producto RecuperarDatos() {
		Producto obj=new Producto();
		obj.setNombreProducto(tfproducto.getText());
		obj.setDesc(taDescripcion.getText());
		obj.setPrecio(Double.parseDouble(tfprecio.getText()));
		obj.setIdCategoria(obteneridCategoria());
		obj.setImg(fis);
		obj.setLon(longitudBytes);
		obj.setEstado(cboxActivo.isSelected());
		
		return obj;
		
		
	}
	public int obteneridCategoria() {
		try {
			Conexion con=new Conexion();
			String cat=cboxCategoria.getSelectedItem().toString();
			ResultSet resultado=con.consultarSql("Select idcategoria from categoria where nombrecategoria ='"+cat+"'");
			
			if(resultado.next()) {
				return resultado.getInt("idcategoria");
				
				
				
			}else {
				return 0;
			}
			
			
		} catch (Exception e) {
			System.out.println("Error "+e);
			return 0;
		}
	}
	public int busquedaidCategoria() {
		try {
			Conexion con=new Conexion();
			String cat=cboxCategoria2.getSelectedItem().toString();
			ResultSet resultado=con.consultarSql("Select idcategoria from categoria where nombrecategoria ='"+cat+"'");
			
			if(resultado.next()) {
				return resultado.getInt("idcategoria");
				
				
				
			}else {
				return 0;
			}
			
			
		} catch (Exception e) {
			System.out.println("Error "+e);
			return 0;
		}
	}
	public void cargarcategorias() {
		try {
			Conexion con=new Conexion();
			ResultSet resultado=con.consultarSql("Select * from categoria");
			
			while(resultado.next()) {
				cboxCategoria.addItem(resultado.getString("nombrecategoria"));
				cboxCategoria2.addItem(resultado.getString("nombrecategoria"));
				
				
				
				
			}
			
		} catch (Exception e) {
			System.out.println("Error "+e);
		}
	}
	
	public boolean ComprobarCampos() {
		if(tfproducto.getText().isEmpty() || tfprecio.getText().isEmpty() ) {

			
			JOptionPane.showMessageDialog(null,"Todos los Campos son obligatorios","Advertencia",JOptionPane.WARNING_MESSAGE);
			return false;
		}else {
			try {
				Double.parseDouble(tfprecio.getText());
				return true;
				
			} catch (NumberFormatException e) {
				tfprecio.setText("");
				JOptionPane.showMessageDialog(null,"Ingrese un precio valido","Advertencia",JOptionPane.WARNING_MESSAGE);
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
        tffecha.setText(f);
	}
	public void mostrarDatos(String[] titulos) {

        table.setDefaultRenderer(Object.class, new Render());
        	modelo = new DefaultTableModel(null, titulos){
            @Override
            public boolean isCellEditable(int row, int column){
                return false;
            }
        };

		try {
			Conexion con=new Conexion();
			ResultSet resultado=con.consultarSql("Select * from producto where idcategoria='"+busquedaidCategoria()+"' order by idproducto");

			while(resultado.next()) {
				Object fila[] = new Object[4];
				fila[0]=resultado.getInt("idproducto");
				fila[1]=resultado.getString("nombreproducto");
				fila[2]=resultado.getDouble("precio");
			
				try {
					InputStream is=resultado.getBinaryStream("img");
					BufferedImage bi= ImageIO.read(is);
					ImageIcon foto=new ImageIcon(bi);
					Image img=foto.getImage();
					Image newimg=img.getScaledInstance(60, 60, 0);
					ImageIcon newicon=new ImageIcon(newimg);
					fila[3] = new JLabel(newicon);
					
				}catch(Exception ex) {
					fila[3] = new JLabel("Sin Imagen");
				}
				
				modelo.addRow(fila);
				
			}
			
			table.setModel(modelo);
			table.getColumnModel().getColumn(0).setPreferredWidth(1);
			table.getColumnModel().getColumn(1).setPreferredWidth(280);
			table.getColumnModel().getColumn(2).setPreferredWidth(10);
			table.getColumnModel().getColumn(3).setPreferredWidth(50);
			table.setRowHeight(60);


			
		} catch (Exception e) {
			System.out.println("Error "+e);
		}
		
	}
	public void limpiarCampos() {
		tfproducto.setText("");
		tfprecio.setText("");
		tfseleccionado.setText("");
		taDescripcion.setText("");
		lblfoto.setIcon(null);
		fis=null;
		longitudBytes=0;
		cboxActivo.setSelected(false);
		
		
	}
	public void Seleccion(String cod) {
			Conexion con=new Conexion();
			try {
				ResultSet resultado=con.consultarSql("Select * from producto where idproducto="+"'"+cod+"'");

				if(resultado.next()) {
					tfproducto.setText(resultado.getString("nombreproducto"));
					tfprecio.setText(resultado.getDouble("precio")+"");
					taDescripcion.setText(resultado.getString("descripcion"));
					boolean estado=resultado.getBoolean("estado");
					if(estado==true || estado==false) {
						cboxActivo.setSelected(resultado.getBoolean("estado"));
					}
					
					ResultSet cat=con.consultarSql("Select nombrecategoria from categoria where idcategoria="+"'"+resultado.getInt("idcategoria")+"'");
					if(cat.next()) {
						cboxCategoria.setSelectedItem(cat.getString(1));
					}
					try {
						InputStream is=resultado.getBinaryStream("img");
						BufferedImage bi= ImageIO.read(is);
						ImageIcon foto=new ImageIcon(bi);
						Image img=foto.getImage();
						Image newimg=img.getScaledInstance(198, 192, java.awt.Image.SCALE_SMOOTH);
						ImageIcon newicon=new ImageIcon(newimg);
						lblfoto.setIcon(newicon);
						lblfoto.setText("");
						

						
						
					}catch(Exception ex) {
						lblfoto.setIcon(null);
						lblfoto.setText("Sin imagen");;
					}
					
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
	public void callcategoria() {
		Categorias y=new Categorias();
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
