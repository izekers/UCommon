package zoker.net.clean;

/**
 * Created by zoker on 2017/12/29.
 */

/**
 * Runs {@link UseCase}s using a {@link UseCaseScheduler}.
 */
public class UseCaseHandler {
    private static UseCaseHandler INSTANCE;

    public static UseCaseHandler getInstance(UseCaseScheduler useCaseScheduler) {
        if (INSTANCE == null) {
            synchronized (UseCaseHandler.class) {
                if (INSTANCE == null) {
                    INSTANCE = new UseCaseHandler(useCaseScheduler);
                }
            }
        }
        return INSTANCE;
    }

    private static UseCaseScheduler mUseCaseScheduler;

    //设置线程池
    private UseCaseHandler(UseCaseScheduler useCaseScheduler) {
        this.mUseCaseScheduler = useCaseScheduler;
    }

    /**
     * 中间层，通知正确回调
     *
     * @param response
     * @param useCaseCallback
     * @param <V>
     */
    public <V extends UseCase.ResponseValues> void notifyResponse(final V response, final UseCase.UseCaseCallback<V> useCaseCallback) {
        mUseCaseScheduler.notifyResponse(response, useCaseCallback);
    }

    public <V extends UseCase.ResponseValues> void notifyError(final UseCase.UseCaseCallback<V> useCaseCallback) {
        mUseCaseScheduler.onError(useCaseCallback);
    }


    public <R extends UseCase.RequestValues, V extends UseCase.ResponseValues> void execute(final UseCase<R, V> useCase, R responseValues, UseCase.UseCaseCallback<V> callback) {
        useCase.setRequestValues(responseValues);
        //这一层主要是添加线程控制
        useCase.setUseCaseCallback(new UiCallbackWrapper<V>(callback, this));

        //测试部分
        // The network request might be handled in a different thread so make sure
        // Espresso knows
        // that the app is busy until the response is handled.
        //网络请求可能在不同的线程，所以要确定让App处于一个请求状态直到回调被捕获

        //EspressoIdlingResource.increment(); // App is busy until further notice  使App处于一个请求状态直到被通知

        mUseCaseScheduler.execute(new Runnable() {
            @Override
            public void run() {

                useCase.run();


                //测试部分
                // This callback may be called twice, once for the cache and once for loading
                // the data from the server API, so we check before decrementing, otherwise
                // it throws "Counter has been corrupted!" exception.
                /**
                 * 回调可能产生两次，一次是从缓存回调，一次从网络回调，所以我们在停止请求
                 *
                 */
//                if (!EspressoIdlingResource.getIdlingResource().isIdleNow()) {
//                    EspressoIdlingResource.decrement(); // Set app as idle.
//                }
            }
        });
    }

    /**
     * 回调给Ui层
     *
     * @param <V>
     */
    private static final class UiCallbackWrapper<V extends UseCase.ResponseValues> implements UseCase.UseCaseCallback<V> {
        private final UseCase.UseCaseCallback<V> mCallback;
        private final UseCaseHandler mUseCaseHandler;

        public UiCallbackWrapper(UseCase.UseCaseCallback<V> callback,
                                 UseCaseHandler useCaseHandler) {
            mCallback = callback;
            mUseCaseHandler = useCaseHandler;
        }

        @Override
        public void onSuccess(V response) {
            mUseCaseHandler.notifyResponse(response, mCallback);
        }

        @Override
        public void onError() {
            mUseCaseHandler.notifyError(mCallback);
        }
    }
}
