/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author mathias
 */
public class GraphQueryException extends Exception{
    public GraphQueryException(Object o){
        super("'"+o.toString()+"' bestaat niet!");
    }
}
