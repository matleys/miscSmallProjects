/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package basketanalysis;

/**
 *
 * @author mathias
 */
public class BasketItem implements Item {
   private String productID;
   private int quantity;
   
   public BasketItem(String productID, int quantity){
       super();
       if(quantity <= 0){
           System.out.println("Ongeldige hoeveelheid!");
           System.exit(0);
       }
       this.productID = productID;
       this.quantity = quantity;
   }

    public int getQuantity() {
        return quantity;
    }
   
   @Override
   public String getItemName(){
       return this.productID;
   }
   
   @Override
   public boolean equals(Object obj){
       boolean b = false;
       if((obj != null) && obj instanceof BasketItem){
           BasketItem bask = (BasketItem) obj;
           b = this.productID.equals(bask.productID);
       }
       return b;
   }
}
