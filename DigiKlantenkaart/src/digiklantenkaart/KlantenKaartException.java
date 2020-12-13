/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package digiklantenkaart;

/**
 *
 * @author mathias
 */
public class KlantenKaartException extends Exception {
    public KlantenKaartException(String boodschap){
      super("Fout Klantenkaart: "+boodschap);  
    }
    
}
