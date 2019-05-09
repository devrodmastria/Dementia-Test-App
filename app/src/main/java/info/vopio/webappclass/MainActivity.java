package info.vopio.webappclass;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button startBtn = (Button)findViewById(R.id.startButton);
        startBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog(getResources().getString(R.string.disclaimerTitle), getResources().getString(R.string.disclaimer));
            }
        });

    }

    private void alertDialog(String title, String message) {
        final AlertDialog.Builder confirmDailog = new AlertDialog.Builder(this);
        confirmDailog.setTitle(title);
        confirmDailog.setMessage(message);

        confirmDailog.setCancelable(false);
        confirmDailog.setPositiveButton("Accept", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                startActivity(new Intent(MainActivity.this, OnBoardingActivity.class));
            }
        });
        confirmDailog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                MainActivity.super.finish();
            }
        });
        confirmDailog.create().show();
    }
}
