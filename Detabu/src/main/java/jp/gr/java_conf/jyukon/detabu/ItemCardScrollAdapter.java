package jp.gr.java_conf.jyukon.detabu;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.Response;
import com.google.android.glass.widget.CardScrollAdapter;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import javax.inject.Inject;

import jp.gr.java_conf.jyukon.detabu.model.Item;
import lombok.Setter;

public class ItemCardScrollAdapter extends CardScrollAdapter {

    private final Context mContext;
    @Setter List<Item> items;
    @Inject ICardFactory mCardFactory;
    @Inject RequestManager mRequestManager;

    static class ViewHolder {
        ICard card;

        public ViewHolder(ICard card) {
            this.card = card;
        }
    }

    public ItemCardScrollAdapter(Context context) {
        mContext = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final ViewHolder holder;

        if (convertView != null) {
            holder = (ViewHolder) convertView.getTag();
        } else {
            ICard card = mCardFactory.getCard();
            holder = new ViewHolder(card);
            convertView.setTag(holder);
        }
        final Item item = items.get(position);

        holder.card.setText(item.getTitle());
        holder.card.setInfo(item.getPlaceName());
        holder.card.setFullScreenImages(true);
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
                        holder.card.addImage(Uri.parse(file.toString()));
                    }
                });

        return holder.card.toView();
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object getItem(int i) {
        return items.get(i);
    }

    @Override
    public int findIdPosition(Object o) {
        return 0;
    }

    @Override
    public int findItemPosition(Object o) {
        return items.indexOf(o);
    }
}
