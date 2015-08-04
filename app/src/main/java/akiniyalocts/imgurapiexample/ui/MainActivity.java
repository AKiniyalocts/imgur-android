package akiniyalocts.imgurapiexample.ui;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.EditText;
import android.widget.ImageView;

import com.akiniyalocts.imgur_api.ImgurClient;
import com.akiniyalocts.imgur_api.model.Image;
import com.akiniyalocts.imgur_api.model.ImgurResponse;
import com.squareup.picasso.Picasso;

import java.io.File;

import akiniyalocts.imgurapiexample.R;
import akiniyalocts.imgurapiexample.helpers.DocumentHelper;
import akiniyalocts.imgurapiexample.helpers.IntentHelper;
import akiniyalocts.imgurapiexample.helpers.NotificationHelper;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;
import retrofit.mime.TypedFile;

public class MainActivity extends AppCompatActivity {
    public final static String TAG = MainActivity.class.getSimpleName();

    /*
      These annotations are for ButterKnife by Jake Wharton
      https://github.com/JakeWharton/butterknife
     */
    @Bind(R.id.imageview)
    ImageView uploadImage;
    @Bind(R.id.editText_upload_title)
    EditText uploadTitle;
    @Bind(R.id.editText_upload_desc)
    EditText uploadDesc;
    @Bind(R.id.toolbar)
    Toolbar toolbar;

    private File chosenFile; //chosen file from intent
    private NotificationHelper notificationHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        notificationHelper = new NotificationHelper(this);

        setSupportActionBar(toolbar);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Uri returnUri;

        if (requestCode != IntentHelper.FILE_PICK) {
            return;
        }

        if (resultCode != RESULT_OK) {
            return;
        }

        returnUri = data.getData();
        String filePath = DocumentHelper.getPath(this, returnUri);
        //Safety check to prevent null pointer exception
        if (filePath == null || filePath.isEmpty()) return;
        chosenFile = new File(filePath);

        /*
            Picasso is a wonderful image loading tool from square inc.
            https://github.com/square/picasso
         */
        Picasso.with(getBaseContext())
                .load(chosenFile)
                .placeholder(R.drawable.ic_photo_library_black)
                .fit()
                .into(uploadImage);

    }


    @OnClick(R.id.imageview)
    public void onChooseImage() {
        uploadDesc.clearFocus();
        uploadTitle.clearFocus();
        IntentHelper.chooseFileIntent(this);
    }

    private void clearInput() {
        uploadTitle.setText("");
        uploadDesc.clearFocus();
        uploadDesc.setText("");
        uploadTitle.clearFocus();
        uploadImage.setImageResource(R.drawable.ic_photo_library_black);
    }

    @OnClick(R.id.fab)
    public void uploadImage() {

        if (chosenFile != null) {

            notificationHelper.createUploadingNotification();

            ImgurClient.getInstance()
                    .uploadImage(
                            new TypedFile("image/*", chosenFile),
                            uploadTitle.getText().toString(),
                            uploadDesc.getText().toString(),
                            new Callback<ImgurResponse<Image>>() {
                                @Override
                                public void success(ImgurResponse<Image> imageImgurResponse, Response response) {
                                    notificationHelper.createUploadedNotification(imageImgurResponse);
                                }

                                @Override
                                public void failure(RetrofitError error) {
                                    notificationHelper.createFailedUploadNotification();
                                }
                            }
                    );
        }


    }



}
