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
public interface IWinkelApp {
	public int laadKlanten(String klantenBestand) throws KlantenKaartException;
	public void laadWinkel(String winkelBestand)throws KlantenKaartException;
	public int getAantalPunten() throws KlantenKaartException;
	public String aanmeldenKlant(String id) throws KlantenKaartException;
	public String registrerenTransactie(double bedrag) throws KlantenKaartException;
	public String registrerenBeloning(int aantalPunten) throws KlantenKaartException;
	public String getEmailAdressenKlanten();
	public int bewaarKlanten(String klantenBestand) throws KlantenKaartException;
	public void bewaarWinkel(String winkelBestand) throws KlantenKaartException;
	public String printOverzichtGespaardePunten(String klantID) throws KlantenKaartException;
}
