package com.example.lms;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Register extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        Objects.requireNonNull(getSupportActionBar()).hide();

        if (Build.VERSION.SDK_INT >= 21) {
            Window window = this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(this.getResources().getColor(R.color.yellow_200));
        }

        Intent RI = getIntent();
        CharSequence Reg_Chooser = RI.getStringExtra("SENDER_KEY");
        CharSequence SignInKey = RI.getStringExtra("MAIN_ACT_KEY");


        EditText securityKey = findViewById(R.id.SecurityKey);
        Button ButtonContent = findViewById(R.id.btnSignup);
        TextView txtAccount = findViewById(R.id.txtAccount);
        TextView txtSign = findViewById(R.id.txtSignup);

        if(Reg_Chooser != null && Reg_Chooser.equals("STD")){
            securityKey.setVisibility(View.GONE );
        }

        if(Reg_Chooser != null) {
            txtAccount.setText("Already have an account?");
            txtSign.setText("Sign In");
        }

        if(SignInKey != null && SignInKey.equals("SIGNIN")){
            securityKey.setVisibility(View.GONE );
            ButtonContent.setText("Sign In");
        }

    }

    public void SignUpTxtClick(View view){
//        TextView txtSign = findViewById(R.id.txtSignup);
//        if(txtSign.getText().toString().equals("Sign In")){
//            Intent i = new Intent(Register.this,Register.class);
//            i.putExtra("MAIN_ACT_KEY", "SIGNIN");
//            startActivity(i);
//        }else{
//            Intent i = new Intent(Register.this, ChooseReg.class);
//            startActivity(i);
//        }

    }

    public void SignUpBtnClick(View view){
        TextView txtSign = findViewById(R.id.btnSignup);
        Log.i("INFORMATION:: ", txtSign.getText().toString());

        if(txtSign.getText().toString().equals("Sign In")){
            //Run api if access granted then move to dash board
            //extraction
            EditText email = findViewById(R.id.EmailAddress);
            EditText password = findViewById(R.id.Password);

            //values
            String EmailVal = email.getText().toString();
            String PasswordVal = email.getText().toString();
            Pattern VALID_EMAIL_ADDRESS_REGEX =
                    Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

            Log.i("INFORMATIONEmailVal", EmailVal);
            Log.i("INFORMATIONPasswordVal", PasswordVal);

            if(EmailVal.matches("")){
                Toast.makeText(Register.this,"Please enter email",Toast.LENGTH_SHORT).show();
            }else if(PasswordVal.matches("")){
                Toast.makeText(Register.this,"Please enter Password",Toast.LENGTH_SHORT).show();
            }

            Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(EmailVal);
            Boolean IsMatch = matcher.find();
            Log.i("INFORMATIONIsMatch", String.valueOf(IsMatch));
//            if(email.getText().toString().length() > 0 && email.getText().toString().matches("/^([a-zA-Z0-9_\\.-]+\\@[\\da-z\\.-]+\\.[a-z\\.]{2,6})$/;\n")){
//                Toast.makeText(Register.this,"Please enter correct email address",Toast.LENGTH_SHORT).show();
//            }
//            Intent i = new Intent(Register.this,Register.class);
//            i.putExtra("MAIN_ACT_KEY", "SIGNIN");
//            startActivity(i);
        }else{
            //Run api if record not found then go to register step 2
            Intent i = new Intent(Register.this, ChooseReg.class);
            startActivity(i);
        }
    }
}