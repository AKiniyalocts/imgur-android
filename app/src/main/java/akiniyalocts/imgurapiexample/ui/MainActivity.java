package akiniyalocts.imgurapiexample.ui;

import android.app.Activity;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.squareup.picasso.Picasso;

import akiniyalocts.imgurapiexample.R;
import akiniyalocts.imgurapiexample.databinding.MainActivityBinding;
import akiniyalocts.imgurapiexample.model.Upload;
import akiniyalocts.imgurapiexample.utils.PermissionUtil;

public class MainActivity extends AppCompatActivity {

    public final static String TAG = MainActivity.class.getSimpleName();


    /**
     *
     * @param activity
     * @param reqCode
     */
    public static void buildGalleryIntent(Activity activity, int reqCode){
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("image/*");
        activity.startActivityForResult(intent, reqCode);
    }

    private static final String KEY_UPLOAD = "key::upload";

    private static final int REQUEST_GALLERY = 1;
    private static final int REQUEST_STORAGE = 2;


    private MainActivityBinding binding;

    private Upload upload; // Upload object containing image and meta data

    private Uri chosenImage; //chosen image from intent

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if(savedInstanceState != null){
            upload = savedInstanceState.getParcelable(KEY_UPLOAD);
        }

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);


        init(upload);
    }

    private void init(@Nullable Upload upload){

        setSupportActionBar(binding.toolbar);

        View.OnClickListener onRequestImage = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(PermissionUtil.requestStorage(MainActivity.this, REQUEST_STORAGE)) {
                    buildGalleryIntent(MainActivity.this, REQUEST_GALLERY);
                }
            }
        };

        binding.imageview.setOnClickListener(onRequestImage);

        if(upload != null){
            showImage(upload);
        }

    }

    private void showImage(@NonNull final Upload upload) {

         /*
            Picasso is a wonderful image loading tool from square inc.
            https://github.com/square/picasso
         */

        Picasso.with(this)
                .load(upload.uri)
                .fit()
                .centerInside()
                .into(binding.imageview);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if(requestCode == REQUEST_STORAGE) {
            if (PermissionUtil.verifyPermissions(grantResults)) {
                buildGalleryIntent(this, REQUEST_GALLERY);
            } else {
                Snackbar.make(binding.rootView, "Gallery permission denied! :(", Snackbar.LENGTH_LONG).show();
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if(resultCode == RESULT_OK && requestCode == REQUEST_GALLERY) {
            if(data.getData() != null){
                chosenImage = data.getData();
            }
        }

        if(chosenImage != null){
            upload = Upload.create(chosenImage);

            showImage(upload);
        }

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if(upload != null){
            outState.putParcelable(KEY_UPLOAD, upload);
        }
    }
}
