package com.example.a.myapp;

import android.app.Dialog;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

import com.example.a.myapp.Album;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private com.example.a.myapp.RecyclerViewAdapter adapter;
    private RecyclerView.LayoutManager layoutManager;

   // private String albumList;
    private List<Album> albumList;

    EditText inputSearch;


    private  String[] artistName ;

    public MainActivity() {
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        inputSearch=(EditText)findViewById(R.id.inputSearch);


       // adapter =new List<Album>(this.albumList.);

/*
        //search
        inputSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence artistName, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence artistName, int i, int i1, int i2) {
                    MainActivity.this.adapter.getFilter().equals(artistName);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

*/
        //Create model list
        albumList=new ArrayList<>();
        albumList.add(new Album("Pies Descalzos","shakira",R.drawable.shakira_album_cover));
        albumList.add(new Album("Pitbull Starring in Rebelution","pitbull",R.drawable.pitbull_album_cover));
        albumList.add(new Album("Laundry Service","charli XCX",R.drawable.album1));
        albumList.add(new Album("She Wolf"," Michael Jackson",R.drawable.album2));
        albumList.add(new Album("MTV Unplugged","lana del rey",R.drawable.album4));

        albumList.add(new Album("I Am... Sasha Fierce","Kneel",R.drawable.a));
        albumList.add(new Album("Beyoncé", "Beyonce", R.drawable.beyonce_album_cover));
        albumList.add(new Album("Live & Off the Record","Madonna",R.drawable.b));

        albumList.add(new Album("Enrique Iglesias","eminem ",R.drawable.album6));
        albumList.add(new Album("Vivir","Enrique Iglesias",R.drawable.enrique_album_cover));
        albumList.add(new Album("Cosas del Amor","Enrique Iglesias",R.drawable.album7));

        albumList.add(new Album("Dangerously in Love","justin bieber",R.drawable.c));
        albumList.add(new Album("Divana","lata mangeshkar",R.drawable.d));
        albumList.add(new Album("Enrique","Enrique Iglesias",R.drawable.album8));
        albumList.add(new Album("Escape","adele",R.drawable.album9));
        albumList.add(new Album("Sex and Love","Enrique Iglesias",R.drawable.album10));


        albumList.add(new Album("M.I.A.M.I.","Pitbull",R.drawable.pitbull_album_cover));
        albumList.add(new Album("El Mariel","Thomas",R.drawable.album11));
        albumList.add(new Album("Pitbull Starring in Rebelution","shreya ghoshal",R.drawable.e));
        albumList.add(new Album("Hanthana","amarasiri peiris",R.drawable.f));
        albumList.add(new Album("Oba Ma","shashika nisansala",R.drawable.g));
        albumList.add(new Album("Mata Nidanna Be","rookantha gunathilaka",R.drawable.h));

        albumList.add(new Album("Kaluwara Rata","nirosha virajini",R.drawable.i));
        albumList.add(new Album("Aradhana", "Master Amaradewa", R.drawable.t));

        //Bind recyclerView
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        //Indicate a layoutManager
        //It shows items in a vertical or horizontal scrolling list.
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        //Create an adapter and set it
        adapter = new RecyclerViewAdapter(MainActivity.this,albumList);



        //Create custom interface object and send it to adapter
        //Adapter trigger it when any item view is clicked
        adapter.setOnItemClickListener(new RecyclerViewItemClickListener() {

         public void onItemClick(View view, int position) {
                Toast.makeText(MainActivity.this, getResources().getString(R.string.clicked_item, albumList.get(position).getAlbumName()), Toast.LENGTH_SHORT).show();


            }

            @Override
            public void onItemLongClick(View view, int position) {

                showEditDialog(position);
                }
        });



        recyclerView.setAdapter(adapter);
    }



    //search

    /*
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.menu,menu);
        MenuItem searchItem=menu.findItem(R.id.item_search);
        SearchView searchView=(SearchView) MenuItemCompat.getActionView(searchItem);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {

                ArrayList<String>templist=new ArrayList<>();

                for(Album temp:albumList){

                    if (temp.toString().contains(new String())) {

                        templist.add(String.valueOf(temp));
                    }
                }
                return true;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                return false;
            }
        });

        return super.onCreateOptionsMenu(menu);
    }

*/



    private void showEditDialog(final int position){
        final Dialog dialog = new Dialog(MainActivity.this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.edit_dialog);

        //Bind dialog views
        final EditText renameEdittext=(EditText)dialog.findViewById(R.id.rename_edittext);
        final Button renameButton=(Button)dialog.findViewById(R.id.rename_button);
        Button deleteButton=(Button)dialog.findViewById(R.id.delete_button);

        //Set clicked album name to rename edittext
        renameEdittext.setText(albumList.get(position).getAlbumName());

        //When rename button is clicked, first rename edittext should be checked if it is empty
        //If it is not empty, data and listview item should be changed.
        renameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!renameEdittext.getText().toString().equals("")) {
                    albumList.get(position).setAlbumName(renameEdittext.getText().toString());
                    //Notify adapter about changing of model list
                    adapter.notifyDataSetChanged();
                    //Close dialog
                    dialog.dismiss();
                } else {
                    Toast.makeText(MainActivity.this, getResources().getString(R.string.cant_be_empty), Toast.LENGTH_SHORT).show();
                }
            }
        });


        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                albumList.remove(position);
                //Notify adapter about changing of model list
                adapter.notifyDataSetChanged();
                //Close dialog
                dialog.dismiss();
            }
        });


        dialog.show();
    }







    }
