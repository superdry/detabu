package jp.gr.java_conf.jyukon.detabu.stub;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RemoteViews;
import android.widget.TextView;

import com.google.android.glass.app.Card;

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
    public ICard setText(String text) {
        this.text.setText(text);
        return this;
    }

    @Override
    public ICard setFootnote(String info) {
        this.info.setText(info);
        return this;
    }

    @Override
    public ICard addImage(Bitmap bitmap) {
        this.image.setImageBitmap(bitmap);
        this.image.setVisibility(View.VISIBLE);
        return this;
    }

    @Override
    public void clearImages() {
    }

    @Override
    public RemoteViews getRemoteViews() {
        return null; // This method will not be used.
    }

    @Override
    public View getView() {
        return mView;
    }
}