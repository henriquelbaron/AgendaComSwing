/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.agenda.daoimpl;

import br.com.agenda.bens.Contato;
import br.com.agenda.bens.Telefone;
import br.com.agenda.dao.ContatoDao;
import br.com.agenda.dao.TelefoneDao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Alunos
 */
public class TelefoneDaoImpl implements TelefoneDao {

    private Connection con;
    private PreparedStatement statement;
    private ResultSet rs;
    private Telefone telefone;

    @Override
    public boolean inserir(Object objeto) throws Exception {
        telefone = (Telefone) objeto;
        String sql = "INSERT INTO telefone (ddd,numero,tipo,idContato ) VALUES (?,?,?,?)";
        try {
            con = SessionFactory.getConnection();
            statement = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, telefone.getDDD());
            statement.setString(2, telefone.getNumero());
            statement.setString(3, telefone.getTipo());
            statement.setInt(4, telefone.getContato().getId());
            statement.executeUpdate();
            rs = statement.getGeneratedKeys();
            if (rs.next()) {
                Integer idInserido = rs.getInt(1);
                telefone.setId(idInserido);
            }
        } catch (Exception e) {
            System.out.println("Falha ao Inserir Telefone " + e.getMessage());
        } finally {
            SessionFactory.closeConnection(con, statement, rs);
        }
        return false;
    }

    @Override
    public boolean update(Object objeto) throws Exception {
        Telefone telefone = (Telefone) objeto;
        String sql = "update telefone set ddd = ?, numero = ?, tipo = ? , idContato= ? where id = ?";
        try {
            con = SessionFactory.getConnection();
            statement = con.prepareStatement(sql);
            statement.setString(1, telefone.getNumero());
            statement.setString(2, telefone.getTipo());
            statement.setString(3, telefone.getDDD());
            statement.setInt(4, telefone.getContato().getId());
            statement.setInt(5, telefone.getId());
            int linhasAtualizadas = statement.executeUpdate();
            return linhasAtualizadas > 0;
        } catch (Exception e) {
            System.out.println("Falha ao Alterar Telefone " + e.getMessage());
            e.printStackTrace();
        } finally {
            SessionFactory.closeConnection(con, statement, rs);
        }
        return false;
    }

    @Override
    public Object pesquisar(Integer id) throws Exception {
        ContatoDao contatoDao = new ContatoDaoImpl();
        try {
            con = SessionFactory.getConnection();
            statement = con.prepareStatement(
                    "select * from telefone where id = ? ");
            statement.setInt(1, id);
            rs = statement.executeQuery();
            while (rs.next()) {
                Telefone telefone = new Telefone();
                telefone.setNumero(rs.getString("numero"));
                telefone.setTipo(rs.getString("tipo"));
                telefone.setId(rs.getInt("id"));
                telefone.setDDD(rs.getString("ddd"));
                telefone.setContato((Contato) contatoDao.pesquisar(rs.getInt("idContato")));
                return telefone;
            }
            return null;
        } catch (Exception e) {
            System.out.println("Falha ao Pesquisar Telefone " + e.getMessage());
            e.printStackTrace();
        } finally {
            SessionFactory.closeConnection(con, statement, rs);
        }
        return null;
    }

    @Override
    public List<Object> pesquisarTodos() throws Exception {
        ContatoDao contatoDao = new ContatoDaoImpl();
        List<Object> telefones = new ArrayList<>();
        try {
            con = SessionFactory.getConnection();
            statement = con.prepareStatement("select * from telefone");
            rs = statement.executeQuery();
            while (rs.next()) {
                Telefone telefone = new Telefone();
                telefone.setNumero(rs.getString("numero"));
                telefone.setTipo(rs.getString("tipo"));
                telefone.setId(rs.getInt("id"));
                telefone.setDDD(rs.getString("ddd"));
                telefone.setContato((Contato) contatoDao.pesquisar(rs.getInt("idContato")));
                telefones.add(telefone);
            }
            return null;
        } catch (Exception e) {
            System.out.println("Falha ao Pesquisar por Todos Telefone " + e.getMessage());
            e.printStackTrace();
        } finally {
            SessionFactory.closeConnection(con, statement, rs);
        }
        return telefones;
    }

    @Override
    public boolean excluir(Integer id) throws Exception {
        try {
            con = SessionFactory.getConnection();
            statement = con.prepareStatement("delete from telefone where id = ? ");
            statement.setInt(1, id);
            int executeUpdate = statement.executeUpdate();
            return executeUpdate != 0;
        } catch (Exception e) {
            System.out.println("Falha ao Excluir Telefone " + e.getMessage());
            e.printStackTrace();
        } finally {
            SessionFactory.closeConnection(con, statement, rs);
        }
        return false;
    }

    @Override
    public List<Telefone> pesquisarTelefoneUsuarios(Contato user) throws Exception {
        List<Telefone> telefones = new ArrayList<>();
        String sql = "select * from telefone where idContato= ?";
        try {
            con = SessionFactory.getConnection();
            statement = con.prepareStatement(sql);
            statement.setInt(1, user.getId());
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Telefone telefone = new Telefone();
                telefone.setNumero(rs.getString("numero"));
                telefone.setTipo(rs.getString("tipo"));
                telefone.setId(rs.getInt("id"));
                telefone.setDDD(rs.getString("ddd"));
                telefone.setContato(user);
                telefones.add(telefone);
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Falha ao Pesquisar Telefone " + e.getMessage());
        } finally {
            SessionFactory.closeConnection(con, statement, rs);
        }
        return telefones;
    }

    @Override
    public boolean excluirTelefoneUsuarios(Integer id) throws Exception {
        try {
            con = SessionFactory.getConnection();
            statement = con.prepareStatement(
                    "delete from telefone where idContato = ? ");
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
                    "delete from telefone where numero = ? ");
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
