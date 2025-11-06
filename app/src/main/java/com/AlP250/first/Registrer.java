package com.AlP250.first;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.textfield.TextInputLayout;

import org.mindrot.jbcrypt.BCrypt;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Registrer extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_registrer);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Button registerButton=findViewById(R.id.registerButton);
        TextInputLayout registrerTILusername=findViewById(R.id.registrerTILusername);
        TextInputLayout registerTILemail=findViewById(R.id.registerTILemail);
        TextInputLayout registerTILpassword=findViewById(R.id.registerTILpassword);
        TextInputLayout registerTILconfirmPassword=findViewById(R.id.registerTILconfirmPassword);

        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor=sharedPref.edit();

        RegisterUtilities registerUtilities=new RegisterUtilities();


        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean canContinue=true;
                if (registerUtilities.isTILEmpty(registrerTILusername)){
                    registrerTILusername.setErrorEnabled(true);
                    registrerTILusername.setError("Nombre vacío");
                    canContinue=false;
                }if (!isEmailCorrect(registerTILemail)) {
                    registerTILemail.setErrorEnabled(true);
                    registerTILemail.setError("Email incorrecto");
                    canContinue=false;
                }if (!arePasswordsTheSame(registerTILpassword,registerTILconfirmPassword)){
                    registerTILconfirmPassword.setErrorEnabled(true);
                    registerTILconfirmPassword.setError("La contraseña no es válida");
                    if (registerUtilities.isTILEmpty(registerTILpassword)&&registerUtilities.isTILEmpty(registerTILconfirmPassword)){
                        Toast.makeText(Registrer.this,"No has escrito nada en el campo contraseña", Toast.LENGTH_SHORT).show();
                    }else {
                        Toast.makeText(Registrer.this,"Las contraseñas no coinciden", Toast.LENGTH_SHORT).show();
                    }
                    canContinue=false;
                }if(canContinue) {
                    editor.putString("userName",String.valueOf(registrerTILusername.getEditText().getText()));
                    editor.putString("email",String.valueOf(registerTILemail.getEditText().getText()));
                    editor.putString("password", registerUtilities.generateHashedPassword(registerUtilities.getTILText(registerTILpassword)));
                    editor.apply();

                    Intent intentMain = new Intent(Registrer.this, Login.class);

                    startActivity(intentMain);
                }
            }
//            public boolean isUserNameEmpty(TextInputLayout userNameTIL){
//                String username=String.valueOf(userNameTIL.getEditText().getText());
//                return (username.isEmpty());
//            }

            public boolean isEmailCorrect(TextInputLayout email){
                String correo=String.valueOf(email.getEditText().getText());
                String emailPattern="^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$";
                Pattern p=Pattern.compile(emailPattern);
                Matcher m=p.matcher(correo);
                return m.find();
            }

            public boolean arePasswordsTheSame(TextInputLayout contrasenia, TextInputLayout comprobation) {
                String password=String.valueOf(contrasenia.getEditText().getText());
                String comprobacion=String.valueOf(comprobation.getEditText().getText());
                return password.equals(comprobacion) && !password.isEmpty();

            }



        });
    }
}