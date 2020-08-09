package com.example.listview3;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.TextView;

public class SQLiteHelper extends SQLiteOpenHelper {

   /* static String DATABASE_NAME="HotelManagement.db";
    public static final String TABLE_NAME="reservations";
    public static final String Table_Column_ID="booking_id";
    public static final String Table_Column_1_ReservationDate="reservation_date";
    public static final String Table_Column_2_RoomType="room_type";
    public static final String Table_Column_3_NumOfRoom="no_of_room";
    public static final String Table_Column_4_CheckIn="check_in";
    public static final String Table_Column_5_CheckOut="check_out";*/


    public static final String dbname = "HotelManagement.db";
    public static final String Reservations = "reservations";
    public static final String Booking_id = "Booking_id";
    public static final String Hotel_name = "hotel_name";
    public static final String Hotel_location = "hotel_location";
    public static final String Room_type = "room_type";
    public static final String Number_of_rooms = "number_of_rooms";
    public static final String Number_of_nights = "number_of_nights";
    public static final String Number_of_adults = "number_of_adults";
    public static final String Number_of_children = "number_of_children";
    public static final String Check_in_date = "check_in_date";
    public static final String Check_out_date = "check_out_date";
    public static final String Price_per_night = "price_per_night";
    public static final String Tax = "tax";
    public static final String Total_price = "total_price";
    public static final String Billed_price = "billed_price";
    public static final String Billing_address = "billing_address";
    public static final String First_name = "first_name";
    public static final String Last_name = "last_name";
    public static final String Reservation_date = "reservation_date";




    public SQLiteHelper(Context context) {
        super(context, dbname, null, 1);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String qry1 = "CREATE TABLE IF NOT EXISTS "+Reservations+" ("+Booking_id+" INTEGER PRIMARY KEY,"+Hotel_name+" text,"+Hotel_location+" text,"+Room_type+" text, "+Number_of_rooms+" text, "+Number_of_nights+" text, "+Number_of_adults+" text, "+Number_of_children+" text," +
                ""+Check_in_date+"text, "+Check_out_date+" text, "+Price_per_night+" text, "+Tax+" text, "+Total_price+" text, "+Billed_price+" text, "+Billing_address+" text, "+First_name+" text, "+Last_name+" text, "+Reservation_date+" text);";
        db.execSQL(qry1);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+Reservations);
        onCreate(db);

    }


    @Override
    public void onConfigure(SQLiteDatabase db) {
        super.onConfigure(db);
        db.setForeignKeyConstraintsEnabled(true);
    }


    public boolean updateData(String id, String roomType,String numberOfRooms,String numberOfAdults,String numberOfChildren, String checkIn, String checkOut) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(Room_type,roomType);
        contentValues.put(Number_of_rooms,numberOfRooms);
        contentValues.put(Number_of_adults,numberOfAdults);
        contentValues.put(Number_of_children,numberOfChildren);
        contentValues.put(Check_in_date,checkIn);
        contentValues.put(Check_out_date,checkOut);

        db.update(Reservations, contentValues, "Booking_id = ?",new String[] { id });
        return true;
    }

    /*public Integer deleteData (String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(Reservations, "Booking_id = ?",new String[] {id});
    }*/

    public boolean updateTable(String bookId, String roomType,String checkInDate,String checkOutDate,
                               String numberOfRoom, String numberOfAdults, String numberOfChildren, String numberOfNight) {
        SQLiteDatabase db = this.getWritableDatabase();

        String updateQuery = "UPDATE " + Reservations + " SET " +
                Room_type + " = '" + roomType + "', " +
                Check_in_date + " = '" + checkInDate + "', " +
                Check_out_date + " = '" + checkOutDate + "', " +
                Number_of_rooms + " = '" + numberOfRoom + "', " +
                Number_of_adults + " = '" + numberOfAdults + "', " +
                Number_of_children+ " = '" + numberOfChildren + "', " +
                Number_of_nights + " = '" + numberOfNight + "' " +
                " WHERE " + Booking_id + " = '" + bookId + "'";
        Cursor cursor = db.rawQuery(updateQuery, null);
        cursor.moveToFirst();
        cursor.close();

        return true;
    }

    public void deleteName(String id){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "DELETE FROM " + Reservations + " WHERE "
                + Booking_id + " = '" + id + "'";
        Log.d("Delete Successful", "******");
        db.execSQL(query);
    }

}