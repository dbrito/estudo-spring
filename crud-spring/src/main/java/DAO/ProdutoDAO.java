package DAO;

import java.util.Date;
import utils.ConnectionFactory;
import model.Produto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class ProdutoDAO {

    static private Connection con = ConnectionFactory.getConnetion();

    public static void inserir(Produto prd) throws Exception {
        PreparedStatement stmt = con.prepareStatement(
            "INSERT INTO produto"
            + "(nome, descricao, preco_compra, preco_venda, quantidade, disponivel)"
            + "VALUES(?,?,?,?,?,?)");
        stmt.setString(1, prd.getNome());
        stmt.setString(2, prd.getDescricao());
        stmt.setDouble(3, prd.getPreco_compra());
        stmt.setDouble(4, prd.getPreco_venda());
        stmt.setInt(5, prd.getQuantidade());
        stmt.setBoolean(6, prd.getDisponivel());
        stmt.execute();
    }

    public static List<Produto> listar() throws Exception {
        PreparedStatement stmt = con.prepareStatement("SELECT * FROM produto");
        ResultSet rs = stmt.executeQuery();
        List<Produto> produtos = new ArrayList<Produto>();

        while (rs.next()) {
            Produto prd = new Produto();
            prd.setId(rs.getInt("id"));
            prd.setNome(rs.getString("nome"));
            prd.setDescricao(rs.getString("descricao"));
            prd.setPreco_compra(rs.getDouble("preco_compra"));
            prd.setPreco_venda(rs.getDouble("preco_venda"));
            prd.setQuantidade(rs.getInt("quantidade"));
            prd.setDisponivel(rs.getBoolean("disponivel"));
            prd.setDt_cadastro(rs.getDate("dt_cadastro"));
            produtos.add(prd);
        }
        //ConnectionFactory.closeConnection(con, stmt);
        return produtos;
    }

    public static Produto listar(Long id) throws Exception {
        PreparedStatement stmt = con.prepareStatement("SELECT * FROM produto WHERE id=?");
        stmt.setInt(1, id.intValue());
        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
            Produto prd = new Produto();
            prd.setId(rs.getInt("id"));
            prd.setNome(rs.getString("nome"));
            prd.setDescricao(rs.getString("descricao"));
            prd.setPreco_compra(rs.getDouble("preco_compra"));
            prd.setPreco_venda(rs.getDouble("preco_venda"));
            prd.setQuantidade(rs.getInt("quantidade"));
            prd.setDisponivel(rs.getBoolean("disponivel"));
            prd.setDt_cadastro(rs.getDate("dt_cadastro"));
            return prd;
        }
        return null;
    }

    public static void editar(Produto prd) throws Exception {
        PreparedStatement stmt = con.prepareStatement(
            "UPDATE produto SET nome=?, descricao=?, preco_compra=?, preco_venda=?, quantidade=?, disponivel=? WHERE id=?");
        stmt.setString(1, prd.getNome());
        stmt.setString(2, prd.getDescricao());
        stmt.setDouble(3, prd.getPreco_compra());
        stmt.setDouble(4, prd.getPreco_venda());
        stmt.setInt(5, prd.getQuantidade());
        stmt.setBoolean(6, prd.getDisponivel());
        stmt.setInt(7, prd.getId());
        stmt.execute();
    }

    public static void remover(Long id) throws Exception {
        PreparedStatement stmt = con.prepareStatement("DELETE FROM produto WHERE id=?");
        stmt.setInt(1, id.intValue());
        stmt.execute();
    }

}
