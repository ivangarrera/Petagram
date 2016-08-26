import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.util.Log;
import android.widget.ImageButton;

import java.util.ArrayList;

import es.uclm.mylittlepets.Adapter.PetAdapter;
import es.uclm.mylittlepets.Layout.MainActivity;
import es.uclm.mylittlepets.POJO.Pet;
import es.uclm.mylittlepets.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class PetsFragment extends Fragment {
    private RecyclerView recyclerView;
    private ArrayList<Pet> pets;

    public PetsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_pets, container,false);

        //Trabajamos con el RecyclerView y hacemos que se comporte como un LinearLayout
        recyclerView = (RecyclerView) view.findViewById(R.id.rvPets);
        pets = new ArrayList<>();
        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(llm);
        inicializarAdaptador();
        inicializarMascotas();

        //Intent
        Intent intent = new Intent(getContext(), MainActivity.class);
        String [] nombre = new String [pets.size()];
        int []numeroLikes = new int[pets.size()];
        int []foto = new int [pets.size()];
        for (int i = 0;i<pets.size();i++){
            nombre [i] = pets.get(i).getPetName();
            numeroLikes[i]=pets.get(i).getNumberOfLikes();
            foto[i] = pets.get(i).getFoto();
        }
        intent.putExtra(getString(R.string.nombre_mascota),nombre);
        intent.putExtra(getResources().getString(R.string.numberOfLikes),numeroLikes);
        intent.putExtra(getResources().getString(R.string.foto),foto);
        startActivity(intent);

        return view;

    }

    public void inicializarAdaptador(){
        PetAdapter petAdapter = new PetAdapter(pets, getActivity());
        recyclerView.setAdapter(petAdapter);
    }
    public void inicializarMascotas(){
        pets.add(new Pet(0,"Nela",R.drawable.perro));
        pets.add(new Pet(0,"Burrito",R.drawable.burro));
        pets.add(new Pet(0,"Kraby", R.drawable.cangrejo));
        pets.add(new Pet(0,"Gallina",R.drawable.gallo));
        pets.add(new Pet(0,"Pulpi",R.drawable.pulpo));
    }

}
