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
import static com.example.listview3.SQLiteHelper.Check_in_date;
import static com.example.listview3.SQLiteHelper.Check_out_date;
import static com.example.listview3.SQLiteHelper.Number_of_adults;
import static com.example.listview3.SQLiteHelper.Number_of_children;
import static com.example.listview3.SQLiteHelper.Number_of_nights;
import static com.example.listview3.SQLiteHelper.Number_of_rooms;
import static com.example.listview3.SQLiteHelper.Reservations;
import static com.example.listview3.SQLiteHelper.Room_type;

public class Details_of_Reservation_Guest_Activity extends AppCompatActivity {

    SQLiteHelper sqLiteHelper;
    SQLiteDatabase sqLiteDatabase;
    Cursor cursor;
    Button ButtonLogOut, ButtonHome, ButtonModify, ButtonCancel;
    EditText roomTypeE;
    EditText checkInDateE;
    EditText checkOutDateE;
    EditText numberOfRoomE;
    EditText numberOfAdultsE;
    EditText numberOfChildrenE;
    EditText numberOfNightsE;
    Boolean editable = false;
    String bookId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_of__reservation__guest_);

        ButtonLogOut = (Button) findViewById(R.id.button_logout);
        ButtonHome = (Button) findViewById(R.id.button_home);
        ButtonModify = (Button) findViewById(R.id.button_Modify);
        ButtonCancel = (Button) findViewById(R.id.button_Cancel);

        bookId = getIntent().getStringExtra("id");
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
        roomTypeE = findViewById(R.id.textViewRoomType);
        checkInDateE = findViewById(R.id.textViewCheckInData);
        checkOutDateE = findViewById(R.id.textViewCheckOutData);
        numberOfRoomE = findViewById(R.id.textViewNumberOfRoom);
        numberOfAdultsE = findViewById(R.id.textViewAdults);
        numberOfChildrenE = findViewById(R.id.textViewChildren);
        numberOfNightsE = findViewById(R.id.textViewNumberOfNights);
        TextView totalPriceE = findViewById(R.id.textViewPrice);
        TextView pricePerNightE = findViewById(R.id.textViewPricePerNight);

        idE.setText(bookId);
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

        sqLiteHelper = new SQLiteHelper(this);

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
//                startActivity(new Intent(Details_of_Reservation_Guest_Activity.this, Modify_Reservation_Guest_Activity.class));
                if(!editable) enableModifyView();
                else {
                    updateTable();
                    disableModifyView();
                }
            }
        });



    ButtonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sqLiteHelper.deleteName(bookId);
                Toast.makeText(Details_of_Reservation_Guest_Activity.this,"Reservation Deleted",Toast.LENGTH_LONG).show();
                finish();
            }
        });
    }

    private void enableModifyView() {
        roomTypeE.setEnabled(true);
        checkInDateE.setEnabled(true);
        checkOutDateE.setEnabled(true);
        numberOfRoomE.setEnabled(true);
        numberOfAdultsE.setEnabled(true);
        numberOfChildrenE.setEnabled(true);
        numberOfNightsE.setEnabled(true);

        ButtonModify.setText("Save");
        editable = true;
    }

    private void disableModifyView() {
        roomTypeE.setEnabled(false);
        checkInDateE.setEnabled(false);
        checkOutDateE.setEnabled(false);
        numberOfRoomE.setEnabled(false);
        numberOfAdultsE.setEnabled(false);
        numberOfChildrenE.setEnabled(false);
        numberOfNightsE.setEnabled(false);

        ButtonModify.setText("Modify");
        editable = false;
    }

    private void updateTable() {
        String roomType = roomTypeE.getText().toString();
        String checkInDate = checkInDateE.getText().toString();
        String checkOutDate = checkOutDateE.getText().toString();
        String numberOfRoom = numberOfRoomE.getText().toString();
        String numberOfAdults = numberOfAdultsE.getText().toString();
        String numberOfChildren = numberOfChildrenE.getText().toString();
        String numberOfNight = numberOfNightsE.getText().toString();

        sqLiteHelper.updateTable(bookId, roomType, checkInDate, checkOutDate, numberOfRoom, numberOfAdults, numberOfChildren, numberOfNight);
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




