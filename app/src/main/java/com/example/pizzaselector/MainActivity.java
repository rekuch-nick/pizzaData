package com.example.pizzaselector;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    //setup our widgets and variables
    Button btnYes;
    Button btnNo;

    ImageView imgSauce;
    ImageView imgTop1;
    ImageView imgTop2;
    ImageView imgTop3;

    String sauce;
    String top1;
    String top2;
    String top3;

    TextView txtTotal;
    private DBItem[] records;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // name stuff
        imgSauce = findViewById(R.id.imgSauce);
        imgTop1 = findViewById(R.id.imgTop1);
        imgTop2 = findViewById(R.id.imgTop2);
        imgTop3 = findViewById(R.id.imgTop3);
        txtTotal = findViewById(R.id.txtTotal);

        // button names & listeners
        btnYes = findViewById(R.id.btnYes);
        btnYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addNewRecord();
                rollNewPizza();
                showRecords();
            }
        });

        btnNo = findViewById(R.id.btnNo);
        btnNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rollNewPizza();
            }
        });

        // we'll need to update the screen on load
        rollNewPizza();
        showRecords();

    } // end of on create





    // this method saves pizzas to the DB by creating a new object and constructing it with the pizza's variables
    private void addNewRecord() {
        DBHelper data = new DBHelper(this);
        DBItem dbItem = new DBItem(sauce, top1, top2, top3);
        boolean insert = data.addRecord(dbItem);


        if(!insert){
            Toast.makeText(this, "I'm not going to remember this pizza.", Toast.LENGTH_SHORT).show();
        } else {
            String mad = data.toString();
            Toast.makeText(this, mad, Toast.LENGTH_SHORT).show();

        }
    }


    // update the total number of pizzas selected
    public void showRecords() {
        records = getAllRecords();
        txtTotal.setText(records.length + " Pizzas accepted.");
    }

    public DBItem[] getAllRecords(){
        DBHelper data = new DBHelper(this);
        return data.getAllRecords();
    }





    // this method selects new pizza settings and updates the on-screen images
    private void rollNewPizza() {

        Random random = new Random();

        //red sauce is more common than white sauce
        int r = random.nextInt(3) + 1;
        if(r == 1){
            sauce = "white";
            imgSauce.setImageResource(R.drawable.pizza_sause_white);
        } else {
            sauce = "red";
            imgSauce.setImageResource(R.drawable.pizza_sause_red);
        }

        r = random.nextInt(5) + 1;
        top1 = getToppingFromNumber(r);
        imgTop1.setImageResource(getImageFromTopping(top1));


        // extra toppings are less common
        imgTop2.setVisibility(View.VISIBLE);
        r = random.nextInt(9) + 1;
        top2 = getToppingFromNumber(r);
        if(r <= 5){
            imgTop2.setImageResource(getImageFromTopping(top2));
        } else {
            imgTop2.setVisibility(View.INVISIBLE);
        }

        imgTop3.setVisibility(View.VISIBLE);
        r = random.nextInt(15) + 1;
        top3 = getToppingFromNumber(r);
        if(r <= 5){
            imgTop3.setImageResource(getImageFromTopping(top3));
        } else {
            imgTop3.setVisibility(View.INVISIBLE);
        }

    }

    private String getToppingFromNumber(int n) {
        if(n == 1){ return "mushroom"; }
        if(n == 2){ return "pepper"; }
        if(n == 3){ return "pepperoni"; }
        if(n == 4){ return "onion"; }
        if(n == 5){ return "anchovy"; }
        return "_";
    }

    private int getImageFromTopping(String s){
        if(s.equals("mushroom")){ return R.drawable.mushrooms; }
        if(s.equals("pepper")){ return R.drawable.peppers; }
        if(s.equals("pepperoni")){ return R.drawable.pepperoni; }
        if(s.equals("onion")){ return R.drawable.onions; }
        if(s.equals("anchovy")){ return R.drawable.anchovis; }
        return -1;
    }


















}
