package net.zoker.ucommon.mvp;

/**
 * Created by zoker on 2017/12/29.
 */

public class BasePresenter<V extends MvpView> implements Presenter<V> {
    private V view;

    /**
     * 获取当前连接的View
     *
     * @return
     */
    public V getView() {
        return view;
    }

    @Override
    public void attachView(V mvpView) {
        this.view = mvpView;
    }

    @Override
    public void detachView() {
        this.view = null;
    }

    /**
     * 是否与View建立连接
     *
     * @return
     */
    public boolean isViewAttached() {
        return this.view != null;
    }


    /**
     * 每次调用业务请求的时候都要先调用方法检查是否与View建立连接，没有则抛出异常
     */
    public void checkViewAttached() {
        if (!isViewAttached()) {
            throw new MvpViewNotAttachedException();
        }
    }

    public static class MvpViewNotAttachedException extends RuntimeException {
        public MvpViewNotAttachedException() {
            super("请求数据前请先调用 attachView(MvpView) 方法与View建立连接");
        }
    }
}
