package jp.gr.java_conf.jyukon.detabu.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import lombok.Data;

@Data
public class Item implements Parcelable {
    @SerializedName("place_geo") PlaceGeo placeGeo;
    String description;
    String title;
    @SerializedName("image_url") String imageUrl;
    @SerializedName("place_name") String placeName;

    protected Item(Parcel in) {
        placeGeo = (PlaceGeo) in.readValue(PlaceGeo.class.getClassLoader());
        description = in.readString();
        title = in.readString();
        imageUrl = in.readString();
        placeName = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(placeGeo);
        dest.writeString(description);
        dest.writeString(title);
        dest.writeString(imageUrl);
        dest.writeString(placeName);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<Item> CREATOR = new Parcelable.Creator<Item>() {
        @Override
        public Item createFromParcel(Parcel in) {
            return new Item(in);
        }

        @Override
        public Item[] newArray(int size) {
            return new Item[size];
        }
    };

    @Data
    public static class PlaceGeo implements Parcelable {
        float lat;
        float lon;

        protected PlaceGeo(Parcel in) {
            lat = in.readFloat();
            lon = in.readFloat();
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeFloat(lat);
            dest.writeFloat(lon);
        }

        @SuppressWarnings("unused")
        public static final Parcelable.Creator<PlaceGeo> CREATOR = new Parcelable.Creator<PlaceGeo>() {
            @Override
            public PlaceGeo createFromParcel(Parcel in) {
                return new PlaceGeo(in);
            }

            @Override
            public PlaceGeo[] newArray(int size) {
                return new PlaceGeo[size];
            }
        };
    }
}
