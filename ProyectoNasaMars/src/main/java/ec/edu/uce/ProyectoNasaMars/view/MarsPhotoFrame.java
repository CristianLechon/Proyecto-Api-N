package ec.edu.uce.ProyectoNasaMars.view;

import ec.edu.uce.ProyectoNasaMars.controller.Container;
import ec.edu.uce.ProyectoNasaMars.model.MarsPhoto;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.util.List;

public class MarsPhotoFrame extends JFrame {
    private JTable table;
    private JComboBox<String> comboBox, comboBox2;
    private JButton btn, btn2;
    private JTextField textField;
    private Container container;

    public MarsPhotoFrame() throws Exception {
        setTitle("Mars Photos");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocation(270, 100);

        container = new Container();

        String[] columnNames = {"ID", "Sol", "Camera Name", "URL Image", "Earth Date", "Rover Name", "Full name"};
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);
        table = new JTable(model);

        JScrollPane scrollPane = new JScrollPane(table);

        // Creamos el comboBox y lo agregamos al panel superior
        comboBox = new JComboBox<>();
        comboBox.addItem(" ");
        comboBox.addItem("Procesamiento Paralelo");
        comboBox.addItem("Procesamiento Secuencial");

        comboBox2 = new JComboBox<>();
        textField = new JTextField(10);

        btn = new JButton("Ver Imagen");
        btn2 = new JButton("Filtrar");

        btn.addActionListener(e -> {
            int selectedRow = table.getSelectedRow();
            if (selectedRow >= 0) {
                String imageUrl = (String) table.getValueAt(selectedRow, 3);
                String insert = "s";
                int position = 4;
                String concatenated = imageUrl.substring(0, position) + insert + imageUrl.substring(position);
                container.getImage(concatenated);
            } else {
                JOptionPane.showMessageDialog(this, "Por favor, seleccione una fila de la tabla.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        JPanel topPanel = new JPanel();
        topPanel.setLayout(new FlowLayout());
        topPanel.add(btn, BorderLayout.WEST);
        topPanel.add(comboBox);
        topPanel.add(comboBox2);
        topPanel.add(textField, BorderLayout.EAST);
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
                    comboBox2.setModel(new DefaultComboBoxModel<>(new String[]{" ", "Id Paralelo", "Name Paralelo", "Date Paralelo"}));
                } else if (selectedOption.equals("Procesamiento Secuencial")) {
                    comboBox2.setModel(new DefaultComboBoxModel<>(new String[]{" ", "Id Secuencial", "Name Secuencial", "Date Secuencial"}));
                }
            }
        });

        comboBox2.addItemListener(e -> {
            if (e.getStateChange() == ItemEvent.SELECTED) {

                for (ActionListener al : btn2.getActionListeners()) {
                    btn2.removeActionListener(al);
                }

                String selectedOption = (String) comboBox2.getSelectedItem();

                btn2.addActionListener(e1 -> {
                    try {
                        String aux = textField.getText().trim();
                        textField.setText("");

                        List<MarsPhoto> photos;
                        if (!selectedOption.equals(" ")) {
                            switch (selectedOption) {
                                case "Id Paralelo":
                                    photos = container.getByIdParallel(Integer.parseInt(aux));
                                    break;
                                case "Id Secuencial":
                                    photos = container.getByIdSequential(Integer.parseInt(aux));
                                    break;
                                case "Name Paralelo":
                                    photos = container.getByNameParallel(aux);
                                    break;
                                case "Name Secuencial":
                                    photos = container.getByNameSequential(aux);
                                    break;
                                case "Date Paralelo":
                                    photos = container.getByDateParallel(aux);
                                    break;
                                case "Date Secuencial":
                                    photos = container.getByDateSequential(aux);
                                    break;
                                default:
                                    photos = container.loadTable();
                                    break;
                            }
                        } else {
                            photos = container.loadTable();
                        }
                        displayPhotos(photos);
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(this, "Por favor, ingrese un número válido.", "Error", JOptionPane.ERROR_MESSAGE);
                    } catch (Exception ex) {
                        throw new RuntimeException(ex);
                    }
                });
            }
        });
    }

    //display para la tabla
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