/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TextEditor;

/**
 *
 * @author Hemre
 */
public class KelimeIslem {
    private KelimeIslemDurumu kelimeIslemDurumu;

    public KelimeIslem() {
    }

    public KelimeIslem(KelimeIslemDurumu kelimeIslemDurumu) {
        this.kelimeIslemDurumu = kelimeIslemDurumu;
    }
    
    
    public void islemiUygula(){
        kelimeIslemDurumu.islemiUygula();
    }
    
    public void durumuDegistir(final KelimeIslemDurumu kelimeIslemDurumu){
        this.kelimeIslemDurumu = kelimeIslemDurumu;
    }
}
