package es.uclm.mylittlepets;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Lenovo on 19/08/2016.
 */
public class PetAdapter extends RecyclerView.Adapter<PetAdapter.PetViewHolder>{
    private ArrayList<Pet> pets;

    //Constructor
    public PetAdapter(ArrayList<Pet> pets){
        this.pets=pets;
    }
    @Override
    //onCreateViewHolder -> infla el contenido de un nuevo ítem para la lista
    public PetViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //Asociamos el layout cardview_mascotas con un View
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_mascotas,parent,false);
        return new PetViewHolder(v);
    }

    @Override
    //onBindViewHolder -> realiza las modificaciones del contenido de cada ítem.
    // Por esta razón recibe como parámetros el view holder y la posición que ocupa en la fuente de datos.
    public void onBindViewHolder(PetViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return pets.size();
    }

    public static class PetViewHolder extends RecyclerView.ViewHolder{
        //Atributos
        private TextView numberOfLines, petName;
        private ImageButton ibEmptyBone;
        private ImageView ivCardview, ivFullBone;

        //Constructor
        public PetViewHolder(View itemView) {
            super(itemView);
            asociarElementos(itemView);
        }
        public void asociarElementos(View itemView){
            numberOfLines = (TextView) itemView.findViewById(R.id.tvLikesNumber);
            petName =  (TextView) itemView.findViewById(R.id.tvPetName);
            ibEmptyBone = (ImageButton) itemView.findViewById(R.id.ibEmptyBone);
            ivCardview = (ImageView) itemView.findViewById(R.id.ivCardview);
            ivFullBone = (ImageView) itemView.findViewById(R.id.ivFullBone);
        }
    } //Fin clase PetViewHolder
} //Fin clase PetAdapter


