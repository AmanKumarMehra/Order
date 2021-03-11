package com.example.order;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

     int quantity = 0;

    public void decrement(View view){
        if(quantity > 1)
            quantity -= 1;
        if(quantity == 1)
            Toast.makeText(this, "You cannot have less than 1 coffee", Toast.LENGTH_SHORT).show();
        display(quantity);
    }

    public void increment(View view){
        if(quantity <= 98)
            quantity += 1;
       else
            Toast.makeText(this,"You cannot have more than 100 coffee", Toast.LENGTH_SHORT).show();
        display(quantity);
    }

    public void display(int quantity){
        TextView ed = findViewById(R.id.price1);
        if(quantity <= 9)
            ed.setText("0"+quantity);
        else
            ed.setText(""+quantity);
    }

    public void order1(View view){

        EditText nameField = findViewById(R.id.name);
        String name = nameField.getText().toString();

        CheckBox tea = findViewById(R.id.checkBox1);
        boolean checkTea = tea.isChecked();

        CheckBox coffee = findViewById(R.id.checkBox2);
        boolean checkCoffee = coffee.isChecked();

        int price = calculatePrice(checkTea, checkCoffee, quantity);
        String priceMessage = createOrderSummary(name, price);

        //public void composeEmail(String[] addresses, String subject) {
            Intent intent = new Intent(Intent.ACTION_SENDTO);
            intent.setData(Uri.parse("mailto:")); // only email apps should handle this
            intent.putExtra(Intent.EXTRA_SUBJECT, "Order for: " + name);
            intent.putExtra(Intent.EXTRA_TEXT, priceMessage);
            if (intent.resolveActivity(getPackageManager()) != null) {
                startActivity(intent);
            }
        //}

    }

    private String createOrderSummary(String name, int price) {
        String str = "Name : " + name + "\n" +
                     "You have ordered " + quantity + " tea "+ "\n"+
                     "You have ordered " + quantity + " coffee "+ "\n" +
                     "Total Price : " + price + "\n" +
                     "Thank You!";

        return str;
    }

    private int calculatePrice(boolean checkTea, boolean checkCoffee, int quantity) {
        int price = 0;

        if(checkTea == true){
            price += 10 * quantity;
        }

        if(checkCoffee == true){
            price += 10 * quantity;
        }

        return price;
    }

}
