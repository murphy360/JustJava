package com.andrios.justjava;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import java.text.NumberFormat;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends ActionBarActivity {
    int numberOfCoffees = 3;
    int pricePerCup = 5;
    int priceWhipped = 1;
    int priceChocolate = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {
        CheckBox whippedCheckbox = (CheckBox) findViewById(R.id.whipped_cream_checkbox);
        boolean isWhipped = whippedCheckbox.isChecked();

        CheckBox chocolateCheckbox = (CheckBox) findViewById(R.id.chocolate_checkbox);
        boolean isChocolate = chocolateCheckbox.isChecked();

        int price = calculatePrice(isWhipped, isChocolate);
        String priceMessage = createOrderSummary(price, isWhipped, isChocolate);

        //Open Email application with order details
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:"));
        intent.putExtra(Intent.EXTRA_EMAIL, "c.murphy360@gmail.com");
        intent.putExtra(Intent.EXTRA_SUBJECT, "New Order");
        intent.putExtra(Intent.EXTRA_TEXT, priceMessage);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }




    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void displayQuantity(int number) {
        TextView quantityTextView = (TextView) findViewById(
                R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }



    public void increment(View view){
        if(numberOfCoffees <100){
            numberOfCoffees++;
            displayQuantity(numberOfCoffees);
            displayPrice();
        }


    }

    public void decriment(View view){
        if (numberOfCoffees > 0) {
            numberOfCoffees--;
            displayQuantity(numberOfCoffees);
            displayPrice();

        };
    }

    public void displayPrice(View v){
        displayPrice();
    }

    /**
     * This method displays the given text on the screen.
     * */
    private void displayPrice() {
        TextView priceTextView = (TextView) findViewById(R.id.order_summary_text_view);
        CheckBox whippedCheckbox = (CheckBox) findViewById(R.id.whipped_cream_checkbox);
        boolean isWhipped = whippedCheckbox.isChecked();

        CheckBox chocolateCheckbox = (CheckBox) findViewById(R.id.chocolate_checkbox);
        boolean isChocolate = chocolateCheckbox.isChecked();

        String message = "$" + calculatePrice(isWhipped, isChocolate);
        priceTextView.setText(message);
    }


    /**
     *
     * @param price
     * @return String with formatted message
     */
    private String createOrderSummary(int price, boolean isWhippedCream, boolean isChocolate){
        EditText editNameText = (EditText) findViewById(R.id.edit_name_text);

        String priceMessage =
                "Name: " + editNameText.getText() + "\n" +
                        "Add whipped cream? " + isWhippedCream + "\n" +
                        "Add Chocolate? " + isChocolate + "\n" +
                        "Quantity: " + numberOfCoffees + "\n" +
                        "Total: $" + (price) + "\n" +
                        "Thank You!";
        return priceMessage;
    }
    /*Calculate price of coffee
     *
     * @returns price
     */
    private int calculatePrice(boolean isWhipped, boolean isChocolate){
      int price = pricePerCup;

        if(isWhipped){

           price += priceWhipped;
        }

        if (isChocolate){
            price += priceChocolate;
        }
        return (price * numberOfCoffees);
    }
}