package com.kyadapaan.iitjqrcodescanner;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.common.BitMatrix;
import com.journeyapps.barcodescanner.BarcodeEncoder;



    @SuppressLint("Registered")
    public class GenerateQRCodeActivity extends AppCompatActivity {

        private EditText inputText;
        private Button generateCodeBtn, scanBtn;
        private ImageView QRCodeImageView;
        private TextView generatedTextView;

        private String publicKey = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAzLZFdAxlWBazbhdDck5c27OOnOXfCDZcBP+vb" +
                "tf8IgcZwWMi/LIHF4xinZYkmbx7j90F2zSffPMUSgVIsQoJFRzwplwxYW4ObWhYQitqc10L7cRuwNkH34mMV4c9Huk4hSar5EPbKVUS" +
                "rKD2fZmVPMDVN2pgyis/v7RDgc10r4A/MXRDxDuVSIULkcJY8gYeJSpR0qfNTBf2F9xLfP65MQlnXfkPI1WFT5lz0xza6dIlqcFRR4Rn" +
                "dUoQ7mvVLcjHB0zmlR0GSGKayezyxzLgGgtqduhohKcJqnFo4i+8yVr1ByFVILlJICqYA9Nrs97isURwWHKwqjN7wAxegexoRQIDAQAB";

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
            setContentView(R.layout.activity_main);

            inputText = findViewById(R.id.inputText);
            generateCodeBtn = findViewById(R.id.generateCodeBtn);
            QRCodeImageView = findViewById(R.id.QRCodeImageView);
            generatedTextView = findViewById(R.id.generatedTextView);
            scanBtn = findViewById(R.id.scanBtn);



            generateCodeBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    MultiFormatWriter multiFormatWriter = new MultiFormatWriter();

                    try {

                        // Get text from input box
                        String inputTextStr = inputText.getText().toString();
                        // Generate hash of input
                        GetSHA256 getSHA256 = new GetSHA256();
                        String hashedInputStr = getSHA256.toHexString(getSHA256.getSHA(inputTextStr));
                        // Encrypt the hashed text
                        RSAEncrypt rsaEncrypt = new RSAEncrypt();
                        String encryptedText = rsaEncrypt.Encrypt(hashedInputStr, publicKey);

                        BitMatrix bitMatrix = multiFormatWriter.encode(encryptedText, BarcodeFormat.QR_CODE, 300, 300);
                        BarcodeEncoder barcodeEncoder = new BarcodeEncoder();
                        Bitmap bitmap = barcodeEncoder.createBitmap(bitMatrix);
                        QRCodeImageView.setImageBitmap(bitmap);
                        generatedTextView.setVisibility(View.VISIBLE);

                        // Logging
                        Log.i("Plain Text ", inputTextStr);
                        Log.i("Hashed Text ", hashedInputStr);
                        Log.i("Encrypted Text ", encryptedText);

                        // Decrypt text
                        String decryptedStr = rsaEncrypt.Decrypt(encryptedText, privateKey);
                        Log.i("Decrypted Text ", decryptedStr);

                    } catch (Exception e) {

                        e.printStackTrace();
                    }
                }
            });



            scanBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(GenerateQRCodeActivity.this, ScanQRCodeActivity.class);
                    startActivity(intent);
                }
            });
        }
    }

