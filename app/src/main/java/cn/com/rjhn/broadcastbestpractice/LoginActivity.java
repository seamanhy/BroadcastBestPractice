package cn.com.rjhn.broadcastbestpractice;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;


public class LoginActivity extends BaseActivity {

    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    private EditText etAccount;
    private EditText etPassword;
    private CheckBox cbRememberPassword;
    private Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        etAccount = (EditText) findViewById(R.id.account);
        etPassword = (EditText) findViewById(R.id.password);
        cbRememberPassword = (CheckBox) findViewById(R.id.cb_remember_password);
        boolean isRemember = sharedPreferences.getBoolean("remember_password", false);
        if (isRemember) {
            //将账号密码都设置到输入框
            String strAccount = sharedPreferences.getString("account", "");
            String strPassword = sharedPreferences.getString("password", "");
            etAccount.setText(strAccount);
            etPassword.setText(strPassword);
            cbRememberPassword.setChecked(true);
        }
        btnLogin = (Button) findViewById(R.id.login);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String strAccount = etAccount.getText().toString();
                String strPassword = etPassword.getText().toString();
                if (strAccount.equals("admin") && strPassword.equals("123456")) {
                    editor = sharedPreferences.edit();
                    if (cbRememberPassword.isChecked()) {
                        editor.putString("account", strAccount);
                        editor.putString("password", strPassword);
                        editor.putBoolean("remember_password", true);
                    } else {
                        editor.clear();
                    }
                    editor.commit();

                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(LoginActivity.this, "account or password is invalid", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
}
