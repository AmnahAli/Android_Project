package com.example.listview3;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class List_of_Reservations_Activity extends AppCompatActivity {

    SQLiteHelper sqLiteHelper;
    SQLiteDatabase sqLiteDatabase;
    Cursor cursor;
    ListAdapter listAdapter ;
    ListView LISTVIEW;
    Button ButtonLogOut, ButtonHome;


    Intent myIntent;

    ArrayList<String> ID_Array;
    ArrayList<String> Reservation_Date_Array;
    ArrayList<String> Room_Type_Array;
    ArrayList<String> Number_of_Room_Array;
    ArrayList<String> CheckIn_Array;
    ArrayList<String> CheckOut_Array;
    ArrayList<String> First_Name_Array;
    ArrayList<String> Last_Name_Array;
    ArrayList<String> Number_Of_Adults_Array;
    ArrayList<String> Number_Of_Children_Array;
    ArrayList<String> Total_Price_Array;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_of_reservations);

        LISTVIEW = (ListView) findViewById(R.id.listView1);

        ButtonLogOut= (Button)findViewById(R.id.button_logout);
        ButtonHome = (Button) findViewById(R.id.button_home);


        ID_Array = new ArrayList<String>();
        Reservation_Date_Array = new ArrayList<String>();
        Room_Type_Array = new ArrayList<String>();
        Number_of_Room_Array = new ArrayList<String>();
        CheckIn_Array = new ArrayList<String>();
        CheckOut_Array = new ArrayList<String>();
        First_Name_Array = new ArrayList<String>();
        Last_Name_Array = new ArrayList<String>();
        Number_Of_Adults_Array = new ArrayList<String>();
        Number_Of_Children_Array = new ArrayList<String>();
        Total_Price_Array = new ArrayList<String>();


        sqLiteHelper = new SQLiteHelper(this);

        myIntent = new Intent(this, Details_of_Reservation_Activity.class);

        ArrayAdapter<String> myAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1);

        LISTVIEW.setAdapter(listAdapter);



        ButtonLogOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(List_of_Reservations_Activity.this, MainActivity.class));
            }
        });



        /*ButtonHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(reservation_summary_manager_Activity.this, managerHomeScreen.class));
            }
        });*/

    }


    @Override
    protected void onResume() {

        ShowSQLiteDBdata() ;

        super.onResume();
    }

    private void ShowSQLiteDBdata( ) {
        String date = getIntent().getStringExtra("date");
        String roomType = getIntent().getStringExtra("roomType");

        sqLiteDatabase = sqLiteHelper.getWritableDatabase();


        cursor = sqLiteDatabase.rawQuery("SELECT *  FROM "+SQLiteHelper.Reservations + " WHERE reservation_date = '" + date + "' AND room_type = '" + roomType + "'", null);

        //"+SQLiteHelper.Booking_id+", "+SQLiteHelper.Reservation_date+","+SQLiteHelper.Room_type+", "+SQLiteHelper.Number_of_rooms+", "+SQLiteHelper.Check_in_date+", "+SQLiteHelper.Check_out_date+"

        ID_Array.clear();
        Reservation_Date_Array.clear();
        Room_Type_Array.clear();
        Number_of_Room_Array.clear();
        CheckIn_Array.clear();
        CheckOut_Array.clear();
        First_Name_Array.clear();
        Last_Name_Array.clear();
        Number_Of_Adults_Array.clear();
        Number_Of_Children_Array.clear();
        Total_Price_Array.clear();



        if (cursor.moveToNext()) {
            do {

                ID_Array.add(cursor.getString(cursor.getColumnIndex(SQLiteHelper.Booking_id)));

                Reservation_Date_Array.add(cursor.getString(cursor.getColumnIndex(SQLiteHelper.Reservation_date)));

                Room_Type_Array.add(cursor.getString(cursor.getColumnIndex(SQLiteHelper.Room_type)));

                Number_of_Room_Array.add(cursor.getString(cursor.getColumnIndex(SQLiteHelper.Number_of_rooms)));

                CheckIn_Array.add(cursor.getString(cursor.getColumnIndex(SQLiteHelper.Check_in_date)));

                CheckOut_Array.add(cursor.getString(cursor.getColumnIndex(SQLiteHelper.Check_out_date)));

                First_Name_Array.add(cursor.getString(cursor.getColumnIndex(SQLiteHelper.First_name)));

                Last_Name_Array.add(cursor.getString(cursor.getColumnIndex(SQLiteHelper.Last_name)));

                Number_Of_Adults_Array.add(cursor.getString(cursor.getColumnIndex(SQLiteHelper.Number_of_adults)));

                Number_Of_Children_Array.add(cursor.getString(cursor.getColumnIndex(SQLiteHelper.Number_of_children)));

                Total_Price_Array.add(cursor.getString(cursor.getColumnIndex(SQLiteHelper.Total_price)));



            } while (cursor.moveToNext());
        }

        listAdapter = new ListAdapter(List_of_Reservations_Activity.this,

                ID_Array,
                Reservation_Date_Array,
                Room_Type_Array,
                Number_of_Room_Array,
                CheckIn_Array,
                CheckOut_Array,
                First_Name_Array,
                Last_Name_Array,
                Number_Of_Adults_Array,
                Number_Of_Children_Array,
                Total_Price_Array
        );

            // Click item in ListView
        /*LISTVIEW.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(List_of_Reservations_Activity.this, Details_of_Reservation_Activity.class);
                intent.putExtra("COURSE_SELECTED", (Bundle) LISTVIEW.getItemAtPosition(i));
                startActivity(intent);
            }
        });*/

        LISTVIEW.setAdapter(listAdapter);

        cursor.close();
    }

}
