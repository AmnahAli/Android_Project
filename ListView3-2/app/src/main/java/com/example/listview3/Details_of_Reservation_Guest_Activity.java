package com.example.listview3;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import static com.example.listview3.SQLiteHelper.Booking_id;
import static com.example.listview3.SQLiteHelper.Reservations;

public class Details_of_Reservation_Guest_Activity extends AppCompatActivity {

    SQLiteHelper sqLiteHelper;
    SQLiteDatabase sqLiteDatabase;
    Cursor cursor;
    Button ButtonLogOut, ButtonHome, ButtonModify, ButtonCancel;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_of__reservation__guest_);

        ButtonLogOut = (Button) findViewById(R.id.button_logout);
        ButtonHome = (Button) findViewById(R.id.button_home);
        ButtonModify = (Button) findViewById(R.id.button_Modify);
        ButtonCancel = (Button) findViewById(R.id.button_Cancel);



        final String id = getIntent().getStringExtra("id");
        String roomType = getIntent().getStringExtra("roomType");
        String numberOfRooms = getIntent().getStringExtra("numberOfRooms");
        String checkInDate = getIntent().getStringExtra("checkInDate");
        String checkOutDate = getIntent().getStringExtra("checkOutDate");
        String hotelName = getIntent().getStringExtra("hotelName");
        String hotelLocation = getIntent().getStringExtra("hotelLocation");
        String numberOfAdults = getIntent().getStringExtra("numberOfAdults");
        String numberOfChildren = getIntent().getStringExtra("numberOfChildren");
        String totalPrice = getIntent().getStringExtra("totalPrice");
        String numberOfNights = getIntent().getStringExtra("numberOfNights");
        String pricePerNight = getIntent().getStringExtra("pricePerNight");


        final TextView idE = (TextView) findViewById(R.id.textViewID);
        TextView hotelNameE = findViewById(R.id.textViewHotelName);
        TextView hotelLocationE = findViewById(R.id.textViewHotelLocation);
        TextView roomTypeE = findViewById(R.id.textViewRoomType);
        TextView checkInDateE = findViewById(R.id.textViewCheckInData);
        TextView checkOutDateE = findViewById(R.id.textViewCheckOutData);
        TextView numberOfRoomE = findViewById(R.id.textViewNumberOfRoom);
        TextView numberOfAdultsE = findViewById(R.id.textViewAdults);
        TextView numberOfChildrenE = findViewById(R.id.textViewChildren);
        TextView totalPriceE = findViewById(R.id.textViewPrice);
        TextView numberOfNightsE = findViewById(R.id.textViewNumberOfNights);
        TextView pricePerNightE = findViewById(R.id.textViewPricePerNight);


        idE.setText(id);
        roomTypeE.setText(roomType);
        checkInDateE.setText(checkInDate);
        checkOutDateE.setText(checkOutDate);
        hotelNameE.setText(hotelName);
        hotelLocationE.setText(hotelLocation);
        numberOfAdultsE.setText(numberOfAdults);
        numberOfChildrenE.setText(numberOfChildren);
        totalPriceE.setText(totalPrice);
        numberOfRoomE.setText(numberOfRooms);
        numberOfNightsE.setText(numberOfNights);
        pricePerNightE.setText(pricePerNight);


        ButtonLogOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Details_of_Reservation_Guest_Activity.this, MainActivity.class));
            }
        });



        /*ButtonHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(reservation_summary_manager_Activity.this, guestHomeScreen.class));
            }
        });*/


        ButtonModify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Details_of_Reservation_Guest_Activity.this, Modify_Reservation_Guest_Activity.class));
            }
        });



    ButtonCancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    sqLiteHelper.deleteName(id);
                    Toast.makeText(Details_of_Reservation_Guest_Activity.this,"Reservation Deleted",Toast.LENGTH_LONG).show();
                    startActivity(new Intent(Details_of_Reservation_Guest_Activity.this,List_of_Reservations_Guest_Activity.class));

                }
            });

        }


        /*ButtonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(Details_of_Reservation_Guest_Activity.this);
                builder.setTitle("Confirm");
                builder.setMessage("Are you sure?");
                builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {

                    @SuppressLint("LongLogTag")
                    public void onClick(DialogInterface dialog, int which) {
                        // Do nothing but close the dialog

                        *//*sqLiteDatabase = sqLiteHelper.getWritableDatabase();
                        Integer deletedRows = sqLiteDatabase.delete(Reservations, "Booking_id = ?",new String[] {idE.getText().toString()});
                        if(deletedRows > 0)
                            Toast.makeText(Details_of_Reservation_Guest_Activity.this,"Data Deleted",Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(Details_of_Reservation_Guest_Activity.this,"Data not Deleted",Toast.LENGTH_LONG).show();
*//*

                        Log.i("Reservation deleted Successful", "**********");
                        startActivity(new Intent(Details_of_Reservation_Guest_Activity.this,List_of_Reservations_Guest_Activity.class));
                        dialog.dismiss();
                    }
                });

                builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        // Do nothing
                        dialog.dismiss();
                    }
                });

                AlertDialog alert = builder.create();
                alert.show();
            }
        });*/


}




