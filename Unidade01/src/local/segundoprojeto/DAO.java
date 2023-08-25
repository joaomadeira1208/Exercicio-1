package local.segundoprojeto;

import java.sql.*;

public class DAO {
	private Connection conexao;
	
	public DAO() {
		conexao = null;
	}
	
	public boolean conectar() {
		String driverName = "org.postgresql.Driver";                    
		String serverName = "localhost";
		String mydatabase = "funcionarios";
		int porta = 5432;
		String url = "jdbc:postgresql://" + serverName + ":" + porta +"/" + mydatabase;
		String username = "ti2cc";
		String password = "ti@cc";
		boolean status = false;

		try {
			Class.forName(driverName);
			conexao = DriverManager.getConnection(url, username, password);
			status = (conexao == null);
			System.out.println("Conexão efetuada com o postgres!");
		} catch (ClassNotFoundException e) { 
			System.err.println("Conexão NÃO efetuada com o postgres -- Driver não encontrado -- " + e.getMessage());
		} catch (SQLException e) {
			System.err.println("Conexão NÃO efetuada com o postgres -- " + e.getMessage());
		}

		return status;
	}
	
	public boolean close() {
		boolean status = false;
		
		try {
			conexao.close();
			status = true;
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}
		return status;
	}
	
	public boolean inserirFuncionario(Funcionario funcionario) {
		boolean status = false;
		try {
            Statement st = conexao.createStatement();
            st.executeUpdate("INSERT INTO funcionario (codigo, nome, sobrenome, salario) "
                    + "VALUES (" + funcionario.getCodigo() + ", '" + funcionario.getNome() + "', '"
                    + funcionario.getSobrenome() + "', " + funcionario.getSalario() + ");");
            st.close();
            status = true;
        } catch (SQLException u) {
            throw new RuntimeException(u);
        }
        return status;
	}
	
	public boolean atualizarFuncionario(Funcionario funcionario) {
		boolean status = false;
		try {  
			Statement st = conexao.createStatement();
			String sql = "UPDATE Funcionario SET nome = '" + funcionario.getNome() + "', sobrenome = '"  
				       + funcionario.getSobrenome() + "', salario = '" + funcionario.getSalario() + "'"
					   + " WHERE codigo = " + funcionario.getCodigo();
			st.executeUpdate(sql);
			st.close();
			status = true;
		} catch (SQLException u) {  
			throw new RuntimeException(u);
		}
		return status;
	}
	
	public boolean excluirFuncionario(int codigo) {
		boolean status = false;
		try {  
			Statement st = conexao.createStatement();
			st.executeUpdate("DELETE FROM funcionario WHERE codigo = " + codigo);
			st.close();
			status = true;
		} catch (SQLException u) {  
			throw new RuntimeException(u);
		}
		return status;
	}
	
	
	public Funcionario[] getFuncionario() {
		Funcionario[] funcionarios = null;
		
		try {
			Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			ResultSet rs = st.executeQuery("SELECT * FROM funcionario");		
	         if(rs.next()){
	             rs.last();
	             funcionarios = new Funcionario[rs.getRow()];
	             rs.beforeFirst();

	             for(int i = 0; rs.next(); i++) {
	                funcionarios[i] = new Funcionario(rs.getInt("codigo"), rs.getString("nome"), 
	                		                  rs.getString("sobrenome"), rs.getDouble("Salario"));
	             }
	          }
	          st.close();
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return funcionarios;
	}

	
	
}