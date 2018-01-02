package zoker.net.clean;

/**
 * Created by zoker on 2017/12/29.
 */

/**
 * Use cases are the entry points to the domain layer.
 * UseCase : Domain Layer 层中的一个基础单位，是Domain layer层的一个入口
 *
 * @param <Q> 数据请求者
 * @param <P> 数据回调者
 */
public abstract class UseCase<Q extends UseCase.RequestValues, P extends UseCase.ResponseValues> {
    private Q mRequestValues;
    private UseCaseCallback<P> mUseCaseCallback;

    public void setRequestValues(Q requestValues) {
        this.mRequestValues = requestValues;
    }

    public Q getRequestValues() {
        return this.mRequestValues;
    }

    public UseCaseCallback<P> getUseCaseCallback() {
        return mUseCaseCallback;
    }

    public void setUseCaseCallback(UseCaseCallback<P> useCaseCallback) {
        this.mUseCaseCallback = useCaseCallback;
    }

    /**
     * 数据请求
     */
    void run() {
        executeUseCase(mRequestValues);
    }

    protected abstract void executeUseCase(Q requestValues);

    /**
     * Data passed to a request
     * 数据请求者
     */
    public interface RequestValues {

    }

    /**
     * Data received from a request
     * 数据接收者
     */
    public interface ResponseValues {

    }

    /**
     * 数据回调
     *
     * @param <reponse>
     */
    public interface UseCaseCallback<reponse> {
        void onSuccess(reponse response);

        void onError();
    }
}
