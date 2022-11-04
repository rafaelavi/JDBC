package br.com.alura.jdbc.dao;

import br.com.alura.jdbc.modelo.Categoria;
import br.com.alura.jdbc.modelo.Produto;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProdutoDao {

    private Connection connection;

    public ProdutoDao(Connection connection) {
        this.connection = connection;
    }

    public void salvar(Produto produto) throws SQLException {
        String sql = "INSERT INTO PRODUTO (NOME, DESCRICAO) VALUES(?, ?)";

        try(PreparedStatement pstn = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){

            pstn.setString(1, produto.getNome());
            pstn.setString(2, produto.getDescricao());

            pstn.execute();

            try(ResultSet rst = pstn.getGeneratedKeys()){
                while(rst.next()){
                    produto.setId(rst.getInt(1));
                }
            }
        }
    }

    public List<Produto> listar() throws SQLException {
        List<Produto> produtos = new ArrayList<>();

        String sql = "SELECT ID, NOME, DESCRICAO FROM PRODUTO";

        try(PreparedStatement pstn = connection.prepareStatement(sql)){
            pstn.execute();

            try(ResultSet rst = pstn.getResultSet()){
                while(rst.next()){
                    Produto produto =
                            new Produto(rst.getInt(1), rst.getString(2), rst.getString(3));
                    produtos.add(produto);
                }
            }
        }
        return produtos;
    }

    public List<Produto> buscar(Categoria ct) throws SQLException {
        List<Produto> produtos = new ArrayList<>();

        System.out.println("executando a query de buscar produto por categoria");

        String sql = "SELECT ID, NOME, DESCRICAO FROM PRODUTO WHERE CATEGORIA_ID = ?";

        try(PreparedStatement pstn = connection.prepareStatement(sql)){
            pstn.setInt(1, ct.getId());
            pstn.execute();

            try(ResultSet rst = pstn.getResultSet()){
                while(rst.next()){
                    Produto produto =
                            new Produto(rst.getInt(1), rst.getString(2), rst.getString(3));
                    produtos.add(produto);
                }
            }
        }
        return produtos;
    }
}
