package com.AlP250.first;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.preference.PreferenceManager;
import android.util.Log;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.textfield.TextInputLayout;

public class Login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Button loginButton=findViewById(R.id.loginButton);
        TextView loginTVRegister=findViewById(R.id.loginTVRegistrer);

        TextInputLayout loginUserNameTIL=findViewById(R.id.loginTILuserName);
        TextInputLayout loginPasswordTIL=findViewById(R.id.loginTILpassword);
        RegisterUtilities registerUtilities=new RegisterUtilities();

        long initialTime = System.currentTimeMillis();

        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(this);
        String hashedPassword = sharedPref.getString("password", "");
        Log.d("hashedPassword", hashedPassword);


        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean canContinue=true;
                if (registerUtilities.isTILEmpty(loginUserNameTIL)){
                    loginUserNameTIL.setErrorEnabled(true);
                    loginUserNameTIL.setError("Necesitas acceder con un nombre de usuario");
                    canContinue=false;
                }if (registerUtilities.isTILEmpty(loginPasswordTIL)){
                    loginPasswordTIL.setErrorEnabled(true);
                    loginPasswordTIL.setError("La contraseña está vacía");
                    canContinue=false;
                }else if (!registerUtilities.checkPassword(registerUtilities.getTILText(loginPasswordTIL), hashedPassword)){
                    loginPasswordTIL.setErrorEnabled(true);
                    loginPasswordTIL.setError("La contraseña es incorrecta");
                    canContinue=false;
                }
                if (canContinue){
                    Intent intentMain = new Intent(Login.this, MainActivity.class);
                    long finalTime=System.currentTimeMillis();
                    intentMain.putExtra("tiempoInvertido", (initialTime-finalTime)/1000);
                    startActivity(intentMain);
                }
            }
        });



        loginTVRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentRegistro=new Intent(Login.this, Registrer.class);
                startActivity(intentRegistro);
            }
        });

    }
}