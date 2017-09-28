package principal;

import java.sql.*;
import java.util.*;
import javax.swing.table.*;

public class Grade extends AbstractTableModel {

	private List<String> colunas;
	private List<List<Object>> linhas;

	public Grade() {
		colunas = new ArrayList<String>();
		linhas = new ArrayList<List<Object>>();
	}

	public Grade(String[] titulos) {
		colunas = new ArrayList<String>();

		for (int i = 0; i < titulos.length; i++) {
			colunas.add(titulos[i]);
			linhas = new ArrayList<List<Object>>();

		}
	}

	public Grade(ResultSet rs, String[] titulos) throws SQLException {
		this();

		ResultSetMetaData metadata = rs.getMetaData();
		if (titulos != null) {
			for (int i = 0; i < titulos.length; i++) {
				colunas.add(titulos[i]);
			}

		} else {
			for (int i = 1; i <= metadata.getColumnCount(); i++) {
				colunas.add(metadata.getColumnLabel(i));
			}
		}

		while (rs.next()) {
			ArrayList<Object> linha = new ArrayList<Object>();
			for (int i = 1; i <= metadata.getColumnCount(); i++) {
				linha.add(rs.getObject(i));

			}

			linhas.add(linha);
		}

	}

	public List<List<Object>> getLinhas() {
		return linhas;
	}

	public int getColumnCount() {
		return colunas.size();
	}

	public int getRowCount() {
		return linhas.size();
	}

	public Object getValueAt(int linha, int coluna) {
		return linhas.get(linha).get(coluna);
	}

	public String getColumnName(int indice) {
		return colunas.get(indice);
	}

	public boolean isCellEditable(int linha, int coluna) {
		return false;
	}

	public Class getColumnClass(int coluna) {
		return getValueAt(0, coluna).getClass();
	}

	public void removeRow(int linha) {
		linhas.remove(linha);
	}

	public void insertRow(List<Object> linha) {
		linhas.add(linha);
	}

	public void clearLines() {
		linhas.clear();
	}
}
