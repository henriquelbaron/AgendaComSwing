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
public class TipoContato {

    private Integer id;
    private String tipo;

    public TipoContato(Integer id) {
        this.id = id;
    }

    public TipoContato(String tipo) {
        this.tipo = tipo;
    }

    public TipoContato() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    @Override
    public String toString() {
        return tipo;
    }

}
