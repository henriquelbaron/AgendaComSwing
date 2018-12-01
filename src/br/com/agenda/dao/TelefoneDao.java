/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.agenda.dao;

import br.com.agenda.bens.Contato;
import br.com.agenda.bens.Telefone;
import java.util.List;

/**
 *
 * @author Alunos
 */
public interface TelefoneDao extends BaseDao {

    public List<Telefone> pesquisarTelefoneUsuarios(Contato user) throws Exception;

    public boolean excluirTelefoneUsuarios(Integer id) throws Exception;
}
