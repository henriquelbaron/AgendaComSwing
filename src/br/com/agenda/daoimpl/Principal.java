/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.agenda.daoimpl;

import br.com.agenda.bens.Contato;
import br.com.agenda.view.FormularioContato;
import br.com.agenda.view.FormularioTipoContato;
import br.com.agenda.view.FramePrincipal;
import br.com.agenda.view.GerarRelatorioFrame;
import br.com.agenda.view.ListaContato;
import br.com.agenda.view.OpcaoRelatorio;
import java.awt.Dimension;
import java.awt.FlowLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

/**
 *
 * @author Alunos
 */
public class Principal {

    private static FramePrincipal FRAME;

    public static void main(String[] args) throws Exception {

        FRAME = new FramePrincipal();
        FRAME.setTitle("Agenda Telefonica");
        FRAME.setExtendedState(JFrame.MAXIMIZED_BOTH);
        FRAME.setVisible(true);
        listarContatos();
    }

    public static void listarContatos() {
        ListaContato panel = new ListaContato();
        FRAME.setContentPane(panel);
        FRAME.setExtendedState(JFrame.MAXIMIZED_BOTH);
        FRAME.setVisible(true);
    }

    public static void adicionarNovoContato(Contato c) {
        FormularioContato panel = new FormularioContato(c);
        FRAME.setContentPane(panel);
        FRAME.setSize(450, 600);
        FRAME.setVisible(true);
    }

    public static void adicionarNovoTipoContato() {
        FormularioTipoContato panel = new FormularioTipoContato();
        FRAME.setContentPane(panel);
        FRAME.setSize(450, 300);
        FRAME.setVisible(true);
    }

    public static void menuGerarRelatorio() {
        GerarRelatorioFrame frameRelatorio = new GerarRelatorioFrame();
        OpcaoRelatorio panel = new OpcaoRelatorio();
        frameRelatorio.setContentPane(panel);
        frameRelatorio.setTitle("Relatorio");
        frameRelatorio.pack();
        frameRelatorio.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        frameRelatorio.setVisible(true);
    }

}
