package jp.gr.java_conf.jyukon.detabu;

import android.content.Context;
import android.graphics.Bitmap;
import android.location.Location;
import android.net.Uri;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.reflect.TypeToken;

import java.util.Collection;
import java.util.List;

import jp.gr.java_conf.jyukon.detabu.model.Item;

public class RequestProxy {
    private RequestQueue mRequestQueue;

    RequestProxy(Context context) {
        mRequestQueue = Volley.newRequestQueue(context.getApplicationContext());
    }

    public void searchNearbyItems(Location location, int radius, Response.Listener<List<Item>>listener) {
        String url = new Uri.Builder()
                .scheme("http")
                .authority("tab-item-viewer.appspot.com")
                .path("search")
                .appendQueryParameter("q", String.format("distance(location,geopoint(%f,%f))<%d", location.getLatitude(), location.getLongitude(), radius))
                .appendQueryParameter("format", "json")
                .build().toString();
        mRequestQueue.add(new GsonRequest(url, new TypeToken<Collection<Item>>(){}.getType(), null, listener,
                new Response.ErrorListener() {
                    @Override public void onErrorResponse(VolleyError error) {
                        VolleyLog.e("Error: ", error.getMessage());
                    }
                }));
    }

    public void getImage(String imageUrl, Response.Listener<Bitmap> listener) {
        mRequestQueue.add(new ImageRequest(imageUrl, listener, 0, 0, Bitmap.Config.ARGB_8888,
                new Response.ErrorListener() {
                    @Override public void onErrorResponse(VolleyError error) {
                        VolleyLog.e("Error: ", error.getMessage());
                    }
                }));
    }
}