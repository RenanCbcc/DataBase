package connection;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Vinculo {
	private static Connection conexao;//objeto mantém referencia a conexão criada
	
	public Vinculo() throws Exception
	{
		try
		{
			if ( conexao != null && !conexao.isClosed()) 
				return;
			else
			Class.forName("com.mysql.jdbc.Driver");// registro do driver
			conexao = DriverManager.getConnection(//(URL,user,password)
					"jdbc:mysql://localhost:3306/Comercio","user","root");//Verificar a porta!
			conexao.setAutoCommit(false);
			conexao.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
		}
		catch(ClassNotFoundException cnf)
		{
			throw new Exception ("Driver não encontrado"+ cnf.getMessage());
		}
		
		catch(SQLException sql)
		{
			throw new Exception ("Falha ocorrida: "+ sql.getMessage());
		}
	}
	
	public Connection getConexao()
	{
		return conexao;
	}
	
	public void fechar() throws Exception
	{
		try
		{
			if (conexao == null || conexao.isClosed())
				return;
			conexao.close();
		}
		
		catch( SQLException sql )
		{
			throw new Exception("Falha ocorrida: " + sql.getMessage());
		}
	}
	
	public void confirmarTransacao() throws Exception
	{
		try
		{
			if (conexao == null || conexao.isClosed())
				return;
			conexao.commit();
		}
		
		catch( SQLException sql )
		{
			throw new Exception("Falha ocorrida: " + sql.getMessage());
		}
	}
	
	public void cancelarTransacao() throws Exception
	{
		try
		{
			if (conexao == null || conexao.isClosed())
				return;
			conexao.rollback();
		}
		
		catch( SQLException sql )
		{
			throw new Exception("Falha ocorrida: " + sql.getMessage());
		}
		
	}
	
}
