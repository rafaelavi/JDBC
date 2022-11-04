package br.com.alura.jdbc.remocao;

import br.com.alura.jdbc.factory.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class TestaRemocao {

    public static void main(String[] args) throws SQLException {
        ConnectionFactory factory = new ConnectionFactory();
        Connection connection = factory.recuperarConexao();

        PreparedStatement stn = connection.prepareStatement("DELETE FROM PRODUTO WHERE ID > ?");
        stn.setInt(1,2);
        stn.execute();

        Integer linhasModificadas = stn.getUpdateCount();
        System.out.println("Quantidade de linhas que foram modificadas " +
                linhasModificadas);
    }
}
