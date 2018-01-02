package zoker.net.clean.datalayer;

/**
 * Created by zoker on 2017/12/29.
 */

/**
 * Main entry point for accessing tasks data.
 * 请求数据主要的入口
 * <p>
 * For simplicity, only getTasks() and getTask() have callbacks. Consider adding callbacks to other
 * methods to inform the user of network/database errors or successful operations.
 * For example, when a new task is created, it's synchronously stored in cache but usually every
 * operation on database or network should be executed in a different thread.
 *
 */
public interface TasksDataSource {
    interface LoadTasksCallback{
    }
    interface GetTaskCallback{

    }
}
