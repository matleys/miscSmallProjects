


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

public class Player implements INode, Comparable<Player>, Serializable {
    
    private static final long serialVersionUID = 7;
    private String name;
    private int number;
    
    public Player(String name, int number){
        this.name = name;
        this.number = number;
    }
    
    @Override
    public String getName(){
        return this.name;
    }
    
    @Override
    public int hashCode(){
        return this.name.hashCode();
    }
    
    @Override
    public boolean equals(Object object){
        if(object == null)
            return false;
        if((object instanceof Player) == false)
            return false;
        Player p = (Player) object;
        if(p.getName() == null){
            if(this.name == null)
                return true;
            else
                return false;
        }
        else{
            if(p.getName().equals(this.name))
                return true;
            return false;
        }
        
    }
    
    @Override
    public String toString(){
        String tekst = this.name + " (" + this.number+ ")";
        return tekst;
    }
    
    @Override
    public int compareTo(Player p){
      if(p.number > this.number)
          return -1;
      if(p.number < this.number)
          return 1;
      return 0;
    }
    
}
