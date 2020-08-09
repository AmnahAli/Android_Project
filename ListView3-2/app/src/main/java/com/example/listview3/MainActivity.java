package com.example.listview3;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    SQLiteDatabase sqLiteDatabaseObj;
    EditText editTextReservationDate, editTextRoomType, editTextNoRoom, editTextCheckIn, editTextCheckOut;
    EditText editTextFirstName, editTextLastName, editTextNumberOfAdults, editTextNumberOfChildren, editTextTotalPrice;
    EditText editTextHotelName, editTextHotelLocation, editTextNumberOfNights, editTextPricePerNight;
    String ReservationDateHolder, RoomTypeHolder, NumRoomHolder, CheckInHolder, CheckOutHolder,  SQLiteDataBaseQueryHolder;
    String FirstNameHolder, LastNameHolder, NumberOfAdultsHolder, NumberOfChildrenHolder, TotalPriceHolder;
    String HotelNameHolder, HotelLocationHolder, NumberOfNightsHolder, PricePerNightHolder;
    Button EnterData, ButtonDisplayData, ButtonDisplayDataGuest;
    Boolean EditTextEmptyHold;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EnterData = (Button)findViewById(R.id.button);
        ButtonDisplayData = (Button)findViewById(R.id.button2);
        ButtonDisplayDataGuest = (Button) findViewById(R.id.button3);

        editTextReservationDate = (EditText)findViewById(R.id.editText);
        editTextRoomType = (EditText)findViewById(R.id.editText2);
        editTextNoRoom = (EditText)findViewById(R.id.editText3);
        editTextCheckIn = (EditText) findViewById(R.id.editText4);
        editTextCheckOut =(EditText)findViewById(R.id.editText5);
        editTextFirstName =(EditText)findViewById(R.id.editText6);
        editTextLastName = (EditText) findViewById(R.id.editText7);
        editTextNumberOfAdults = (EditText)findViewById(R.id.editText8);
        editTextNumberOfChildren = (EditText) findViewById(R.id.editText9);
        editTextTotalPrice = (EditText) findViewById(R.id.editText10);
        editTextHotelName = (EditText) findViewById(R.id.editText11);
        editTextHotelLocation = (EditText) findViewById(R.id.editText12);
        editTextNumberOfNights = (EditText) findViewById(R.id.editText13);
        editTextPricePerNight = (EditText) findViewById(R.id.editText14);




        EnterData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SQLiteDataBaseBuild();
                SQLiteTableBuild();
                CheckEditTextStatus();
                InsertDataIntoSQLiteDatabase();
                EmptyEditTextAfterDataInsert();
            }
        });

        ButtonDisplayData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(MainActivity.this, reservation_summary_manager_Activity.class);
                startActivity(intent);
            }
        });


        ButtonDisplayDataGuest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(MainActivity.this, reservation_summary_guest_Activity.class);
                startActivity(intent);
            }
        });

    }

    public void SQLiteDataBaseBuild(){

        sqLiteDatabaseObj = openOrCreateDatabase(SQLiteHelper.dbname, Context.MODE_PRIVATE, null);

    }

    public void SQLiteTableBuild(){

        sqLiteDatabaseObj.execSQL("CREATE TABLE IF NOT EXISTS "+SQLiteHelper.Reservations+"("+SQLiteHelper.Booking_id+" INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, "+SQLiteHelper.Hotel_name+" text,"+SQLiteHelper.Hotel_location+" text,"+SQLiteHelper.Room_type+" text, "+SQLiteHelper.Number_of_rooms+" text, "+SQLiteHelper.Number_of_nights+" text, "+SQLiteHelper.Number_of_adults+" text, "+SQLiteHelper.Number_of_children+" text," +
                ""+SQLiteHelper.Check_in_date+" text, "+SQLiteHelper.Check_out_date+" text, "+SQLiteHelper.Price_per_night+" text, "+SQLiteHelper.Tax+" text, "+SQLiteHelper.Total_price+" text, "+SQLiteHelper.Billed_price+" text, "+SQLiteHelper.Billing_address+" text, "+SQLiteHelper.First_name+" text, "+SQLiteHelper.Last_name+" text, "+SQLiteHelper.Reservation_date+" text);");
    }

    public void CheckEditTextStatus(){

        ReservationDateHolder = editTextReservationDate.getText().toString() ;
        RoomTypeHolder = editTextRoomType.getText().toString();
        NumRoomHolder = editTextNoRoom.getText().toString();
        CheckInHolder = editTextCheckIn.getText().toString();
        CheckOutHolder = editTextCheckOut.getText().toString();
        FirstNameHolder = editTextFirstName.getText().toString();
        LastNameHolder = editTextLastName.getText().toString();
        NumberOfAdultsHolder = editTextNumberOfAdults.getText().toString();
        NumberOfChildrenHolder = editTextNumberOfChildren.getText().toString();
        TotalPriceHolder = editTextTotalPrice.getText().toString();

        HotelNameHolder = editTextHotelName.getText().toString();
        HotelLocationHolder = editTextHotelLocation.getText().toString();
        NumberOfNightsHolder = editTextNumberOfNights.getText().toString();
        PricePerNightHolder = editTextPricePerNight.getText().toString();

        if(TextUtils.isEmpty(ReservationDateHolder)){
            EditTextEmptyHold = false ;
        }
        else {
            EditTextEmptyHold = true ;
        }
    }

    public void InsertDataIntoSQLiteDatabase(){

        if(EditTextEmptyHold == true)
        {

            SQLiteDataBaseQueryHolder = "INSERT INTO "+SQLiteHelper.Reservations+
                    " (reservation_date,room_type,number_of_rooms,check_in_date, check_out_date, " +
                    "first_name, last_name, number_of_adults, number_of_children, total_price, hotel_name , hotel_location, number_of_nights, price_per_night) " +
                    "VALUES('"+ReservationDateHolder+"', '"+RoomTypeHolder+"' , '"+NumRoomHolder+"', " +
                    "'"+CheckInHolder+"', '"+CheckOutHolder+"', '"+FirstNameHolder+"','"+LastNameHolder+"'," +
                    "'"+NumberOfAdultsHolder+"','"+NumberOfChildrenHolder+"','"+TotalPriceHolder+"', '"+HotelNameHolder+"', '"+HotelLocationHolder+"'," +
                    "'"+NumberOfNightsHolder+"', '"+PricePerNightHolder+"');";

            sqLiteDatabaseObj.execSQL(SQLiteDataBaseQueryHolder);

            Toast.makeText(MainActivity.this,"Data Inserted Successfully", Toast.LENGTH_LONG).show();

        }
        else {

            Toast.makeText(MainActivity.this,"Please Fill All The Required Fields.", Toast.LENGTH_LONG).show();

        }

    }

    public void EmptyEditTextAfterDataInsert(){

        editTextReservationDate.getText().clear();
        editTextRoomType.getText().clear();
        editTextNoRoom.getText().clear();
        editTextCheckIn.getText().clear();
        editTextCheckOut.getText().clear();
        editTextFirstName.getText().clear();
        editTextLastName.getText().clear();
        editTextNumberOfAdults.getText().clear();
        editTextNumberOfChildren.getText().clear();
        editTextTotalPrice.getText().clear();

        editTextHotelName.getText().clear();
        editTextHotelLocation.getText().clear();
        editTextNumberOfNights.getText().clear();
        editTextPricePerNight.getText().clear();

    }

}