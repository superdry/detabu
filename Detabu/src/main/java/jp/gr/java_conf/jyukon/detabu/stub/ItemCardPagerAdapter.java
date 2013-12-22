package jp.gr.java_conf.jyukon.detabu.stub;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.Response;
import com.squareup.otto.Bus;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import javax.inject.Inject;

import jp.gr.java_conf.jyukon.detabu.DetabuBaseActivity;
import jp.gr.java_conf.jyukon.detabu.ICard;
import jp.gr.java_conf.jyukon.detabu.ICardFactory;
import jp.gr.java_conf.jyukon.detabu.RequestManager;
import jp.gr.java_conf.jyukon.detabu.event.ItemSelectedEvent;
import jp.gr.java_conf.jyukon.detabu.model.Item;
import lombok.Setter;

public class ItemCardPagerAdapter extends PagerAdapter {

    private final Context mContext;
    @Setter List<Item> items;
    @Inject ICardFactory mCardFactory;
    @Inject RequestManager mRequestManager;
    @Inject Bus bus;

    public ItemCardPagerAdapter(Context context) {
        mContext = context;
        ((DetabuBaseActivity)context).inject(this);
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        final ICard card = mCardFactory.getCard();
        final Item item = items.get(position);

        card.setText(item.getTitle());
        card.setInfo(item.getPlaceName());
        card.setFullScreenImages(true);
        mRequestManager.doRequest().getImage(item.getImageUrl(),
                new Response.Listener<Bitmap>() {
                    @Override
                    public void onResponse(Bitmap bitmap) {
                        File file = new File(mContext.getExternalFilesDir(null), Uri.parse(item.getImageUrl()).getLastPathSegment());
                        try {
                            FileOutputStream fos = new FileOutputStream(file);
                            bitmap.compress(Bitmap.CompressFormat.JPEG, 75, fos);
                            fos.flush();
                            fos.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        card.addImage(Uri.parse(file.toString()));
                    }
                });
        container.addView(card.toView());
        card.toView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bus.post(new ItemSelectedEvent(item));
            }
        });
        return card.toView();
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }
}
