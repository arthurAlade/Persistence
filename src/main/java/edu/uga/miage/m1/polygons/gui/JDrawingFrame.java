package edu.uga.miage.m1.polygons.gui;

import edu.uga.miage.m1.polygons.gui.command.*;
import edu.uga.miage.m1.polygons.gui.persistence.JSONSaver;
import edu.uga.miage.m1.polygons.gui.persistence.Visitable;
import edu.uga.miage.m1.polygons.gui.persistence.XMLSaver;
import edu.uga.miage.m1.polygons.gui.shapes.Circle;
import edu.uga.miage.m1.polygons.gui.shapes.Cube;
import edu.uga.miage.m1.polygons.gui.shapes.SimpleShape;
import edu.uga.miage.m1.polygons.gui.shapes.Square;
import edu.uga.miage.m1.polygons.gui.shapes.Triangle;
import edu.uga.singleshape.CubePanel;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.Serial;
import java.util.*;

/**
 * This class represents the main application class, which is a JFrame subclass
 * that manages a toolbar of shapes and a drawing canvas.
 *
 * @author <a href="mailto:christophe.saint-marcel@univ-grenoble-alpes.fr">Christophe</a>
 */
public class JDrawingFrame extends JFrame implements MouseListener, MouseMotionListener {

    private enum EditButton {

        SQUARE, TRIANGLE, CIRCLE, MOVE, CUBE
    }

    @Serial
    private static final long serialVersionUID = 1L;

    private final JToolBar mToolbar;

    private EditButton mSelected;

    private final JPanel mPanel;

    private final JLabel mLabel;

    private final transient ActionListener mReusableActionListener = new ShapeActionListener();

    private final ArrayList<Visitable> mVisitablesList = new ArrayList<>();
    private final ArrayList<SimpleShape> mShapesList = new ArrayList<>();

    /**
     * Tracks buttons to manage the background.
     */
    private final Map<EditButton, JButton> mButtons = new HashMap<>();

    private final transient CommandList commandList = new CommandList();

    private SimpleShape shapeToMove;

