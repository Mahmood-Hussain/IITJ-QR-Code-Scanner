package com.kyadapaan.iitjqrcodescanner;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import com.journeyapps.barcodescanner.CaptureActivity;

public class ScanQRCodeActivity extends AppCompatActivity {

    private Button scanButton;
    private TextView resultTextView;
    private String privateKey = "MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQDMtkV0DGVYFrNuF0NyTlzbs46c\n" +
            "5d8INlwE/69u1/wiBxnBYyL8sgcXjGKdliSZvHuP3QXbNJ988xRKBUixCgkVHPCmXDFhbg5taFhC" +
            "K2pzXQvtxG7A2QffiYxXhz0e6TiFJqvkQ9spVRKsoPZ9mZU8wNU3amDKKz+/tEOBzXSvgD8xdEPE" +
            "O5VIhQuRwljyBh4lKlHSp81MF/YX3Et8/rkxCWdd+Q8jVYVPmXPTHNrp0iWpwVFHhGd1ShDua9Ut" +
            "yMcHTOaVHQZIYprJ7PLHMuAaC2p26GiEpwmqcWjiL7zJWvUHIVUguUkgKpgD02uz3uKxRHBYcrCq" +
            "M3vADF6B7GhFAgMBAAECggEAHcebOWtPN94PiqGgOmnraLuJcMog5JsxzjlazjtlzpZtWPxgu9Vz" +
            "IioWM3oCx4mLiQ1cW3vfgqgC6NS1cym0Ugm0eWL6fQOrXh+ajT+vG+qqgFN90BiSZGKoU5hVHCDN" +
            "y9LpGQsFABGD5rCAwxs7QkwECGx1eZuKicsBsgmPIrN44UpzdBs0Qk5DZxqJ1DUtLHJN5ZpHh/VQ" +
            "H0fozSB7g6O4s+kakSRWhus0yZ9nYJdj8/A1+vE4cZi7N7KTcBr9xtylvuAhnbpPAikt2kkaejur" +
            "8P28TL04DwjF5oTk/9xrb7cmOz1LR8UqZDjHWoOMWpvz95hvNucsRTM7lmQfmQKBgQDzzLsYzPmx" +
            "FMKXuTG4VX7Zs3kejL9+R5AVer/YjveHIFZIBA0boOcN6H7yGQQrIilnd331YRinQ3rn2yQka5F/" +
            "OmC5GUL+QTD2y4IxqhXPDgUOPwf+SVQY2pDIsKUuFOG9g0biIGm8fM+xzg8lXNsKX4VS37yUnXof" +
            "Cx1/G2ggeQKBgQDW9MufO702dMthxXz68sNrQV2uchFH+5mXQGkaN3Q0PmHKxU0b5R10yNTtgH5q" +
            "U9G7NrIt5E7A8dKu3EeBbVvgS/mG5L7ZxcxGV3dvUR0SrQgwLTmdulAscz4ejspL/ACrnNY2+gNl" +
            "ijdj0Crthp6W+j9zu3Dv8We79EG1waWLLQKBgQCVkFqTW2Qu44qBY8aBzElWqsi6GiR8BtD4rGY3" +
            "J3Xo+siTw5B7C5nTqXb/dKNfBsUZyW+HcnqaWkZzKLgic689uwWNaFb1/e5Y6P5FE4/vhy1I0RhJ" +
            "QpkJ6kP6KowbGgpmpbt9i97yGCuea8hU5oMl+PWkosdg8L7zfnEpAho+GQKBgEW82arpaLOYJLut" +
            "pKSNyhSr+ka0PyMJX4UQooic4Rhp/LQaWq/oHyxaU6pR+7FnTC3eyuPUpDA4xE9yq8gzwJD+VRAM" +
            "elaQs3tGuTrmJ/Z9KKDoqz6eyZACO28SiFMqiNUkZuMjLaLtupapXKP+/sigm6FieHt/6FW0IvVi" +
            "w+BpAoGBAMztSayveMTEvOalEQfwcue6srOPPWPizRAXMNfR8ehYC7tKYhNt9ZtpVw4kJ2v22cER" +
            "X0EUdh0ssf4oXXufcEB+hK1cW1Cem6EjSEy1792Zm07HhyhVHTZ4yti1PiX70oFGYYPBCqA3M6wT" +
            "MaCkSBC2PMNlMEWSl4YlGgxRzp6C";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan_qrcode);

        scanButton = findViewById(R.id.scanButton);
        resultTextView = findViewById(R.id.resultTextView);

        ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.CAMERA}, PackageManager.PERMISSION_GRANTED);

        scanButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                IntentIntegrator intentIntegrator = new IntentIntegrator(ScanQRCodeActivity.this);
                intentIntegrator.initiateScan();

                IntentIntegrator integrator = new IntentIntegrator(ScanQRCodeActivity.this);
                integrator.setPrompt("Scan a barcode");
                integrator.setCameraId(0);  // Use a specific camera of the device
                integrator.setOrientationLocked(true);
                integrator.setBeepEnabled(true);
                integrator.initiateScan();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        IntentResult intentResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if (intentResult != null){
            resultTextView.setVisibility(View.VISIBLE);
            if (intentResult.getContents() == null){
                String cancelled = "CANCELLED SCAN";
                resultTextView.setText(cancelled);
            }else{
                String intentQRCode = intentResult.getContents();
                Log.i("QR Code Text: ", intentQRCode);
//                Toast.makeText(this, intentQRCode, Toast.LENGTH_LONG).show();
                // Decrypt code
                try {
                    RSAEncrypt rsaEncrypt = new RSAEncrypt();
                    String decryptedQRCode = rsaEncrypt.Decrypt(intentQRCode, privateKey);
                    resultTextView.setText(decryptedQRCode);
                    Log.i("Decrypted QR Code: ", decryptedQRCode);
                } catch (Exception e){
                    e.printStackTrace();
                }

            }
        }


        super.onActivityResult(requestCode, resultCode, data);
    }
}

/*
2020-07-26 12:44:28.751 23360-23360/com.kyadapaan.iitjqrcodescanner I/Plain Text: Mahmood
2020-07-26 12:44:28.751 23360-23360/com.kyadapaan.iitjqrcodescanner I/Hashed Text: 7e230009f38144dac6632d29d9bf1a5cbcdb9a589791342f42b2a1925e7b836d
2020-07-26 12:44:28.751 23360-23360/com.kyadapaan.iitjqrcodescanner I/Encrypted Text: 1yvdbc23d3ith1l2s3sg41u4wf3998ysw3qyumfynojxe0bckcd5fgu0d7wjgvac0xne5o1axidhyrer58nh08103cxjwwhf2r9r3m1asj7cqstev77sbtpguy286chsr37ra7823hb9vqmt3af2gprtgug2ajj2ellzb5nvanzgk4l3d71s4drusq3cj7ktwr1tojo
2020-07-26 12:44:28.757 23360-23360/com.kyadapaan.iitjqrcodescanner I/Decrypted Text: 7e230009f38144dac6632d29d9bf1a5cbcdb9a589791342f42b2a1925e7b836d
 */
