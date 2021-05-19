package es.Studium.Mentiroso;


import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Random;

public class Modelo
{
	Random rnd = new Random();

	public void barajar(int uno[], int dos[], int tres[], int[] cuatro)
	{		
		int jugador = 0;
		int numeroArepartir = 1;
		int contador1=0, contador2=0, contador3=0, contador4=0;
		for(int i = 0; i < 48; i++)
		{
			jugador = rnd.nextInt(2);
			if((jugador==0)&&(contador1<12))
			{
				uno[contador1] = numeroArepartir;
				contador1++;
			}
			else if (contador2<12)
			{
				dos[contador2] = numeroArepartir;
				contador2++;
			}
			
			else if (contador3<12)
			{
				tres[contador3] = numeroArepartir;
				contador3++;
			}
			
			else if (contador4<12)
			{
				cuatro[contador4] = numeroArepartir;
				contador4++;
			}
			if(numeroArepartir%11==0)
			{
				numeroArepartir=1;
			}
			else
			{
				numeroArepartir++;
			}
		}
	}

	public void rebarajar(int t[])
	{
		int aleatorio, auxiliar;
		for(int i=0; i<12; i++)
		{
			aleatorio = rnd.nextInt(11)+1;
			auxiliar = t[i];
			t[i] = t[aleatorio];
			t[aleatorio] = auxiliar;
		}
	}

	//METODO CONECTAR
	public Connection conectar()
	{
		Connection c = null;
		String driver = "com.mysql.cj.jdbc.Driver";
		String url = "jdbc:mysql://localhost:3306/el_mentiroso?serverTimezone=UTC";//ESPECIFICAMOS LA URL
		String Login = "root";
		String password = "Studium2020;";
		try
		{
			//Cargar los controladores para el acceso a la BD
			Class.forName(driver);
			//Establecer la conexi�n con la BD clientes
			c = DriverManager.getConnection(url, Login, password);
		}
		catch (ClassNotFoundException cnfe)
		{
			System.out.println("Error 1-"+cnfe.getMessage());
		}
		catch (SQLException sqle)
		{
			System.out.println("Error 2-"+sqle.getMessage());
		}
		return (c);
	}

	public void cerrar(Connection conexion) 
	{
		try
		{
			if(conexion!=null)
			{
				conexion.close();
			}
		}
		catch (SQLException error)
		{
			System.out.println("Error 3-" +error.getMessage());
		}

	}

	public String mejoresJugadores(Connection conexion) 
	{
		String datos ="";
		Statement statement = null;
		ResultSet rs = null;
		String sentencia = "SELECT idJugador, nombreJugador, puntosJugador FROM jugadores ORDER BY puntosJugador DESC;";

		try
		{
			statement = conexion.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);
			rs = statement.executeQuery(sentencia);
			while(rs.next())
			{
				datos = datos + rs.getInt("idJugador") + "\t";
				datos = datos + rs.getString("nombreJugador")+ "\t";
				datos = datos + rs.getInt("puntosJugador") + "\n";
			}
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		return(datos);
	}



	public String crearJugadorNuevo(Connection conexion) 
	{
		String datos ="";
		Statement statement = null;
		ResultSet rs = null;
		String sentencia = "INSERT INTO jugadores VALUES ('','','0');";

		try
		{
			statement = conexion.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);
			rs = statement.executeQuery(sentencia);
			while(rs.next())
			{
				datos = datos + rs.getInt("idJugador");
				datos = datos + rs.getString("nombreJugador");
				datos = datos + rs.getInt("puntosJugador");
			}
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		return(datos);
	}
	
	public String crearPartidaNueva(Connection conexion) 
	{
		String datos ="";
		Statement statement = null;
		ResultSet rs = null;
		String sentencia = "INSERT INTO partidas VALUES ('null','','');";

		try
		{
			statement = conexion.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);
			rs = statement.executeQuery(sentencia);
			while(rs.next())
			{
				datos = datos + rs.getInt("idPartida");
				datos = datos + rs.getString("nombrePartida");
				datos = datos + rs.getInt("idJugadorFK");
			}
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		return(datos);
	}

	
	public void ayuda() 
	{
		try 
		{ 
			//EJECUTA EL ARCHIVO DE AYUDA
			Runtime.getRuntime().exec("hh.exe ayuda.chm"); 
		} 
		catch (IOException e) 
		{ 
			e.printStackTrace(); 
		} 
	}
	
//	//CREAMOS LA SENTENCIA
//	statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
//			ResultSet.CONCUR_READ_ONLY);
//	//TOMAMOS EL TEXTO
//	if(((textoNombreCliente.getText().length())!=0)
//			&& 	((textoDirreccionCliente.getText().length())!=0)
//			&&	((textoCorreoCliente.getText().length())!=0)
//			&&	((textoDNICliente.getText().length())!=0))
//	{
//		//INGRESAMOS LOS DATOS DE LA TABLA CLIENTES DE LA BASE DE DATOS
//		sentencia = "INSERT INTO clientes VALUES (null, '" + 
//				textoNombreCliente.getText() + "','" + 
//				textoDirreccionCliente.getText() + "','" + 
//				textoDNICliente.getText() + "', '" +
//				textoCorreoCliente.getText() + "')";
//		System.out.println(sentencia);
//		log.guardar(usuario, sentencia);
//		statement.executeUpdate(sentencia);


}





