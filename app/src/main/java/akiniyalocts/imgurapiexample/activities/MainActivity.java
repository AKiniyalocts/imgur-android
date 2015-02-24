package akiniyalocts.imgurapiexample.activities;

import akiniyalocts.imgurapiexample.R;
import akiniyalocts.imgurapiexample.helpers.DocumentHelper;
import akiniyalocts.imgurapiexample.helpers.IntentHelper;
import akiniyalocts.imgurapiexample.imgurmodel.ImageResponse;
import akiniyalocts.imgurapiexample.imgurmodel.Upload;
import akiniyalocts.imgurapiexample.services.OnImageUploadedListener;
import akiniyalocts.imgurapiexample.services.UploadService;
import akiniyalocts.imgurapiexample.utils.aLog;
import android.content.Intent;
import android.net.Uri;
import android.os.PersistableBundle;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import com.squareup.picasso.Picasso;
import java.io.File;

public class MainActivity extends ActionBarActivity implements OnImageUploadedListener{
  public final static String TAG = MainActivity.class.getSimpleName();

  /*
    These annotations are for ButterKnife by Jake Wharton

    https://github.com/JakeWharton/butterknife

   */
  @InjectView(R.id.upload_image)ImageView uploadImage;
  @InjectView(R.id.upload_btn)Button uploadBtn;
  @InjectView(R.id.upload_title)EditText uploadTitle;
  @InjectView(R.id.upload_desc)EditText uploadDesc;

  private Upload upload; // Upload object containging image and meta data
  private File chosenFile; //chosen file from intent

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    ButterKnife.inject(this);
  }



  @Override protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    super.onActivityResult(requestCode, resultCode, data);
    Uri returnUri;

    if(requestCode == IntentHelper.FILE_PICK) {
      if (resultCode == RESULT_OK) {
        returnUri = data.getData();
        chosenFile = new File(DocumentHelper.getPath(this, returnUri));

        /*
          Picasso is a wonderful image loading tool from square inc.

          https://github.com/square/picasso
         */
        Picasso.with(this).load(chosenFile).fit().into(uploadImage);

        /*
          Enable the upload button after the file has been chosen
         */
        uploadBtn.setEnabled(true);
      }
    }

  }

  @OnClick(R.id.choose_btn)
  public void onChooseImage(){
    IntentHelper.chooseFileIntent(this);
  }

  @OnClick(R.id.upload_btn)
  public void uploadImage(){
    /*
      Create the @Upload object
     */
    createUpload(chosenFile);

    /*
      Start upload
     */
    new UploadService(upload, this).execute();
  }

  @Override public void onImageUploaded(ImageResponse response) {
    /*
      Logging the response from the image upload.
     */
    aLog.w(TAG, response.toString());
  }

  private void createUpload(File image){
    upload = new Upload();

    upload.image = image;
    upload.title = uploadTitle.getText().toString();
    upload.description = uploadDesc.getText().toString();

  }
}
