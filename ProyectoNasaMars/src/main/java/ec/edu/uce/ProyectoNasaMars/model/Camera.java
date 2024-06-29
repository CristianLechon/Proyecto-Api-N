package ec.edu.uce.ProyectoNasaMars.model;

public class Camera {
    private int id;
    private String name;
    private int rover_id;
    private String full_name;

    public String getName() {
        return name;
    }

    public String getFull_name() {
        return full_name;
    }

    @Override
    public String toString() {
        return "id=" + id +
                ", name='" + name + '\'' +
                ", rover_id=" + rover_id +
                ", full_name='" + full_name;
    }
}
