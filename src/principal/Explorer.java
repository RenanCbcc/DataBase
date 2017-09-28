package principal;

import statement.*;
import static javax.swing.JOptionPane.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;
import javax.swing.event.*;

import connection.Vinculo;

public class Explorer extends JFrame implements ListSelectionListener, ItemListener, ActionListener {

	private JList<String> Bancos;
	private JComboBox<String> Tabs;
	private JComboBox<String> Cpos;
	private JButton Consultar;
	private JButton Alterar;
	private JButton Incluir;
	private JButton Excluir;
	private JTable Grade;
	private JPanel Combos;
	private JPanel Centro;
	private Vinculo vinculo;

	public Explorer() {
		setTitle("Banco de Dados Explorer");
		setSize(750, 250);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		Bancos = new JList<String>(new DefaultListModel<String>());
		Bancos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		Tabs = new JComboBox<String>(new DefaultComboBoxModel<String>());
		Cpos = new JComboBox<String>(new DefaultComboBoxModel<String>());

		Consultar = new JButton("Consultar");
		Consultar.setMnemonic('C');
		Consultar.setToolTipText("Consultar o Banco de Dados");

		Alterar = new JButton("Alterar");
		Alterar.setMnemonic('A');
		Alterar.setToolTipText("Alterar o Banco de Dados");

		Incluir = new JButton("Incluir");
		Incluir.setMnemonic('I');
		Incluir.setToolTipText("Incluir no Banco de Dados");

		Excluir = new JButton("Excluir");
		Excluir.setMnemonic('E');
		Excluir.setToolTipText("Excluir registro no Banco de Dados");

		Grade = new JTable(new Grade());

		Combos = new JPanel(new FlowLayout(FlowLayout.LEFT));
		Combos.add(Tabs);
		Combos.add(Cpos);
		Combos.add(Alterar);
		Combos.add(Consultar);
		Combos.add(Excluir);
		Combos.add(Incluir);

		Centro = new JPanel(new BorderLayout());
		Centro.add(Combos, BorderLayout.NORTH);
		Centro.add(
				new JScrollPane(Grade, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS),
				BorderLayout.CENTER);

		getContentPane().setLayout(new BorderLayout());
		getContentPane().add(new JScrollPane(Bancos), BorderLayout.WEST);
		getContentPane().add(Centro, BorderLayout.CENTER);

		Dimension dm = Toolkit.getDefaultToolkit().getScreenSize();
		setLocation((dm.width - getWidth()) / 2, (dm.height - getHeight()) / 2);

		Bancos.addListSelectionListener(this);
		Tabs.addItemListener(this);
		Consultar.addActionListener(this);
		Incluir.addActionListener(this);
		Alterar.addActionListener(this);
		Excluir.addActionListener(this);

		try {
			openConnection();
			DatabaseMetaData dbmd = vinculo.getConexao().getMetaData();
			ResultSet bancos = dbmd.getCatalogs();

			DefaultListModel<String> dlm = (DefaultListModel<String>) Bancos.getModel();
			while (bancos.next())
				dlm.addElement(bancos.getString("TABLE_CAT"));
		} catch (Exception ex) {
			showMessageDialog(this, "Explorer Error: " + ex.getMessage(), "Erro", ERROR_MESSAGE);
		}
	}

	public void openConnection() {
		try {
			this.vinculo = new Vinculo();
			showMessageDialog(null, "Vinculo com Banco de Dados Estabelecida!");

		} catch (Exception ex) {
			showMessageDialog(null, ex.getMessage(), "Erro", ERROR_MESSAGE);

		}

	}

	public void ConsultadoBD() {
		new Consulta(this.vinculo).setVisible(true);
	}

	// Sempre que um novo bando de dados for selecionado
	public void valueChanged(ListSelectionEvent e) {
		try {
			String banco = Bancos.getSelectedValue().toString();
			openConnection();
			DatabaseMetaData dbmd = vinculo.getConexao().getMetaData();
			ResultSet rs_tabelas = dbmd.getTables(banco, null, null, null);

			DefaultComboBoxModel<String> dcm = (DefaultComboBoxModel<String>) Tabs.getModel();
			dcm.removeAllElements();
			while (rs_tabelas.next())
				dcm.addElement(rs_tabelas.getString("TABLE_NAME"));
		} catch (Exception ex) {
			showMessageDialog(this, "valueChanged Error: " + ex.getMessage(), "Erro", ERROR_MESSAGE);
		}
	}

	// Sempre que uma tablea for selecionada
	public void itemStateChanged(ItemEvent e) {
		if (Tabs.getSelectedIndex() == -1)
			return;

		String banco = Bancos.getSelectedValue().toString();
		String tabela = Tabs.getSelectedItem().toString();

		try {
			DatabaseMetaData dbmd = vinculo.getConexao().getMetaData();
			ResultSet rs_campos = dbmd.getColumns(banco, null, tabela, null);
			DefaultComboBoxModel<String> dcm = (DefaultComboBoxModel<String>) Cpos.getModel();
			dcm.removeAllElements();
			while (rs_campos.next())
				dcm.addElement(rs_campos.getString("COLUMN_NAME"));
		} catch (Exception ex) {
			showMessageDialog(this, "itemStateChanged Error: " + ex.getMessage(), "Erro", ERROR_MESSAGE);
		}
	}

	public void actionPerformed(ActionEvent ae) {
		try {
			if (ae.getSource() == this.Consultar) {
				Statement stm = vinculo.getConexao().createStatement();
				ResultSet rs = stm.executeQuery(
						"SELECT * FROM " + Tabs.getSelectedItem() + " ORDER BY " + Cpos.getSelectedItem());
				Grade.setModel(new Grade(rs, null));
			}
			if (ae.getSource() == this.Incluir) {
				new Inclui(vinculo).setVisible(true);
			}

			if (ae.getSource() == this.Excluir) {
				new Exclui(this.vinculo).setVisible(true);
			}

			if (ae.getSource() == this.Alterar) {
				new Altera(vinculo).setVisible(true);
			}

		} catch (Exception ex) {
			showMessageDialog(this, "actionPerformed Error: " + ex.getMessage(), "Erro", ERROR_MESSAGE);
		}
	}

}
