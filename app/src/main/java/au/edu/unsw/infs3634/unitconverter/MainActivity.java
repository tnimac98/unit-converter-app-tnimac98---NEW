package au.edu.unsw.infs3634.unitconverter;

import static java.lang.Math.round;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.Timer;

public class MainActivity extends AppCompatActivity {

    Button button;
    TextView tvOutput, tv;
    Spinner spinner, s;
    EditText edit;
    RadioGroup control;
    RadioButton rbWeight, rbHeight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv = findViewById(R.id.tv);
        control = findViewById(R.id.rgControl);
        rbWeight = findViewById(R.id.rbWeight);
        rbHeight = findViewById(R.id.rbHeight);
        s = findViewById(R.id.sp1);
        spinner = findViewById(R.id.sp2);

        control.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch(i) {
                    case R.id.rbHeight:
                        tv.setText("Height Mode!");
                        break;
                    case R.id.rbWeight:
                        tv.setText("Weight Mode!");
                        break;
                }
            }
         });

        edit = findViewById(R.id.txInput);
        String[] arraySpinner = new String[]{
                "g", "kg", "lb", "ft", "in", "m"
        };


        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, arraySpinner);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        s.setAdapter(adapter);

        String[] arraySpinner2 = new String[]{
                "g", "kg", "lb", "ft", "in", "m"
        };


        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, arraySpinner2);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter2);

        tvOutput = findViewById(R.id.tvOutput);
        button = findViewById(R.id.btnConvert);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                convert();
            }
        });

    }

    private void convert() {
        String text = s.getSelectedItem().toString();
        String text2 = spinner.getSelectedItem().toString();
        String check = edit.getText().toString();


        if(check.matches("")){
            tv.setText("Please enter value");
            Handler h=new Handler();
            h.postDelayed(new Runnable(){
                public void run(){
                    tv.setText("");
                }
            }, 2000);
            return;
        }

        double text3 = Double.parseDouble(edit.getText().toString());

        if(tv.getText().equals("Height Mode!")){
            if( (text.equals("g") || text.equals("kg") || text.equals("lb")) ||
                    (text2.equals("g") || text2.equals("kg") || text2.equals("lb"))) {

                tv.setText("You cannot compare weights in height mode!");
                tvOutput.setText("");

                Handler h=new Handler();
                h.postDelayed(new Runnable(){
                    public void run(){
                            tv.setText("Height Mode!");
                    }
                }, 3000);

            } else if (text.equals("ft") && text2.equals("in")){
                double text4 = text3*12;
                tvOutput.setText(String.valueOf(text4));
            } else if (text.equals("in") && text2.equals("ft")){
                double text4 = text3/12;
                tvOutput.setText(String.valueOf(text4));
            } else if (text.equals("m") && text2.equals("in")){
                double text4 = text3*39.3701;
                tvOutput.setText(String.valueOf(text4));
            } else if (text.equals("in") && text2.equals("m")) {
                double text4 = text3 / 39.3701;
                tvOutput.setText(String.valueOf(text4));
            } else if (text.equals("ft") && text2.equals("m")) {
                double text4 = text3 / 3.28084;
                tvOutput.setText(String.valueOf(text4));
            } else if (text.equals("m") && text2.equals("ft")) {
                double text4 = text3 * 3.28084;
                tvOutput.setText(String.valueOf(text4));
            } else if(text.equals("m") && text2.equals("m")) {
                tvOutput.setText(String.valueOf(text3));
            } else if(text.equals("ft") && text2.equals("ft")) {
                tvOutput.setText(String.valueOf(text3));
            } else if(text.equals("in") && text2.equals("in")) {
                tvOutput.setText(String.valueOf(text3));
            } else {
                return;
            }

        }
        else if(tv.getText().equals("Weight Mode!")) {
            if((text.equals("ft") || text.equals("in") || text.equals("m")) ||
                    (text2.equals("ft") || text2.equals("in") || text2.equals("m")) ){

                tv.setText("You cannot compare heights in weight mode!");
                tvOutput.setText("");

                Handler h=new Handler();
                h.postDelayed(new Runnable(){
                    public void run(){
                        tv.setText("Weight Mode!");
                    }
                }, 3000);

            } else if(text.equals("g") && text2.equals("kg")){
                double text4 = text3/1000;
                tvOutput.setText(String.valueOf(text4));
            } else if(text.equals("kg") && text2.equals("g")){
                double text4 = text3*1000;
                tvOutput.setText(String.valueOf(text4));
            } else if(text.equals("kg") && text2.equals("lb")){
                double text4 = text3*2.20462;
                tvOutput.setText(String.valueOf(text4));
            } else if(text.equals("lb") && text2.equals("kg")){
                double text4 = text3/2.20462;
                tvOutput.setText(String.valueOf(text4));
            } else if(text.equals("g") && text2.equals("lb")){
                double text4 = text3/1000;
                double text5 = text4*2.20462;
                tvOutput.setText(String.valueOf(text5));
            } else if(text.equals("lb") && text2.equals("g")) {
                double text4 = text3 / 2.20462;
                double text5 = text3 * 1000;
                tvOutput.setText(String.valueOf(text5));
            } else if(text.equals("g") && text2.equals("g")) {
                tvOutput.setText(String.valueOf(text3));
            } else if(text.equals("kg") && text2.equals("kg")) {
                tvOutput.setText(String.valueOf(text3));
            } else if(text.equals("lb") && text2.equals("lb")) {
                tvOutput.setText(String.valueOf(text3));
            } else{
                return;
            }

        } else {
            return;
        }
    }
}