package com.creativityapps.gmailbackground;

import android.os.Bundle;
import android.os.Environment;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.creativityapps.gmailbackgroundlibrary.BackgroundMail;



public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        findViewById(R.id.bt_send_text).setOnClickListener(view -> sendEmail(BackgroundMail.TYPE_PLAIN, null));
        findViewById(R.id.bt_send_html).setOnClickListener(view -> sendEmail(BackgroundMail.TYPE_HTML, null));
        findViewById(R.id.bt_send_attachment).setOnClickListener(view ->
        {
            String fileName = Environment.getExternalStorageDirectory().getPath() + "/Test.txt";
            sendEmail(BackgroundMail.TYPE_PLAIN, fileName);
        });
    }


    private void sendEmail(String type, String fileName) {
        BackgroundMail.Builder builder = BackgroundMail.newBuilder(this)
                .withUsername("username@gmail.com")
                .withPassword("password12345")
                .withSenderName("Your sender name")
                .withMailTo("to-email@gmail.com")
                .withMailCc("cc-email@gmail.com")
                .withMailBcc("bcc-email@gmail.com")
                .withSubject("This is the subject")
                .withType(type)
                .withUseDefaultSession(false)
                .withSendingMessage(R.string.sending_email)
                .withOnSuccessCallback(new BackgroundMail.OnSendingCallback() {
                    @Override
                    public void onSuccess() {
                        Toast.makeText(MainActivity.this, R.string.msg_email_sent_successfully, Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFail(Exception e) {
                        Toast.makeText(MainActivity.this, R.string.msg_error_sending_email, Toast.LENGTH_SHORT).show();
                    }
                });

        if (BackgroundMail.TYPE_HTML.equalsIgnoreCase(type)) {
            builder.withBody("<!DOCTYPE html>\n" +
                    "<html>\n" +
                    "<body>\n" +
                    "\n" +
                    "<h1>This is test html title</h1>\n" +
                    "\n" +
                    "<p>This is test html paragraph.</p>\n" +
                    "\n" +
                    "</body>\n" +
                    "</html>\n");
        } else {
            builder.withBody("This is test plain body");
        }
        if (fileName != null) {
            builder.withAttachments(fileName);
        }

        builder.send();
    }
}
