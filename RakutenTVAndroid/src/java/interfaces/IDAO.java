/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import java.util.ArrayList;

/**
 *
 * @author Diego Abad
 */
public interface IDAO <B,I> {
//:3	
    public int add(B bean);
    public int delete(I Integer);
    public ArrayList<B> findAll(B bean);
    public int update(B bean);
	

}
