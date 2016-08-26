import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.Properties;

import javax.activation.DataHandler;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import es.uclm.mylittlepets.R;

public class Contacto extends AppCompatActivity {
    private Button button;
    private EditText etNombre, etMensaje, etEmail;
    Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacto);
        asignarViews();
        setSupportActionBar(toolbar);

        //Boton de back en la toolbar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // Añadimos un Listener en el button
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                       try{
                            procesoEnviarMail();
                        }catch (Exception ex){
                            Log.e("Error", "Exception: " + ex.getMessage());                        }*/
                    }
                });

            }
        });
    }

    public void asignarViews(){
        toolbar=(Toolbar) findViewById(R.id.toolbarrr);
        button = (Button)findViewById(R.id.btnButton);
        etNombre = (EditText) findViewById(R.id.etNombre);
        etEmail = (EditText)findViewById(R.id.etEmail);
        etMensaje = (EditText)findViewById(R.id.etMensaje);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void procesoEnviarMail(){
        Properties props = new Properties();

        props.setProperty("mail.smtp.host", "smtp.gmail.com");
        props.setProperty("mail.smtp.starttls.enable", "true");
        props.put("mail.transport.protocol","smtp");
        props.put("mail.smtp.port",25);
        props.setProperty("mail.smtp.user", "ejemplo@gmail.com");
        props.setProperty("mail.smtp.auth", "true");

        Session mailSession = Session.getInstance(props,null);

        Message msg = new MimeMessage(mailSession);

        //Establecemos el asunto, el remitente y el destinatario
        try{
            msg.setSubject(etNombre.getText().toString());
            msg.setFrom(new InternetAddress(etEmail.getText().toString(),etNombre.getText().toString()));
            msg.addRecipients(Message.RecipientType.TO, new InternetAddress[] { new InternetAddress("ejemplo@gmail.com") });
        }catch (javax.mail.MessagingException ex){
            ex.getMessage();
        }
        catch (java.io.UnsupportedEncodingException e){
            e.getMessage();
        }

        //Mensaje que se quiere enviar (cuerpo del mensaje)
        DataHandler dh = new DataHandler(etMensaje.getText().toString(),"text/plain");
        try {
            msg.setDataHandler(dh);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        //Línea para envíar el mensaje
        try {
            javax.mail.Transport.send(msg);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}
