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


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
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


import Clases.Render;
import Clases.carro;
import Conect.Conexion;
import com.toedter.calendar.JDateChooser;

public class pedidos extends JFrame {

	private JPanel contentPane;
	private int xMouse, yMouse;
	private JLabel lblx, lbl_min;
	private JTable table_consumo;
	private JTextField tfFecha;
	
	private String[] titulos= {"COD", "FECHA", "HORA", "CLIENTE", "TOTAL Bs.", "DELIVERY", "ENTREGADO"};
	private String[] titulos_carro= {"COD", "PRODUCTO", "CANTIDAD", "PRECIO Bs."};
	

	DefaultTableModel modelo, modelo2;
	private JTable table_detalle;
	private JTextField tfcliente;
	
	public static int fila,columna;
	
	private JButton btndel=new JButton("ELIMINAR");
	
	public static ArrayList<carro>lista=new ArrayList<carro>();
	
	private int idventa;
	private double total=0;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					pedidos frame = new pedidos();
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
	public pedidos() {
		setLocationByPlatform(true);
		setUndecorated(true);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1650, 860);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon(empleados.class.getResource("/imgs/logo_pq.jpg")));
		lblNewLabel_1.setBounds(1482, 10, 158, 150);
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
		panel_menu.setBounds(0, 49, 288, 811);
		contentPane.add(panel_menu);
		panel_menu.setLayout(null);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(0, 76, 287, 2);
		panel_menu.add(separator);
		
		JButton btnVentas = new JButton("LISTA PEDIDOS");
		btnVentas.setIcon(new ImageIcon(pedidos.class.getResource("/imgs/lista1.png")));
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
		btnVentas.setBounds(0, 30, 287, 47);
		panel_menu.add(btnVentas);
		
		JPanel btnCerrarSesion = new JPanel();
		btnCerrarSesion.setBounds(0, 739, 287, 56);
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
				
				JButton btnVentas_1 = new JButton("NUEVA VENTA");
				btnVentas_1.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						guardarDatos();
						callventas();
					}
				});
				btnVentas_1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
				btnVentas_1.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseEntered(MouseEvent e) {
						btnVentas_1.setBackground(new Color(242,76,76));
						btnVentas_1.setForeground(Color.black);
						
					}
					
					@Override
					public void mouseExited(MouseEvent e) {
						btnVentas_1.setBackground(new Color(41,52,98));
						btnVentas_1.setForeground(Color.white);
					}
				});
				btnVentas_1.setRolloverSelectedIcon(new ImageIcon(pedidos.class.getResource("/imgs/agregar.png")));
				btnVentas_1.setRolloverIcon(new ImageIcon(pedidos.class.getResource("/imgs/agregar.png")));
				btnVentas_1.setSelectedIcon(new ImageIcon(pedidos.class.getResource("/imgs/agregar.png")));
				btnVentas_1.setIcon(new ImageIcon(pedidos.class.getResource("/imgs/agregar2.png")));
				btnVentas_1.setForeground(Color.WHITE);
				btnVentas_1.setFont(new Font("Yu Gothic UI Semilight", Font.BOLD, 24));
				btnVentas_1.setFocusPainted(false);
				btnVentas_1.setBorder(null);
				btnVentas_1.setBackground(new Color(41,52,98));
				btnVentas_1.setBounds(0, 84, 287, 47);
				panel_menu.add(btnVentas_1);
				
				JSeparator separator_1 = new JSeparator();
				separator_1.setBounds(0, 131, 287, 2);
				panel_menu.add(separator_1);
				
				JButton btnEstadisticas = new JButton("ESTADISTICAS");
				btnEstadisticas.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						callestadisticas();
					}
				});
				btnEstadisticas.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseEntered(MouseEvent e) {
						btnEstadisticas.setBackground(new Color(242,76,76));
						btnEstadisticas.setForeground(Color.black);
						
					}
					
					@Override
					public void mouseExited(MouseEvent e) {
						btnEstadisticas.setBackground(new Color(41,52,98));
						btnEstadisticas.setForeground(Color.white);
					}
				});
				btnEstadisticas.setSelectedIcon(new ImageIcon(pedidos.class.getResource("/imgs/estadistica2.png")));
				btnEstadisticas.setRolloverSelectedIcon(new ImageIcon(pedidos.class.getResource("/imgs/estadistica2.png")));
				btnEstadisticas.setRolloverIcon(new ImageIcon(pedidos.class.getResource("/imgs/estadistica2.png")));
				btnEstadisticas.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
				btnEstadisticas.setIcon(new ImageIcon(pedidos.class.getResource("/imgs/estadistica1.png")));
				btnEstadisticas.setForeground(Color.WHITE);
				btnEstadisticas.setFont(new Font("Yu Gothic UI Semilight", Font.BOLD, 24));
				btnEstadisticas.setFocusPainted(false);
				btnEstadisticas.setBorder(null);
				btnEstadisticas.setBackground(new Color(41, 52, 98));
				btnEstadisticas.setBounds(0, 136, 287, 47);
				panel_menu.add(btnEstadisticas);
				
				JSeparator separator_2 = new JSeparator();
				separator_2.setBounds(0, 183, 287, 2);
				panel_menu.add(separator_2);
				
				JLabel lblTitulo = new JLabel("LISTA DE PEDIDOS");
				lblTitulo.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 21));
				lblTitulo.setBounds(822, 67, 240, 38);
				contentPane.add(lblTitulo);
				
				
				JScrollPane scrollPane = new JScrollPane();
				scrollPane.setBounds(290, 244, 787, 593);
				contentPane.add(scrollPane);
				
				table_consumo = new JTable();
				table_consumo.setBackground(new Color(250, 240, 230));
				table_consumo.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 15));
				modelo= new DefaultTableModel(null, titulos);
				table_consumo.setModel(modelo);
			
					
				scrollPane.setViewportView(table_consumo);
				
				JSeparator separator_4 = new JSeparator();
				separator_4.setBounds(314, 116, 1158, 13);
				contentPane.add(separator_4);
				
				JScrollPane scrollPane_1 = new JScrollPane();
				scrollPane_1.setBounds(1082, 244, 558, 593);
				contentPane.add(scrollPane_1);
				
				table_detalle = new JTable();

				table_detalle.setBackground(new Color(250, 240, 230));
				table_detalle.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 15));
				modelo2= new DefaultTableModel(null, titulos_carro);
				table_detalle.setModel(modelo2);
				scrollPane_1.setViewportView(table_detalle);
				
				JLabel lblTodosLosProductos = new JLabel("TODOS LOS PEDIDOS");
				lblTodosLosProductos.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 21));
				lblTodosLosProductos.setBounds(562, 182, 224, 38);
				contentPane.add(lblTodosLosProductos);
				
				JLabel lblListaDePedido = new JLabel("DETALLE DEL PEDIDO");
				lblListaDePedido.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 21));
				lblListaDePedido.setBounds(1081, 195, 224, 38);
				contentPane.add(lblListaDePedido);
				
				JButton btnGuardar = new JButton("Guardar");
				btnGuardar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						guardarDatos();
						mostrarDatos(titulos,tfFecha.getText());
						
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
				btnGuardar.setBounds(939, 171, 138, 62);
				contentPane.add(btnGuardar);
				
				JLabel lblFecha = new JLabel("FECHA");
				lblFecha.setBounds(1237, 84, 62, 21);
				contentPane.add(lblFecha);
				lblFecha.setFont(new Font("Yu Gothic UI Semilight", Font.PLAIN, 15));
				
				tfFecha = new JTextField();
				tfFecha.setBounds(1309, 79, 138, 30);
				contentPane.add(tfFecha);
				tfFecha.setEditable(false);
				tfFecha.setFont(new Font("Yu Gothic UI Semilight", Font.PLAIN, 16));
				tfFecha.setColumns(10);
				
				JDateChooser dateChooser = new JDateChooser();
				dateChooser.getCalendarButton().setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 15));
				dateChooser.setBounds(298, 182, 183, 38);
				contentPane.add(dateChooser);
				
				JButton btnBuscar = new JButton("");
				btnBuscar.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseEntered(MouseEvent e) {
						btnBuscar.setBackground(new Color(242,76,76));
						btnBuscar.setForeground(Color.black);
						
					}
					
					@Override
					public void mouseExited(MouseEvent e) {
						btnBuscar.setBackground(new Color(41,52,98));
						btnBuscar.setForeground(Color.white);
					}
				});
				btnBuscar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
				btnBuscar.setSelectedIcon(new ImageIcon(pedidos.class.getResource("/imgs/lupa.png")));
				btnBuscar.setRolloverIcon(new ImageIcon(pedidos.class.getResource("/imgs/lupa.png")));
				btnBuscar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if(dateChooser.getDate()!=null) {
							Date date=dateChooser.getDate();
							long d=date.getTime();
							java.sql.Date fechasel=new java.sql.Date(d);
							String fe=fechasel.toString();
							mostrarDatos(titulos,fe);
							
							
						}else {
							JOptionPane.showMessageDialog(null,"Seleccione una fecha","Exito",JOptionPane.WARNING_MESSAGE);
						}

						
						
					}
				});
				btnBuscar.setIcon(new ImageIcon(pedidos.class.getResource("/imgs/lupa2.png")));
				btnBuscar.setForeground(Color.WHITE);
				btnBuscar.setFont(new Font("Yu Gothic UI Semilight", Font.BOLD, 18));
				btnBuscar.setFocusPainted(false);
				btnBuscar.setBorder(null);
				btnBuscar.setBackground(new Color(41, 52, 98));
				btnBuscar.setBounds(491, 171, 62, 49);
				contentPane.add(btnBuscar);
				
				JLabel lblBuscarPorFecha = new JLabel("Buscar por fecha");
				lblBuscarPorFecha.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 21));
				lblBuscarPorFecha.setBounds(298, 122, 173, 38);
				contentPane.add(lblBuscarPorFecha);
				
				JLabel lblCliente = new JLabel("Pedido del Cliente:");
				lblCliente.setBounds(1082, 139, 138, 21);
				contentPane.add(lblCliente);
				lblCliente.setFont(new Font("Yu Gothic UI Semilight", Font.PLAIN, 15));
				
				tfcliente = new JTextField();
				tfcliente.setBounds(1082, 165, 323, 30);
				contentPane.add(tfcliente);
				tfcliente.setEditable(false);
				tfcliente.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 15));
				tfcliente.setColumns(10);
				
				JButton btnCancelar = new JButton("Cancelar pedido");
				btnCancelar.setBounds(1416, 171, 173, 62);
				contentPane.add(btnCancelar);
				btnCancelar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						int dialogButton = JOptionPane.YES_NO_OPTION;
						int dialogResult = JOptionPane.showConfirmDialog (null, "¿Seguro que desea Eliminar el pedido del cliente "+tfcliente.getText()+"?","Advertencia",dialogButton,JOptionPane.WARNING_MESSAGE);
						
						if(idventa>0 && dialogResult == JOptionPane.YES_OPTION && !tfcliente.getText().isEmpty()) {
							Conexion con=new Conexion();

							try {

								String sql="Delete from venta where idventa='"+idventa+"'";
								PreparedStatement ps=con.ejecutarSentencia(sql);
	
								ps.execute();
								ps.close();
								tfcliente.setText("");
								idventa=0;
								mostrarDatos(titulos, tfFecha.getText());
						        table_detalle.setDefaultRenderer(Object.class, new Render());
						    	modelo2 = new DefaultTableModel(null, titulos_carro){
						        @Override
						        public boolean isCellEditable(int row, int column){
						            return false;
						        }
						    	};
						    	table_detalle.setModel(modelo2);
								table_detalle.getColumnModel().getColumn(0).setPreferredWidth(1);
								table_detalle.getColumnModel().getColumn(1).setPreferredWidth(280);
								table_detalle.getColumnModel().getColumn(2).setPreferredWidth(1);
								table_detalle.getColumnModel().getColumn(3).setPreferredWidth(10);
						    	
								JOptionPane.showMessageDialog(null,"Pedido Eliminado Exitosamente","Exito",JOptionPane.INFORMATION_MESSAGE);
								
								
								
								
							} catch (Exception e2) {
								JOptionPane.showMessageDialog(null, e2);
							}
							
						}
					}
				});
				btnCancelar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
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
				btnCancelar.setSelectedIcon(new ImageIcon(pedidos.class.getResource("/imgs/cancelar1.png")));
				btnCancelar.setRolloverSelectedIcon(new ImageIcon(pedidos.class.getResource("/imgs/cancelar1.png")));
				btnCancelar.setRolloverIcon(new ImageIcon(pedidos.class.getResource("/imgs/cancelar1.png")));
				btnCancelar.setIcon(new ImageIcon(pedidos.class.getResource("/imgs/cancelar2.png")));
				btnCancelar.setForeground(Color.WHITE);
				btnCancelar.setFont(new Font("Yu Gothic UI Semilight", Font.BOLD, 18));
				btnCancelar.setFocusPainted(false);
				btnCancelar.setBorder(null);
				btnCancelar.setBackground(new Color(41, 52, 98));
		

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
		
		table_consumo.addMouseListener(new MouseAdapter() {
		
			@Override
			public void mouseClicked(MouseEvent e) {
				if(e.getClickCount()==1) {
					JTable receptor=(JTable) e.getSource();
					
					idventa=(int) receptor.getModel().getValueAt(receptor.getSelectedRow(), 0);
					tfcliente.setText(receptor.getModel().getValueAt(receptor.getSelectedRow(), 3).toString());
					seleccion();
			
					
				}
	
			}
		});
		table_detalle.getColumnModel().getColumn(0).setPreferredWidth(1);
		table_detalle.getColumnModel().getColumn(1).setPreferredWidth(280);
		table_detalle.getColumnModel().getColumn(2).setPreferredWidth(1);
		table_detalle.getColumnModel().getColumn(3).setPreferredWidth(10);
		
		
		if(login.tipouser.equals("Empleado")) {
			btnEstadisticas.setVisible(false);
			separator_2.setVisible(false);
			btnBuscar.setVisible(false);
			dateChooser.setVisible(false);
			lblBuscarPorFecha.setVisible(false);
			
			
		}
		setIconImage(getIconImage());
		fechaActual();
		mostrarDatos(titulos, tfFecha.getText());

	}
	public void seleccion() {
        table_detalle.setDefaultRenderer(Object.class, new Render());
    	modelo2 = new DefaultTableModel(null, titulos_carro){
        @Override
        public boolean isCellEditable(int row, int column){
            return false;
        }
    };
		try {
			
			Conexion con=new Conexion();
			ResultSet resultado=con.consultarSql("Select a.idproducto, a.nombreproducto, a.precio, b.cantidad"
					+ " from producto a, detalle b"
					+ " where b.idproducto=a.idproducto"
					+ " and b.idventa='"+idventa+"'");

			while(resultado.next()) {
				Object fila[] = new Object[4];
				fila[0]=resultado.getInt("idproducto");
				fila[1]=resultado.getString("nombreproducto");
				fila[2]=resultado.getInt("cantidad");
				fila[3]=resultado.getDouble("precio");
				
				modelo2.addRow(fila);
				
			}
			table_detalle.setRowHeight(40);
			table_detalle.setModel(modelo2);
			table_detalle.getColumnModel().getColumn(0).setPreferredWidth(1);
			table_detalle.getColumnModel().getColumn(1).setPreferredWidth(280);
			table_detalle.getColumnModel().getColumn(2).setPreferredWidth(1);
			table_detalle.getColumnModel().getColumn(3).setPreferredWidth(10);
		}catch (Exception e) {
				System.out.println("Error "+e);
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
	public void calllogin() {
		login y=new login();
		this.setVisible(false);
		y.setLocationRelativeTo(null);
		y.setVisible(true);
	}
	public void callestadisticas() {
		estadisticas y=new estadisticas();
		this.setVisible(false);
		y.setLocationRelativeTo(null);
		y.setVisible(true);
	}
	public void mostrarDatos(String[] titulos, String fe) {

		boolean ColumnasEditables[]={false,false,false,false,false,true,true};
		
		Class tipo[]=new Class[] {java.lang.Object.class,java.lang.Object.class,java.lang.Object.class,java.lang.Object.class,java.lang.Object.class,
		java.lang.Boolean.class,java.lang.Boolean.class};
		DefaultTableModel modelo=new DefaultTableModel(null, titulos){
		@Override
		public boolean isCellEditable(int row,int col){
		     return ColumnasEditables[col];
		}
		public Class getColumnClass (int index){
		     return tipo[index];
		}
		
		};
		
		
		

		try {
			Conexion con=new Conexion();
			ResultSet resultado=con.consultarSql("Select * from venta where fecha='"+fe+"' order by hora DESC");

			while(resultado.next()) {
				Object fila[] = new Object[7];
				fila[0]=resultado.getInt("idventa");
				fila[1]=resultado.getDate("fecha");
				fila[2]=resultado.getTime("hora");
				fila[3]=resultado.getString("cliente");
				fila[4]=resultado.getDouble("total");
				fila[5]=resultado.getBoolean("delivery");
				fila[6]=resultado.getBoolean("entregado");

				modelo.addRow(fila);
				
			}
			
			table_consumo.setModel(modelo);
			table_consumo.getColumnModel().getColumn(0).setPreferredWidth(1);
			table_consumo.getColumnModel().getColumn(1).setPreferredWidth(20);
			table_consumo.getColumnModel().getColumn(2).setPreferredWidth(20);
			table_consumo.getColumnModel().getColumn(3).setPreferredWidth(250);
			table_consumo.getColumnModel().getColumn(4).setPreferredWidth(5);
			table_consumo.getColumnModel().getColumn(5).setPreferredWidth(5);
			table_consumo.getColumnModel().getColumn(6).setPreferredWidth(15);


			table_consumo.setRowHeight(30);


			
		} catch (Exception e) {
			System.out.println("Error "+e);
		}
		
	}
	public void guardarDatos() {
		for (int i = 0; i < table_consumo.getRowCount(); i++) {
			int cod = 0;
			boolean delivery;
			boolean entregado;
			
			Conexion con=new Conexion();
			cod=(int) table_consumo.getValueAt(i, 0);
			delivery=(boolean) table_consumo.getValueAt(i, 5);
			entregado=(boolean) table_consumo.getValueAt(i, 6);

			
			String sentencia=String.format("Update venta "
					+ "set delivery='%s', entregado='%s' where idventa='%s'", delivery, entregado, cod);
			con.ejecutarSentenciaSql(sentencia);

			
		}
	}
	@Override
	public Image getIconImage(){
		 Image retValue=Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("imgs/logo_pq.jpg"));
		 return retValue;
	}
}
