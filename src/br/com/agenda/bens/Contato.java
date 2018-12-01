/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.agenda.bens;

import java.util.Date;
import java.util.List;

/**
 * nome data email tipo de contato e telefones
 *
 * @author Alunos
 */
public class Contato implements Comparable<Contato>{

    private Integer id;
    private String nome;
    private Date dataNascimento;
    private String email;
    private TipoContato tipoContato;
    private List<Telefone> telefones;

    public Contato() {
    }

    public Contato( String nome, Date dataNascimento, String email) {
        this.nome = nome;
        this.dataNascimento = dataNascimento;
        this.email = email;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Date getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public TipoContato getTipoContato() {
        return tipoContato;
    }

    public void setTipoContato(TipoContato tipoContato) {
        this.tipoContato = tipoContato;
    }

    public List<Telefone> getTelefones() {
        return telefones;
    }

    public void setTelefones(List<Telefone> telefones) {
        this.telefones = telefones;
    }


    @Override
    public int compareTo(Contato t) {
        return nome.compareTo(t.getNome());
    }

}
