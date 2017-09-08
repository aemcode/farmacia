/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.aemcode.farmacia.models;

import javax.persistence.Transient;

public class GenericModelImpl extends GenericModel {
    
    @Transient
    public String getAtivoFormatado() {

        String ativoFormatado = "Não";

        if (getAtivo()) {
            ativoFormatado = "Sim";
        }

        return ativoFormatado;
    }

    
}
