package com.marcos.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.activeandroid.query.Select;
import com.marcos.myapplication.Models.Contacts;

import java.util.ArrayList;
import java.util.List;

public class ModifyContactActivity extends AppCompatActivity {

    Intent               intentVar;
    Bundle               bundle;
    String               username;
    ListView             listView;
    ArrayList<String>    arrayList;
    ArrayAdapter<String> arrayAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify_contact);
        InitVariables();
        InitListView();
        InitAdapter();
        ShowDataBD();
        ClickEventListView();
    }
    private void InitListView() {
        arrayList.add("Bot one");
    }

    private void InitAdapter() {
        arrayAdapter=new ArrayAdapter<>(getApplicationContext(),
                R.layout.item_list, R.id.textViewItemList,
                arrayList);
        listView.setAdapter(arrayAdapter);
    }

    private void InitVariables() {
        listView  = (ListView)findViewById(R.id.lv_all_contacts);
        arrayList = new ArrayList<>();
        bundle = getIntent().getExtras();
        username = bundle.getString("user");
    }

    private void ShowDataBD() {
        List<Contacts> itemsDb = new Select(new String[]{"Id,name"}).from(Contacts.class)
                .where("user = ?", username).execute();
        arrayList.clear();
        for(Contacts i: itemsDb){
            arrayList.add(i.getName());
        }
        arrayAdapter.notifyDataSetChanged();
    }

    private void ClickEventListView(){
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String varContactName = listView.getItemAtPosition(position).toString();
                intentVar = new Intent(getApplicationContext(), ModifyContactsDetailsActivity.class);
                intentVar.putExtra("contactName", varContactName);
                intentVar.putExtra("user", username);
                startActivity(intentVar);
            }
        });
    }

}
