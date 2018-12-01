/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.agenda.daoimpl;

import br.com.agenda.bens.Contato;
import br.com.agenda.bens.Telefone;
import br.com.agenda.bens.TipoContato;
import br.com.agenda.dao.ContatoDao;
import br.com.agenda.dao.TelefoneDao;
import br.com.agenda.dao.TipoContatoDao;
import com.mysql.jdbc.Statement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Alunos
 */
public class ContatoDaoImpl implements ContatoDao {

    private Connection con;
    private PreparedStatement statement;
    private ResultSet rs;
    private Contato contato;
    private TelefoneDao telefoneDao;
    private TipoContatoDao tipoContatoDao;

    public ContatoDaoImpl() {
        this.telefoneDao = new TelefoneDaoImpl();
        this.tipoContatoDao = new TipoContatoDaoImpl();
    }

    @Override
    public boolean inserir(Object objeto) throws Exception {
        contato = (Contato) objeto;
        String sql = "Insert Into contato (nome, dataNascimento, email, idTipoContato) values(?,?,?,?)";
        try {
            con = SessionFactory.getConnection();
            statement = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, contato.getNome());
            statement.setDate(2, new Date(contato.getDataNascimento().getTime()));
            statement.setString(3, contato.getEmail());
            statement.setInt(4, contato.getTipoContato().getId());
            statement.executeUpdate();
            rs = statement.getGeneratedKeys();
            if (rs.next()) {
                int idInserido = rs.getInt(1);
                contato.setId(idInserido);
                gravarTelefones(contato);
                return true;
            }

        } catch (Exception e) {
            System.out.println("Erro ao inserir Contato " + e.getMessage());
            e.printStackTrace();
        } finally {
            SessionFactory.closeConnection(con, statement, rs);
        }
        return false;
    }

    @Override
    public boolean update(Object objeto) throws Exception {
        String sql = "update contato set nome = ?, dataNascimento = ?,"
                + " email = ?, idTipoContato = ? where id = ?";
        contato = (Contato) objeto;
        try {
            con = SessionFactory.getConnection();
            statement = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, contato.getNome());
            statement.setDate(2, new Date(contato.getDataNascimento().getTime()));
            statement.setString(3, contato.getEmail());
            statement.setInt(4, contato.getTipoContato().getId());
            statement.setInt(5, contato.getId());
            int linhasAtualizadas = statement.executeUpdate();
            gravarTelefones(contato);
            return linhasAtualizadas > 0;
        } catch (Exception e) {
            System.out.println("Erro ao alterar " + e.getMessage());
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
                    "select * from contato where id = ? ");
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Contato contato = new Contato();
                contato.setNome(rs.getString("nome"));
                contato.setEmail(rs.getString("email"));
                contato.setDataNascimento(rs.getDate("dataNascimento"));
                TipoContato tipoContato = (TipoContato) tipoContatoDao
                        .pesquisar(rs.getInt("idTipoContato"));
                contato.setTipoContato(tipoContato);
                contato.setId(id);
                contato.setTelefones(telefoneDao.pesquisarTelefoneUsuarios(contato));
                return contato;
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
        List<Object> contatos = new ArrayList<>();
        try {
            con = SessionFactory.getConnection();
            statement = con.prepareStatement(
                    "select * from contato");
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Contato contato = new Contato();
                contato.setNome(rs.getString("nome"));
                contato.setEmail(rs.getString("email"));
                contato.setDataNascimento(rs.getDate("dataNascimento"));
                Integer idTipoContato = rs.getInt("idTipoContato");
                contato.setTipoContato((TipoContato) tipoContatoDao.pesquisar(idTipoContato));
                contato.setId(rs.getInt("id"));
                contato.setTelefones(telefoneDao.pesquisarTelefoneUsuarios(contato));
                contatos.add(contato);
            }
            return contatos;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            SessionFactory.closeConnection(con, statement, rs);
        }
        return contatos;
    }

    @Override
    public boolean excluir(Integer id) throws Exception {
        try {
            con = SessionFactory.getConnection();
            statement = con.prepareStatement(
                    "delete from contato where id = ? ");
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
    public void gravarTelefones(Contato contato) throws Exception {
        telefoneDao.excluirTelefoneUsuarios(contato.getId());
        if (contato.getTelefones() != null && !contato.getTelefones().isEmpty()) {
            for (Telefone telefone : contato.getTelefones()) {
                telefone.setContato(contato);
                telefoneDao.inserir(telefone);
            }
        }
    }

    @Override
    public boolean excluir(String nome) throws Exception {
        try {
            con = SessionFactory.getConnection();
            statement = con.prepareStatement(
                    "delete from contato where nome = ? ");
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

    @Override
    public List<Contato> listarTodos() throws Exception {
        List<Contato> contatos = new ArrayList<>();
        try {
            con = SessionFactory.getConnection();
            statement = con.prepareStatement(
                    "select * from contato");
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Contato contato = new Contato();
                contato.setNome(rs.getString("nome"));
                contato.setEmail(rs.getString("email"));
                contato.setDataNascimento(rs.getDate("dataNascimento"));
                Integer idTipoContato = rs.getInt("idTipoContato");
                contato.setTipoContato((TipoContato) tipoContatoDao.pesquisar(idTipoContato));
                contato.setId(rs.getInt("id"));
                contato.setTelefones(telefoneDao.pesquisarTelefoneUsuarios(contato));
                contatos.add(contato);
            }
            return contatos;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            SessionFactory.closeConnection(con, statement, rs);
        }
        return contatos;
    }

    @Override
    public List<Contato> listarTodos(TipoContato tipo) throws Exception {
        List<Contato> contatos = new ArrayList<>();
        try {
            con = SessionFactory.getConnection();
            statement = con.prepareStatement(
                    "select * from contato where idTipoContato = ?");
            statement.setInt(1, tipo.getId());
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Contato contato = new Contato();
                contato.setNome(rs.getString("nome"));
                contato.setEmail(rs.getString("email"));
                contato.setDataNascimento(rs.getDate("dataNascimento"));
                Integer idTipoContato = rs.getInt("idTipoContato");
                contato.setTipoContato((TipoContato) tipoContatoDao.pesquisar(idTipoContato));
                contato.setId(rs.getInt("id"));
                contato.setTelefones(telefoneDao.pesquisarTelefoneUsuarios(contato));
                contatos.add(contato);
            }
            return contatos;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            SessionFactory.closeConnection(con, statement, rs);
        }
        return contatos;
    }
}
