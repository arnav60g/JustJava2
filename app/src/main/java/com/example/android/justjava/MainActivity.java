package com.example.android.justjava; /**
 * Add your package below. Package name can be found in the project's AndroidManifest.xml file.
 * This is the package name our example uses:
 *
 * package com.example.android.justjava;
 */

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import static android.R.attr.name;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * This method is called when the order button is clicked.
     */
    int quantity = 0;
    public void submitOrder(View view) {
        int price = calculatePrice();
        String priceMessage = createOrderSummary(quantity) + "\nTotal :$" + price + "\nThank You!";
        EditText yourname = (EditText) findViewById(R.id.yourName);
        CheckBox checkbox = (CheckBox) findViewById(R.id.whippedCream);
        CheckBox choco = (CheckBox) findViewById(R.id.chocolate);
        String name = yourname.getText().toString();
        priceMessage = "Name:" + name + "\n" + priceMessage;
        if (checkbox.isChecked()) {
            priceMessage = priceMessage + "\nAdd Whipped Cream";
        }
        if (choco.isChecked()){
            priceMessage += "\nAdd Chocolate";
        }
        composeEmail(priceMessage);
    }
        public void composeEmail(  String priceMessage) {
            Intent intent = new Intent(Intent.ACTION_SENDTO);
            intent.setData(Uri.parse("mailto:")); // only email apps should handle this
            String subject="Just Java order for "+ name ;
            intent.putExtra(Intent.EXTRA_SUBJECT, subject);
            intent.putExtra(Intent.EXTRA_SUBJECT, priceMessage);
            if (intent.resolveActivity(getPackageManager()) != null) {
                startActivity(intent);
            }
        }
//        displayMessage(priceMessage);
 //   }


    private String createOrderSummary(int price)  {
        String summmary = "Quantity: " + price;
        return summmary;

    }

    public void increment(View view)  {
        displayQuantity(++quantity);

    }

    public void decrement(View view)  {
        displayQuantity(--quantity);

    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void displayQuantity(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);

    }

    private int calculatePrice()  {
        int price;
        CheckBox checkbox = (CheckBox) findViewById(R.id.whippedCream);
        CheckBox chocolateCheck = (CheckBox) findViewById(R.id.chocolate);
        if (checkbox.isChecked()) {
            if (chocolateCheck.isChecked()){
                price = quantity * 8 ;
            } else
            price = quantity * 6;
        } else {
            if (chocolateCheck.isChecked()){
                price = quantity * 7;
            } else
            price = quantity * 5;
        }
        return price;
    }


    /**
     * This method displays the given text on the screen.
     */
    private void displayMessage(String message) {
        TextView orderSummaryTextView = (TextView) findViewById(R.id.order_summary_text_view);
        orderSummaryTextView.setText(message);
    }

}