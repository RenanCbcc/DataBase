package aspects;

import connection.Vinculo;
import static javax.swing.JOptionPane.ERROR_MESSAGE;
import static javax.swing.JOptionPane.showMessageDialog;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public aspect Exception {

	pointcut SQLexception():call(* Vinculo.*(..));
	
	
	after() throwing (ClassNotFoundException cnf):
	call(* Vinculo.*(..))
	{
		JOptionPane.showMessageDialog(null, cnf.getMessage(), "Erro", 0);
	}

	after() throwing (SQLException sqle):
	call( *  Vinculo.*(..))
	{
		JOptionPane.showMessageDialog(null, sqle.getMessage(), "Erro", 0);
	}

}
