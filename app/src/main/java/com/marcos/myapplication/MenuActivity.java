package com.marcos.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MenuActivity extends AppCompatActivity {

    Bundle   bundle;
    TextView welcomeMessage;
    Button   buttonAdd;
    Button   buttonDelete;
    Button   buttonShowAll;
    Button   buttonModify;
    Intent   intentVar;
    String   username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        InitVariables();
        ShowWelcomeMessage();
        ClickEventAddContact();
        ClickEventShowContact();
        ClickEventDeleteContact();
        ClickEventModifyContact();
    }

    private void ShowWelcomeMessage() {
        username = bundle.getString("user");
        welcomeMessage.setText("Welcome: " + username);
    }

    private void InitVariables() {
        welcomeMessage = (TextView)findViewById(R.id.txt_welcomeMessage_menu);
        bundle         = getIntent().getExtras();
        buttonAdd      = (Button)findViewById(R.id.btn_addContact_menu);
        buttonDelete   = (Button)findViewById(R.id.btn_deleteContact_menu);
        buttonModify   = (Button)findViewById(R.id.btn_modifyContact_menu);
        buttonShowAll  = (Button)findViewById(R.id.btn_showContact_menu);
    }

    private void ClickEventAddContact(){
        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intentVar = new Intent(getApplicationContext(), AddContactActivity.class);
                intentVar.putExtra("user", username);
                startActivity(intentVar);
            }
        });
    }

    private void ClickEventShowContact(){
        buttonShowAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intentVar = new Intent(getApplicationContext(), ShowContactsActivity.class);
                intentVar.putExtra("user", username);
                startActivity(intentVar);
            }
        });
    }
    private void ClickEventDeleteContact(){
        buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intentVar = new Intent(getApplicationContext(), DeleteContactActivity.class);
                intentVar.putExtra("user", username);
                startActivity(intentVar);
            }
        });
    }
    private void ClickEventModifyContact(){
        buttonModify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intentVar = new Intent(getApplicationContext(), ModifyContactActivity.class);
                intentVar.putExtra("user", username);
                startActivity(intentVar);
            }
        });
    }
}
