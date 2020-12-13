


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author mathias
 */

import java.io.Serializable;

public class Pass implements IEdge, Serializable {
    private static final long serialVersionUID = 12;
    private Player sender, receiver;
    private int nrOfTimes;
    
    public Pass(Player sender, Player receiver, int nrOfTimes){
        this.sender= sender;
        this.receiver = receiver;
        this.nrOfTimes = nrOfTimes;
    }
   
    @Override
    public int getWeight(){
        return this.nrOfTimes;
    }
    
    @Override
    public INode getStart(){
        return this.sender;
    }
    
    @Override
    public INode getEnd(){
        return this.receiver;
    }
    
    @Override
    public int hashCode(){
        return this.sender.hashCode() + this.receiver.hashCode();
    }
    
    @Override
    public boolean equals(Object object){
        boolean samerec = false;
        boolean samesend = false;
        if(object == null)
            return false;
        if((object instanceof Pass) == false)
            return false;
        Pass p = (Pass) object;
        try{
        if(p.receiver.equals(this.receiver))
            samerec = true;
        }
        catch(Exception e){
            if(this.receiver == null)
                samerec = true;
        }
        try{
        if(p.sender.equals(this.sender))
            samesend = true;
        }
        catch(Exception e){
            if(this.sender == null)
                samesend = true;
        }
        
        return (samerec && samesend);
    }
    
    @Override
    public String toString(){
        String tekst = "Pass from " + this.sender.getName() + " to "+ this.receiver.getName();
        return tekst;
    }
    
    
}
