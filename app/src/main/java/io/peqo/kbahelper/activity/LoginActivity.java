package io.peqo.kbahelper.activity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.peqo.kbahelper.R;
import io.peqo.kbahelper.database.SQLiteHandler;
import io.peqo.kbahelper.model.User;
import io.peqo.kbahelper.network.ApiConnection;
import io.peqo.kbahelper.util.SessionManager;
import okhttp3.Response;

public class LoginActivity extends Activity {

    private static final String TAG = LoginActivity.class.getSimpleName();

    @BindView(R.id.login_btn) Button btnLogin;
    @BindView(R.id.login_username) TextView inputUsername;
    @BindView(R.id.login_password) EditText inputPassword;

    private ProgressDialog dialog;
    private SessionManager session;
    private SQLiteHandler db;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        dialog = new ProgressDialog(this);
        dialog.setCancelable(false);

        db = new SQLiteHandler(getApplicationContext());
        session = new SessionManager(getApplicationContext());

        if(session.isLoggedIn()) {
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        }
    }

    @OnClick(R.id.login_btn)
    public void login() {
        final String username = inputUsername.getText().toString().trim();
        final String password = inputPassword.getText().toString().trim();

        if(!username.isEmpty() && !password.isEmpty()) {
            dialog.setMessage("Logger ind...");
            dialog.show();
            String[] credentials = {username, password};
            new VerifyUserCredentials().execute(credentials);
        } else {
            Toast.makeText(getApplicationContext(), "Forkert input.", Toast.LENGTH_LONG).show();
        }
    }

    private class VerifyUserCredentials extends AsyncTask<String, Void, Boolean> {

        @Override
        protected Boolean doInBackground(String... strings) {
            final String username = strings[0];
            final String password = strings[1];
            final ObjectMapper mapper = new ObjectMapper();

            Map<String, String> data = new HashMap<>();
            data.put("username", username);
            data.put("password", password);

            try {
                Response response = ApiConnection.open("http://207.154.199.94/api/login")
                        .syncPostRequest(mapper.writeValueAsString(data));
                Log.d("TAG", "Posted data to server: " + mapper.writeValueAsString(data));

                if (response != null && response.isSuccessful()) {
                    JSONObject obj = new JSONObject(response.body().string());
                    int status = obj.getInt("status");

                    if (status == 0) {
                        session.setLogin(true);
                        User user = new User.Builder()
                                .setId(obj.getLong("id"))
                                .setFirstName(obj.getString("first_name"))
                                .setLastName(obj.getString("last_name"))
                                .setUsername(obj.getString("username"))
                                .build();
                        db.addUser(user);
                        return true;
                    }
                }
            } catch (Exception e) {
                Log.d(TAG, "Error: " + e);
            }
            return false;
        }

        @Override
        protected void onPostExecute(Boolean result) {
            if(result) {
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            } else {
                Toast.makeText(getApplicationContext(), "Prøv venligst igen.", Toast.LENGTH_SHORT).show();
            }
            dialog.dismiss();
        }
    }
}
