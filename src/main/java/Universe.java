import java.io.*;
import java.util.List;
import java.util.concurrent.ExecutorService;

/**
 * @program: LifeRealTimRendering
 * @description: ${description}
 * @author: Mr.Liu
 * @create: 2018-10-15 22:48
 **/
public class Universe implements Runnable, Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id;                      //宇宙id
    private List<People> peoples;         //当前宇宙里存活的人
    private static ExecutorService timeThreadPool = TimeThreadPool.getNewCachedThreadPool();  //执行时间的线程池单元
    private boolean isRun = false;

    public boolean isRun() {
        return isRun;
    }

    public void setRun(boolean run) {
        isRun = run;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<People> getPeoples() {
        return peoples;
    }

    public void setPeoples(List<People> peoples) {
        this.peoples = peoples;
    }

    public ExecutorService getTimeThreadPool() {
        return timeThreadPool;
    }

    public void setTimeThreadPool(ExecutorService timeThreadPool) {
        this.timeThreadPool = timeThreadPool;
    }

    public Object deepClone() throws Exception {
        // 将对象写到流里
        ByteArrayOutputStream bo = new ByteArrayOutputStream();
        ObjectOutputStream oo = new ObjectOutputStream(bo);
        oo.writeObject(this);
        // 从流里读出来
        ByteArrayInputStream bi = new ByteArrayInputStream(bo.toByteArray());
        ObjectInputStream oi = new ObjectInputStream(bi);
        return (oi.readObject());
    }

    @Override
    public void run() {
        synchronized (this) {
            isRun = true;
            try {
                this.wait(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            peoples.forEach(people -> {
                try {
                    people.awake();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
            timeThreadPool.execute(() -> {
                peoples.forEach(people -> {
                    if (people.isDead()) {
                        MultUniverse.getUniverses().forEach(universe -> {
                            universe.getPeoples().forEach(peopleOther -> {
                                if (peopleOther.getId().equals(people.getId()) && peopleOther.isDead()) {
                                    try {
                                        //发生时空迁跃生成新的多元宇宙
                                        MultUniverse.getUniverses().add((Universe) universe.deepClone());
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                }
                            });
                        });
                        //把people从该宇宙清除掉
                        peoples.remove(people);
                    }
                });
            });
        }
    }
}
