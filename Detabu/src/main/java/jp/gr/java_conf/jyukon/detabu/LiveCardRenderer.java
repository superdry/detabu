package jp.gr.java_conf.jyukon.detabu;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.net.Uri;
import android.os.Environment;
import android.view.SurfaceHolder;

import com.android.volley.Response;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import javax.inject.Inject;

import jp.gr.java_conf.jyukon.detabu.model.Item;
import lombok.Setter;

public abstract class LiveCardRenderer implements SurfaceHolder.Callback {
    final int MAX_IMAGES = 3;
    protected final Context mContext;
    @Inject protected ICard mCard;
    @Inject RequestManager mRequestManager;
    protected SurfaceHolder mHolder;
    @Setter protected PendingIntent pendingIntent;

    public LiveCardRenderer(Context context) {
        mContext = context;
        ((DetabuBaseService)context).inject(this);
    }

    public abstract void redrawCard();

    public void setItems(List<Item> items) {
        mCard.setText(mContext.getString(R.string.item_found_message, items.size()));
        mCard.clearImages();
        for (Item item : items.subList(0, MAX_IMAGES)) {
            addImage(item.getImageUrl());
        }
        redrawCard();
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        mHolder = holder;
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
    }

    private void addImage(final String imageUrl) {
        if (!isExternalStorageWritable())
            return;
        mRequestManager.doRequest().getImage(imageUrl,
                new Response.Listener<Bitmap>() {
                    @Override
                    public void onResponse(Bitmap bitmap) {
                        File file = new File(mContext.getExternalFilesDir(null), Uri.parse(imageUrl).getLastPathSegment());
                        try {
                            FileOutputStream fos = new FileOutputStream(file);
                            bitmap.compress(Bitmap.CompressFormat.JPEG, 75, fos);
                            fos.flush();
                            fos.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        // TODO この条件分岐はあとで考える
//                        if (mCard.getImageCount() >= MAX_IMAGES)
                            mCard.clearImages();
                        mCard.addImage(bitmap);
                        redrawCard();
                    }
                });
    }

    private boolean isExternalStorageWritable() {
        return Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState());
    }
}
