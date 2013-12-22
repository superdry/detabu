package jp.gr.java_conf.jyukon.detabu.stub;

import android.content.Context;
import android.net.Uri;
import android.view.View;
import android.widget.RemoteViews;

import java.util.ArrayList;

import jp.gr.java_conf.jyukon.detabu.ICard;
import jp.gr.java_conf.jyukon.detabu.R;


public class NotificationCard implements ICard {

    private RemoteViews mRemoteViews;
    private final static int imageViewIds[] = {
        R.id.image1,
        R.id.image2,
        R.id.image3
    };
    private ArrayList<Uri> mImageUris = new ArrayList<Uri>();

    public NotificationCard(Context context) {
        mRemoteViews = new RemoteViews(context.getPackageName(), R.layout.notification_card);
    }

    @Override
    public ICard setFullScreenImages(boolean fullScreenImages) {
        return this;
    }

    @Override
    public ICard setText(String text) {
        mRemoteViews.setTextViewText(R.id.text, text);
        return this;
    }

    @Override
    public ICard setInfo(String info) {
        mRemoteViews.setTextViewText(R.id.info, info);
        return this;
    }

    @Override
    public ICard addImage(Uri uri) {
        mRemoteViews.setImageViewUri(imageViewIds[mImageUris.size()], uri);
        mRemoteViews.setViewVisibility(imageViewIds[mImageUris.size()], View.VISIBLE);
        mImageUris.add(uri);
        return this;
    }

    @Override
    public void clearImages() {
        mImageUris.clear();
    }

    @Override
    public int getNumberOfImages() {
        return mImageUris.size();
    }

    @Override
    public RemoteViews toRemoteViews() {
        return mRemoteViews;
    }

    @Override
    public View toView() {
        return null; // This method will not be used.
    }
}
