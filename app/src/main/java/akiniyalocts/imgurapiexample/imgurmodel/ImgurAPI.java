package akiniyalocts.imgurapiexample.imgurmodel;

import retrofit.http.Body;
import retrofit.http.Header;
import retrofit.http.POST;
import retrofit.http.Query;
import retrofit.mime.TypedFile;

/**
 * Created by AKiniyalocts on 2/23/15.
 */
public interface ImgurAPI {

  /****************************************
   * Upload
   * Image upload API
   */

  /**
   *
   * @param auth #Type of authorization for upload
   * @param title #Title of image
   * @param description #Description of image
   * @param albumId #ID for album (if the user is adding this image to an album)
   * @return
   */
  @POST("/3/image")ImageResponse postImage (
      @Header("Authorization")String auth,
      @Query("title")String title,
      @Query("description")String description,
      @Query("album")String albumId,
      @Query("account_url")String username,
      @Body TypedFile file
  );
}
