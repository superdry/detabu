package jp.gr.java_conf.jyukon.detabu.stub;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RemoteViews;
import android.widget.TextView;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.InjectView;
import jp.gr.java_conf.jyukon.detabu.DetabuBaseActivity;
import jp.gr.java_conf.jyukon.detabu.ICard;
import jp.gr.java_conf.jyukon.detabu.R;

public class FullScreenImageCard implements ICard {

    @Inject LayoutInflater layoutInflater;
    @InjectView(R.id.text) TextView text;
    @InjectView(R.id.info) TextView info;
    @InjectView(R.id.image) ImageView image;
    private View mView;

    public FullScreenImageCard(Context context) {
        ((DetabuBaseActivity)context).inject(this);
        mView = layoutInflater.inflate(R.layout.full_screen_image_card, null);
        ButterKnife.inject(this, mView);
    }

    @Override
    public ICard setFullScreenImages(boolean fullScreenImages) {
        return this;
    }

    @Override
    public ICard setText(String text) {
        this.text.setText(text);
        return this;
    }

    @Override
    public ICard setInfo(String info) {
        this.info.setText(info);
        return this;
    }

    @Override
    public ICard addImage(Uri uri) {
        this.image.setImageURI(uri);
        this.image.setVisibility(View.VISIBLE);
        return this;
    }

    @Override
    public void clearImages() {
    }

    @Override
    public int getNumberOfImages() {
        return 0;
    }

    @Override
    public RemoteViews toRemoteViews() {
        return null; // This method will not be used.
    }

    @Override
    public View toView() {
        return mView;
    }
}