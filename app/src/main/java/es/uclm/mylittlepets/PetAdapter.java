package es.uclm.mylittlepets;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import es.uclm.mylittlepets.Layout.FavouritePet;
import es.uclm.mylittlepets.Layout.MainActivity;
import es.uclm.mylittlepets.POJO.Pet;
import es.uclm.mylittlepets.R;

/**
 * Created by Lenovo on 19/08/2016.
 */
public class PetAdapter extends RecyclerView.Adapter<PetAdapter.PetViewHolder>{
    private ArrayList<Pet> pets;
    private Activity activity;

    //Constructor
    public PetAdapter(ArrayList<Pet> pets, Activity activity){
        this.pets=pets;
        this.activity=activity;
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
    public void onBindViewHolder(final PetViewHolder holder, int position) {
        final Pet pet = pets.get(position);

        // Asignamos a los atributos estáticos anteriores las correspondientes imagenes, etc...
        holder.petName.setText(pet.getPetName());
        holder.numberOfLikes.setText(String.valueOf(pet.getNumberOfLikes()));
        holder.ivCardview.setImageResource(pet.getFoto());
        holder.ibEmptyBone.setImageResource(R.drawable.dog_bone_48);
        holder.ivFullBone.setImageResource(R.drawable.dog_bone_48_1);

        //Listener a EmptyBone para sumar el numero de Likes.
        holder.ibEmptyBone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pet.setNumberOfLikes(pet.getNumberOfLikes()+1);
                holder.numberOfLikes.setText(String.valueOf(pet.getNumberOfLikes()));
            }
        });

    }


    @Override
    public int getItemCount() {
        return pets.size();
    }

    public static class PetViewHolder extends RecyclerView.ViewHolder{
        //Atributos
        private TextView numberOfLikes, petName;
        private ImageButton ibEmptyBone;
        private ImageView ivCardview, ivFullBone;

        //Constructor
        public PetViewHolder(View itemView) {
            super(itemView);
            asociarElementos(itemView);
        }
        public void asociarElementos(View itemView){
            numberOfLikes = (TextView) itemView.findViewById(R.id.tvLikesNumber);
            petName =  (TextView) itemView.findViewById(R.id.tvPetName);
            ibEmptyBone = (ImageButton) itemView.findViewById(R.id.ibEmptyBone);
            ivCardview = (ImageView) itemView.findViewById(R.id.ivCardview);
            ivFullBone = (ImageView) itemView.findViewById(R.id.ivFullBone);
        }
    } //Fin clase PetViewHolder
} //Fin clase PetAdapter
