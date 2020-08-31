package com.project.machinetest;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.project.machinetest.adapter.UserAdapter;
import com.project.machinetest.model.AccountDetails;
import com.project.machinetest.model.User;
import com.project.machinetest.remotedatasource.ApiClient;
import com.project.machinetest.remotedatasource.ApiInterface;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.project.machinetest.app.MachineTestApp.getUserId;
import static com.project.machinetest.app.MachineTestApp.setUserId;

public class HomeActivity extends AppCompatActivity {

    List<User> userList=new ArrayList<>();
    RecyclerView rvUserList;
    UserAdapter adapter;
    TextView tvLogout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        tvLogout=(TextView) findViewById(R.id.tvLogout);
        rvUserList=(RecyclerView)findViewById(R.id.rvUserList);
        rvUserList.setLayoutManager(new LinearLayoutManager(this));

        getUserList();

        tvLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                logout();
            }
        });

    }

    private void logout() {
        new AlertDialog.Builder(HomeActivity.this)
                .setTitle(R.string.app_name)
                .setMessage("Are you sure you want logout!")
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        setUserId(null);
                        Intent intent=new Intent(HomeActivity.this,MainActivity.class);
                        startActivity(intent);
                    }
                })
                .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
    }

    private void getUserList() {
        //String userId=getIntent().getStringExtra("userId");
        String userId=getUserId();
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<AccountDetails> call = apiService.getUsers(userId);
        call.enqueue(new Callback<AccountDetails>() {
            @Override
            public void onResponse(Call<AccountDetails>call, Response<AccountDetails> response) {
                try {
                    String GetHdAccountsAndMenusResult=response.body().getGetHdAccountsAndMenusResult();
                    JSONObject obj=new JSONObject(GetHdAccountsAndMenusResult);
                    JSONArray arrayList=obj.getJSONObject("account").getJSONArray("rows");
                    for(int i = 0; i < arrayList.length(); i++)
                    {
                        String fullaccountnumber = arrayList.getJSONObject(i).getString("fullaccountnumber");
                        String headname = arrayList.getJSONObject(i).getString("headname");
                        String customername = arrayList.getJSONObject(i).getString("customername");
                        userList.add(new User(fullaccountnumber,headname,customername));
                    }
                    //userList = response.body();
                    if(userList.size()>0){
                        displayData(userList);
                    }else{
                        Toast.makeText(HomeActivity.this, "No user found", Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<AccountDetails>call, Throwable t) {
                Toast.makeText(HomeActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void displayData(List<User> userList) {
        adapter=new UserAdapter(HomeActivity.this,userList);
        rvUserList.setAdapter(adapter);
    }

}
