import java.util.concurrent.ExecutorService;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @program: LifeRealTimRendering
 * @description: ${description}
 * @author: Mr.Liu
 * @create: 2018-10-15 22:49
 **/
public class TimeThreadPool {
    public static ExecutorService getNewCachedThreadPool() {
        return new ThreadPoolExecutor(0, Integer.MAX_VALUE, 60L, TimeUnit.SECONDS, new SynchronousQueue<Runnable>());
    }
}
