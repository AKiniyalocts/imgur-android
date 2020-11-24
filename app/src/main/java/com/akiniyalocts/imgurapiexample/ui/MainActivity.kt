package com.akiniyalocts.imgurapiexample.ui

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.Settings
import androidx.appcompat.app.AppCompatActivity
import com.akiniyalocts.imgurapiexample.BuildConfig
import com.akiniyalocts.imgurapiexample.R
import com.akiniyalocts.imgurapiexample.databinding.ActivityMainBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.snackbar.Snackbar
import org.koin.androidx.viewmodel.ext.android.viewModel
import permissions.dispatcher.PermissionRequest
import permissions.dispatcher.ktx.constructPermissionsRequest

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainViewModel by viewModel()

    /**
     * A permission request for storage to access the users camera roll.
     * https://github.com/permissions-dispatcher/PermissionsDispatcher/tree/master/ktx
     */
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
        startActivityForResult(Intent.createChooser(intent, "Select Image"), REQUEST_IMAGE)
    }

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

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {

            val uri = data?.data ?: return

            when (requestCode) {
                REQUEST_IMAGE -> {

                    val takeFlags = data.flags.and(Intent.FLAG_GRANT_READ_URI_PERMISSION).or(Intent.FLAG_GRANT_WRITE_URI_PERMISSION)

                    try {
                        contentResolver.takePersistableUriPermission(uri, takeFlags)
                        viewModel.selectedImageUri(uri)
                    }
                    catch (e: SecurityException){
                        MaterialAlertDialogBuilder(this)
                            .setTitle(R.string.error)
                            .setMessage(e.localizedMessage)
                            .create()
                            .show()
                    }

                }

            }
        }
    }

    private fun onShowStoragePermissionRationale(request: PermissionRequest) {
        request.proceed()
    }

    private fun onStoragePermissionDenied() {
        showPermissionErrorSnackbar()
    }

    private fun onStoragePermissionNeverAskAgain() {
       showPermissionErrorSnackbar()
    }

    private fun showPermissionErrorSnackbar(){
        Snackbar.make(binding.root, R.string.permission_denied, Snackbar.LENGTH_LONG).setAction(R.string.settings){
            startActivity(Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS, Uri.parse("package:" + BuildConfig.APPLICATION_ID)))
        }.show()
    }

    companion object{
        private const val REQUEST_IMAGE = 2021
    }
}