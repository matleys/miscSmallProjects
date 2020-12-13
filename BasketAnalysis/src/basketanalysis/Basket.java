/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package basketanalysis;

import java.util.ArrayList;

/**
 *
 * @author mathias
 */
public class Basket implements Transaction {
   private String clientID, basketID;
   private ArrayList<BasketItem> basketItems;
   
   public Basket(String basketID, String clientID){
       this.clientID = clientID;
       this.basketID = basketID;
       this.basketItems = new ArrayList<>();
   }

    public String getClientID() {
        return clientID;
    }

    public String getBasketID() {
        return basketID;
    }
   
   @Override
   public ArrayList<Item> getItems(){
       ArrayList<Item> list = new ArrayList<>(this.basketItems.size());
       for(BasketItem basitem: this.basketItems){
           list.add(basitem);
       }
       return list;
   }
   
   @Override
   public boolean containsItem(Item item){
       if(item == null)
           return false;
       
       boolean b = false;
       for(BasketItem basitem: this.basketItems){
           if(item.equals(basitem))
               b = true;
       }
       return b;
   }
   
   @Override
   public boolean containsItemSet(Item[] itemSet){
       if(itemSet == null)
           return false;
       if(itemSet.length == 0)
           return false;
       int counter = 0;
       for(Item item: itemSet){
           if(this.containsItem(item))
               counter++;
       }
       if(itemSet.length == counter)
           return true;
       else
           return false;
   }
   
   public static Basket parseBasketLine(String line){
       String[] array = line.split(";");
       Basket klasse = new Basket(array[0], array[1]);
       for(int i = 2; i < array.length; i = i+2){
           BasketItem item = new BasketItem(array[i], Integer.parseInt(array[i+1]));
           klasse.basketItems.add(item);
       }
       return klasse;
   }
   
   
}
