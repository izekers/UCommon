package net.zoker.ucommon.data;

import java.util.List;

/**
 * Created by Administrator on 2017/8/22.
 */

public abstract class PageLoader<T> {
//    private boolean isUpLoad = false;
//    private boolean hasMore = true;
//
//    private int page = 1,rows = 30;
//    private int beginPage = 1;
//
//    public void up(){
//        isUpLoad = true;
//        if (hasMore){
//            page++;
//            loadData(page,rows);
//        }else
//            show_noMore();
//    }
//
//    public void down(){
//        isUpLoad = false;
//        page = beginPage;
//        hasMore = true;
//        loadData(page,rows);
//    }
//
//    public void show_noMore(){
//
//    }
//    public abstract List<T> source(int page, int rows) throws Throwable;
//    public abstract void onData(boolean isResetData,List<T> data);
//    public abstract void onError(Throwable e);
//    private void loadData(final int page, final int rows){
////        Observable.create(new Observable.OnSubscribe<List<T>>() {
////            @Override
////            public void call(Subscriber<? super List<T>> subscriber) {
////                subscriber.onStart();
////                try {
////                    List<T> data = source(page, rows);
////                    subscriber.onNext(data);
////                }catch (Throwable e){
////                    e.printStackTrace();
////                    subscriber.onError(e);
////                }
////                subscriber.onCompleted();
////            }
////        }).compose(PageLoader.<List<T>>applySchedulers())
////        .subscribe(new Subscriber<List<T>>() {
////            @Override
////            public void onCompleted() {
////
////            }
////
////            @Override
////            public void onError(Throwable e) {
////                PageLoader.this.onError(e);
////            }
////
////            @Override
////            public void onNext(List<T> datas) {
////                if (isUpLoad){
////                    if (datas==null || datas.size()<rows){
////                        hasMore=false;
////                        show_noMore();
////                    }
////                }
////                onData(!isUpLoad,datas);
////            }
////        });
//    }
//
//
//    /**
//     * 数据加载调度
//     * @return 返回服务
//     */
//    public  static <T> Observable.Transformer<T, T> applySchedulers() {
//        return new Observable.Transformer<T, T>() {
//            @Override
//            public Observable<T> call(Observable<T> observable) {
//                return observable.subscribeOn(Schedulers.io())
//                        .observeOn(AndroidSchedulers.mainThread());
//            }
//        };
//    }
//
//
//    public int getPage() {
//        return page;
//    }
//
//    public void setPage(int page) {
//        this.page = page;
//    }
//
//    public int getRows() {
//        return rows;
//    }
//
//    public void setRows(int rows) {
//        this.rows = rows;
//    }
//
//    public int getBeginPage() {
//        return beginPage;
//    }
//
//    public void setBeginPage(int beginPage) {
//        this.beginPage = beginPage;
//    }
}
