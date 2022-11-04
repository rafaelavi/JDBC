package br.com.alura.jdbc.insercao;

import br.com.alura.jdbc.factory.ConnectionFactory;
import br.com.alura.jdbc.dao.ProdutoDao;
import br.com.alura.jdbc.modelo.Produto;

import java.sql.*;
import java.util.List;

public class TestaInsercaoEListagemComProduto {
    public static void main(String[] args) throws SQLException {

        Produto comoda = new Produto("Cômoda", "Cômoda Vertical");

        try(Connection connection = new ConnectionFactory().recuperarConexao()){
            ProdutoDao produtoDao = new ProdutoDao(connection);
            produtoDao.salvar(comoda);
            List<Produto> listaDeProdutos = produtoDao.listar();
            listaDeProdutos.stream().forEach(System.out::println);
        }
    }
}
