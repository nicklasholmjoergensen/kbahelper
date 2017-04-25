package io.peqo.kbahelper.network;

import android.content.Intent;
import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import org.json.JSONException;
import org.json.JSONObject;

import io.peqo.kbahelper.activity.MainActivity;

public class MyFirebaseMessingService extends FirebaseMessagingService {

    private static final String TAG = MyFirebaseMessingService.class.getSimpleName();

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        if(remoteMessage.getData().size() > 0) {
            Log.d(TAG, "Payload: " + remoteMessage.getData().toString());
            try {
                JSONObject json = new JSONObject(remoteMessage.getData().toString());
                sendPushNotification(json);
            } catch(Exception e) {
                Log.d(TAG, "Error: " + e);
            }
        }
    }

    private void sendPushNotification(JSONObject json) {
        try {
            JSONObject data = json.getJSONObject("data");

            String title = data.getString("title");
            String message = data.getString("message");

            MyFirebaseNotificationManager notificationManager = new MyFirebaseNotificationManager(getApplicationContext());
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);

            notificationManager.showNotification(title, message, intent);
        } catch (JSONException e) {
            Log.d(TAG, "Error: " + e);
        }
    }

}
