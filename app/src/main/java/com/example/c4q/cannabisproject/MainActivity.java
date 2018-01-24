package com.example.c4q.cannabisproject;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.c4q.cannabisproject.network.AllStrainFragment;
import com.example.c4q.cannabisproject.network.StrainNameFragment;

public class MainActivity extends AppCompatActivity {



    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.all_strain:
                    AllStrainFragment allStrainFragment = new AllStrainFragment();
                    FragmentManager fragmentManager = getSupportFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.frag_container, allStrainFragment);
                    fragmentTransaction.addToBackStack("AllstrainFrag");
                    fragmentTransaction.commit();
                     return true;
                case R.id.description:

                    StrainNameFragment strainNameFragment = new StrainNameFragment();
                    getSupportFragmentManager().beginTransaction()
                            .addToBackStack("strain")
                            .replace(R.id.frag_container,strainNameFragment)
                            .commit();

                    return true;
                case R.id.images:

                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);



        StrainNameFragment strainNameFragment = new StrainNameFragment();
        strainNameFragment.setRetrofit();

    }

}
