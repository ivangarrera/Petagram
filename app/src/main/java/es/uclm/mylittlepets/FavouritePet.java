import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import es.uclm.mylittlepets.Adapter.FavouriteAdapter;
import es.uclm.mylittlepets.POJO.Pet;
import es.uclm.mylittlepets.R;

/**
 * Created by Lenovo on 20/08/2016.
 */
public class FavouritePet extends AppCompatActivity {
    private ArrayList<Pet> pets;
    private RecyclerView rvPets2;

    @Override
    protected void onCreate (Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.favourite_pet);

        //Establecemos la Toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.myActionBar);
        setSupportActionBar(toolbar);
        //Para el boton de back
         getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //Trabajamos con el RecyclerView
        rvPets2 = (RecyclerView) findViewById(R.id.rvPets2);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        rvPets2.setLayoutManager(llm);

        pets = new ArrayList<Pet>();

        //Bundle
        Bundle bndl=getIntent().getExtras();
        pets=inicializarMascotas(bndl,pets);

        ordenarMascotas(pets);
        inicializarAdaptador();

    }
    public ArrayList<Pet> ordenarMascotas(ArrayList<Pet> pets){

        Collections.sort(pets, new Comparator() {
            @Override
            public int compare(Object o, Object t1) {
                return 0;
            }
            public int compare(Pet p1, Pet p2) {
                return new Integer(p2.getNumberOfLikes()).compareTo(new Integer(p1.getNumberOfLikes()));
            }

        });
        return pets;
    }
    public ArrayList<Pet> inicializarMascotas (Bundle bndl, ArrayList<Pet> pets){
        String [] nombreMasctoa = bndl.getStringArray(getString(R.string.nombre_mascota));
        int [] numeroLikes = bndl.getIntArray(getString(R.string.numberOfLikes));
        int [] foto = bndl.getIntArray(getString(R.string.foto));
        for (int i = 0; i<nombreMasctoa.length;i++){
            pets.add(new Pet(numeroLikes[i],nombreMasctoa[i],foto[i]));
        }
        return pets;
    }
    public void inicializarAdaptador(){
        FavouriteAdapter fva = new FavouriteAdapter(pets,this);
        rvPets2.setAdapter(fva);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
