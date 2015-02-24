package akiniyalocts.imgurapiexample.services;

import akiniyalocts.imgurapiexample.imgurmodel.ImageResponse;

/**
 * Created by AKiniyalocts on 1/14/15.
 *
 * Listener for when an image is uploaded
 */
public interface OnImageUploadedListener {
  public void onImageUploaded(ImageResponse response);
}
