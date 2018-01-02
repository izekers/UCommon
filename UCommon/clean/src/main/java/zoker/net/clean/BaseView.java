package zoker.net.clean;

/**
 * Created by zoker on 2017/12/29.
 */

/**
 * BaseView ：MVP中的V层
 * @param <P>
 */
public interface BaseView<P extends BasePresenter> {

    void setPresenter(P presenter);
}
