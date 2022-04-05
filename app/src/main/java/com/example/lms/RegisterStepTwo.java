package com.example.lms;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;
import java.util.Objects;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterStepTwo extends AppCompatActivity {
    EditText editText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_step_two);

        Objects.requireNonNull(getSupportActionBar()).hide();

        if (Build.VERSION.SDK_INT >= 21) {
            Window window = this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(this.getResources().getColor(R.color.yellow_200));
        }


        editText = findViewById(R.id.DateOfBirth);

        editText.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                DialogFragment dialogfragment = new DatePickerDialogTheme1();

                dialogfragment.show(getFragmentManager(), "Theme 1");

            }

        });

    }

    public static class DatePickerDialogTheme1 extends DialogFragment implements DatePickerDialog.OnDateSetListener {

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState){
            final Calendar calendar = Calendar.getInstance();
            int year = calendar.get(Calendar.YEAR);
            int month = calendar.get(Calendar.MONTH);
            int day = calendar.get(Calendar.DAY_OF_MONTH);

            DatePickerDialog datepickerdialog = new DatePickerDialog(getActivity(),
                    AlertDialog.THEME_DEVICE_DEFAULT_DARK,this,year,month,day);

            return datepickerdialog;
        }

        public void onDateSet(DatePicker view, int year, int month, int day){

            EditText textview = getActivity().findViewById(R.id.DateOfBirth);

            textview.setText(day + "-" + (month+1) + "-" + year);
        }
    }

    public void OnClickRegister(View view){
        //Extraction
        EditText firstname = findViewById(R.id.FirstName);
        EditText lastname = findViewById(R.id.LastName);
        EditText phonenumber = findViewById(R.id.PhoneNumber);
        EditText gender = findViewById(R.id.Gender);
        EditText dob = findViewById(R.id.DateOfBirth);

        //values
        String firstnameval = firstname.getText().toString();
        String lastnameval = lastname.getText().toString();
        Editable phonenumberval = phonenumber.getText();
        String genderval = gender.getText().toString();
        String dobval = dob.getText().toString();

        Log.i("FIRST NAME", firstnameval);
        Log.i("LAST NAME", lastnameval);
        Log.i("PHONE NUMBER", String.valueOf(phonenumberval));
        Log.i("Gender", genderval);
        Log.i("DATE OF BIRTH", dobval);

        if(firstnameval.matches("") &&
                lastnameval.matches("") &&
                phonenumberval.length() == 0 &&
                genderval.matches("") &&
                dobval.matches("")
        ){
            Toast.makeText(RegisterStepTwo.this,"All fields are required",Toast.LENGTH_SHORT).show();
        }else{
            int Currentyear = Calendar.getInstance().get(Calendar.YEAR);
            String[] arrOfStr = dobval.split("-", -2);
            int selectedYear = Integer.parseInt(arrOfStr[2]);

            System.out.println(arrOfStr[2]);
            int diff = Currentyear - selectedYear;
            if(diff < 14){
                Toast.makeText(RegisterStepTwo.this,"You must be 14 year old",Toast.LENGTH_SHORT).show();
            }else {

                Log.i("FIRST NAME", firstnameval);
                Log.i("LAST NAME", lastnameval);
                Log.i("PHONE NUMBER", String.valueOf(phonenumberval));
                Log.i("Gender", genderval);
                Log.i("DATE OF BIRTH", dobval);

                Intent RI = getIntent();
                CharSequence Reg_Chooser = RI.getStringExtra("SENDER_KEY");

                if(Reg_Chooser.equals("LIB")){
                    Intent i = new Intent(RegisterStepTwo.this, dashboard.class);
                    startActivity(i);
                }else{
                    Intent i = new Intent(RegisterStepTwo.this, student_dashboard.class);
                    startActivity(i);
                }

            }
        }
    }


}