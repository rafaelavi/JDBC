package br.com.alura.jdbc.insercao;

import br.com.alura.jdbc.factory.ConnectionFactory;

import java.sql.*;

public class TestaInsercao {

    public static void main(String[] args) throws SQLException {
        String nome = "Mouse";
        String descricao = "Mouse sem fio";

        ConnectionFactory factory = new ConnectionFactory();
        Connection connection = factory.recuperarConexao();

        PreparedStatement stn = connection.prepareStatement("INSERT INTO PRODUTO (nome, descricao) VALUES (?, ?)",
                Statement.RETURN_GENERATED_KEYS);
        stn.execute();

        ResultSet rst = stn.getGeneratedKeys();
        while(rst.next()){
            Integer id = rst.getInt(1);
            System.out.println("O id criado foi " + id);
        }

        connection.close();
    }
}
