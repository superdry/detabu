package jp.gr.java_conf.jyukon.detabu;

import android.app.Activity;

import dagger.Module;
import dagger.Provides;

@Module(
    injects = {
        ItemsActivity.class,
        ItemScrollFragment.class,
        CardScrollViewImpl.class,
        ItemCardScrollAdapter.class
    },
    complete = false,
    library = true
)
public class ActivityModule {

    private Activity mActivity;

    public ActivityModule(Activity activity) {
        mActivity = activity;
    }

    @Provides
    ICardScrollView provideICardScrollView() {
        return new CardScrollViewImpl(mActivity);
    }

    @Provides
    ICardFactory provideICardFactory() {
        return new CardFactory(mActivity);
    }

    @Provides
    ItemCardScrollAdapter provideItemCardScrollAdapter() {
        return new ItemCardScrollAdapter(mActivity);
    }
}