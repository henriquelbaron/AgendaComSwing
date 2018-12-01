/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.poo.daoimpl;

import br.com.agenda.daoimpl.ContatoDaoImpl;
import br.com.agenda.bens.Contato;
import java.util.List;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Alunos
 */
public class ContatoDaoImplTest {
    
    public ContatoDaoImplTest() {
    }
    
   
    @Test
    public void testInserir() throws Exception {
        System.out.println("inserir");
        Object objeto = null;
        ContatoDaoImpl instance = new ContatoDaoImpl();
        boolean expResult = false;
        boolean result = instance.inserir(objeto);
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    @Test
    public void testUpdate() throws Exception {
        System.out.println("update");
        Object objeto = null;
        ContatoDaoImpl instance = new ContatoDaoImpl();
        boolean expResult = false;
        boolean result = instance.update(objeto);
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    @Test
    public void testPesquisar() throws Exception {
        System.out.println("pesquisar");
        Integer id = null;
        ContatoDaoImpl instance = new ContatoDaoImpl();
        Object expResult = null;
        Object result = instance.pesquisar(id);
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    @Test
    public void testPesquisarTodos() throws Exception {
        System.out.println("pesquisarTodos");
        ContatoDaoImpl instance = new ContatoDaoImpl();
        List<Object> expResult = null;
        List<Object> result = instance.pesquisarTodos();
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    @Test
    public void testExcluir() throws Exception {
        System.out.println("excluir");
        Integer id = null;
        ContatoDaoImpl instance = new ContatoDaoImpl();
        boolean expResult = false;
        boolean result = instance.excluir(id);
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    @Test
    public void testGravarTelefones() throws Exception {
        System.out.println("gravarTelefones");
        Contato contato = null;
        ContatoDaoImpl instance = new ContatoDaoImpl();
        instance.gravarTelefones(contato);
        fail("The test case is a prototype.");
    }
    
}
