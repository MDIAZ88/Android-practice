package com.marcos.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.activeandroid.query.Select;
import com.marcos.myapplication.Models.Contacts;

import java.util.ArrayList;
import java.util.List;

public class ShowContactsDetailsActivity extends AppCompatActivity {

    Bundle            bundle;
    TextView          text;
    ListView          listView;
    String            contact_name;
    String            username;
    EditText          etName;
    EditText          etCellphone;
    EditText          etPhone;
    EditText          etAddress;
    EditText          etBirthday;
    EditText          etEmail;
    EditText          etCgroup;
    ArrayList<String> arrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_contacts_details);
        text         = (TextView)findViewById(R.id.contactname);
        bundle       = getIntent().getExtras();
        contact_name = bundle.getString("contactName");
        username     = bundle.getString("user");
        InitVariables();
        NoEditableText();
        InitListView();
        ShowDataBD();
    }

    private void NoEditableText() {
        etName.setKeyListener(null);
        etCellphone.setKeyListener(null);
        etPhone.setKeyListener(null);
        etAddress.setKeyListener(null);
        etBirthday.setKeyListener(null);
        etEmail.setKeyListener(null);
        etCgroup.setKeyListener(null);
    }

    private void InitListView() {
        arrayList.add("Bot one");
    }

    private void InitVariables() {
        listView    = (ListView)findViewById(R.id.lv_all_contacts);
        arrayList   = new ArrayList<>();
        etName      = (EditText)findViewById(R.id.et_name_showcontactsdetails);
        etCellphone = (EditText)findViewById(R.id.et_celphone_showcontactsdetails);
        etPhone     = (EditText)findViewById(R.id.et_phone_showcontactsdetails);
        etAddress   = (EditText)findViewById(R.id.et_address_showcontactsdetails);
        etBirthday  = (EditText)findViewById(R.id.et_birthdate_showcontactsdetails);
        etEmail     = (EditText)findViewById(R.id.et_email_showcontactsdetails);
        etCgroup    = (EditText)findViewById(R.id.et_cgroup_showcontactsdetails);
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
        etCellphone.setText(arrayList.get(4));
        etBirthday.setText(arrayList.get(5));
        etCgroup.setText(arrayList.get(6));
    }

}
