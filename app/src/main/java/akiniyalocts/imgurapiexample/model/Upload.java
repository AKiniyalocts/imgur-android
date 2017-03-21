package akiniyalocts.imgurapiexample.model;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by AKiniyalocts on 2/24/15.
 *
 * Basic object for upload.
 */
public class Upload implements Parcelable{

  public static Upload create(Uri uri){
    return new Upload(uri, null, null, null);
  }

  public final Uri uri;
  public final String title;
  public final String description;
  public final String albumId;

  public Upload(Uri uri, String title, String description, String albumId) {
    this.uri = uri;
    this.title = title;
    this.description = description;
    this.albumId = albumId;
  }

  protected Upload(Parcel in) {
    uri = in.readParcelable(Uri.class.getClassLoader());
    title = in.readString();
    description = in.readString();
    albumId = in.readString();
  }

  public static final Creator<Upload> CREATOR = new Creator<Upload>() {
    @Override
    public Upload createFromParcel(Parcel in) {
      return new Upload(in);
    }

    @Override
    public Upload[] newArray(int size) {
      return new Upload[size];
    }
  };

  @Override
  public int describeContents() {
    return 0;
  }

  @Override
  public void writeToParcel(Parcel dest, int flags) {
    dest.writeParcelable(uri, flags);
    dest.writeString(title);
    dest.writeString(description);
    dest.writeString(albumId);
  }

}
