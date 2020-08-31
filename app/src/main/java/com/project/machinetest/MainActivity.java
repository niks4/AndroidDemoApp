package com.project.machinetest;

import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.project.machinetest.model.Credential;
import com.project.machinetest.model.Result;
import com.project.machinetest.model.User;
import com.project.machinetest.remotedatasource.ApiClient;
import com.project.machinetest.remotedatasource.ApiInterface;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.project.machinetest.app.MachineTestApp.getUserId;
import static com.project.machinetest.app.MachineTestApp.setUserId;

public class MainActivity extends AppCompatActivity {

    EditText etUsername,etPassword;
    Button btnSubmit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(getUserId()==null){}
        else{
            Intent intent=new Intent(MainActivity.this,HomeActivity.class);
            startActivity(intent);
        }

        init();

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isValidate()){
                    login(etUsername.getText().toString().trim(),etPassword.getText().toString().trim());
                }
            }
        });

    }

    private void login(String userName, String password ) {

        String auth=getBase64Result(userName,password);

        String finalAuth="Basic "+auth.replace("\n","");

        Credential credential=new Credential(userName,password);

        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);

        Call<Result> call = apiService.getLogin(finalAuth,credential);
        call.enqueue(new Callback<Result>() {
            @Override
            public void onResponse(Call<Result>call, Response<Result> response) {
                try {
                    String AuthenticateResult=response.body().getAuthenticateResult();
                    String userid=new JSONObject(AuthenticateResult).getJSONObject("authdatatable").getJSONArray("rows").getJSONObject(0).getString("userid");
                    if(AuthenticateResult!=null || AuthenticateResult!=""){
                        Toast.makeText(MainActivity.this, "Login Successfull", Toast.LENGTH_SHORT).show();
                        Intent intent=new Intent(MainActivity.this,HomeActivity.class);
                        setUserId(userid);
                        //intent.putExtra("userId",userid);
                        startActivity(intent);
                    }else{
                        Toast.makeText(MainActivity.this, "Login failed", Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(MainActivity.this, "Something went wrong", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Result>call, Throwable t) {
                Toast.makeText(MainActivity.this, "Login failed", Toast.LENGTH_SHORT).show();
            }
        });

    }

    private String getBase64Result(String userName, String password) {
        String base64="";
        try {
            String val=userName+":"+password;
            byte[] data = new byte[0];
            data = val.getBytes("UTF-8");
            base64 = Base64.encodeToString(data, Base64.DEFAULT);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return base64;
    }

    private boolean isValidate() {
        if(etUsername.getText().toString().isEmpty()){
            etUsername.setError("* Required");
            return false;
        }else if(etPassword.getText().toString().isEmpty()){
            etPassword.setError("* Required");
            return false;
        }else{
            return true;
        }
    }

    private void init() {
        etUsername=findViewById(R.id.etUsername);
        etPassword=findViewById(R.id.etPassword);
        btnSubmit=findViewById(R.id.btnSubmit);
    }

    @Override
    public void onBackPressed() {
        System.exit(0);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            finishAffinity();
        }
        finish();
    }
}
