package com.owcsx.bikky.deardiary;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Note extends AppCompatActivity {
    SQLiteDatabase db;
    TextView tv;
    EditText et1,et2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);
        //initialize all view objects
        tv=(TextView)findViewById(R.id.textView1);
        et1=(EditText)findViewById(R.id.editText1);
        et2=(EditText)findViewById(R.id.editText2);
        //create database if not already exist
        db= openOrCreateDatabase("Mydb", MODE_PRIVATE, null);
        //create new table if not already exist
        db.execSQL("create table if not exists mytable(Title varchar, Memory varchar)");
    }
    //This method will call on when we click on insert button
    public void insert(View v)
    {
        String Title=et1.getText().toString();
        String Memory=et2.getText().toString();
        et1.setText("");
        et2.setText("");
        //insert data into able
        db.execSQL("insert into mytable values('"+Title+"','"+Memory+"')");
        //display Toast
        Toast.makeText(this, "Memory Added.", Toast.LENGTH_LONG).show();
    }

    //This method will call when we click on display button
    public void display(View v)
    {
        //use cursor to keep all data
        //cursor can keep data of any data type
        Cursor c=db.rawQuery("select * from mytable", null);
        tv.setText("");
        //move cursor to first position
        c.moveToFirst();
        //fetch all data one by one
        do
        {
            //we can use c.getString(0) here
            //or we can get data using column index
            String Title=c.getString(c.getColumnIndex("Title"));
            String Memory=c.getString(1);
            //display on text view
            tv.append("Title:"+Title+" and Memory:"+Memory+"\n");
            //move next position until end of the data
        }while(c.moveToNext());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_activity1, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.item1:
                Intent i1=new Intent(this, Activity1.class);
                startActivity(i1);
                break;

            case R.id.item2:
                break;

            case R.id.item3:
                Intent i3=new Intent(this, About.class);
                startActivity(i3);
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
