package Interfaces;


import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.Font;
import java.awt.Image;

import javax.swing.JPasswordField;

import java.awt.Color;
import javax.swing.SwingConstants;
import javax.swing.ImageIcon;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Cursor;
import javax.swing.JSeparator;
import java.awt.event.MouseMotionAdapter;
import java.sql.ResultSet;

import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;

import Conect.Conexion;


import java.awt.SystemColor;
import java.awt.Toolkit;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class login extends JFrame {

	private JPanel contentPane;
	private JTextField tfci;
	private JPasswordField tfpass;
	private int xMouse, yMouse;
	private JLabel lblx, lbl_min;
	public static String cilogeado, tipouser;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					login frame = new login();
					
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
	public login() {
		setLocationByPlatform(true);
		setUndecorated(true);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 860, 600);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		tfci = new JTextField();
		tfci.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode()==KeyEvent.VK_ENTER) {
					entrar();
				}
			}
		});
		tfci.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				tfci.setCaretPosition(0);
			}
		});
		tfci.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				if(tfci.getText().equals("Ingrese su C.I.")) {
					tfci.setText("");
					tfci.setForeground(Color.black);
				}
				if(String.valueOf(tfpass.getPassword()).isEmpty()) {
					tfpass.setText("********");
					tfpass.setForeground(Color.gray);
				}

			}
		});
		tfci.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 15));
		tfci.setForeground(new Color(192, 192, 192));
		tfci.setSelectionColor(SystemColor.activeCaption);
		tfci.setSelectedTextColor(SystemColor.textText);
		tfci.setText("Ingrese su C.I.");
		tfci.setBorder(null);
		tfci.setColumns(10);
		tfci.setBounds(157, 270, 332, 26);
		contentPane.add(tfci);
		
		JLabel lblNewLabel = new JLabel("CI ");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel.setBounds(73, 284, 74, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Contrase\u00F1a");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_1.setBounds(38, 350, 109, 14);
		contentPane.add(lblNewLabel_1);
		
		tfpass = new JPasswordField();
		tfpass.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode()==KeyEvent.VK_ENTER) {
					entrar();
				}
			}
		});
		tfpass.setText("********");
		tfpass.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				if(String.valueOf(tfpass.getPassword()).equals("********")) {
					tfpass.setText("");
					tfpass.setForeground(Color.black);
				}
				if(tfci.getText().isEmpty()) {
					tfci.setText("Ingrese su C.I.");
					tfci.setForeground(Color.gray);
				}

			}
		});
		tfpass.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 15));
		
		tfpass.setBorder(null);
		tfpass.setForeground(new Color(192, 192, 192));
		tfpass.setBounds(157, 346, 332, 26);
		contentPane.add(tfpass);
		
		JPanel panel = new JPanel();

		panel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

		panel.setBackground(new Color(236, 155, 59));
		panel.setBounds(219, 429, 141, 56);
		contentPane.add(panel);
		panel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				panel.setBackground(new Color(247, 215, 22));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				panel.setBackground(new Color(236, 155, 59));
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				String ci=tfci.getText();
				String ps=tfpass.getText();
				if(!ci.equals("") && !ps.equals("")) {
					try {
						Conexion con=new Conexion();
						String sentencia=String.format("select ci, clave, tipo_usuario from usuario where ci='%s' and clave='%s'", ci,ps);
						ResultSet resultado=con.consultarSql(sentencia);
						if(resultado.next()) {
							tipouser=resultado.getString("tipo_usuario");
							cilogeado=resultado.getString("ci");
							callinicio();
							
							
							
						}else {
							reset();
							JOptionPane.showMessageDialog(null,"Ingrese un ci y una contraseña válida","Advertencia",JOptionPane.WARNING_MESSAGE);
							
						}
						
					}catch(Exception e1) {
						
					}
					
				}
			}
		});
		panel.setLayout(null);
		
		JLabel lblNewLabel_3 = new JLabel("ENTRAR");
		lblNewLabel_3.setBounds(0, 0, 141, 56);
		panel.add(lblNewLabel_3);

		lblNewLabel_3.setFont(new Font("Dubai Light", Font.BOLD, 24));
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
		
		JSeparator separator = new JSeparator();
		separator.setForeground(Color.BLACK);
		separator.setBackground(Color.BLACK);
		separator.setBounds(157, 298, 332, 14);
		contentPane.add(separator);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setForeground(Color.BLACK);
		separator_1.setBackground(Color.BLACK);
		separator_1.setBounds(157, 373, 332, 14);
		contentPane.add(separator_1);
		
		JPanel superior_panel = new JPanel();
		superior_panel.setBorder(new MatteBorder(1, 1, 0, 1, (Color) new Color(0, 0, 0)));

		superior_panel.setBackground(Color.WHITE);
		superior_panel.setBounds(0, 0, 860, 49);
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
		btn_min.setBounds(54, 0, 53, 49);
		superior_panel.add(btn_min);
		
		lbl_min = new JLabel("-");
		lbl_min.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_min.setFont(new Font("Yu Gothic UI Semilight", Font.PLAIN, 35));
		lbl_min.setBounds(0, 0, 53, 49);
		btn_min.add(lbl_min);
		
		JLabel lblNewLabel_4 = new JLabel("INICIAR SESION");
		lblNewLabel_4.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 38));
		lblNewLabel_4.setBounds(157, 113, 351, 113);
		contentPane.add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("");
		lblNewLabel_5.setIcon(new ImageIcon(login.class.getResource("/imgs/logo.jpg")));
		lblNewLabel_5.setBounds(518, 110, 313, 378);
		contentPane.add(lblNewLabel_5);
		
		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setIcon(new ImageIcon(login.class.getResource("/imgs/perfil-del-usuario.png")));
		lblNewLabel_2.setBounds(25, 90, 122, 168);
		contentPane.add(lblNewLabel_2);
		

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
		setIconImage(getIconImage());
		
	}
	public void callinicio() {
		Ventas y=new Ventas();
		this.setVisible(false);
		y.setLocationRelativeTo(null);
		y.setVisible(true);
	}
	public void reset() {
		login y=new login();
		this.setVisible(false);
		y.setLocationRelativeTo(null);
		y.setVisible(true);
	}
	public void entrar() {
		String ci=tfci.getText();
		String ps=tfpass.getText();
		if(!ci.equals("") && !ps.equals("")) {
			try {
				Conexion con=new Conexion();
				String sentencia=String.format("select ci, clave, tipo_usuario from usuario where ci='%s' and clave='%s'", ci,ps);
				ResultSet resultado=con.consultarSql(sentencia);
				if(resultado.next()) {
					tipouser=resultado.getString("tipo_usuario");
					cilogeado=resultado.getString("ci");
					callinicio();
					
					
					
				}else {
					reset();
					JOptionPane.showMessageDialog(null,"Ingrese un ci y una contraseña válida","Advertencia",JOptionPane.WARNING_MESSAGE);
					
				}
				
			}catch(Exception e1) {
				
			}
			
		}
	}
	@Override
	public Image getIconImage(){
		 Image retValue=Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("imgs/logo_pq.jpg"));
		 return retValue;
	}
}