    /**
     * Default constructor that populates the main window.
     */
    public JDrawingFrame(String frameName) {
        super(frameName);
        // Instantiates components
        mToolbar = new JToolBar("Toolbar");
        mPanel = new JPanel();
        mPanel.setBackground(Color.WHITE);
        mPanel.setLayout(null);
        mPanel.setMinimumSize(new Dimension(400, 400));
        mPanel.addMouseListener(this);
        mPanel.addMouseMotionListener(this);
        mLabel = new JLabel(" ", SwingConstants.LEFT);

        JButton mXmlButton = new JButton("XML");
        JButton mJsonButton = new JButton("JSON");
        JButton mUndoButton = new JButton("Undo");

        // Adds action listeners
        mXmlButton.addActionListener(e -> {
            XMLSaver xmlSaver = new XMLSaver(mVisitablesList);
            xmlSaver.saveShapes();
            boolean isSaved = xmlSaver.saveXML();
            if (isSaved) {
                JOptionPane.showMessageDialog(this, "File saved successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "Error while saving file", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
        mJsonButton.addActionListener(e -> {
            JSONSaver jsonSaver = new JSONSaver(mVisitablesList);
            jsonSaver.saveShapes();
            boolean isSaved = jsonSaver.saveJSON();
            if (isSaved) {
                JOptionPane.showMessageDialog(this, "File saved successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "Error while saving file", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        mUndoButton.addActionListener(e -> {
            commandList.undoneLastCommand();
        });

        // Fills the panel
        setLayout(new BorderLayout());
        add(mToolbar, BorderLayout.NORTH);
        add(mPanel, BorderLayout.CENTER);
        add(mLabel, BorderLayout.SOUTH);
        // Add shapes in the menu
        addShape(EditButton.SQUARE, new ImageIcon(Objects.requireNonNull(getClass().getResource("images/square.png"))));
        addShape(EditButton.TRIANGLE, new ImageIcon(Objects.requireNonNull(getClass().getResource("images/triangle.png"))));
        addShape(EditButton.CIRCLE, new ImageIcon(Objects.requireNonNull(getClass().getResource("images/circle.png"))));
        addShape(EditButton.MOVE, new ImageIcon(Objects.requireNonNull(getClass().getResource("images/move.png"))));
        addShape(EditButton.CUBE, new ImageIcon(Objects.requireNonNull(getClass().getResource("images/underc.png"))));

        addButtonToToolbar(mXmlButton);
        addButtonToToolbar(mJsonButton);
        addButtonToToolbar(mUndoButton);
        setPreferredSize(new Dimension(400, 400));
    }

    private void addButtonToToolbar(JButton button) {
        mToolbar.add(button);
        mToolbar.validate();
        repaint();
    }

    /**
     * Injects an available <tt>SimpleShape</tt> into the drawing frame.
     * @param shape The shape to inject.
     * @param icon The icon associated with the injected <tt>SimpleShape</tt>.
     */
    private void addShape(EditButton shape, ImageIcon icon) {
        JButton button = new JButton(icon);
        button.setBorderPainted(false);
        mButtons.put(shape, button);
        button.setActionCommand(shape.toString());
        button.addActionListener(mReusableActionListener);
        if (mSelected == null) {
            button.doClick();
        }
        mToolbar.add(button);
        mToolbar.validate();
        repaint();
    }

    /**
     * Implements method for the <tt>MouseListener</tt> interface to
     * draw the selected shape into the drawing canvas.
     * @param evt The associated mouse event.
     */
    public void mouseClicked(MouseEvent evt) {
        if (mPanel.contains(evt.getX(), evt.getY())) {
            Graphics2D g2 = (Graphics2D) mPanel.getGraphics();
            switch(mSelected) {
                case CIRCLE:
                    Circle circle = new Circle(evt.getX(), evt.getY());
                    addShape(circle, g2);
                    break;
                case TRIANGLE:
                    Triangle triangle = new Triangle(evt.getX(), evt.getY());
                    addShape(triangle, g2);
                    break;
                case SQUARE:
                    Square square = new Square(evt.getX(), evt.getY());
                    addShape(square, g2);
                    break;
                case MOVE:
                    moveShape(evt, g2);
                    break;
                case CUBE:
                    Cube cube = new Cube(evt.getX(), evt.getY());
                    addShape(cube, g2);

                    break;
                default:
                    throw new IllegalStateException("Unexpected value: " + mSelected);
            }
        }
    }

    public void addShape(SimpleShape shape, Graphics2D g2) {
        commandList.add(new AddCommand(shape, g2, this));
        commandList.executeLastCommand();
        addShapeToList(shape);
    }

    public void addShapeToList(SimpleShape shape) {
        mVisitablesList.add((Visitable) shape);
        mShapesList.add(shape);
    }

    private void moveShape(MouseEvent evt, Graphics2D g2) {
        if(shapeToMove == null) {
            System.out.println("Move shape");
            boolean isShapeSelected = false;
            for (SimpleShape shape : mShapesList) {
                System.out.println("Shape : " + shape.toString()+", x : "+shape.getX()+", y : "+shape.getY());
                int x = evt.getX() - shape.getX();
                int y = evt.getY() - shape.getY();
                System.out.println("x : "+x+", y : "+y);
                if (x >= 0 && x <= 50 && y >= 0 && y <= 50) {
                    System.out.println("Shape selected");
                    shapeToMove = shape;
                    isShapeSelected = true;
                    break;
                }
            }
            if (isShapeSelected) {
                commandList.add(new MoveCommand(shapeToMove, g2, this));
                commandList.executeLastCommand();
            }
        }
        else{
            shapeToMove.setX(evt.getX()-25);
            shapeToMove.setY(evt.getY()-25);

            commandList.executeLastCommand();
            shapeToMove = null;
        }
    }

    public void removeShape(int index) {
        if (!mVisitablesList.isEmpty()) {
            mVisitablesList.remove(index);
            mShapesList.remove(index);
            Graphics2D g2 = (Graphics2D) mPanel.getGraphics();
            g2.clearRect(0, 0, mPanel.getWidth(), mPanel.getHeight());
            g2.setColor(Color.WHITE);
            g2.fillRect(0, 0, mPanel.getWidth(), mPanel.getHeight());
            mShapesList.forEach(shape -> shape.draw(g2));
        }
    }

    public void printList(String a){
        System.out.println("Liste des formes "+a+", size :"+mShapesList.size());
        mShapesList.forEach(shape -> System.out.println(shape.toString()));
    }

    public int getShapesListSize() {
        return mShapesList.size();
    }

    public int getShapesListIndex(SimpleShape shape) {
        return mShapesList.indexOf(shape);
    }


    /**
     * Implements an empty method for the <tt>MouseListener</tt> interface.
     * @param evt The associated mouse event.
     */
    public void mouseEntered(MouseEvent evt) {
        // Method not used
    }

    /**
     * Implements an empty method for the <tt>MouseListener</tt> interface.
     * @param evt The associated mouse event.
     */
    public void mouseExited(MouseEvent evt) {
        mLabel.setText(" ");
        mLabel.repaint();
    }

    /**
     * Implements method for the <tt>MouseListener</tt> interface to initiate
     * shape dragging.
     * @param evt The associated mouse event.
     */
    public void mousePressed(MouseEvent evt) {
        // Method not used
    }

    /**
     * Implements method for the <tt>MouseListener</tt> interface to complete
     * shape dragging.
     * @param evt The associated mouse event.
     */
    public void mouseReleased(MouseEvent evt) {
      // Method not used
    }

    /**
     * Implements method for the <tt>MouseMotionListener</tt> interface to
     * move a dragged shape.
     * @param evt The associated mouse event.
     */
    public void mouseDragged(MouseEvent evt) {
        // Method not used
    }

    /**
     * Implements an empty method for the <tt>MouseMotionListener</tt>
     * interface.
     * @param evt The associated mouse event.
     */
    public void mouseMoved(MouseEvent evt) {
        modifyLabel(evt);
    }

    private void modifyLabel(MouseEvent evt) {
        mLabel.setText("(" + evt.getX() + "," + evt.getY() + ")");
    }

    /**
     * Simple action listener for shape tool bar buttons that sets
     * the drawing frame's currently selected shape when receiving
     * an action event.
     */
    private class ShapeActionListener implements ActionListener {

        public void actionPerformed(ActionEvent evt) {
            // Itère sur tous les boutons
            for (Map.Entry<EditButton, JButton> shapesJButtonEntry : mButtons.entrySet()) {
                EditButton shape = shapesJButtonEntry.getKey();
                JButton btn = mButtons.get(shape);
                if (evt.getActionCommand().equals(shape.toString())) {
                    btn.setBorderPainted(true);
                    mSelected = shape;
                } else {
                    btn.setBorderPainted(false);
                }
                btn.repaint();
            }
        }
    }

}
