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
import java.io.InputStream;

import java.sql.ResultSet;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;


import javax.imageio.ImageIO;
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


import Clases.Render;
import Clases.carro;
import Conect.Conexion;
import javax.swing.JComboBox;
import javax.swing.JTextArea;

public class Ventas extends JFrame {

	private JPanel contentPane;
	private int xMouse, yMouse;
	private JLabel lblx, lbl_min;
	private JTable table;
	
	private String[] titulos= {"COD", "PRODUCTO", "PRECIO Bs.", "IMAGEN"};
	private String[] titulos_carro= {"COD", "PRODUCTO", "PRECIO Bs.", "CANTIDAD", "SUBTOTAL Bs.", "OPCION"};
	
	private JButton btnAgregar, btnVaciar, btnCalacular, btnCancelar;
	DefaultTableModel modelo, modelo2;
	private JTextField tfseleccionado;
	private JTable table_carro;
	private JTextField tfcantidad;
	private JTextField tftotal;
	private JTextField tfmonto;
	private JTextField tfcambio;
	private JTextField tfcliente;
	private JComboBox cboxCategoria;
	private JTextArea taDescripcion;
	
	public static int fila,columna;
	
	private JButton btndel=new JButton("ELIMINAR");
	
	public static ArrayList<carro>lista=new ArrayList<carro>();
	
