import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import es.uclm.mylittlepets.POJO.Pet;
import es.uclm.mylittlepets.R;

/**
 * Created by Lenovo on 20/08/2016.
 */
public class FavouriteAdapter extends RecyclerView.Adapter<FavouriteAdapter.FavouriteViewHolder>{
    ArrayList<Pet> pets;
    Activity activity;

    public FavouriteAdapter(ArrayList<Pet> pets, Activity activity){
        this.pets=pets;
        this.activity = activity;
    }
    @Override
    public FavouriteViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_favourite_pets,parent,false);
        return new FavouriteViewHolder(v);
    }

    @Override
    public void onBindViewHolder(FavouriteViewHolder holder, int position) {
        Pet pet = pets.get(position);
        asignarAtributos(holder,pet);

    }

    @Override
    public int getItemCount() {
        return pets.size();
    }
    public void asignarAtributos(FavouriteViewHolder holder, Pet pet){
        holder.petName.setText(pet.getPetName());
        holder.numberOfLikes.setText(String.valueOf(pet.getNumberOfLikes()));
        //holder.ibStar.setImageResource(R.drawable.star_48);
        holder.ivCardview.setImageResource(pet.getFoto());
        holder.ivFullBone.setImageResource(R.drawable.dog_bone_48_1);
        holder.ivEmptyBone.setImageResource(R.drawable.dog_bone_48);
    }
    public static class FavouriteViewHolder extends RecyclerView.ViewHolder{
        //Atributos
        private TextView numberOfLikes, petName;
       // private ImageButton ibStar;
        private ImageView ivCardview, ivFullBone, ivEmptyBone;

        public FavouriteViewHolder(View itemView) {
            super(itemView);
            asociarElementos(itemView);
        }
        public void asociarElementos(View itemView){
            numberOfLikes = (TextView) itemView.findViewById(R.id.tvLikesNumberFavourite);
            petName = (TextView) itemView.findViewById(R.id.tvPetNameFavourite);
           // ibStar = (ImageButton) itemView.findViewById(R.id.ibStar);
            ivCardview = (ImageView) itemView.findViewById(R.id.ivFavouritePetImage);
            ivFullBone = (ImageView) itemView.findViewById(R.id.ivFullBoneFavourite);
            ivEmptyBone = (ImageView) itemView.findViewById(R.id.ivEmptyBoneFavourite);
        }
    }
}
