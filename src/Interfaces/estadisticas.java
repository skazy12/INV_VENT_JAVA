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
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import javax.swing.JFrame;
import javax.swing.JLabel;

import javax.swing.JPanel;

import javax.swing.JSeparator;

import javax.swing.JTextField;
import javax.swing.SwingConstants;

import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;
import javax.swing.table.DefaultTableModel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.time.Day;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.ui.RectangleInsets;

import Clases.carro;

import Conect.Conexion;

import com.toedter.calendar.JCalendar;


public class estadisticas extends JFrame {

	private JPanel contentPane;
	private int xMouse, yMouse;
	private JLabel lblx, lbl_min;

	private JTextField tffecha;
	private JCalendar finicio, ffin;
	private JLabel lblfi, lblff;

	
	private String[] titulos= {"COD", "FECHA", "HORA", "CLIENTE", "TOTAL Bs.", "DELIVERY", "ENTREGADO"};
	private String[] titulos_carro= {"COD", "PRODUCTO", "PRECIO Bs.", "CANTIDAD"};
	

	DefaultTableModel modelo, modelo2;
	
	public static int fila,columna;
	
	private JButton btndel=new JButton("ELIMINAR");
	
	public static ArrayList<carro>lista=new ArrayList<carro>();
	
	private int idventa;
	private double total=0;
	private JTextField tftotal;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					estadisticas frame = new estadisticas();
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
	public estadisticas() {
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
		btn_min.setBounds(57, 0, 53, 49);
		superior_panel.add(btn_min);
		
		lbl_min = new JLabel("-");
		lbl_min.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_min.setFont(new Font("Yu Gothic UI Semilight", Font.PLAIN, 35));
		lbl_min.setBounds(0, 0, 53, 49);
		btn_min.add(lbl_min);
		
		JLabel lblFecha = new JLabel("FECHA");
		lblFecha.setBounds(960, 23, 62, 21);
		superior_panel.add(lblFecha);
		lblFecha.setFont(new Font("Yu Gothic UI Semilight", Font.PLAIN, 15));
		
		tffecha = new JTextField();
		tffecha.setBounds(1016, 18, 138, 30);
		superior_panel.add(tffecha);
		tffecha.setEditable(false);
		tffecha.setFont(new Font("Yu Gothic UI Semilight", Font.PLAIN, 16));
		tffecha.setColumns(10);
		
		JPanel panel_menu = new JPanel();
		panel_menu.setBackground(new Color(41,52,98));
		panel_menu.setBounds(0, 49, 288, 781);
		contentPane.add(panel_menu);
		panel_menu.setLayout(null);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(0, 76, 287, 2);
		panel_menu.add(separator);
		
		JButton btnEstadisticas = new JButton("ESTADISTICAS");
		btnEstadisticas.setIcon(new ImageIcon(estadisticas.class.getResource("/imgs/estadistica2.png")));
		btnEstadisticas.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnEstadisticas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});

		btnEstadisticas.setForeground(Color.BLACK);
		btnEstadisticas.setFont(new Font("Yu Gothic UI Semilight", Font.BOLD, 24));
		btnEstadisticas.setFocusPainted(false);
		btnEstadisticas.setBorder(null);
		btnEstadisticas.setBackground(new Color(242,76,76));
		btnEstadisticas.setBounds(0, 136, 287, 47);
		panel_menu.add(btnEstadisticas);
				
				JButton btnNuevaVenta = new JButton("NUEVA VENTA");
				btnNuevaVenta.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						callventas();
					}
				});
				btnNuevaVenta.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
				btnNuevaVenta.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseEntered(MouseEvent e) {
						btnNuevaVenta.setBackground(new Color(242,76,76));
						btnNuevaVenta.setForeground(Color.black);
						
					}
					
					@Override
					public void mouseExited(MouseEvent e) {
						btnNuevaVenta.setBackground(new Color(41,52,98));
						btnNuevaVenta.setForeground(Color.white);
					}
				});
				btnNuevaVenta.setRolloverSelectedIcon(new ImageIcon(pedidos.class.getResource("/imgs/agregar.png")));
				btnNuevaVenta.setRolloverIcon(new ImageIcon(pedidos.class.getResource("/imgs/agregar.png")));
				btnNuevaVenta.setSelectedIcon(new ImageIcon(pedidos.class.getResource("/imgs/agregar.png")));
				btnNuevaVenta.setIcon(new ImageIcon(pedidos.class.getResource("/imgs/agregar2.png")));
				btnNuevaVenta.setForeground(Color.WHITE);
				btnNuevaVenta.setFont(new Font("Yu Gothic UI Semilight", Font.BOLD, 24));
				btnNuevaVenta.setFocusPainted(false);
				btnNuevaVenta.setBorder(null);
				btnNuevaVenta.setBackground(new Color(41,52,98));
				btnNuevaVenta.setBounds(0, 84, 287, 47);
				panel_menu.add(btnNuevaVenta);
				
				JSeparator separator_1 = new JSeparator();
				separator_1.setBounds(0, 131, 287, 2);
				panel_menu.add(separator_1);
				
				JButton btnpedidos = new JButton("LISTA PEDIDOS");
				btnpedidos.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						callpedidos();
					}
				});
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
				btnpedidos.setSelectedIcon(new ImageIcon(estadisticas.class.getResource("/imgs/lista1.png")));
				btnpedidos.setRolloverSelectedIcon(new ImageIcon(estadisticas.class.getResource("/imgs/lista1.png")));
				btnpedidos.setRolloverIcon(new ImageIcon(estadisticas.class.getResource("/imgs/lista1.png")));
				btnpedidos.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
				btnpedidos.setIcon(new ImageIcon(estadisticas.class.getResource("/imgs/lista2.png")));
				btnpedidos.setForeground(Color.WHITE);
				btnpedidos.setFont(new Font("Yu Gothic UI Semilight", Font.BOLD, 24));
				btnpedidos.setFocusPainted(false);
				btnpedidos.setBorder(null);
				btnpedidos.setBackground(new Color(41, 52, 98));
				btnpedidos.setBounds(0, 28, 287, 47);
				panel_menu.add(btnpedidos);
				
				JSeparator separator_2 = new JSeparator();
				separator_2.setBounds(0, 183, 287, 2);
				panel_menu.add(separator_2);
				
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
				
				JLabel lblTitulo = new JLabel("ESTADISTICAS");
				lblTitulo.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 21));
				lblTitulo.setBounds(619, 60, 164, 38);
				contentPane.add(lblTitulo);
				modelo= new DefaultTableModel(null, titulos);
				
				JSeparator separator_4 = new JSeparator();
				separator_4.setBounds(298, 109, 894, 2);
				contentPane.add(separator_4);
				modelo2= new DefaultTableModel(null, titulos_carro);
				
				JLabel lblTodosLosProductos = new JLabel("FECHA INICIO");
				lblTodosLosProductos.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 21));
				lblTodosLosProductos.setBounds(371, 207, 144, 38);
				contentPane.add(lblTodosLosProductos);
				
				JButton btnGraficar = new JButton("Graficar");
				btnGraficar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						int anioi = finicio.getCalendar().get(Calendar.YEAR);
						int mesi = finicio.getCalendar().get(Calendar.MARCH);
						int diai = finicio.getCalendar().get(Calendar.DAY_OF_MONTH);
						
						String fi=anioi+"-"+(mesi+1)+"-"+diai;
						
						int aniof = ffin.getCalendar().get(Calendar.YEAR);
						int mesf = ffin.getCalendar().get(Calendar.MARCH);
						int diaf = ffin.getCalendar().get(Calendar.DAY_OF_MONTH);
						
						String ff=aniof+"-"+(mesf+1)+"-"+diaf;
						String titulo="REPORTE DE VENTAS";
						

						
						Conexion con=new Conexion();
						ResultSet resultado=con.consultarSql("Select EXTRACT(YEAR from fecha) as anio, EXTRACT (month from fecha) as mes, EXTRACT (day from fecha) as dia, total"
								+ " from tot_ventas "
								+ " where fecha>='"+fi+"'"
								+ " and fecha<='"+ff+"'");
						TimeSeries s1 = new TimeSeries("Ventas");
						try {
							while(resultado.next()) {
								int anio=(int) resultado.getDouble("anio");
								int mes=(int) resultado.getDouble("mes");
								int dia=(int) resultado.getDouble("dia");
								double tot=resultado.getDouble("total");
								s1.add(new Day(dia,mes,anio),tot);
												

							}
						} catch (SQLException e1) {
							
							e1.printStackTrace();
						}
					        
					        TimeSeriesCollection dataset = new TimeSeriesCollection();
					        dataset.addSeries(s1);
							

					        JFreeChart chart = ChartFactory.createTimeSeriesChart(
					                titulo,  // title
					                "Fecha",             // x-axis label
					                "Total Ventas (Bs.)",   // y-axis label
					                dataset,            // data
					                true,               // create legend?
					                true,               // generate tooltips?
					                false               // generate URLs?
					            );
					        
					        chart.setBackgroundPaint(Color.white);

					        XYPlot plot = (XYPlot) chart.getPlot();
					        plot.setBackgroundPaint(Color.lightGray);
					        plot.setDomainGridlinePaint(Color.white);
					        plot.setRangeGridlinePaint(Color.white);
					        plot.setAxisOffset(new RectangleInsets(5.0, 5.0, 5.0, 5.0));
					        plot.setDomainCrosshairVisible(true);
					        plot.setRangeCrosshairVisible(true);

					        XYItemRenderer r = plot.getRenderer();
					        if (r instanceof XYLineAndShapeRenderer) {
					            XYLineAndShapeRenderer renderer = (XYLineAndShapeRenderer) r;
					            renderer.setBaseShapesVisible(true);
					            renderer.setBaseShapesFilled(true);
					            renderer.setDrawSeriesLineAsPath(true);
					        }

					        DateAxis axis = (DateAxis) plot.getDomainAxis();
					        axis.setDateFormatOverride(new SimpleDateFormat("dd-MMM-yyyy"));

					        // Mostramos la grafica en pantalla
					        ChartFrame frame1 = new ChartFrame("Grafica de Ventas", chart);
					        frame1.pack();
					        frame1.setVisible(true);
					        frame1.setLocationRelativeTo(null);
					        
					        definirtotal(fi,ff);
						
						
					}
				});
				btnGraficar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
				btnGraficar.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseEntered(MouseEvent e) {
						btnGraficar.setBackground(new Color(242,76,76));
						btnGraficar.setForeground(Color.black);
						
					}
					
					@Override
					public void mouseExited(MouseEvent e) {
						btnGraficar.setBackground(new Color(41,52,98));
						btnGraficar.setForeground(Color.white);
					}
				});
				btnGraficar.setIcon(new ImageIcon(estadisticas.class.getResource("/imgs/informe-grafico.png")));
				btnGraficar.setForeground(Color.WHITE);
				btnGraficar.setFont(new Font("Yu Gothic UI Semilight", Font.BOLD, 18));
				btnGraficar.setFocusPainted(false);
				btnGraficar.setBorder(null);
				btnGraficar.setBackground(new Color(41, 52, 98));
				btnGraficar.setBounds(484, 518, 138, 62);
				contentPane.add(btnGraficar);
				
				JButton btnTorta = new JButton("Graficar");
				btnTorta.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						Conexion con=new Conexion();
						DefaultPieDataset data = new DefaultPieDataset();
						ResultSet resultado=con.consultarSql("select a.producto, a.consumidos "
								+ " from mas_vendidos a, producto b"
								+ " where a.id=b.idproducto"
								+ " and b.precio>0;");
						
						try {
							while(resultado.next()) {
								String producto=resultado.getString("producto");
								int cantidad=resultado.getInt("consumidos");
								data.setValue(producto, cantidad);
												

							}
						} catch (SQLException e1) {
							
							e1.printStackTrace();
						}
						
				        
				        // create a chart...
				        JFreeChart chart = ChartFactory.createPieChart(
				            "Productos Vendidos",
				            data,
				            true, // legend?
				            true, // tooltips?
				            false // URLs?
				        );
				        // create and display a frame...
				        ChartFrame frame = new ChartFrame("Grafico de Torta", chart);
				        frame.pack();
				        frame.setVisible(true);
				        frame.setLocationRelativeTo(null);

					}
				});
				btnTorta.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
				btnTorta.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseEntered(MouseEvent e) {
						btnTorta.setBackground(new Color(242,76,76));
						btnTorta.setForeground(Color.black);
						
					}
					
					@Override
					public void mouseExited(MouseEvent e) {
						btnTorta.setBackground(new Color(41,52,98));
						btnTorta.setForeground(Color.white);
					}
				});
				btnTorta.setIcon(new ImageIcon(estadisticas.class.getResource("/imgs/grafico-de-torta.png")));
				btnTorta.setForeground(Color.WHITE);
				btnTorta.setFont(new Font("Yu Gothic UI Semilight", Font.BOLD, 18));
				btnTorta.setFocusPainted(false);
				btnTorta.setBorder(null);
				btnTorta.setBackground(new Color(41, 52, 98));
				btnTorta.setBounds(937, 329, 138, 62);
				contentPane.add(btnTorta);
				
				JLabel lblFechaFin = new JLabel("FECHA FIN");
				lblFechaFin.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 21));
				lblFechaFin.setBounds(640, 207, 117, 38);
				contentPane.add(lblFechaFin);
				
				finicio = new JCalendar();
				finicio.getDayChooser().getDayPanel().setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 12));
				finicio.getMonthChooser().getComboBox().setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 12));
				finicio.setBounds(308, 256, 249, 234);
				contentPane.add(finicio);
				
				ffin = new JCalendar();
				ffin.getDayChooser().getDayPanel().setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 12));
				ffin.getMonthChooser().getComboBox().setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 12));
				ffin.setBounds(582, 256, 249, 234);
				contentPane.add(ffin);
				
				JLabel lblVerLosProductos = new JLabel("VER LOS PRODUCTOS M\u00C1S VENDIDOS");
				lblVerLosProductos.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 21));
				lblVerLosProductos.setBounds(960, 160, 376, 62);
				contentPane.add(lblVerLosProductos);
				
				JLabel lblVerTotalDe = new JLabel("VER TOTAL DE VENTAS POR LAPSOS DE TIEMPO");
				lblVerTotalDe.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 21));
				lblVerTotalDe.setBounds(347, 122, 499, 62);
				contentPane.add(lblVerTotalDe);
				
				JLabel lblGraficoDeTorta = new JLabel("GRAFICO DE TORTA");
				lblGraficoDeTorta.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 21));
				lblGraficoDeTorta.setBounds(909, 236, 201, 62);
				contentPane.add(lblGraficoDeTorta);
				
				JLabel lblGraficoDeBarras = new JLabel("GRAFICO DE BARRAS");
				lblGraficoDeBarras.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 21));
				lblGraficoDeBarras.setBounds(1147, 236, 232, 62);
				contentPane.add(lblGraficoDeBarras);
				
				JButton btnBarras = new JButton("Graficar");
				btnBarras.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						Conexion con=new Conexion();
						DefaultCategoryDataset dataset = new DefaultCategoryDataset();
						ResultSet resultado=con.consultarSql("select a.producto, (a.consumidos*b.precio) as total "
								+ " from mas_vendidos a, producto b"
								+ " where a.id=b.idproducto"
								+ " and b.precio>0;");
						
						try {
							while(resultado.next()) {
								String producto=resultado.getString("producto");
								double cons=resultado.getDouble("total");
								
								
								dataset.setValue(cons,producto,"");
												

							}
						} catch (SQLException e1) {
							
							e1.printStackTrace();
						}
						
				        
				        // create a chart...
				        JFreeChart chart = ChartFactory.createBarChart(
				                "Grafica de Barras",
				                "producto", 
				                "total Vendido (Bs.)", 
				                dataset, 
				                PlotOrientation.VERTICAL,
				                true, 
				                false, 
				                false
				        );
				        // create and display a frame...
				        ChartFrame frame = new ChartFrame("Grafico de Torta", chart);
				        frame.pack();
				        frame.setVisible(true);
				        frame.setLocationRelativeTo(null);
						
					}
				});
				btnBarras.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
				btnBarras.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseEntered(MouseEvent e) {
						btnBarras.setBackground(new Color(242,76,76));
						btnBarras.setForeground(Color.black);
						
					}
					
					@Override
					public void mouseExited(MouseEvent e) {
						btnBarras.setBackground(new Color(41,52,98));
						btnBarras.setForeground(Color.white);
					}
				});
				btnBarras.setIcon(new ImageIcon(estadisticas.class.getResource("/imgs/grafico-de-barras.png")));
				btnBarras.setForeground(Color.WHITE);
				btnBarras.setFont(new Font("Yu Gothic UI Semilight", Font.BOLD, 18));
				btnBarras.setFocusPainted(false);
				btnBarras.setBorder(null);
				btnBarras.setBackground(new Color(41, 52, 98));
				btnBarras.setBounds(1198, 329, 138, 62);
				contentPane.add(btnBarras);
				
				tftotal = new JTextField();
				tftotal.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 15));
				tftotal.setEditable(false);
				tftotal.setColumns(10);
				tftotal.setBounds(606, 613, 225, 30);
				contentPane.add(tftotal);
				
				JLabel lblTotalVendidoEn = new JLabel("Total Vendido en el tiempo seleccionado Bs.");
				lblTotalVendidoEn.setFont(new Font("Yu Gothic UI Semilight", Font.PLAIN, 15));
				lblTotalVendidoEn.setBounds(298, 617, 298, 21);
				contentPane.add(lblTotalVendidoEn);
				
				lblfi = new JLabel("");
				lblfi.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 21));
				lblfi.setBounds(371, 568, 159, 38);
				contentPane.add(lblfi);
				
				lblff = new JLabel("");
				lblff.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 21));
				lblff.setBounds(642, 568, 158, 38);
				contentPane.add(lblff);
				
				JSeparator separator_5 = new JSeparator();
				separator_5.setOrientation(SwingConstants.VERTICAL);
				separator_5.setBounds(897, 109, 12, 721);
				contentPane.add(separator_5);
		

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
		
		fechaActual();
		setIconImage(getIconImage());
	}

	public void definirtotal(String fi, String ff) {
		Conexion con=new Conexion();

		ResultSet resultado=con.consultarSql("Select sum(total)as total from tot_ventas"
				+ " where fecha>='"+fi+"'"
				+ " and fecha<='"+ff+"'");
		try {
			if(resultado.next()) {
				double tot=resultado.getDouble("total");
				tftotal.setText(tot+"");
			}
			lblfi.setText(fi);
			lblff.setText(ff);
			
		} catch (SQLException e1) {
			
			e1.printStackTrace();
		}
		
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
	public void fechaActual() {
		String f="";
		Calendar fecha=new GregorianCalendar();
        int annio = fecha.get(Calendar.YEAR);
        int mes = fecha.get(Calendar.MONTH);
        int dia = fecha.get(Calendar.DAY_OF_MONTH);
        
        f=dia+"-"+(mes+1)+"-"+annio;
        tffecha.setText(f);
	}
	@Override
	public Image getIconImage(){
		 Image retValue=Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("imgs/logo_pq.jpg"));
		 return retValue;
	}
}
