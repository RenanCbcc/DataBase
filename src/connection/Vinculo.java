package connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Vinculo {
	private static Connection conexao;// objeto mantém referencia a conexão
										// criada

	public Vinculo() throws ClassNotFoundException, SQLException {
		if (conexao != null && !conexao.isClosed())
			return;
		else
			Class.forName("com.mysql.jdbc.Driver");// registro do driver
		conexao = DriverManager.getConnection(// (URL,user,password)
				"jdbc:mysql://localhost:3306/Comercio", "root", "admin");
		conexao.setAutoCommit(false);
		conexao.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);

	}

	public Connection getConexao() {
		return conexao;
	}

	public void fechar() throws SQLException {
		if (conexao == null || conexao.isClosed())
			return;
		conexao.close();

	}

	public void confirmarTransacao() throws SQLException {
		if (conexao == null || conexao.isClosed())
			return;
		conexao.commit();
	}

	public void cancelarTransacao() throws SQLException {
		if (conexao == null || conexao.isClosed())
			return;
		conexao.rollback();

	}

}
