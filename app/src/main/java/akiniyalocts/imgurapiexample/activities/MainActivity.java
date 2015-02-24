package akiniyalocts.imgurapiexample.activities;

import akiniyalocts.imgurapiexample.R;
import akiniyalocts.imgurapiexample.helpers.DocumentHelper;
import akiniyalocts.imgurapiexample.helpers.IntentHelper;
import akiniyalocts.imgurapiexample.utils.aLog;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import com.squareup.picasso.Picasso;
import java.io.File;

public class MainActivity extends ActionBarActivity {
  public final static String TAG = MainActivity.class.getSimpleName();

  @InjectView(R.id.upload_image)ImageView uploadImage;
  @InjectView(R.id.upload_btn)Button uploadBtn;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    ButterKnife.inject(this);
  }

  @Override protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    super.onActivityResult(requestCode, resultCode, data);
    Uri returnUri;
    File chosenFile;

    if(requestCode == IntentHelper.FILE_PICK) {
      if (resultCode == RESULT_OK) {
        returnUri = data.getData();
        chosenFile = new File(DocumentHelper.getPath(this, returnUri));
        Picasso.with(this).load(chosenFile).fit().into(uploadImage);
        uploadBtn.setEnabled(true);
      }
    }

  }

  @OnClick(R.id.choose_btn)
  public void onChooseImage(){
    IntentHelper.chooseFileIntent(this);
  }
}
