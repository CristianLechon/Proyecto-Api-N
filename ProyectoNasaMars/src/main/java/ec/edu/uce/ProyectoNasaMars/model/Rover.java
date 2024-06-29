package ec.edu.uce.ProyectoNasaMars.model;

public class Rover {
    private int id;
    private String name;
    private String landing_date;
    private String launch_date;
    private String status;
    private int max_sol;
    private String max_date;
    private int total_photos;


    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "id=" + id +
                ", name='" + name + '\'' +
                ", landing_date='" + landing_date + '\'' +
                ", launch_date='" + launch_date + '\'' +
                ", status='" + status + '\'' +
                ", max_sol=" + max_sol +
                ", max_date='" + max_date + '\'' +
                ", total_photos=" + total_photos;
    }
}
