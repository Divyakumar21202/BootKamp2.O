package com.example.bootkamp2o;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.tabs.TabLayout;

public class HomeFragmentActivity extends AppCompatActivity {

    TabLayout tab;
    ViewPager viewPager;

    Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_fragment);
        tab=findViewById(R.id.tab);
        viewPager=findViewById(R.id.viewPager);
        HomeFragmentViewPagerAdapter adapter=new HomeFragmentViewPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(adapter);
        toolbar=findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);
        toolbar.setTitle("Welcome To BootKamp");

        tab.setupWithViewPager(viewPager);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        new MenuInflater(this).inflate(R.menu.home_fragment_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int itemId= item.getItemId();
        if(itemId==R.id.sign_out){
            Toast.makeText(getApplicationContext(),"Sign Out Button",Toast.LENGTH_SHORT).show();

        }
        else if(itemId==R.id.rate){
            Toast.makeText(getApplicationContext(),"Rate Button",Toast.LENGTH_SHORT).show();

        }
        else if(itemId==R.id.profile){
            Toast.makeText(getApplicationContext(),"Profile Button",Toast.LENGTH_SHORT).show();

        }
        return super.onOptionsItemSelected(item);
    }
}