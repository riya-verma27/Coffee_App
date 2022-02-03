package com.example.coffeeapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.NumberFormat;

public class MainActivity extends AppCompatActivity {
    int quantity=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void submitOrder(View v)
    {
        CheckBox obj=(CheckBox) findViewById(R.id.whipped_cream);
        boolean has_cream=obj.isChecked();
        CheckBox obj2=(CheckBox) findViewById(R.id.chocolate_checkbox);
        boolean has_chocolate=obj2.isChecked();
        EditText ob=(EditText)findViewById(R.id.name_field);
        String name=ob.getText().toString();
        String msg=create_order_summary(name,has_cream,has_chocolate);
        Intent intent=new Intent(Intent.ACTION_SEND);
        intent.setData(Uri.parse("mailto:"));
        intent.putExtra(Intent.EXTRA_SUBJECT,"Coffee Order for"+name);
        intent.putExtra(Intent.EXTRA_TEXT,msg);
        intent.setType("message/cfr893");
        startActivity(Intent.createChooser(intent, "Choose an Email client :"));
        //if(intent.resolveActivity(getPackageManager())!=null)
        //{
            //startActivity(intent);
        //}
    }
    String create_order_summary(String n,boolean cr,boolean choco)
    {
        int cost=find_price(cr,choco);
        String ms="NAME:"+n+"\nWhipped Cream Added:- "+cr;
        ms=ms+"\nChocolate Added:-"+choco+"\nQUANTITY:"+quantity;;
        ms=ms+"\nTotal: $"+cost;
        ms=ms+"\nThank You!!";
        return ms;

    }
    int find_price(boolean cream,boolean chocolate)
    {
        int base_price=5;
        if(cream)
        {
            base_price=base_price+1;
        }
        if(chocolate)
        {
            base_price=base_price+2;
        }
        return (quantity*base_price);
    }

    public void increment(View v)
    {
        if(quantity==100) {
            Toast.makeText(this,"You cannot have maore than 100 coffee",Toast.LENGTH_SHORT).show();
            return;
        }
        quantity=quantity+1;
        display(quantity);
    }
    public void decrement(View v)
    {
        if(quantity==1) {
            Toast.makeText(this,"You cannot have less than 1 coffee",Toast.LENGTH_SHORT).show();
            return;
        }
        quantity=quantity-1;
        display(quantity);
    }
    private void display(int num)
    {
        TextView quantity_text=(TextView)findViewById(R.id.quantity);
        quantity_text.setText(" "+num);
    }
}