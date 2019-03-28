package com.example.pc.lunerplayer;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class ContactUsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_us);
    }
    public void send_click(View v)
    {
        EditText name = (EditText) findViewById(R.id.EditText_name);
        EditText email = (EditText) findViewById(R.id.EditText_email);
        EditText mesg = (EditText) findViewById(R.id.message);

        String nam=name.getText().toString();
        String em=email.getText().toString();
        String ms=mesg.getText().toString();


        if(name.getText().toString().equals(""))
            name.setError("Mandatory field");
        else if(email.getText().toString().equals(""))
            email.setError("Mandatory field");
        else if(mesg.getText().toString().equals(""))
            mesg.setError("Mandatory field");
        else
        {
            Intent i = new Intent(Intent.ACTION_SENDTO);
            i.setData(Uri.parse("mailto:"));
            i.putExtra(i.EXTRA_TEXT,nam);
            i.putExtra(i.EXTRA_EMAIL,new String[]{"thaviduis@outlook.com"});
            i.putExtra(i.EXTRA_TEXT,"dear Luner Player"+mesg.getText().toString());
            i.putExtra(i.EXTRA_TEXT,em);

             i.setType("message/rfc822");
            startActivity( Intent.createChooser(i,"send mail"));

            try{
                startActivity( Intent.createChooser(i,"send mail"));
            }
            catch(android.content.ActivityNotFoundException ex)
            {
                Toast.makeText(this,"no mail app found",Toast.LENGTH_SHORT).show();
            }
            catch(Exception e)
            {
                Toast.makeText(this,"unexpected error" +e.toString(),Toast.LENGTH_SHORT);
            }

        }

    }
}
