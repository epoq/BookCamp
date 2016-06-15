/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entites;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author cdi104
 */
public class Panier {
    
    private List<Livre> mesLivres;

    public Panier() {
        mesLivres = new ArrayList<>();
    }

    public List<Livre> getMesLivres() {
        return mesLivres;
    }

    public void setMesLivres(List<Livre> mesLivres) {
        this.mesLivres = mesLivres;
    }
    
    
    
}
