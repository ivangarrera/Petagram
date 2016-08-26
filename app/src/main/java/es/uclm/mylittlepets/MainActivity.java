package es.uclm.mylittlepets;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;

import es.uclm.mylittlepets.Adapter.PageAdapter;
import es.uclm.mylittlepets.Fragment.PetDetailFragment;
import es.uclm.mylittlepets.Fragment.PetsFragment;
import es.uclm.mylittlepets.POJO.Pet;
import es.uclm.mylittlepets.R;

public class MainActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private ViewPager viewPager;
    private TabLayout tabLayout;
    private ArrayList<Pet> pets;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Establecemos la toolbar
        Toolbar actionbar = (Toolbar) findViewById(R.id.myActionBar);
        setSupportActionBar(actionbar);
        asociarViews();
        setUpViewPager();
        if (toolbar!=null){
            setSupportActionBar(toolbar);
        }

        //Cambiamos la fuente a Petagram
        TextView tvPetagram = (TextView) findViewById(R.id.tvPetagram);
        /*String font_path ="font/RobinSchooler.ttf";
        Typeface TF = new Typeface.createFromAsset(getApplicationContext().getAssets(),font_path);
        tvPetagram.setTypeface(TF);*/
        pets = new ArrayList<>();
        ImageButton ibStar;

        //Bundle para inicializar pets
        Bundle bundle = getIntent().getExtras();
        String [] nombres = bundle.getStringArray(getResources().getString(R.string.nombre_mascota));
        int [] nLikes = bundle.getIntArray(getResources().getString(R.string.numberOfLikes));
        int [] foto  = bundle.getIntArray(getResources().getString(R.string.foto));
        for (int i = 0; i<nombres.length;i++){
            pets.add(new Pet(nLikes[i],nombres[i],foto[i]));
        }


        // Listener del button
        ibStar = (ImageButton) findViewById(R.id.ibStar);
        ibStar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intn = new Intent(MainActivity.this,FavouritePet.class);
                String [] nombres = new String [pets.size()];
                int [] nLikes = new int [pets.size()];
                int [] foto = new int [pets.size()];

                for (int i= 0; i<pets.size();i++){
                    nombres [i] = pets.get(i).getPetName();
                    nLikes [i] = pets.get(i).getNumberOfLikes();
                    foto [i] = pets.get(i).getFoto();
                }

                intn.putExtra(getString(R.string.nombre_mascota), nombres);
                intn.putExtra(getString(R.string.numberOfLikes), nLikes);
                intn.putExtra(getString(R.string.foto), foto);
                startActivity(intn);
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.settings,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.itmContacto:
                Intent intn = new Intent(MainActivity.this, Contacto.class);
                startActivity(intn);
                break;
            case R.id.itmAbout:
                Intent intent = new Intent(MainActivity.this, About.class);
                startActivity(intent);
                break;
        }
        return super.onOptionsItemSelected(item);
    }
    public void asociarViews(){
        toolbar = (Toolbar) findViewById(R.id.Toolbar_Fragment);
        viewPager= (ViewPager)findViewById(R.id.vpViewPager);
        tabLayout = (TabLayout) findViewById(R.id.tabTabLayout);
    }
    private ArrayList<Fragment> addFragments(){
        ArrayList<Fragment> fragments = new ArrayList<>();
        fragments.add(new PetsFragment());
        fragments.add(new PetDetailFragment());
        return fragments;
    }
    private void setUpViewPager(){
        viewPager.setAdapter(new PageAdapter(getSupportFragmentManager(),addFragments()));
        tabLayout.setupWithViewPager(viewPager);
        try {
            tabLayout.getTabAt(0).setIcon(R.mipmap.ic_home);
            tabLayout.getTabAt(1).setIcon(R.mipmap.ic_dog);
        }catch (java.lang.NullPointerException ex){
            ex.getMessage();
        }
    }

}