	private int idproducto_seleccionado;
	private double total=0;
	private JTextField tffecha;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Ventas frame = new Ventas();
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
	public Ventas() {
		setLocationByPlatform(true);
		setUndecorated(true);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1650, 830);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon(empleados.class.getResource("/imgs/logo_pq.jpg")));
		lblNewLabel_1.setBounds(1478, 11, 158, 150);
		contentPane.add(lblNewLabel_1);
		
		JPanel superior_panel = new JPanel();
		superior_panel.setBorder(new MatteBorder(1, 1, 0, 1, (Color) new Color(0, 0, 0)));

		superior_panel.setBackground(Color.WHITE);
		superior_panel.setBounds(0, 0, 1650, 49);
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
		lblFecha.setBounds(1245, 28, 62, 21);
		superior_panel.add(lblFecha);
		
		tffecha = new JTextField();
		tffecha.setText("9-7-2022");
		tffecha.setFont(new Font("Yu Gothic UI Semilight", Font.PLAIN, 16));
		tffecha.setEditable(false);
		tffecha.setColumns(10);
		tffecha.setBounds(1305, 19, 97, 30);
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
		btnEmpleado.setRolloverSelectedIcon(new ImageIcon(Ventas.class.getResource("/imgs/empleado.png")));
		btnEmpleado.setRolloverIcon(new ImageIcon(Ventas.class.getResource("/imgs/empleado.png")));
		btnEmpleado.setIcon(new ImageIcon(Ventas.class.getResource("/imgs/empleado2.png")));
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
		
		JSeparator sep = new JSeparator();
		sep.setBounds(0, 76, 287, 2);
		panel_menu.add(sep);
		
		JButton btnVentas = new JButton("VENTAS");
		btnVentas.setRolloverIcon(new ImageIcon(Ventas.class.getResource("/imgs/compras.png")));
		btnVentas.setRolloverSelectedIcon(new ImageIcon(Ventas.class.getResource("/imgs/compras.png")));
		btnVentas.setIcon(new ImageIcon(Ventas.class.getResource("/imgs/compras.png")));
		btnVentas.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnVentas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});

		btnVentas.setForeground(Color.BLACK);
		btnVentas.setFont(new Font("Yu Gothic UI Semilight", Font.BOLD, 24));
		btnVentas.setFocusPainted(false);
		btnVentas.setBorder(null);
		btnVentas.setBackground(new Color(242,76,76));
		btnVentas.setBounds(0, 29, 287, 47);
		panel_menu.add(btnVentas);
		
		JSeparator sep1 = new JSeparator();
		sep1.setBounds(0, 129, 287, 2);
		panel_menu.add(sep1);
		
		JButton btnCategoria = new JButton("CATEGORIAS");
		btnCategoria.setSelectedIcon(new ImageIcon(Ventas.class.getResource("/imgs/categorias.png")));
		btnCategoria.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				callcategorias();
			}
		});
		btnCategoria.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnCategoria.setRolloverSelectedIcon(new ImageIcon(Ventas.class.getResource("/imgs/categorias.png")));
		btnCategoria.setRolloverIcon(new ImageIcon(Ventas.class.getResource("/imgs/categorias.png")));
		btnCategoria.setIcon(new ImageIcon(Ventas.class.getResource("/imgs/categorias2.png")));

		btnCategoria.setForeground(Color.WHITE);
		btnCategoria.setFont(new Font("Yu Gothic UI Semilight", Font.BOLD, 24));
		btnCategoria.setFocusPainted(false);
		btnCategoria.setBorder(null);
		btnCategoria.setBackground(new Color(41, 52, 98));
		btnCategoria.setBounds(0, 131, 287, 47);
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
		panel_menu.add(btnCategoria);
		
		JSeparator sep2 = new JSeparator();
		sep2.setBounds(0, 177, 287, 2);
		panel_menu.add(sep2);
		
		JButton btnMenu = new JButton("MENU");
		btnMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				callmenu();
			}
		});
		btnMenu.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

		btnMenu.setRolloverSelectedIcon(new ImageIcon(Ventas.class.getResource("/imgs/piezas-de-cubiertos.png")));
		btnMenu.setRolloverIcon(new ImageIcon(Ventas.class.getResource("/imgs/piezas-de-cubiertos.png")));
		btnMenu.setIcon(new ImageIcon(Ventas.class.getResource("/imgs/piezas-de-cubiertos2.png")));
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
		
		JSeparator sep3 = new JSeparator();
		sep3.setBounds(0, 227, 287, 2);
		panel_menu.add(sep3);
		
		JPanel btnCerrarSesion = new JPanel();
		btnCerrarSesion.setBounds(0, 716, 287, 56);
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
				
				JLabel lblTitulo = new JLabel("VENTAS");
				lblTitulo.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 21));
				lblTitulo.setBounds(879, 65, 91, 38);
				contentPane.add(lblTitulo);
				
				JLabel lblNewLabel = new JLabel("CATEGORIA:");
				lblNewLabel.setFont(new Font("Yu Gothic UI Semilight", Font.PLAIN, 15));
				lblNewLabel.setBounds(298, 122, 198, 21);
				contentPane.add(lblNewLabel);
				
				btnAgregar = new JButton("A\u00F1adir");
				btnAgregar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {

						if(ComprobarCampos()) {
							try {
								
								Conexion con=new Conexion();
								ResultSet resultado=con.consultarSql("Select * from producto where idproducto ='"+idproducto_seleccionado+ "'");
								
								if(resultado.next()) {
									carro obj=new carro();

									obj.setId(idproducto_seleccionado);
									obj.setProducto(resultado.getString("nombreproducto"));
									obj.setCantidad(Integer.parseInt(tfcantidad.getText()));
									obj.setPrecio(resultado.getDouble("precio"));
		
									
									Object fila[] = new Object[6];
									fila[0]=obj.getId();
									fila[1]=obj.getProducto();
									fila[2]=obj.getPrecio();
									fila[3]=obj.getCantidad();
									fila[4]=obj.calcularSubtotal(obj.getCantidad(), obj.getPrecio());
									fila[5]=btndel;
									modelo2.addRow(fila);
									
									total=total+obj.calcularSubtotal(obj.getCantidad(), obj.getPrecio());
									tftotal.setText(total+"");
									cancelar();
									
									
								}

								
							} catch (Exception e2) {
								System.out.println("Error "+e2);
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
				btnAgregar.setBounds(591, 651, 138, 62);
				contentPane.add(btnAgregar);
				
				btnVaciar = new JButton("Vaciar Todo");
				btnVaciar.setFocusPainted(false);
				btnVaciar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						mostrarcarro();
						total=0;
						tftotal.setText("");
						tfcambio.setText("");
						tfmonto.setText("");
					}
				});
				btnVaciar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
				btnVaciar.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseEntered(MouseEvent e) {
						btnVaciar.setBackground(new Color(242,76,76));
						btnVaciar.setForeground(Color.black);
						
					}
					
					@Override
					public void mouseExited(MouseEvent e) {
						btnVaciar.setBackground(new Color(41,52,98));
						btnVaciar.setForeground(Color.white);
					}
				});
				btnVaciar.setSelectedIcon(new ImageIcon(Ventas.class.getResource("/imgs/eliminar1.png")));
				btnVaciar.setRolloverSelectedIcon(new ImageIcon(Ventas.class.getResource("/imgs/eliminar1.png")));
				btnVaciar.setRolloverIcon(new ImageIcon(Ventas.class.getResource("/imgs/eliminar1.png")));
				btnVaciar.setIcon(new ImageIcon(Ventas.class.getResource("/imgs/eliminar2.png")));
				btnVaciar.setForeground(Color.WHITE);
				btnVaciar.setFont(new Font("Yu Gothic UI Semilight", Font.BOLD, 18));
				btnVaciar.setBorder(null);
				btnVaciar.setBackground(new Color(41, 52, 98));
				btnVaciar.setBounds(1330, 111, 138, 62);
				contentPane.add(btnVaciar);
				
				btnCalacular = new JButton("Calcular Cambio");
				btnCalacular.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if(!tfmonto.getText().isEmpty()) {
							try {
								double tot=Double.parseDouble(tftotal.getText());
								double monto=Double.parseDouble(tfmonto.getText());
								if(monto>=tot) {
									double cambio=monto-tot;
									tfcambio.setText(cambio+"");
								}else {
									tfmonto.setText("");
									tfcambio.setText("");
									JOptionPane.showMessageDialog(null,"Debe ingresar un monto Valido","Advertencia",JOptionPane.WARNING_MESSAGE);
								}
							} catch (Exception e2) {
								tfmonto.setText("");
								JOptionPane.showMessageDialog(null,"Debe ingresar un monto Valido","Advertencia",JOptionPane.WARNING_MESSAGE);
							}

							
							
						}else {
							
							JOptionPane.showMessageDialog(null,"Debe ingresar un monto","Advertencia",JOptionPane.WARNING_MESSAGE);
						}

					}
				});
				btnCalacular.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
				btnCalacular.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseEntered(MouseEvent e) {
						btnCalacular.setBackground(new Color(242,76,76));
						btnCalacular.setForeground(Color.black);
						
					}
					
					@Override
					public void mouseExited(MouseEvent e) {
						btnCalacular.setBackground(new Color(41,52,98));
						btnCalacular.setForeground(Color.white);
					}
				});
				btnCalacular.setSelectedIcon(new ImageIcon(Ventas.class.getResource("/imgs/calculadora2.png")));
				btnCalacular.setRolloverSelectedIcon(new ImageIcon(Ventas.class.getResource("/imgs/calculadora2.png")));
				btnCalacular.setRolloverIcon(new ImageIcon(Ventas.class.getResource("/imgs/calculadora2.png")));
				btnCalacular.setIcon(new ImageIcon(Ventas.class.getResource("/imgs/calculadora1.png")));
				btnCalacular.setForeground(Color.WHITE);
				btnCalacular.setFont(new Font("Yu Gothic UI Semilight", Font.BOLD, 18));
				btnCalacular.setFocusPainted(false);
				btnCalacular.setBorder(null);
				btnCalacular.setBackground(new Color(41, 52, 98));
				btnCalacular.setBounds(1124, 682, 182, 62);
				contentPane.add(btnCalacular);
				
				JScrollPane scrollPane = new JScrollPane();
				scrollPane.setBounds(298, 226, 605, 373);
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
						cancelar();
						

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
				btnCancelar.setBounds(765, 651, 138, 62);
				contentPane.add(btnCancelar);
				
				JSeparator separator_4 = new JSeparator();
				separator_4.setBounds(325, 111, 995, 12);
				contentPane.add(separator_4);
				
				JLabel lblCodSeleccionado = new JLabel("Descripcion :");
				lblCodSeleccionado.setFont(new Font("Yu Gothic UI Semilight", Font.PLAIN, 15));
				lblCodSeleccionado.setBounds(298, 702, 151, 21);
				contentPane.add(lblCodSeleccionado);
				
				tfseleccionado = new JTextField();
				tfseleccionado.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 15));
				tfseleccionado.setEditable(false);
				tfseleccionado.setColumns(10);
				tfseleccionado.setBounds(471, 610, 339, 30);
				contentPane.add(tfseleccionado);
				
				cboxCategoria = new JComboBox();
				cboxCategoria.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						mostrarDatos(titulos);
					}
				});
				cboxCategoria.setFont(new Font("Yu Gothic UI Semilight", Font.PLAIN, 18));
				cboxCategoria.setBackground(new Color(255, 160, 122));
				cboxCategoria.setBounds(298, 154, 303, 30);
				contentPane.add(cboxCategoria);
				
				taDescripcion = new JTextArea();
				taDescripcion.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 15));
				taDescripcion.setWrapStyleWord(true);
				taDescripcion.setLineWrap(true);
				taDescripcion.setEditable(false);
				taDescripcion.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
				taDescripcion.setBounds(298, 724, 504, 95);
				contentPane.add(taDescripcion);
				
				JLabel lblProductoSeleccionado = new JLabel("Producto seleccionado: ");
				lblProductoSeleccionado.setFont(new Font("Yu Gothic UI Semilight", Font.PLAIN, 15));
				lblProductoSeleccionado.setBounds(298, 614, 167, 21);
				contentPane.add(lblProductoSeleccionado);
				
				JScrollPane scrollPane_1 = new JScrollPane();
				scrollPane_1.setBounds(917, 174, 724, 427);
				contentPane.add(scrollPane_1);
				
				table_carro = new JTable();
				table_carro.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						columna=table_carro.getColumnModel().getColumnIndexAtX(e.getX());
						fila=e.getY()/table_carro.getRowHeight();
						if(columna<=table_carro.getColumnCount() && columna>=0 && fila<=table_carro.getRowCount() && fila>=0) {
							Object objeto=table_carro.getValueAt(fila, columna);
							if(objeto instanceof JButton) {
								JTable receptor=(JTable) e.getSource();
								
								double sub=(double) receptor.getModel().getValueAt(receptor.getSelectedRow(), 4);
								total=total-sub;
								tftotal.setText(total+"");
								tfcambio.setText("");
								tfmonto.setText("");
								modelo2.removeRow(table_carro.getSelectedRow());
								table_carro.setModel(modelo2);
								
								
							}
						}
				
					}
				});
				table_carro.setBackground(new Color(250, 240, 230));
				table_carro.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 15));
				modelo2= new DefaultTableModel(null, titulos_carro);
				table_carro.setModel(modelo2);
				scrollPane_1.setViewportView(table_carro);
				
				JLabel lblCantidad = new JLabel("Cantidad:");
				lblCantidad.setFont(new Font("Yu Gothic UI Semilight", Font.PLAIN, 15));
				lblCantidad.setBounds(369, 658, 80, 21);
				contentPane.add(lblCantidad);
				
				tfcantidad = new JTextField();
				tfcantidad.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 15));
				tfcantidad.setColumns(10);
				tfcantidad.setBounds(471, 654, 100, 30);
				contentPane.add(tfcantidad);
				
				tftotal = new JTextField();
				tftotal.setEditable(false);
				tftotal.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 15));
				tftotal.setColumns(10);
				tftotal.setBounds(1422, 612, 214, 30);
				contentPane.add(tftotal);
				
				JLabel lblTotal = new JLabel("Total Bs:");
				lblTotal.setFont(new Font("Yu Gothic UI Semilight", Font.PLAIN, 15));
				lblTotal.setBounds(1322, 616, 80, 21);
				contentPane.add(lblTotal);
				
				tfmonto = new JTextField();
				tfmonto.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 15));
				tfmonto.setColumns(10);
				tfmonto.setBounds(1422, 656, 214, 30);
				contentPane.add(tfmonto);
				
				JLabel lblMonto = new JLabel("Monto Bs:");
				lblMonto.setFont(new Font("Yu Gothic UI Semilight", Font.PLAIN, 15));
				lblMonto.setBounds(1322, 660, 80, 21);
				contentPane.add(lblMonto);
				
				tfcambio = new JTextField();
				tfcambio.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 15));
				tfcambio.setEditable(false);
				tfcambio.setColumns(10);
				tfcambio.setBounds(1422, 711, 214, 30);
				contentPane.add(tfcambio);
				
				JLabel lblCambio = new JLabel("Cambio Bs:");
				lblCambio.setFont(new Font("Yu Gothic UI Semilight", Font.PLAIN, 15));
				lblCambio.setBounds(1322, 711, 80, 21);
				contentPane.add(lblCambio);
				
				tfcliente = new JTextField();
				tfcliente.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 15));
				tfcliente.setColumns(10);
				tfcliente.setBounds(926, 641, 366, 30);
				contentPane.add(tfcliente);
				
				JLabel lblCliente = new JLabel("Cliente:");
				lblCliente.setFont(new Font("Yu Gothic UI Semilight", Font.PLAIN, 15));
				lblCliente.setBounds(927, 612, 80, 21);
				contentPane.add(lblCliente);
				
				JLabel lblTodosLosProductos = new JLabel("TODOS LOS PRODUCTOS");
				lblTodosLosProductos.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 21));
				lblTodosLosProductos.setBounds(471, 188, 258, 38);
				contentPane.add(lblTodosLosProductos);
				
				JLabel lblListaDePedido = new JLabel("CONSUMO");
				lblListaDePedido.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 21));
				lblListaDePedido.setBounds(1197, 123, 123, 38);
				contentPane.add(lblListaDePedido);
				
				JButton btnGuardar = new JButton("Guardar");
				btnGuardar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						guardarVenta();
						
					}
				});
				btnGuardar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
				btnGuardar.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseEntered(MouseEvent e) {
						btnGuardar.setBackground(new Color(242,76,76));
						btnGuardar.setForeground(Color.black);
						
					}
					
					@Override
					public void mouseExited(MouseEvent e) {
						btnGuardar.setBackground(new Color(41,52,98));
						btnGuardar.setForeground(Color.white);
					}
				});
				btnGuardar.setSelectedIcon(new ImageIcon(Ventas.class.getResource("/imgs/guardar-el-archivo2.png")));
				btnGuardar.setRolloverSelectedIcon(new ImageIcon(Ventas.class.getResource("/imgs/guardar-el-archivo2.png")));
				btnGuardar.setRolloverIcon(new ImageIcon(Ventas.class.getResource("/imgs/guardar-el-archivo2.png")));
				btnGuardar.setIcon(new ImageIcon(Ventas.class.getResource("/imgs/guardar-el-archivo1.png")));
				btnGuardar.setForeground(Color.WHITE);
				btnGuardar.setFont(new Font("Yu Gothic UI Semilight", Font.BOLD, 18));
				btnGuardar.setFocusPainted(false);
				btnGuardar.setBorder(null);
				btnGuardar.setBackground(new Color(41, 52, 98));
				btnGuardar.setBounds(936, 757, 138, 62);
				contentPane.add(btnGuardar);
				
				JSeparator separator_5 = new JSeparator();
				separator_5.setOrientation(SwingConstants.VERTICAL);
				separator_5.setBounds(913, 114, 12, 716);
				contentPane.add(separator_5);
				
				JButton btnpedidos = new JButton("VER PEDIDOS");
				btnpedidos.setBounds(728, 122, 175, 62);
				contentPane.add(btnpedidos);
				btnpedidos.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						callpedidos();
					}
				});
				btnpedidos.setSelectedIcon(new ImageIcon(Ventas.class.getResource("/imgs/lista1.png")));
				btnpedidos.setRolloverSelectedIcon(new ImageIcon(Ventas.class.getResource("/imgs/lista1.png")));
				btnpedidos.setRolloverIcon(new ImageIcon(Ventas.class.getResource("/imgs/lista1.png")));
				btnpedidos.setIcon(new ImageIcon(Ventas.class.getResource("/imgs/lista2.png")));
				btnpedidos.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
				btnpedidos.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseEntered(MouseEvent e) {
						btnpedidos.setBackground(new Color(242,76,76));
						btnpedidos.setForeground(Color.black);
						
					}
					
					@Override
					public void mouseExited(MouseEvent e) {
						btnpedidos.setBackground(new Color(41,52,98));
						btnpedidos.setForeground(Color.white);
					}
				});
				btnpedidos.setForeground(Color.WHITE);
				btnpedidos.setFont(new Font("Yu Gothic UI Semilight", Font.BOLD, 18));
				btnpedidos.setFocusPainted(false);
				btnpedidos.setBorder(null);
				btnpedidos.setBackground(new Color(41, 52, 98));
		

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
					
					idproducto_seleccionado=(int) receptor.getModel().getValueAt(receptor.getSelectedRow(), 0);
					tfseleccionado.setText(receptor.getModel().getValueAt(receptor.getSelectedRow(), 1).toString());
					seleccion();
					
	
					
				}



				
			}
		});
		setIconImage(getIconImage());
		fechaActual();
		cargarCategoria();
		mostrarDatos(titulos);
		mostrarcarro();
		
		if(login.tipouser.equals("Empleado")) {
			btnCategoria.setVisible(false);
			btnEmpleado.setVisible(false);
			btnMenu.setVisible(false);
			sep.setVisible(false);
			sep1.setVisible(false);
			sep2.setVisible(false);
			sep3.setVisible(false);
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
	
	public void mostrarcarro() {
        table_carro.setDefaultRenderer(Object.class, new Render());
    	modelo2 = new DefaultTableModel(null, titulos_carro){
        @Override
        public boolean isCellEditable(int row, int column){
            return false;
        }
    	};
    	
    	table_carro.setModel(modelo2);
    	table_carro.getColumnModel().getColumn(0).setPreferredWidth(1);
    	table_carro.getColumnModel().getColumn(1).setPreferredWidth(200);
    	table_carro.getColumnModel().getColumn(2).setPreferredWidth(15);
    	table_carro.getColumnModel().getColumn(3).setPreferredWidth(5);
    	table_carro.getColumnModel().getColumn(4).setPreferredWidth(15);
    	table_carro.getColumnModel().getColumn(5).setPreferredWidth(30);
    	table_carro.setRowHeight(40);
	}
	
	
	public void cargarCategoria() {
		try {
			Conexion con=new Conexion();
			ResultSet resultado=con.consultarSql("Select * from categoria");
			
			while(resultado.next()) {
				cboxCategoria.addItem(resultado.getString("nombrecategoria"));
								
				
			}
			
		} catch (Exception e) {
			System.out.println("Error "+e);
		}
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
	public void mostrarDatos(String[] titulos) {

        table.setDefaultRenderer(Object.class, new Render());
        	modelo = new DefaultTableModel(null, titulos){
            @Override
            public boolean isCellEditable(int row, int column){
                return false;
            }
        };

		try {
			int cat=obteneridCategoria();
			Conexion con=new Conexion();
			ResultSet resultado=con.consultarSql("Select * from producto where idcategoria ='"+cat+ "' and estado='true' order by idproducto");

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
			table.setRowHeight(60);
			table.setModel(modelo);
			table.getColumnModel().getColumn(0).setPreferredWidth(1);
			table.getColumnModel().getColumn(1).setPreferredWidth(280);
			table.getColumnModel().getColumn(2).setPreferredWidth(10);
			table.getColumnModel().getColumn(3).setPreferredWidth(30);
			
		} catch (Exception e) {
			System.out.println("Error "+e);
		}
		
	}
	public void seleccion() {
		try {
			Conexion con=new Conexion();
			ResultSet resultado=con.consultarSql("Select descripcion from producto where idproducto ='"+idproducto_seleccionado+"'");
			if(resultado.next()) {
				taDescripcion.setText(resultado.getString(1));
			}
		} catch (Exception e) {
			System.out.println("Error: "+e);
		}

	}
	public void cancelar() {
		idproducto_seleccionado=0;
		tfseleccionado.setText("");
		
		taDescripcion.setText("");
		tfcantidad.setText("");
	}
	
	public boolean ComprobarCampos() {

		if(tfcantidad.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null,"Debe ingresar una cantidad Valida","Advertencia",JOptionPane.WARNING_MESSAGE);
			return false;
		}else {
			try {
				Integer.parseInt(tfcantidad.getText());
				if(Integer.parseInt(tfcantidad.getText())>0) {
					return true;
				}else {
					JOptionPane.showMessageDialog(null,"Debe ingresar una cantidad Valida","Advertencia",JOptionPane.WARNING_MESSAGE);
					return false;
				}
				
			} catch (NumberFormatException e) {
				JOptionPane.showMessageDialog(null,"Debe ingresar una cantidad Valida","Advertencia",JOptionPane.WARNING_MESSAGE);
				return false;
			}
		}
	}
	public String obtenerhora() {
		LocalDateTime locaDate = LocalDateTime.now();
		int hours  = locaDate.getHour();
		int minutes = locaDate.getMinute();
		int seconds = locaDate.getSecond();
		String actual=hours+":"+minutes+":"+seconds;
		return actual;
	}
	public void guardarVenta() {
		if(!tftotal.getText().isEmpty() && Double.parseDouble(tftotal.getText())>0 && !tfcliente.getText().isEmpty()) {
			Conexion con=new Conexion();
			try {
				String horaactual=obtenerhora();
				String sentencia=String.format("Insert into venta (fecha, hora, usuario_ci, cliente, total, delivery, entregado) "
						+ "values('%s', '%s', '%s', '%s', '%s', '%s', '%s')", tffecha.getText(), horaactual, login.cilogeado, tfcliente.getText(), tftotal.getText(), false, false);
				int res=con.ejecutarSentenciaSql(sentencia);
				if(res==1) {
					ResultSet resultado=con.consultarSql("Select idventa from venta where fecha='"+tffecha.getText()+"' and hora='"+horaactual+"'");
					if(resultado.next()) {
						int idventa=resultado.getInt("idventa");
						guardarDetalle(idventa);
						
					}
					
					callventas();
					JOptionPane.showMessageDialog(null,"Venta Exitosa","Exito",JOptionPane.INFORMATION_MESSAGE);
					
				}


			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, e);
			}
		}else {
			JOptionPane.showMessageDialog(null,"Debe Ingresar un cliente y agregar minimo un producto","Advertencia",JOptionPane.WARNING_MESSAGE);
		}
		
	}
	public void guardarDetalle(int idventa) {
		for (int i = 0; i < table_carro.getRowCount(); i++) {
			int cod = 0;
			int cant=0;
			Conexion con=new Conexion();
			cod=(int) table_carro.getValueAt(i, 0);
			cant=(int) table_carro.getValueAt(i, 3);

			
			String sentencia=String.format("Insert into detalle (idproducto, idventa, cantidad) "
					+ "values('%s', '%s', '%s')", cod, idventa, cant);
			con.ejecutarSentenciaSql(sentencia);

			
		}
	}
	public void callempleados() {
		empleados y=new empleados();
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
	public void callpedidos() {
		pedidos y=new pedidos();
		this.setVisible(false);
		y.setLocationRelativeTo(null);
		y.setVisible(true);
	}
	public void callventas() {
		Ventas y=new Ventas();
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
