package aspects;

import conection.Vinculo;
import principal.Grade;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public aspect Exceptions {

	public pointcut SQLexception(): execution (* Vinculo.*(..));

	public pointcut CNFexception():execution( Vinculo.new(..));


	after() throwing (ClassNotFoundException cnf): CNFexception()
	{
		JOptionPane.showMessageDialog(null, cnf.getMessage(), "Erro", 0);
	}

	after() throwing (SQLException sqle): SQLexception() // pointcut named 
	{
		JOptionPane.showMessageDialog(null, sqle.getMessage(), "Erro", 0);
	}
	
	after() throwing (SQLException sqle): execution ( Grade.new(..)) // pointcut anonimo 
	{
		JOptionPane.showMessageDialog(null, sqle.getMessage(), "Erro", 0);
	}


}
