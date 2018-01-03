package net.zoker.ucommon.app.demo3.contract;

/**
 * Created by zoker on 2018/1/2.
 */

public interface Contract {
    public interface MvpView{
        /**
         * 显示loading对话框
         *
         * @param msg
         */
        void showLoading(String msg);

        /**
         * 隐藏loading对话框
         */
        void hideLoading();

        /**
         * 显示错误信息
         *
         * @param errorMsg
         */
        void showError(String errorMsg);

        /**
         * 显示信息
         * @param msg
         */
        void showMsg(String msg);
    }


    public interface Presenter<V extends MvpView> {
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
}
