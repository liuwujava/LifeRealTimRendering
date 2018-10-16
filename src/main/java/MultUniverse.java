import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutorService;

/**
 * @program: LifeRealTimRendering
 * @description: ${description}
 * @author: Mr.Liu
 * @create: 2018-10-15 22:47
 **/
public class MultUniverse implements Runnable, Serializable {
    private static final long serialVersionUID = 1L;
    private static CopyOnWriteArrayList<Universe> universes = new CopyOnWriteArrayList<Universe>();   //多元宇宙
    private static ExecutorService timeThreadPool = TimeThreadPool.getNewCachedThreadPool();  //执行时间的线程池单元

    //模拟宇宙大爆炸
    public static void BigBang() {
        Universe universe = new Universe();
        universe.setId(System.currentTimeMillis());
        //创建一群人，并确保每个人都能存活
        CopyOnWriteArrayList<People> peoples = new CopyOnWriteArrayList<>();
        People people_1 = new People();
        people_1.setId(1);
        people_1.setName("小一");
        people_1.setCapacity(0);
        people_1.setDead(true);
        People people_2 = new People();
        people_2.setId(2);
        people_2.setName("小二");
        people_2.setCapacity(0);
        people_2.setDead(true);
        People people_3 = new People();
        people_3.setId(3);
        people_3.setName("小三");
        people_3.setCapacity(0);
        people_3.setDead(true);
        People people_4 = new People();
        people_4.setId(4);
        people_4.setName("小四");
        people_4.setCapacity(0);
        people_4.setDead(true);
        People people_5 = new People();
        people_5.setId(5);
        people_5.setName("小五");
        people_5.setCapacity(0);
        people_5.setDead(true);
        peoples.add(people_1);
        peoples.add(people_2);
        peoples.add(people_3);
        peoples.add(people_4);
        peoples.add(people_5);
        universe.setPeoples(peoples);
        universes.add(universe);
    }

    public static void printInfo() {
        System.out.println("当前宇宙的总数为:" + universes.size());
        universes.forEach(universe -> {
            System.out.println("================================================================");
            System.out.println("当前宇宙的总数为:" + universes.size());
            System.out.println("当前的宇宙的id为:" + universe.getId() + "当前的宇宙的存活的人数为:" + universe.getPeoples().size());
            universe.getPeoples().forEach(people -> {
                System.out.println("################################################################");
                System.out.println("当前的宇宙存活的人的id为:" + people.getId() + "当前的宇宙的存活的人名字为:" + people.getName() + "当前的宇宙的存活的人的开发度为:" + people.getCapacity());
                System.out.println("################################################################");
                System.out.println("================================================================");
            });
        });
    }

    public static List<Universe> getUniverses() {
        return universes;
    }

    public void run() {
        while (true) {
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            universes.forEach(universe -> {
                if (!universe.isRun()) {
                    universe.run();
                }
            });
            printInfo();
        }
    }
}