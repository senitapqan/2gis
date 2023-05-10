package Graph;

public class Transport {
    int id;
    String name;
    double speed;
    String lifeTime; ///hh:mm-hh:mm

    public Transport(int id, String name, double speed, String lifeTime) {
        this.id = id;
        this.name = name;
        this.speed = speed;
        this.lifeTime = lifeTime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public String getLifeTime() {
        return lifeTime;
    }

    public void setLifeTime(String lifeTime) {
        this.lifeTime = lifeTime;
    }
}

