/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author mathias
 */
public class GraphAdditionException extends Exception {
    public GraphAdditionException(INode n){
        super("De node '"+n.toString()+"' bestaat al!");
    }
    public GraphAdditionException(IEdge e){
        super("De edge '"+e.toString()+"' bestaat al!");
    }
}
