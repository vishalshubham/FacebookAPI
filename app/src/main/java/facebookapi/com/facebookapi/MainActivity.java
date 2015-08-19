package facebookapi.com.facebookapi;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.content.pm.Signature;
import android.view.Menu;
import android.view.MenuItem;

import com.facebook.FacebookSdk;

import java.security.MessageDigest;

public class MainActivity extends AppCompatActivity {

    public static String DEBUGTAG = "VC";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext());
        setContentView(R.layout.activity_main);
        getUserInfo();
    }

    public void getUserInfo(){
        try {
            PackageInfo info = getPackageManager().getPackageInfo(getPackageName(), PackageManager.GET_SIGNATURES);

            for(Signature signature : info.signatures){
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                Log.d(DEBUGTAG, "Key Hash: " + Base64.encodeToString(md.digest(), Base64.DEFAULT));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
