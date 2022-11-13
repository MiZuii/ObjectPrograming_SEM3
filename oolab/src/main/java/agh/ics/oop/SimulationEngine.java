package agh.ics.oop;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.lang.Thread;

public class SimulationEngine implements IEngine{

    private MoveDirection[] moves;
    private IWorldMap map;
    private ArrayList<Animal> animal_order = new ArrayList<Animal>();

    public SimulationEngine(MoveDirection[] moves, IWorldMap map, Vector2d[] positions){
        this.moves = moves;
        this.map = map;

        for (Vector2d position: positions) {
            Animal new_anim = new Animal(this.map, position);
            if (map.place(new_anim)) {
                this.animal_order.add(new_anim);
            }
        }
    }

    @Override
    public void run() {
        // printout initial state of the map
        System.out.print(this.map.toString());

        for (int iter=0; iter<moves.length; iter++) {
            if (animal_order.isEmpty()) {
                return;
            }

            switch (moves[iter]) {
                case FORWARD -> {Animal tmp = animal_order.get(iter% animal_order.size());
                                    tmp.move(MoveDirection.FORWARD);}
                case BACKWARD -> {Animal tmp = animal_order.get(iter% animal_order.size());
                                    tmp.move(MoveDirection.BACKWARD);}
                case LEFT -> {Animal tmp = animal_order.get(iter% animal_order.size());
                                    tmp.move(MoveDirection.LEFT);}
                case RIGHT -> {Animal tmp = animal_order.get(iter% animal_order.size());
                                    tmp.move(MoveDirection.RIGHT);}
                default -> {}
            }
            System.out.print(this.map.toString());
        }
    }

    private String stringToHtml(String mapString){
        return "<html>" + mapString.replaceAll("<","&lt;").replaceAll(">", "&gt;").replaceAll("\n", "<br/>") + "</html>";
    }

    public void runWithSwing() {

        // swing init
        JFrame frame = new JFrame();
        JLabel mapv = new JLabel();
        JPanel panel = new JPanel();
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        panel.setLayout(new GridLayout(0, 1));
        panel.add(mapv);

        frame.add(panel, BorderLayout.CENTER);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Little Animols");
        frame.pack();
        frame.setVisible(true);

        // printout initial state of the map
        mapv.setText(stringToHtml(this.map.toString()));
        //

        for (int iter = 0; iter < moves.length; iter++) {
            if (animal_order.isEmpty()) {
                return;
            }

            switch (moves[iter]) {
                case FORWARD -> {
                    Animal tmp = animal_order.get(iter % animal_order.size());
                    tmp.move(MoveDirection.FORWARD);
                }
                case BACKWARD -> {
                    Animal tmp = animal_order.get(iter % animal_order.size());
                    tmp.move(MoveDirection.BACKWARD);
                }
                case LEFT -> {
                    Animal tmp = animal_order.get(iter % animal_order.size());
                    tmp.move(MoveDirection.LEFT);
                }
                case RIGHT -> {
                    Animal tmp = animal_order.get(iter % animal_order.size());
                    tmp.move(MoveDirection.RIGHT);
                }
                default -> {
                }
            }
            // Wait before drawing next
            try {
                Thread.sleep(2000);
            }
            catch (Exception e) {

                // catching the exception
                System.out.println(e);
            }

            // draw next frame
            mapv.setText(stringToHtml(this.map.toString()));
            //
        }
    }
}
