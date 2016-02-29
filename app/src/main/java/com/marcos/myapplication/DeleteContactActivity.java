package com.marcos.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.activeandroid.query.Delete;
import com.activeandroid.query.Select;
import com.marcos.myapplication.Models.Contacts;

import java.util.ArrayList;
import java.util.List;

public class DeleteContactActivity extends AppCompatActivity {

    String               username;
    Bundle               bundle;
    Spinner              spinnerOptions;
    ArrayList<String>    spinnerArrayList;
    ArrayAdapter<String> adapterOptions;
    Button               buttonDelete;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_contact);
        InitVariables();
        InitArrayList();
        InitSpinner();
        ClickEventDeleteContact();
    }
    private void InitVariables() {
        spinnerOptions    = (Spinner)findViewById(R.id.spinnerOptions);
        spinnerArrayList  = new ArrayList<>();
        bundle            = getIntent().getExtras();
        buttonDelete      = (Button)findViewById(R.id.bt_deletecontact);
    }

    private void InitSpinner() {
        adapterOptions = new ArrayAdapter<>(getApplicationContext(),
                R.layout.item_list, spinnerArrayList);
        spinnerOptions.setAdapter(adapterOptions);
    }

    private void InitArrayList() {
        username               = bundle.getString("user");
        List<Contacts> itemsDb = new Select(new String[]{"Id,name"}).from(Contacts.class)
                .where("user = ?", username).execute();
        spinnerArrayList.clear();
        for(Contacts i: itemsDb){
            spinnerArrayList.add(i.getName());
        }
    }

    private void ClickEventDeleteContact(){
        buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String contactName     = spinnerOptions.getSelectedItem().toString();
                new Delete().from(Contacts.class)
                        .where("name = ? and user = ?", contactName, username).execute();
                InitArrayList();
                InitSpinner();
            }
        });
    }
}
