package com.marcos.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.activeandroid.query.Select;
import com.marcos.myapplication.Models.Users;
import java.util.ArrayList;
import java.util.List;

public class NewUserActivity extends AppCompatActivity {

    EditText etNewusername;
    EditText etNewpassword;
    Button   btAddnewuser;
    Users    itemObj;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_user);
        InitVariables();
        ClickEventAddNewUser();
    }

    private void InitVariables() {
        etNewusername = (EditText)findViewById(R.id.et_newuser);
        etNewpassword = (EditText)findViewById(R.id.et_newpassword);
        btAddnewuser  = (Button)findViewById(R.id.btn_adduser_newuser);
    }

    private void AddNewUser(){
        String pass = etNewpassword.getText().toString();
        String user = etNewusername.getText().toString();

        if(!pass.isEmpty() && !user.isEmpty()) {

            List<Users> itemsDb = new Select(new String[]{"Id,User"})
                    .from(Users.class).execute();

            ArrayList<String> arrayList = new ArrayList<>();
            for (Users i : itemsDb) {
                arrayList.add(i.getUserName());
            }

            if (arrayList.contains(user)) {
                Toast.makeText(getApplicationContext(), "The Username already exits",
                        Toast.LENGTH_SHORT).show();
            } else {
                itemObj = new Users();
                itemObj.setUserName(user);
                itemObj.setPassword(pass);
                itemObj.save();
                Toast.makeText(getApplicationContext(), "User saved", Toast.LENGTH_SHORT).show();
                ClearValues();
            }

        }else{
            Toast.makeText(getApplicationContext(), "Please insert the values",
                    Toast.LENGTH_LONG).show();
        }
    }

    private void ClearValues() {
        etNewpassword.getText().clear();
        etNewusername.getText().clear();
    }

    private void ClickEventAddNewUser(){
        btAddnewuser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddNewUser();
            }
        });
    }
}
