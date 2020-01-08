package com.yp.weathermap.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

import com.yp.weathermap.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class IntroduceActivity extends AppCompatActivity {
    @BindView(R.id.edt_name)
    EditText edtName;
    @BindView(R.id.edt_zipCode)
    EditText edtZipCode;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_introduce);
        ButterKnife.bind(this);

    }

    @OnClick(R.id.btn_forecast)
    public void cekData() {
        if( edtName.getText().equals("") || edtZipCode.getText().equals("")){
            Toast.makeText(getApplicationContext(), "Please Fill All Field", Toast.LENGTH_LONG).show();
        }else{
            Intent intent = new Intent(this, MainActivity.class);
            intent.putExtra("fullname", edtName.getText().toString());
            intent.putExtra("zipcode", edtZipCode.getText().toString());
            startActivity(intent);
            finish();
        }
    }
}
