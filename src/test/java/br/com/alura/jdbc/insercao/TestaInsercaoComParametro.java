package br.com.alura.jdbc.insercao;

import br.com.alura.jdbc.factory.ConnectionFactory;

import java.sql.*;

public class TestaInsercaoComParametro {

    public static void main(String[] args) throws SQLException {
        ConnectionFactory factory = new ConnectionFactory();
        try(Connection connection = factory.recuperarConexao()) {

            connection.setAutoCommit(false);

            try (PreparedStatement stn =
                         connection.prepareStatement("INSERT INTO PRODUTO (nome, descricao) VALUES (? , ?)",
                                 Statement.RETURN_GENERATED_KEYS)
            ) {
                adicionarVariavel("SmartTV", "45 polegadas", stn);
                adicionarVariavel("Radio", "Radio de bateria", stn);

                connection.commit();
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("ROLLBACK EXECUTADO!");
                connection.rollback();
            }
        }
    }

    private static void adicionarVariavel(String nome, String descricao, PreparedStatement stn) throws SQLException {
        stn.setString(1,nome);
        stn.setString(2,descricao);

//        if(nome.equals("Radio")){
//            throw new RuntimeException("Não foi possivel adicionar o produto");
//        }

        stn.execute();

        try (ResultSet rst = stn.getGeneratedKeys()){
            while (rst.next()) {
                Integer id = rst.getInt(1);
                System.out.println("O id criado foi " + id);
            }
        }
    }
}
