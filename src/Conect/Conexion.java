package Conect;

import java.sql.*;

import javax.swing.JOptionPane;



public class Conexion {

	
	private String usuario="postgres";
	private String pass="7530";
	private String port="5432";
	private String bdname="DB_GL1";
	private String host="localhost";
	private Connection con = null;
	 

	public Conexion(){
		try {
			Class.forName("org.postgresql.Driver");
			String servidor = "jdbc:postgresql://"+host+":"+port+"/"+bdname;
			con=DriverManager.getConnection(servidor,usuario,pass);

			
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null,"Error en la conexion: "+e,"Error",JOptionPane.ERROR_MESSAGE);

		}
		
	}

	public int ejecutarSentenciaSql(String sentencia) {
		try {
			PreparedStatement pstm=con.prepareStatement(sentencia);
			pstm.execute();
			return 1;
			
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null,"Error en la conexion: "+e,"Error",JOptionPane.ERROR_MESSAGE);
			return 0;
		}
		
	}
	
	public ResultSet consultarSql(String sentencia) {
		try {
			PreparedStatement pstm=con.prepareStatement(sentencia);
			ResultSet answer=pstm.executeQuery();

			
			return answer;
			
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null,"Error en la conexion: "+e,"Error",JOptionPane.ERROR_MESSAGE);
			return null;
		}
		
	}
	public PreparedStatement ejecutarSentencia(String sentencia) {
		try {
			PreparedStatement pstm=con.prepareStatement(sentencia);

			return pstm;
			
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null,"Error en la conexion: "+e,"Error",JOptionPane.ERROR_MESSAGE);
			return null;
		}
		
	}
	


}
