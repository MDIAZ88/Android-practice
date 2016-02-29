package com.marcos.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.activeandroid.query.Select;
import com.marcos.myapplication.Models.Contacts;

import java.util.ArrayList;
import java.util.List;

public class AddContactActivity extends AppCompatActivity {

    EditText    etName;
    EditText    etCelphone;
    EditText    etPhone;
    EditText    etAddress;
    EditText    etBirthday;
    EditText    etEmail;
    RadioGroup  buttonGroup;
    RadioButton buttonRadio;
    Button      btnAcept;
    Button      btnCancel;
    Contacts    itemObj;
    Bundle      bundle;
    String      username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_contact);
        initVariables();
        ClickEventAcept();
        ClickEventCancel();
    }

    private void initVariables() {
        etName      = (EditText)findViewById(R.id.et_name_addcontact);
        etCelphone  = (EditText)findViewById(R.id.et_celphone_addcontact);
        etPhone     = (EditText)findViewById(R.id.et_phone_addcontact);
        etAddress   = (EditText)findViewById(R.id.et_address_addcontact);
        etBirthday  = (EditText)findViewById(R.id.et_birthdate_addcontact);
        etEmail     = (EditText)findViewById(R.id.et_email_addcontact);
        btnAcept    = (Button)findViewById(R.id.btn_acept);
        btnCancel   = (Button)findViewById(R.id.btn_cancel);
        buttonGroup = (RadioGroup)findViewById(R.id.rg_button_group_addcontact);
        bundle      = getIntent().getExtras();
    }

    private void createContact(){
        String name             = etName.getText().toString();
        String celphone         = etCelphone.getText().toString();
        String phone            = etPhone.getText().toString();
        String address          = etAddress.getText().toString();
        String birthdate        = etBirthday.getText().toString();
        String email            = etEmail.getText().toString();
        int selectedId          = buttonGroup.getCheckedRadioButtonId();
        buttonRadio             = (RadioButton)findViewById(selectedId);
        String radioButtonValue = buttonRadio.getText().toString();
        username                = bundle.getString("user");

        if(!name.isEmpty() && !celphone.isEmpty() && !phone.isEmpty() && !address.isEmpty()
               && !birthdate.isEmpty() && !email.isEmpty()) {

            List<Contacts> itemsDb = new Select(new String[]{"Id,name"}).from(Contacts.class)
                    .where("user = ?", username).execute();
            ArrayList<String> arrayList = new ArrayList<>();
            for (Contacts i : itemsDb) {
                arrayList.add(i.getName());
            }

            if (arrayList.contains(name)) {
                Toast.makeText(getApplicationContext(), "The contact name already exits", Toast.LENGTH_SHORT).show();
            } else {
                itemObj = new Contacts();
                itemObj.setName(name);
                itemObj.setCelphone(celphone);
                itemObj.setPhone(phone);
                itemObj.setAddress(address);
                itemObj.setBirthday(birthdate);
                itemObj.setEmail(email);
                itemObj.setCgroup(radioButtonValue);
                itemObj.setUser(username);
                itemObj.save();
                Toast.makeText(getApplicationContext(), "Contact saved", Toast.LENGTH_SHORT).show();
                ClearValues();
                finish();
            }

        }else{
            Toast.makeText(getApplicationContext(), "Please insert the values",
                    Toast.LENGTH_LONG).show();
        }
    }

    private void ClearValues(){
        etName.getText().clear();
        etCelphone.getText().clear();
        etPhone.getText().clear();
        etAddress.getText().clear();
        etBirthday.getText().clear();
        etEmail.getText().clear();
        RadioButton familyRadioButtom  = (RadioButton)findViewById(R.id.rb_family_addcontact);
        familyRadioButtom.setChecked(true);
    }

    private void ClickEventAcept(){
        btnAcept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createContact();
            }
        });
    }

    private void ClickEventCancel(){
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClearValues();
                finish();
            }
        });
    }
}
