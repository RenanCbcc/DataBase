package principal;
import conexao.ComercioConexao;
import static javax.swing.JOptionPane.*;
public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try
		{
			ComercioConexao c = new ComercioConexao();
			showMessageDialog(null,"Conexão Aberta!");
			c.fechar();
			showMessageDialog(null,"Conexão Fechada!");
			
		}
		catch (Exception ex)
		{
			showMessageDialog(null,ex.getMessage(),"Erro",ERROR_MESSAGE);
			
		}
		
		System.exit(0);
	}

}
