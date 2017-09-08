/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.aemcode.farmacia.util;

import javax.ws.rs.ApplicationPath;
import org.glassfish.jersey.server.ResourceConfig;

/**
 *
 * @author Andre
 * http://localhost:8080/nomeprojeto/nomerest
 */

@ApplicationPath("rest")
public class FarmaciaResourceConfig extends ResourceConfig {

    public FarmaciaResourceConfig() {
        packages("br.com.aemcode.farmacia.serices");
    }
    
}
