package agh.ics.oop;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.lang.Thread;

public class SimulationEngine implements IEngine{

    private MoveDirection[] moves;
    private IWorldMap map;
    private Animal[] animalOrder;

    public SimulationEngine(MoveDirection[] moves, IWorldMap map, Vector2d[] positions){
        this.moves = moves;
        this.map = map;
        animalOrder = new Animal[positions.length];

        for (int i=0; i< positions.length; i++) {
            Animal new_anim = new Animal(this.map, positions[i]);
            if (map.place(new_anim)) {
                animalOrder[i] = new_anim;
            }
            else {
                throw new IllegalArgumentException(positions[i] + " is not legal place for animal");
            }
        }
    }

    @Override
    public void run() {
        // printout initial state of the map
        System.out.print(this.map.toString());

        for (int iter=0; iter<moves.length; iter++) {
            switch (moves[iter]) {
                case FORWARD -> this.animalOrder[iter%animalOrder.length].move(MoveDirection.FORWARD);
                case BACKWARD -> this.animalOrder[iter%animalOrder.length].move(MoveDirection.BACKWARD);
                case LEFT -> this.animalOrder[iter%animalOrder.length].move(MoveDirection.LEFT);
                case RIGHT -> this.animalOrder[iter%animalOrder.length].move(MoveDirection.RIGHT);
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
        JLabel mapv = new JLabel("", SwingConstants.CENTER);
        JPanel panel = new JPanel();

        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        panel.setLayout(new GridLayout(0, 1));
        mapv.setFont(new Font("Serif", Font.CENTER_BASELINE, 21));
        panel.add(mapv);

        frame.add(panel, BorderLayout.CENTER);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Little Animols");
        frame.pack();
        frame.setSize(240, 290);
        frame.setMinimumSize(new Dimension(400, 600));
        frame.setVisible(true);

        // printout initial state of the map
        mapv.setText(stringToHtml(this.map.toString()));
        //
        for (int iter=0; iter<moves.length; iter++) {
            switch (moves[iter]) {
                case FORWARD -> this.animalOrder[iter% animalOrder.length].move(MoveDirection.FORWARD);
                case BACKWARD -> this.animalOrder[iter% animalOrder.length].move(MoveDirection.BACKWARD);
                case LEFT -> this.animalOrder[iter% animalOrder.length].move(MoveDirection.LEFT);
                case RIGHT -> this.animalOrder[iter% animalOrder.length].move(MoveDirection.RIGHT);
                default -> {}
            }

            // Wait before drawing next
            try {
                Thread.sleep(500);
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
