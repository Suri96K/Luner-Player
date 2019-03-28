/*//package com.example.a.myapp;

/*
import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import static android.view.View.*;

public class DBcconnection extends AppCompatActivity implements OnClickListener{


        SQLiteDatabase db;
        EditText album_name,artist_name,album_cover;
        SQLiteDatabase sqlitedb;
        Button Add, Delete, Update, Searchall, Search;
        String artistName,albumName,albumCoverImage;

        @SuppressLint("WrongViewCast")
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);


            db = openOrCreateDatabase("DBcconnection", Context.MODE_PRIVATE, null);
            //create table Employee
            db.execSQL("CREATE TABLE IF NOT EXISTS Album(ArtID INTEGER PRIMARY KEY AUTOINCREMENT,artistName VARCHAR,albumName VARCHAR );");

            album_name = (EditText) findViewById(R.id.album_name);
            artist_name = (EditText) findViewById(R.id.artist_name);
           // Add = (Button) findViewById(R.id.button);
           // Search= (Button) findViewById(R.id. button4);
            //Searchall=(Button) findViewById(R.id. button2);
            Add.setOnClickListener(this);
            Delete.setOnClickListener(this);
            Update.setOnClickListener(this);
            Search.setOnClickListener(this);
            Searchall.setOnClickListener(this);
        }

        public void onClick(View v) {

            // Insert data
           // if (v.getId() == R.id.button) {

              //  if (artist_name.getText().toString().trim().length() == 0 || album_name.getText().toString().trim().length() == 0 ) {
              //      Toast.makeText(this, "Fields cannot be Empty", Toast.LENGTH_SHORT).show();
             //       return;
                }
               // db.execSQL("INSERT INTO Album(artistName,albumName)VALUES('" + albumName.getText() + "','" + artistName.getText() + "');
             //   Toast.makeText(this, "Record Added", Toast.LENGTH_SHORT).show();
            }
            //Update data
          /*  else if(v.getId()==R.id.button3)
            {
                //code for update data
                if(editsearch.getText().toString().trim().length()==0)
                {
                        Toast.makeText(this, "Enter Artist  artistName",Toast.LENGTH_SHORT).show();
                    return;
                }
                Cursor c=db.rawQuery("SELECT * FROM Album WHERE ArtistName='"+ editsearch.getText()+"'", null);
                if(c.moveToFirst()) {
                    db.execSQL("UPDATE Album  SET artistName ='"+ artistName.getText()+"',WHERE EmpName ='"+editsearch.getText()+"'");
                    Toast.makeText(this, "Record Modified",Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Toast.makeText(this, "Invalid  artistName",Toast.LENGTH_SHORT);
                }
            }
            //Delete data
            else if(v.getId()==R.id.button5)
            {
                //code for delete data
                if(editsearch.getText().toString().trim().length()==0)
                {
           q         Toast.makeText(this, " Please enter Artist  Name ",Toast.LENGTH_SHORT).show();
                    return;
                }
                Cursor c=db.rawQuery("SELECT * FROM Album WHERE artistName ='"+ editsearch.getText()+"'", null);
                if(c.moveToFirst())
                {
                    db.execSQL("DELETE FROM Album WHERE artistName ='"+ editsearch.getText()+"'");
                    Toast.makeText(this, "Record Deleted",Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Toast.makeText(this, "Invalid Artist Name ",Toast.LENGTH_SHORT).show();
                }
            }
            //Select all records
            else if (v.getartID() == R.id.button2)
            {
                //code for select all data
                Cursor c=db.rawQuery("SELECT * FROM Album", null);
                if(c.getCount()==0)
                {
                    Toast.makeText(this, "No records found",Toast.LENGTH_SHORT).show();
                    return;
                }
                StringBuffer buffer=new StringBuffer();
                while(c.moveToNext())
                {
                    buffer.append("artistName: "+c.getString(1)+"\n");
                    buffer.append("Employee AlbumName: "+c.getString(2)+"\n\n");

                }
                Toast.makeText(this, buffer.toString(),Toast.LENGTH_SHORT).show();
            }

            //Select a particular record
            else if(v.getId()==R.id.button4)
            {
                //code for select particular data
                if(editsearch.getText().toString().trim().length()==0)
                {
                    Toast.makeText(this, "Enter Artist Name",Toast.LENGTH_SHORT).show();
                    return;
                }
                Cursor c=db.rawQuery("SELECT * FROM Artist WHERE artistName='"+editsearch.getText()+"'", null);
                if(c.moveToFirst())
                {
                    editartistName.setText(c.getString(1));
                    editartistAlbum.setText(c.getString(2));

                }
                else
                {
                    Toast.makeText(this, "Invalid Album AtistName",Toast.LENGTH_SHORT).show();
                }
            }


        }
    }

}

*/