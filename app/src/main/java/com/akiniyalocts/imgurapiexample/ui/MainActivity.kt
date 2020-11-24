package com.akiniyalocts.imgurapiexample.ui

import android.Manifest
import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.net.toFile
import com.akiniyalocts.imgurapiexample.api.ImgurApi
import com.akiniyalocts.imgurapiexample.R
import com.akiniyalocts.imgurapiexample.databinding.ActivityMainBinding
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import permissions.dispatcher.PermissionRequest
import permissions.dispatcher.ktx.constructPermissionsRequest

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater).apply {
            viewModel = this@MainActivity.viewModel
            lifecycleOwner = this@MainActivity
        }
        setContentView(binding.root)
        setupView()
    }

    private fun setupView() {
        binding.chooseImageButton.setOnClickListener {
            storageRequest.launch()
        }
    }

    private val storageRequest = constructPermissionsRequest(
        Manifest.permission.READ_EXTERNAL_STORAGE,
        onShowRationale = ::onShowStoragePermissionRationale,
        onPermissionDenied = ::onStoragePermissionDenied,
        onNeverAskAgain = ::onStoragePermissionNeverAskAgain
    ) {
        val intent = Intent().apply {
            type = "image/*"
            action = Intent.ACTION_OPEN_DOCUMENT
            addCategory(Intent.CATEGORY_OPENABLE)
        }
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), RC_PICK_PHOTO)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {

            val uri = data?.data ?: return

            when (requestCode) {
                RC_PICK_PHOTO -> {

                    val takeFlags = data.flags.and(Intent.FLAG_GRANT_READ_URI_PERMISSION).or(Intent.FLAG_GRANT_WRITE_URI_PERMISSION)

                    try {
                        contentResolver.takePersistableUriPermission(uri, takeFlags)
                        viewModel.selectedImageUri(uri)
                    }
                    catch (e: SecurityException){
                        //TODO: show security exception details
                    }

                }

            }
        }
    }

    private fun onShowStoragePermissionRationale(request: PermissionRequest) {
        request.proceed()
    }
    private fun onStoragePermissionDenied() {

    }
    private fun onStoragePermissionNeverAskAgain() {

    }

    companion object{
        private const val RC_PICK_PHOTO = 2001
    }
}