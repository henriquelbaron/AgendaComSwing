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
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author ACER
 */
public class GerarRelatorio {

    private static ContatoDao contatoDao;
    private static Contato contato;
    private static SimpleDateFormat DF;

    public static void main(String[] args) {
        contatoDao = new ContatoDaoImpl();
        DF = new SimpleDateFormat("dd/MM/yyyy");
        TipoContato tipo = new TipoContato(3);
        tipo.setTipo("Todos");
        gerarRelatorio("relatorios", tipo);
    }

    public static boolean gerarRelatorio(String destino, TipoContato tipo) {
        contatoDao = new ContatoDaoImpl();
        DF = new SimpleDateFormat("dd/MM/yyyy");
        List<Contato> contatos;
        try {
            if (tipo.getTipo().equalsIgnoreCase("Todos")) {
                contatos = contatoDao.listarTodos();
            } else {
                contatos = contatoDao.listarTodos(tipo);
            }
            Collections.sort(contatos);
            String cabecalho = "Id;Nome;Aniversario;Email;Telefones;Tipo";
            escreverArquivoConcatenando(destino + ".csv", cabecalho);
            for (Contato c : contatos) {
                List<Telefone> telefones = c.getTelefones();
                String telefoneString = "";
                for (Telefone telefone : telefones) {
                    telefoneString += telefone + ", ";
                }
                String linha = c.getId() + ";" + c.getNome() + ";"
                        + DF.format(c.getDataNascimento()).toString() + ";"
                        + c.getEmail() + "; " + telefoneString + ";" + c.getTipoContato().getTipo();
                escreverArquivoConcatenando(destino + ".csv", linha);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            e.getMessage();
        }
        return false;
    }

    public static void escreverArquivo(String nmArquivo, String conteudo) throws Exception {
        FileWriter escritor = new FileWriter(nmArquivo);
        BufferedWriter bf = new BufferedWriter(escritor);
        bf.append(conteudo);
        bf.close();
    }

    public static void escreverArquivoConcatenando(String nmArquivo, String conteudo) throws Exception {
        FileWriter escritor = new FileWriter(nmArquivo, true);
        BufferedWriter bf = new BufferedWriter(escritor);
        bf.append(conteudo + "\n");
        bf.close();
    }

}
