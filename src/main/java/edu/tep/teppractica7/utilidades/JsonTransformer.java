/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.tep.teppractica7.utilidades;

import com.google.gson.Gson;
import spark.ResponseTransformer;

/**
 *
 * @author vacax
 */
public class JsonTransformer implements ResponseTransformer {

    private Gson gson = new Gson();
    
    @Override
    public String render(Object model) throws Exception {
        return gson.toJson(model);
    }
    
}
