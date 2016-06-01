package example.codeclan.com.wearablenotificationtest;

import android.app.NotificationManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.app.Notification;
import android.app.PendingIntent;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;

//Handling Custom Notifications

import android.view.View;
import android.widget.Button;
import android.widget.EditText;

//Handling Voice Notifications
import android.support.v4.app.RemoteInput;
import android.util.Log;





public class MainActivity extends AppCompatActivity {


    //add our notification message ID
    //This value can be any arbitrary string
    // value, but each unique notification will need to
    // have it's own ID as it will be responsible for
    // sending notification message between our handheld
    // device and the wearable.

    int Notification_ID = 001;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Clear all previous notifications and
        // generate new unique ids
        NotificationManagerCompat.from(this).cancelAll();

        //Intent is used to bind itself to the current activities
        //connection at runtime to send messages from one component
        //to another within the same application context

    Intent intent = new Intent(this, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0,
                intent, PendingIntent.FLAG_UPDATE_CURRENT);

        // Method to display our basic notification
        displayBasicNotification(pendingIntent);

    }

    // Method for displaying our basic notification message
    public void displayBasicNotification(PendingIntent pendingIntent){
        // Set up our Notification Action class method
        //1, gets the icon to display; 2, gets the title of the notification; 3,pendingIntent
        NotificationCompat.Action action = new NotificationCompat.Action.Builder(R.mipmap.ic_launcher,
                getString(R.string.notification_title), pendingIntent).build();

        //now we are actually going to create the notification object to send
        Notification notification = new NotificationCompat.Builder(MainActivity.this).
                setContentText(getString(R.string.basic_notify_msg)).
                setContentTitle(getString(R.string.notification_title)).
                setSmallIcon(R.mipmap.ic_launcher).extend(new NotificationCompat.WearableExtender()).
                addAction(action).build();
        //We use the extend property to add support for wearable devices
        //and pass our action variable so that actions can be responded
        //to by these devices.

        NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from((MainActivity.this));
        notificationManagerCompat.notify(Notification_ID, notification);

    }
}
