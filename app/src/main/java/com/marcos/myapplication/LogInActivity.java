package com.marcos.myapplication;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.activeandroid.query.Select;
import com.marcos.myapplication.Models.Users;
import java.util.List;

public class LogInActivity extends AppCompatActivity {

    EditText et1;
    EditText et2;
    Button   add;
    Button   newuser;
    Intent   intentVar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);
        InitVariables();
        ClickEvent();
        ClickEventNewUser();
    }

    private void InitVariables() {
        et1       = (EditText)findViewById(R.id.et_user_login);
        et2       = (EditText)findViewById(R.id.et_pass_login);
        add       = (Button)findViewById(R.id.btn_acept_login);
        newuser   = (Button)findViewById(R.id.bt_newuser_login);
    }

    private void UserAndPassValidation() {
        String user        = et1.getText().toString();
        String pass        = et2.getText().toString();
        String username    = "";
        String password    = "";
        List<Users> userDB = new Select().from(Users.class).where("User = ?", user.toString()).execute();

        for(Users u : userDB){
            username = u.getUserName();
            password = u.getPassword();
        }

        if(user.equals(username) && pass.equals(password)){
            Toast.makeText(getApplicationContext(), R.string.txt_toast_login_correct,
                    Toast.LENGTH_SHORT).show();
            intentVar = new Intent(getApplicationContext(), MenuActivity.class);
            intentVar.putExtra("user", username);
            startActivity(intentVar);

        }
        else{
            Toast.makeText(getApplicationContext(), R.string.txt_toast_login_error,
                    Toast.LENGTH_SHORT).show();
        }
    }

    private void ClickEvent(){
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UserAndPassValidation();
            }
        });
    }

    private void ClickEventNewUser(){
        newuser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intentVar = new Intent(getApplicationContext(), NewUserActivity.class);
                startActivity(intentVar);
            }
        });
    }
}
