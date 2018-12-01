/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.agenda.daoimpl;

import br.com.agenda.bens.TipoContato;
import br.com.agenda.dao.TipoContatoDao;
import com.mysql.jdbc.Statement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Alunos
 */
public class TipoContatoDaoImpl implements TipoContatoDao {

    private Connection con;
    private PreparedStatement statement;
    private ResultSet rs;
    private TipoContato tipoContato;

    @Override
    public boolean inserir(Object objeto) throws Exception {
        tipoContato = (TipoContato) objeto;
        String sql = "Insert into tipoContato(tipo) values(?)";
        try {
            con = SessionFactory.getConnection();
            statement = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, tipoContato.getTipo());
            statement.executeUpdate();
            rs = statement.getGeneratedKeys();
            if (rs.next()) {
                int idInserido = rs.getInt(1);
                tipoContato.setId(idInserido);
                return true;
            }
        } catch (Exception e) {
            System.out.println("Falha ao Inserir " + e.getMessage());
        } finally {
            SessionFactory.closeConnection(con, statement, rs);
        }
        return false;
    }

    @Override
    public boolean update(Object objeto) throws Exception {
        tipoContato = (TipoContato) objeto;
        try {
            con = SessionFactory.getConnection();
            statement = con.prepareStatement("update tipoContato set tipo = ? where id = ? ");
            statement.setString(1, tipoContato.getTipo());
            statement.setInt(2, tipoContato.getId());
            int linhasAtualizadas = statement.executeUpdate();
            return linhasAtualizadas > 0;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            SessionFactory.closeConnection(con, statement, rs);
        }
        return false;
    }

    @Override
    public Object pesquisar(Integer id) throws Exception {
        try {
            con = SessionFactory.getConnection();
            statement = con.prepareStatement(
                    "select * from tipoContato where id = ? ");
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                TipoContato tipoContato = new TipoContato();
                tipoContato.setTipo(rs.getString("tipo"));
                tipoContato.setId(id);
                return tipoContato;
            }
            return null;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            SessionFactory.closeConnection(con, statement, rs);
        }
        return null;
    }

    @Override
    public List<Object> pesquisarTodos() throws Exception {
        List<Object> tipoContatos = new ArrayList<>();
        try {
            con = SessionFactory.getConnection();
            statement = con.prepareStatement(
                    "select * from tipoContato");
            rs = statement.executeQuery();
            while (rs.next()) {
                TipoContato tipoContato = new TipoContato();
                tipoContato.setTipo(rs.getString("tipo"));
                tipoContato.setId(rs.getInt("id"));
                tipoContatos.add(tipoContato);
            }
            return tipoContatos;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            SessionFactory.closeConnection(con, statement, rs);
        }
        return tipoContatos;
    }

    @Override
    public boolean excluir(Integer id) throws Exception {
        try {
            con = SessionFactory.getConnection();
            statement = con.prepareStatement(
                    "delete from tipoContato where id = ? ");
            statement.setInt(1, id);
            int executeUpdate = statement.executeUpdate();
            return executeUpdate != 0;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            SessionFactory.closeConnection(con, statement, rs);
        }
        return false;
    }

    @Override
    public boolean excluir(String nome) throws Exception {
        try {
            con = SessionFactory.getConnection();
            statement = con.prepareStatement(
                    "delete from tipoContato where tipo = ? ");
            statement.setString(1, nome);
            int executeUpdate = statement.executeUpdate();
            return executeUpdate != 0;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            SessionFactory.closeConnection(con, statement, rs);
        }
        return false;
    }

}
