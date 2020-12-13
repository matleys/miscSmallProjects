/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package basketanalysis;

import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author mathias
 */
public class BasketAnalysis {
  private ArrayList<Basket> baskets; 
  
  public BasketAnalysis(String basketsFileContent){
      if(basketsFileContent == null || basketsFileContent.length() == 0){
          this.baskets = new ArrayList<>();
      }
      else{
      this.baskets = new ArrayList<>();
      Scanner string = new Scanner(basketsFileContent);
      while(string.hasNextLine())
          this.baskets.add(Basket.parseBasketLine(string.nextLine()));
      }
  }
  
  private static boolean isUnique(ArrayList<Item> items, Item item){
      int count = 0;
      for(Item it: items){
          if(item.equals(it))
              count++;
      }
      if(count >= 2)
          return false;
      else
          return true;
  }
  public static Item[] getItemsFromBaskets(Basket[] baskets){
      ArrayList<Item> arrlist = new ArrayList<>(baskets.length);
      for(int i = 0; i < baskets.length; i++){
          for(int a = 0; a < baskets[i].getItems().size(); a++)
              arrlist.add(baskets[i].getItems().get(a));
      }
      for(int i = 0; i < arrlist.size(); i++){
          if(BasketAnalysis.isUnique(arrlist, arrlist.get(i)) == false){
               arrlist.remove(i);
               i = 0;
          }
      }
      Item[] items = new Item[arrlist.size()];
      for(int i = 0; i < items.length; i++){
          items[i] = arrlist.get(i);
      }
      return items;
  }
  
  public Basket[] getClientBaskets(String clientID){
      ArrayList<Basket> arrlist =  new ArrayList<>();
      for(Basket basket: this.baskets){
          if(basket.getClientID().equals(clientID))
              arrlist.add(basket);
      }
      Basket[] bask = new Basket[arrlist.size()];
      for(int i = 0; i < bask.length; i++){
          bask[i] = arrlist.get(i);
      }
      return bask;
  }
  
  public void analyzeClients(String[] clients, int treshold){
      for(String client: clients){
          System.out.println("Analysis Client " + client+ " with threshold "+ treshold+":");
          TransactionAnalysis transactionanalysis = new TransactionAnalysis(
                  this.getClientBaskets(client), BasketAnalysis.getItemsFromBaskets(this.getClientBaskets(client)));
          System.out.println(transactionanalysis.aPrioriAlgorithm(treshold));
      }
  }
  
}
