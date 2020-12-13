/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author mathias
 */
public interface IEdge {
	public int getWeight();
	public INode getStart();
	public INode getEnd();
	@Override
	public int hashCode();
	@Override
	public boolean equals(Object object);
	@Override
	public String toString();
}
