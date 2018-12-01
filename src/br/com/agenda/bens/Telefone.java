/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.agenda.bens;

/**
 *
 * @author Alunos
 */
public class Telefone {

    private Integer id;
    private String ddd;
    private String numero;
    private String tipo;
    private Contato contato;

    public Telefone() {
    }

    public Telefone(String DDD, String numero, String tipo) {
        this.ddd = DDD;
        this.numero = numero;
        this.tipo = tipo;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDDD() {
        return ddd;
    }

    public void setDDD(String DDD) {
        this.ddd = DDD;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public Contato getContato() {
        return contato;
    }

    public void setContato(Contato contato) {
        this.contato = contato;
    }

    @Override
    public String toString() {
        return ddd + "-" + numero;
    }

}
