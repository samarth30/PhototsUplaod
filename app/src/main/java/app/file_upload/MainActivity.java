package app.file_upload;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.Toast;

import com.nbsp.materialfilepicker.MaterialFilePicker;
import com.nbsp.materialfilepicker.ui.FilePickerActivity;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Pattern;

import droidninja.filepicker.FilePickerBuilder;
import droidninja.filepicker.FilePickerConst;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    private Button button,button1,button2,button3;

    ArrayList<String> filepaths = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = (Button) findViewById(R.id.button);


        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            if(ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED){
                requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},100);
                return;
            }
        }

        enable_button();
    }

    private void enable_button() {

            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    filepaths.clear();
//
                    FilePickerBuilder.getInstance().setMaxCount(4)
                            .setSelectedFiles(filepaths)
                            .setActivityTheme(R.style.AppTheme)
                            .pickPhoto(MainActivity.this);
//                    new MaterialFilePicker()
//                            .withActivity(MainActivity.this)
//                            .withRequestCode(10)
//                            .start();
//                    Intent intent = new Intent();
//                    intent.setType("image/*");
//                    intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
//                    intent.setAction(Intent.ACTION_GET_CONTENT);
//                    startActivityForResult(Intent.createChooser(intent,"Select Picture"), 1);
                }
            });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if(requestCode == 100 && (grantResults[0] == PackageManager.PERMISSION_GRANTED)){
            enable_button();
        }else {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},100);
            }
        }
    }

    ProgressDialog progress;

//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, final Intent data) {
//        if(requestCode == 10 && resultCode == RESULT_OK){
//
//            progress = new ProgressDialog(MainActivity.this);
//            progress.setTitle("Uploading");
//            progress.setMessage("Please wait...");
//            progress.show();
//
//            Thread t = new Thread(new Runnable() {
//                @Override
//                public void run() {
//
//                    File f  = new File(data.getStringExtra(FilePickerActivity.RESULT_FILE_PATH));
//                    String content_type  = getMimeType(f.getPath());
//
//                    String file_path = f.getAbsolutePath();
//                    OkHttpClient client = new OkHttpClient();
//                    RequestBody file_body = RequestBody.create(MediaType.parse(content_type),f);
//
//                    RequestBody request_body = new MultipartBody.Builder()
//                            .setType(MultipartBody.FORM)
//                            .addFormDataPart("token","P1I0VONPV1SPZA6IJEQJ04VM")
//                            .addFormDataPart("title","pentintiadfasfads")
//                            .addFormDataPart("price","7896")
//                            .addFormDataPart("description","aldjf")
//                            .addFormDataPart("image1Featured",file_path.substring(file_path.lastIndexOf("/")+1), file_body)
////                            .addFormDataPart("image2",file_path.substring(file_path.lastIndexOf("/")+1), file_body)
////                            .addFormDataPart("image3",file_path.substring(file_path.lastIndexOf("/")+1), file_body)
////                            .addFormDataPart("image4",file_path.substring(file_path.lastIndexOf("/")+1), file_body)
//                            .build();
//
//                    Request request = new Request.Builder()
//                            .url("https://satyapaulmlk6.pythonanywhere.com/adminuser/createproduct/")
//                            .post(request_body)
//                            .build();
//
//                    try {
//                        Response response = client.newCall(request).execute();
//
//                        if(!response.isSuccessful()){
//                            throw new IOException("Error : "+response);
//                        }
//
//                        progress.dismiss();
//
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }
//
//
//                }
//            });
//
//            t.start();
//
//
//
//
//        }
//    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, final Intent data) {
        super.onActivityResult(requestCode,resultCode,data);


            if (data != null && resultCode == RESULT_OK) {

                progress = new ProgressDialog(MainActivity.this);
                progress.setTitle("Uploading");
                progress.setMessage("Please wait...");
                progress.show();

                filepaths = data.getStringArrayListExtra(FilePickerConst.KEY_SELECTED_PHOTOS);

                Thread t = new Thread(new Runnable() {
                    @Override
                    public void run() {

                        File f = new File(filepaths.get(0));
                        File f2 = new File(filepaths.get(1));
                        File f3 = new File(filepaths.get(2));
                        File f4 = new File(filepaths.get(3));




                        OkHttpClient client = new OkHttpClient();
                        RequestBody file_body1 = RequestBody.create(MediaType.parse("image/*"), f);
                        RequestBody file_body2 = RequestBody.create(MediaType.parse("image/*"), f2);
                        RequestBody file_body3 = RequestBody.create(MediaType.parse("image/*"), f3);
                        RequestBody file_body4 = RequestBody.create(MediaType.parse("image/*"), f4);

                        RequestBody request_body = new MultipartBody.Builder()
                                .setType(MultipartBody.FORM)
                                .addFormDataPart("token", "LXJIS1PZSADNRFB3CIAFD4TH")
                                .addFormDataPart("title", "NEW APP 10")
                                .addFormDataPart("price", "7")
                                .addFormDataPart("description", "a")
                                .addFormDataPart("image1Featured", filepaths.get(0).substring(filepaths.get(0).lastIndexOf("/") + 1), file_body1)
                                .addFormDataPart("image2",filepaths.get(1).substring(filepaths.get(1).lastIndexOf("/")+1), file_body2)
                                .addFormDataPart("image3",filepaths.get(2).substring(filepaths.get(2).lastIndexOf("/")+1), file_body3)
                                .addFormDataPart("image4",filepaths.get(3).substring(filepaths.get(3).lastIndexOf("/")+1), file_body4)
                                .build();

                        Request request = new Request.Builder()
                                .url("https://satyapaulmlk6.pythonanywhere.com/adminuser/createproduct/")
                                .post(request_body)
                                .build();

                        try {
                            Response response = client.newCall(request).execute();
                            if (!response.isSuccessful()) {
                                throw new IOException("Error : " + response);
                            }

                            progress.dismiss();

                        } catch (IOException e) {
                            e.printStackTrace();
                        }


                    }
                });

                t.start();


            }
        }



    private String getMimeType(String path) {

        String extension = MimeTypeMap.getFileExtensionFromUrl(path);

        return MimeTypeMap.getSingleton().getMimeTypeFromExtension(extension);
    }
}
