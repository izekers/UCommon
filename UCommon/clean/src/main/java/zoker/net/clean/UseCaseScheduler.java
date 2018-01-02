package zoker.net.clean;

/**
 * Created by zoker on 2017/12/29.
 */

import zoker.net.clean.scheduler.UseCaseThreadPoolScheduler;

/**
 * Interface for schedulers, see {@link UseCaseThreadPoolScheduler}.
 * scheduler ：调度器
 * UseCase 的 调度器接口
 */
public interface UseCaseScheduler {
    void execute(Runnable runnable);

    /**
     * 通知回调
     *
     * @param response        数据回调入口
     * @param useCaseCallback
     * @param <V>
     */
    <V extends UseCase.ResponseValues> void notifyResponse(final V response, final UseCase.UseCaseCallback<V> useCaseCallback);

    /**
     * 通知回调
     *
     * @param useCaseCallback
     * @param <V>
     */
    <V extends UseCase.ResponseValues> void onError(final UseCase.UseCaseCallback<V> useCaseCallback);
}
