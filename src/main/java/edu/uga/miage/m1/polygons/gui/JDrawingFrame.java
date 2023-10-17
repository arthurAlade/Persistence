package edu.uga.miage.m1.polygons.gui;

import edu.uga.miage.m1.polygons.gui.command.AddCommand;
import edu.uga.miage.m1.polygons.gui.command.Command;
import edu.uga.miage.m1.polygons.gui.command.CommandList;
import edu.uga.miage.m1.polygons.gui.command.RemoveCommand;
import edu.uga.miage.m1.polygons.gui.persistence.JSONSaver;
import edu.uga.miage.m1.polygons.gui.persistence.Visitable;
import edu.uga.miage.m1.polygons.gui.persistence.XMLSaver;
import edu.uga.miage.m1.polygons.gui.shapes.Circle;
import edu.uga.miage.m1.polygons.gui.shapes.SimpleShape;
import edu.uga.miage.m1.polygons.gui.shapes.Square;
import edu.uga.miage.m1.polygons.gui.shapes.Triangle;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.util.List;
import java.util.logging.Logger;

/**
 * This class represents the main application class, which is a JFrame subclass
 * that manages a toolbar of shapes and a drawing canvas.
 *
 * @author <a href="mailto:christophe.saint-marcel@univ-grenoble-alpes.fr">Christophe</a>
 */
public class JDrawingFrame extends JFrame implements MouseListener, MouseMotionListener {

    private enum Shapes {

        SQUARE, TRIANGLE, CIRCLE
    }

    private static final long serialVersionUID = 1L;

    private final JToolBar mToolbar;

    private Shapes mSelected;

    private final JPanel mPanel;

    private final JLabel mLabel;

    private final ActionListener mReusableActionListener = new ShapeActionListener();

    private final JButton mXmlButton;
    private final JButton mJsonButton;
    private final JButton mRemoveButton;

    private final ArrayList<Visitable> mVisitablesList = new ArrayList<>();
    private final ArrayList<SimpleShape> mShapesList = new ArrayList<>();
    private final Logger logger = Logger.getLogger(JDrawingFrame.class.getName());

    /**
     * Tracks buttons to manage the background.
     */
    private final Map<Shapes, JButton> mButtons = new HashMap<>();

    private final CommandList commandList = new CommandList();

    /**
     * Default constructor that populates the main window.
     * @param frameName
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

        mXmlButton = new JButton("XML");
        mJsonButton = new JButton("JSON");
        mRemoveButton = new JButton("Remove - Crtl + Z");

        // Adds action listeners
        mXmlButton.addActionListener(e -> {
            XMLSaver xmlSaver = new XMLSaver(mVisitablesList);
            xmlSaver.saveShapes();
            xmlSaver.saveXML();
        });
        mJsonButton.addActionListener(e -> {
            JSONSaver jsonSaver = new JSONSaver(mVisitablesList);
            jsonSaver.saveShapes();
            jsonSaver.saveJSON();
        });

        mRemoveButton.addActionListener(e -> {
            commandList.add(new RemoveCommand(this));
            commandList.executeLastCommand();
        });

        // Fills the panel
        setLayout(new BorderLayout());
        add(mToolbar, BorderLayout.NORTH);
        add(mPanel, BorderLayout.CENTER);
        add(mLabel, BorderLayout.SOUTH);
        // Add shapes in the menu
        addShape(Shapes.SQUARE, new ImageIcon(Objects.requireNonNull(getClass().getResource("images/square.png"))));
        addShape(Shapes.TRIANGLE, new ImageIcon(Objects.requireNonNull(getClass().getResource("images/triangle.png"))));
        addShape(Shapes.CIRCLE, new ImageIcon(Objects.requireNonNull(getClass().getResource("images/circle.png"))));

        addButtonToToolbar(mXmlButton);
        addButtonToToolbar(mJsonButton);
        addButtonToToolbar(mRemoveButton);
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
    private void addShape(Shapes shape, ImageIcon icon) {
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
                    commandList.add(new AddCommand(circle, g2));
                    commandList.executeLastCommand();
                    mVisitablesList.add(circle);
                    mShapesList.add(circle);
                    break;
                case TRIANGLE:
                    Triangle triangle = new Triangle(evt.getX(), evt.getY());
                    commandList.add(new AddCommand(triangle, g2));
                    commandList.executeLastCommand();
                    mVisitablesList.add(triangle);
                    mShapesList.add(triangle);
                    break;
                case SQUARE:
                    Square square = new Square(evt.getX(), evt.getY());
                    commandList.add(new AddCommand(square, g2));
                    commandList.executeLastCommand();
                    mVisitablesList.add(square);
                    mShapesList.add(square);
                    break;
                default:
                    logger.severe("No shape named " + mSelected);
            }
        }
    }

    public void removeLastShape() {
        if (!mVisitablesList.isEmpty()) {
            mVisitablesList.remove(mVisitablesList.size() - 1);
            mShapesList.remove(mShapesList.size() - 1);
            Graphics2D g2 = (Graphics2D) mPanel.getGraphics();
            g2.clearRect(0, 0, mPanel.getWidth(), mPanel.getHeight());
            mShapesList.forEach(shape -> shape.draw(g2));
        }
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
            Iterator<Shapes> keys = mButtons.keySet().iterator();
            while (keys.hasNext()) {
                Shapes shape = keys.next();
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
