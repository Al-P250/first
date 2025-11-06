package com.AlP250.first;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    int contador;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


        contador=0;

        Button mainButton=findViewById(R.id.mainButton);
        TextView mainTV=findViewById(R.id.mainTV);
        Bundle bundle=getIntent().getExtras();
        if (bundle!=null){
            String tiempo=String.valueOf(bundle.getLong("tiempo invertido", 0));
            mainTV.setText(tiempo+" has tardado en llenar el login.");

        }
        mainTV.setText(contador+"");

        mainButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                contador++;
                mainTV.setText(String.valueOf(contador));
            }
        });
    }
}