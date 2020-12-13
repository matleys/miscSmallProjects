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
import java.util.ArrayList;

public interface Transaction {

	public ArrayList<Item> getItems();

	public boolean containsItem(Item item);

	public boolean containsItemSet(Item[] itemSet);
}
