package zoker.net.clean.scheduler;

/**
 * Created by zoker on 2017/12/29.
 */

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import android.os.Handler;

import zoker.net.clean.UseCase;
import zoker.net.clean.UseCaseScheduler;

/**
 * Executes asynchronous tasks using a {@link ThreadPoolExecutor}.
 * UseCaseScheduler的一种实现方式：通过使用ThreadPoolExecutor执行异步任务
 * 推测：这个类进行再设计可以使用RxJava做修改
 * <p>
 * See also {@link Executors} for a list of factory methods to create common
 * {@link java.util.concurrent.ExecutorService}s for different scenarios.
 */
public class UseCaseThreadPoolScheduler implements UseCaseScheduler {
    private final Handler mHandler = new Handler();

    private static final int POOL_SIZE = 2; //线程池数量
    private static final int MAX_POOL_SIZE = 4;
    private static final int TIMEOUT = 30;

    ThreadPoolExecutor mThreadPoolExecutor;

    public UseCaseThreadPoolScheduler() {
        //创建线程池，这里线程池的知识还未阅读，需要加强
        mThreadPoolExecutor = new ThreadPoolExecutor(POOL_SIZE, MAX_POOL_SIZE, TIMEOUT, TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(POOL_SIZE));
    }


    @Override
    public void execute(Runnable runnable) {
        mThreadPoolExecutor.execute(runnable);
    }

    @Override
    public <V extends UseCase.ResponseValues> void notifyResponse(final V response, final UseCase.UseCaseCallback<V> useCaseCallback) {
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                useCaseCallback.onSuccess(response);
            }
        });
    }

    //思考   错误信息怎么办
    @Override
    public <V extends UseCase.ResponseValues> void onError(final UseCase.UseCaseCallback<V> useCaseCallback) {
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                useCaseCallback.onError();
            }
        });
    }
}
