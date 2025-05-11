package com.example.k22411casampleproject;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.connectors.EmployeeConnector;
import com.example.models.Employee;

public class LoginActivity extends AppCompatActivity {

    EditText edtUserName;
    EditText edtPassword;
    CheckBox chkSaveLogin;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);
        addViews(); //hàm này phải đứng sau setcontentview
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    private void addViews() {
        edtUserName=findViewById(R.id.edtUserName);
        edtPassword=findViewById(R.id.edtPassword);
        chkSaveLogin=findViewById(R.id.chkSaveLoginInfor);
    }

    public void do_login(View view) {
        EmployeeConnector ec=new EmployeeConnector();

        String usr=edtUserName.getText().toString();
        String pwd=edtPassword.getText().toString();

        Employee e_login=ec.login(usr,pwd);
        if (e_login!=null)
        {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }
        else
        {
            Toast.makeText(this, "Login failed! Check your account again!",Toast.LENGTH_LONG).show();
        }
    }

    public void do_exit(View view) {
        AlertDialog.Builder builder=new AlertDialog.Builder(LoginActivity.this);
        //Thiết lập tiêu đề
        builder.setTitle(getResources().getText(R.string.title_confirm_exit_title));
        builder.setMessage(getResources().getText(R.string.title_confirm_exit_message));
        builder.setIcon(android.R.drawable.ic_dialog_alert);

        //Thiết lập tương tác người dùng
        builder.setPositiveButton(getResources().getText(R.string.title_confirm_exit_ok), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int i) {
                System.exit(0);
            }
        });
        builder.setNegativeButton(getResources().getText(R.string.title_confirm_exit_cancel), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int i) {
                dialog.cancel();
            }
        });
        AlertDialog dialog=builder.create();
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();

    }

}