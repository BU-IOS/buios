package com.buios.buios;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class RegisterActivity extends AppCompatActivity {
    EditText edit_id, edit_pw, edit_email;
    Button register, cancel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        edit_id=findViewById(R.id.edit_id);
        edit_pw=findViewById(R.id.edit_pw);
        edit_email=findViewById(R.id.edit_email);
        register=findViewById(R.id.register);
        cancel=findViewById(R.id.cancel);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String idValue=edit_id.getText().toString();
                String pwValue=edit_pw.getText().toString();
                String emailValue=edit_email.getText().toString();
            }
        });


    }
}