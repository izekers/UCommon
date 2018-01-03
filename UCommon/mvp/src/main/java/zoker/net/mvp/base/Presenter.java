package zoker.net.mvp.base;

/**
 * Created by zoker on 2018/1/2.
 */

public interface Presenter<V extends MvpView> {
    /**
     * 初始化数据相关操作
     */
    void start();

    /**
     * Presenter与View建立连接
     *
     * @param mvpView 与此Presenter相对应的View
     */
    void attachView(V mvpView);

    /**
     * Presenter与View连接断开
     */
    void detachView();

}
