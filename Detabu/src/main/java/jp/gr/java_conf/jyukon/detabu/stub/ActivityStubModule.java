package jp.gr.java_conf.jyukon.detabu.stub;

import android.app.Activity;

import dagger.Module;
import dagger.Provides;
import jp.gr.java_conf.jyukon.detabu.ICardFactory;
import jp.gr.java_conf.jyukon.detabu.ICardScrollView;
import jp.gr.java_conf.jyukon.detabu.ItemDirectionFragment;
import jp.gr.java_conf.jyukon.detabu.ItemScrollFragment;
import jp.gr.java_conf.jyukon.detabu.ItemsActivity;

@Module(
    injects = {
        ItemsActivity.class,
        ItemScrollFragment.class,
        ItemDirectionFragment.class,
        CardScrollViewImpl.class,
        ItemCardPagerAdapter.class,
        FullScreenImageCard.class
    },
    complete = false,
    library = true
)
public class ActivityStubModule {

    private Activity mActivity;

    public ActivityStubModule(Activity activity) {
        mActivity = activity;
    }

    @Provides
    ICardScrollView provideICardScrollView() {
        return new jp.gr.java_conf.jyukon.detabu.stub.CardScrollViewImpl(mActivity);
    }

    @Provides
    ICardFactory provideICardFactory() {
        return new jp.gr.java_conf.jyukon.detabu.stub.CardFactory(mActivity);
    }

    @Provides
    ItemCardPagerAdapter provideItemCardPagerAdapter() {
        return new ItemCardPagerAdapter(mActivity);
    }
}
