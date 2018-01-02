package zoker.net.mvp.base;

/**
 * Created by zoker on 2018/1/2.
 */

public interface MvpView<P extends Presenter> {
    void setPresenter(P presenter);
}
