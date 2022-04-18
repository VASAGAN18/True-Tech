package com.example.splashscreen;

import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.RatingBar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.splashscreen.HelperClass.HomeAdapter.ArrivalAdapter;
import com.example.splashscreen.HelperClass.HomeAdapter.ArrivalHelperClass;
import com.example.splashscreen.HelperClass.HomeAdapter.TopRatedAdapter;
import com.example.splashscreen.HelperClass.HomeAdapter.TopRatedHelperClass;
import com.example.splashscreen.HelperClass.HomeAdapter.pop_mob_Adapter;
import com.example.splashscreen.HelperClass.HomeAdapter.pop_mob_HelperClass;

import java.util.ArrayList;

public class CustomerDash extends AppCompatActivity {

    RecyclerView newArivals_recycler, popular_mobiles_recycler, toprated_recycler;
    RecyclerView.Adapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_customer_dash);

//      Hooks

        newArivals_recycler = findViewById(R.id.newArivals_recycler);
        newArivals_recycler();

        popular_mobiles_recycler = findViewById(R.id.popular_mobiles_recycler);
        popular_mobiles_recycler();

        toprated_recycler = findViewById(R.id.toprated_recycler);
        toprated_recycler();
    }


    private void newArivals_recycler() {

        newArivals_recycler.setHasFixedSize(true);
        newArivals_recycler.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        ArrayList<ArrivalHelperClass> arrivallocations = new ArrayList<>();
        arrivallocations.add(new ArrivalHelperClass(R.drawable.oneplus_9r, "One Plus 9R 5G", "M.R.P : ₹36,999.00\n8GB RAM, 128GB Storage\nQualcomm Snapdragon 870"));
        arrivallocations.add(new ArrivalHelperClass(R.drawable.samsung_m32, "Samsung Galaxy M32", "M.R.P : ₹14,999.00\n8GB RAM, 128GB Storage\nInfinity-U Display"));
        arrivallocations.add(new ArrivalHelperClass(R.drawable.xiaomi_11, "mi Xiomi11  Lite NE 5G", "M.R.P : ₹26,999.00\nLPDDR5 RAM+UFS 3.1 storage\nQualcomm Snapdragon 888"));
        arrivallocations.add(new ArrivalHelperClass(R.drawable.google_pixel_4a, "Google pixel 4a 5G", "M.R.P : ₹29,999.00\n6 GBRAM + 128GB storage\nQualcomm SDM730 Snapdragon 730G"));
        arrivallocations.add(new ArrivalHelperClass(R.drawable.vivo_x60_pro, "Vivo X60 pro", "M.R.P : ₹48,990.00\n8GB, 128GB Storage\nQualcomm Snapdragon 870"));


        adapter = new ArrivalAdapter(arrivallocations);
        newArivals_recycler.setAdapter(adapter);
    }


    private void popular_mobiles_recycler() {

        popular_mobiles_recycler.setHasFixedSize(true);
        popular_mobiles_recycler.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        ArrayList<pop_mob_HelperClass> pop_mob_Locations = new ArrayList<>();
        pop_mob_Locations.add(new pop_mob_HelperClass(R.drawable.oneplus_9r, "One Plus 9R 5G", "M.R.P : ₹36,999.00\n8GB RAM, 128GB Storage\nQualcomm Snapdragon 870"));
        pop_mob_Locations.add(new pop_mob_HelperClass(R.drawable.samsung_galaxy_s21, "Samsung Galaxy S21", "M.R.P : ₹72,450.00\n8GB RAM, 128GB Storage\nInfinity-U Display"));
        pop_mob_Locations.add(new pop_mob_HelperClass(R.drawable.xiaomi_11, "mi Xiomi11  Lite NE 5G", "M.R.P : ₹26,999.00\nLPDDR5 RAM + UFS 3.1 storage\nQualcomm Snapdragon 888"));
        pop_mob_Locations.add(new pop_mob_HelperClass(R.drawable.oppo_reno_6, "Oppo Reno 6 5G", "M.R.P : ₹38,990.00\n12GB RAM,256GB Storage\nMediaTek Dimensity 1200 Processor"));
        pop_mob_Locations.add(new pop_mob_HelperClass(R.drawable.iphone_13, "iphone 13", "M.R.P : ₹79,990.00\nSuper Retina XDR display\n\niOS 15\nNew 6‑core CPU"));

        adapter = new pop_mob_Adapter(pop_mob_Locations);
        popular_mobiles_recycler.setAdapter(adapter);


    }

    private void toprated_recycler() {
        toprated_recycler.setHasFixedSize(true);
        toprated_recycler.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        ArrayList<TopRatedHelperClass> TopRatedLocations = new ArrayList<>();
        TopRatedLocations.add(new TopRatedHelperClass(R.drawable.samsung_logo, "SAMSUNG"));
        TopRatedLocations.add(new TopRatedHelperClass(R.drawable.redmi_logo, "REDMI"));
        TopRatedLocations.add(new TopRatedHelperClass(R.drawable.oneplus_logo, "ONEPLUS"));
        TopRatedLocations.add(new TopRatedHelperClass(R.drawable.google_logo, "GOOGLE"));
        TopRatedLocations.add(new TopRatedHelperClass(R.drawable.iphone_logo, "IPHONE"));

        adapter = new TopRatedAdapter(TopRatedLocations);
        toprated_recycler.setAdapter(adapter);

    }

}
