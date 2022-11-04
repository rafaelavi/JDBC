package br.com.alura.jdbc.listagem;

import br.com.alura.jdbc.factory.ConnectionFactory;
import br.com.alura.jdbc.dao.CategoriaDAO;
import br.com.alura.jdbc.modelo.Categoria;
import br.com.alura.jdbc.modelo.Produto;

import java.sql.*;
import java.util.List;

public class TestaListagemDeCategorias {

    public static void main(String[] args) throws SQLException {

        try(Connection connection = new ConnectionFactory().recuperarConexao()) {

            CategoriaDAO categoriaDAO = new CategoriaDAO(connection);
            List<Categoria> listaDeCategoria = categoriaDAO.listarComProdutos();
            listaDeCategoria.forEach(ct -> {
                System.out.println(ct.getNome());
                for(Produto produto : ct.getProdutos()){
                    System.out.println(ct.getNome() + " - " + produto.getNome());
                }
            });
        }
    }
}
