package ec.edu.uce.ProyectoNasaMars.view;

import ec.edu.uce.ProyectoNasaMars.controller.Container;
import ec.edu.uce.ProyectoNasaMars.model.MarsPhoto;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.util.List;

public class MarsPhotoFrame extends JFrame {
    private JTable table;
    private JComboBox<String> comboBox, comboBox2;
    private JButton btn, btn2;
    private JTextField textField, textField2;
    private Container container;

    public MarsPhotoFrame() throws Exception {
        setTitle("Mars Photos");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocation(270, 100);

        container = new Container();

        String[] columnNames = {"ID", "Sol", "Camera Name", "Image", "Earth Date", "Rover Name", "Full name"};
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);
        table = new JTable(model);

        JScrollPane scrollPane = new JScrollPane(table);

        // Creamos el comboBox y lo agregamos al panel superior
        comboBox = new JComboBox<>();
        comboBox.addItem("Procesamiento Paralelo");
        comboBox.addItem("Procesamiento Secuencial");

        comboBox2 = new JComboBox<>();

        textField = new JTextField(20);
        textField2 = new JTextField(10);

        btn = new JButton("Ver");
        btn2 = new JButton("Filtrar");

        btn.addActionListener(e -> {
            String imageUrl = textField.getText().trim();
            if (!imageUrl.isEmpty()) {
                new ImageFrame(imageUrl);
            } else {
                JOptionPane.showMessageDialog(MarsPhotoFrame.this, "Ingrese una URL.");
            }
        });

        JPanel topPanel = new JPanel();
        topPanel.setLayout(new FlowLayout());
        topPanel.add(textField, BorderLayout.WEST);
        topPanel.add(btn, BorderLayout.WEST);
        topPanel.add(comboBox);
        topPanel.add(comboBox2);
        topPanel.add(textField2, BorderLayout.EAST);
        topPanel.add(btn2, BorderLayout.EAST);


        setLayout(new BorderLayout());
        add(topPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);

        displayPhotos(container.loadTable());

        getBox();
    }

    public void getBox() {
        comboBox.addItemListener(e -> {
            if (e.getStateChange() == ItemEvent.SELECTED) {
                String selectedOption = (String) comboBox.getSelectedItem();
                if (selectedOption.equals("Procesamiento Paralelo")) {
                    comboBox2.setModel(new DefaultComboBoxModel<>(new String[]{"Id Paralelo", "Name Paralelo", "Date Paralelo"}));
                } else if (selectedOption.equals("Procesamiento Secuencial")) {
                    comboBox2.setModel(new DefaultComboBoxModel<>(new String[]{"Id Secuencial", "Name Secuencial", "Date Secuencial"}));
                }

            }
        });

        comboBox2.addItemListener(e -> {
            if (e.getStateChange() == ItemEvent.SELECTED) {
                String selectedOption = (String) comboBox2.getSelectedItem();
                btn2.addActionListener(e1 -> {
                    String aux;
                    int a;
                    try {
                        List<MarsPhoto> photos;
                        switch (selectedOption) {
                            case "Id Paralelo":

                                aux = textField2.getText().trim();
                                a = Integer.parseInt(aux);

                                photos = container.getByIdParallel(a);
                                break;
                            case "Name Paralelo":
                                aux = textField2.getText().trim();
                                photos = container.getByNameParallel(aux);
                                break;
                            case "Date Paralelo":
                                aux = textField2.getText().trim();
                                photos = container.getByDateParallel(aux);
                                break;
                            case "Id Secuencial":
                                aux = textField2.getText().trim();
                                a = Integer.parseInt(aux);
                                photos = container.getByIdSequential(a);
                                break;
                            case "Name Secuencial":
                                aux = textField2.getText().trim();
                                photos = container.getByNameSequential(aux);
                                break;
                            case "Date Secuencial":
                                aux = textField2.getText().trim();
                                photos = container.getByDateSequential(container.loadTable(), aux);
                                break;
                            default:
                                photos = container.loadTable();
                                break;
                        }
                        displayPhotos(photos);
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                });

            }
        });
    }

    public void displayPhotos(List<MarsPhoto> photos) {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0);

        for (MarsPhoto photo : photos) {
            model.addRow(new Object[]{
                    photo.getId(),
                    photo.getSol(),
                    photo.getCamera().getName(),
                    photo.getImg_src(),
                    photo.getEarth_date(),
                    photo.getRover().getName(),
                    photo.getCamera().getFull_name()
            });
        }

        setVisible(true);
    }
}