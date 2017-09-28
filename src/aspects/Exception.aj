package aspects;

import connection.Vinculo;
import principal.Grade;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public aspect Exception {

	
	public pointcut CNFexception(): execution( Vinculo.new());
	public pointcut SQLexception(): execution( Vinculo.new());
	
	after() throwing (SQLException sqle): SQLexception() // pointcut nomeado 
	{
		JOptionPane.showMessageDialog(null, sqle.getMessage(), "Erro", 0);
	}
	
	after() throwing (ClassNotFoundException cnfe): CNFexception() // pointcut nomeado 
	{
		JOptionPane.showMessageDialog(null, cnfe.getMessage(), "Erro", 0);
	}

	// pointcuts anonimos 
	after() throwing (SQLException sqle): execution ( Grade.new(..))  
	{
		JOptionPane.showMessageDialog(null, sqle.getMessage(), "Erro", 0);
	}
	
	after() throwing (SQLException sqle): execution (* Vinculo.*(..))  
	{
		JOptionPane.showMessageDialog(null, sqle.getMessage(), "Erro", 0);
	}
	
	
}
