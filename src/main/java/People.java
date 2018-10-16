import java.io.Serializable;

/**
 * @program: LifeRealTimRendering
 * @description: ${description}
 * @author: Mr.Liu
 * @create: 2018-10-15 22:48
 **/
public class People implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer id;                        //人的id，多元宇宙里面人也只有一个id
    private String name;                       //人的名字
    private Integer capacity;                  //开发度
    private boolean isDead = true;                    //记录在当前宇宙人是否已经死了，是否会发生多元迁越

    public boolean awake() {
        if (100 * Math.random() + capacity < 50) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return false;
        } else {
            capacity++;
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return awake();
        }
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    public boolean isDead() {
        return isDead;
    }

    public void setDead(boolean dead) {
        isDead = dead;
    }
}
