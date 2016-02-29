package com.marcos.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.activeandroid.query.Select;
import com.activeandroid.query.Update;
import com.marcos.myapplication.Models.Contacts;

import java.util.ArrayList;
import java.util.List;

public class ModifyContactsDetailsActivity extends AppCompatActivity {
    Bundle            bundle;
    TextView          text;
    ListView          listView;
    String            contact_name;
    String            username;
    EditText          etName;
    EditText          etCelphone;
    EditText          etPhone;
    EditText          etAddress;
    EditText          etBirthday;
    EditText          etEmail;
    EditText          etCgroup;
    ArrayList<String> arrayList;
    Button            buttonUpdate;
    Button            buttonCancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify_contacts_details);
        text         = (TextView)findViewById(R.id.contactname);
        bundle       = getIntent().getExtras();
        contact_name = bundle.getString("contactName");
        username     = bundle.getString("user");
        InitVariables();
        InitListView();
        ShowDataBD();
        ClickEventUpdate();
        ClickEventCancel();

    }

    private void InitListView() {
        arrayList.add("Bot one");
    }

    private void InitVariables() {
        listView     = (ListView)findViewById(R.id.lv_all_contacts);
        arrayList    = new ArrayList<>();
        etName       = (EditText)findViewById(R.id.et_name_modifycontactsdetails);
        etCelphone   = (EditText)findViewById(R.id.et_celphone_modifycontactsdetails);
        etPhone      = (EditText)findViewById(R.id.et_phone_modifycontactsdetails);
        etAddress    = (EditText)findViewById(R.id.et_address_modifycontactsdetails);
        etBirthday   = (EditText)findViewById(R.id.et_birthdate_modifycontactsdetails);
        etEmail      = (EditText)findViewById(R.id.et_email_modifycontactsdetails);
        etCgroup     = (EditText)findViewById(R.id.et_group_modifycontactsdetails);
        buttonUpdate = (Button)findViewById(R.id.btn_update_modifycontact);
        buttonCancel = (Button)findViewById(R.id.btn_cancel_modifycontact);
    }

    private void ShowDataBD() {
        List<Contacts> itemsDb = new Select().from(Contacts.class)
                .where("user = ? and name = ?", username, contact_name).execute();
        arrayList.clear();
        for(Contacts i: itemsDb){
            arrayList.add(i.getName());
            arrayList.add(i.getAddress());
            arrayList.add(i.getEmail());
            arrayList.add(i.getPhone());
            arrayList.add(i.getCelphone());
            arrayList.add(i.getBirthday());
            arrayList.add(i.getCgroup());
        }

        etName.setText(arrayList.get(0));
        etAddress.setText(arrayList.get(1));
        etEmail.setText(arrayList.get(2));
        etPhone.setText(arrayList.get(3));
        etCelphone.setText(arrayList.get(4));
        etBirthday.setText(arrayList.get(5));
        etCgroup.setText(arrayList.get(6));
    }

    private void ClearValues(){
        etName.getText().clear();
        etCelphone.getText().clear();
        etPhone.getText().clear();
        etAddress.getText().clear();
        etBirthday.getText().clear();
        etEmail.getText().clear();
        etCgroup.getText().clear();
    }

    private void updateContactsInDB(){
        new Update(Contacts.class)
                .set(" name = ?, celphone = ?, phone = ?, address = ?, birthday = ?, email = ?, cgroup = ?"
                        , etName.getText().toString(),
                        etCelphone.getText().toString(),
                        etPhone.getText().toString(),
                        etAddress.getText().toString(),
                        etBirthday.getText().toString(),
                        etEmail.getText().toString(),
                        etCgroup.getText().toString())
                .where("user = ? and name = ? ", username, contact_name)
                .execute();
        Toast.makeText(getApplicationContext(), "Contact Modify", Toast.LENGTH_SHORT).show();
        ClearValues();
        finish();
    }

    private void ClickEventUpdate(){
        buttonUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateContactsInDB();
            }
        });
    }
    private void ClickEventCancel(){
        buttonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClearValues();
                finish();
            }
        });
    }
}
