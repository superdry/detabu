package jp.gr.java_conf.jyukon.detabu;

import android.content.Context;
import android.graphics.Canvas;

public class LiveCardRendererImpl extends LiveCardRenderer {

    public LiveCardRendererImpl(Context context) {
        super(context);
    }

    @Override
    public void redrawCard() {
        Canvas canvas = mHolder.lockCanvas();
        if (canvas != null) {
            mCard.getView().draw(canvas);
            mHolder.unlockCanvasAndPost(canvas);
        }
    }
}
