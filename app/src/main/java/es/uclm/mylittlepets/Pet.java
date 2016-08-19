package es.uclm.mylittlepets;

import android.widget.ImageView;

/**
 * Created by Lenovo on 19/08/2016.
 */
public class Pet {
    //Atributos
    private int  numberOfLines, foto;
    private String petName;

    //Constructor
    public Pet (int numberOfLines, String petName, int foto){
        this.numberOfLines=numberOfLines;
        this.petName=petName;
        this.foto = foto;
    }

    public int getNumberOfLines() {
        return numberOfLines;
    }

    public void setNumberOfLines(int numberOfLines) {
        this.numberOfLines = numberOfLines;
    }

    public String getPetName() {
        return petName;
    }

    public void setPetName(String petName) {
        this.petName = petName;
    }

    public int getFoto(){
        return foto;
    }

    public void setFoto (int foto) {
        this.foto = foto;
    }
}
