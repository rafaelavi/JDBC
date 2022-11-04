package br.com.alura.jdbc.listagem;

import br.com.alura.jdbc.factory.ConnectionFactory;

import java.sql.*;

public class TestaListagem {
    public static void main(String[] args) throws SQLException {
        ConnectionFactory criaConexao = new ConnectionFactory();
        Connection connection = criaConexao.recuperarConexao();

        PreparedStatement stn = connection.prepareStatement("SELECT ID, NOME, DESCRICAO FROM PRODUTO");
        stn.execute();

        ResultSet rst = stn.getResultSet();

        while(rst.next()){
            Integer id = rst.getInt("ID");
            System.out.println(id);
            String nome = rst.getString("NOME");
            System.out.println(nome);
            String descricao = rst.getString("DESCRICAO");
            System.out.println(descricao);
        }

        connection.close();
    }
}
