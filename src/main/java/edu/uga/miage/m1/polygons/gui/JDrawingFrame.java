package edu.uga.miage.m1.polygons.gui;

import edu.uga.miage.m1.polygons.gui.command.*;
import edu.uga.miage.m1.polygons.gui.persistence.JSONSaver;
import edu.uga.miage.m1.polygons.gui.persistence.XMLImporter;
import edu.uga.miage.m1.polygons.gui.persistence.XMLSaver;
import edu.uga.miage.m1.polygons.gui.shapes.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.Serial;
import java.util.*;
import java.util.List;

/**
 * This class represents the main application class, which is a JFrame subclass that manages a
 * toolbar of shapes and a drawing canvas.
 *
 * @author <a href="mailto:christophe.saint-marcel@univ-grenoble-alpes.fr">Christophe</a>
 */
public class JDrawingFrame extends JFrame
        implements MouseListener, MouseMotionListener, KeyListener {

    private enum EditButton {

        SQUARE, TRIANGLE, CIRCLE, MOVE, CUBE, GROUP
    }

    @Serial
    private static final long serialVersionUID = 1L;

    private final JToolBar mToolbar;

    private EditButton mSelected;

    private final JPanel mPanel;

    private final JLabel mLabel;

    private final transient ActionListener mReusableActionListener = new ShapeActionListener();

    private final transient ArrayList<AbstractShape> mShapesList = new ArrayList<>();

    /**
     * Tracks buttons to manage the background.
     */
    private final Map<EditButton, JButton> mButtons = new EnumMap<>(EditButton.class);

    private final transient CommandList commandList = new CommandList();
    private final JButton mXmlButton;
    private final JButton mJsonButton;
    private final JButton mUndoButton;
    private final JButton mXmlImportButton;
    private final JButton mXmlImportButton2;

    private transient AbstractShape shapeToMove;
    private transient GroupShape groupShapeToDo;

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

        mPanel.addKeyListener(this);
        mPanel.setFocusable(true);

        mXmlButton = new JButton("XML");
        mJsonButton = new JButton("JSON");
        mUndoButton = new JButton("Undo");
        mXmlImportButton = new JButton("Import XML");
        mXmlImportButton2 = new JButton("Import XML2");

        // Adds action listeners
        mXmlButton.addActionListener(e -> {
            XMLSaver xmlSaver = new XMLSaver(mShapesList);
            xmlSaver.saveShapes();
            boolean isSaved = xmlSaver.saveXML();
            if (isSaved) {
                JOptionPane.showMessageDialog(this, "File saved successfully", "Success",
                        JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "Error while saving file", "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        });
        mJsonButton.addActionListener(e -> {
            JSONSaver jsonSaver = new JSONSaver(mShapesList);
            jsonSaver.saveShapes();
            boolean isSaved = jsonSaver.saveJSON();
            if (isSaved) {
                JOptionPane.showMessageDialog(this, "File saved successfully", "Success",
                        JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "Error while saving file", "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        });
        mUndoButton.addActionListener(e -> {
            commandList.undoneLastCommand();
        });
        mXmlImportButton.addActionListener(e -> {
            XMLImporter xmlImporter = new XMLImporter();
            boolean isImported = xmlImporter.importXML();
            if (isImported) {
                JOptionPane.showMessageDialog(this, "File imported successfully", "Success",
                        JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "Error while importing file", "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        });

        mXmlImportButton2.addActionListener(e -> {
            XMLImporter xmlImporter = new XMLImporter();
            xmlImporter.importAbstractShape("save.xml");
            for (AbstractShape shape : xmlImporter.importAbstractShape("save.xml")) {
                addShape(shape, (Graphics2D) mPanel.getGraphics());
                if ((Graphics2D) mPanel.getGraphics() == null) {
                    System.out.println("null");
                }
            }
        });


        // Fills the panel
        setLayout(new BorderLayout());
        add(mToolbar, BorderLayout.NORTH);
        add(mPanel, BorderLayout.CENTER);
        add(mLabel, BorderLayout.SOUTH);
        // Add shapes in the menu
        addShape(EditButton.SQUARE,
                new ImageIcon(Objects.requireNonNull(getClass().getResource("images/square.png"))));
        addShape(EditButton.TRIANGLE, new ImageIcon(
                Objects.requireNonNull(getClass().getResource("images/triangle.png"))));
        addShape(EditButton.CIRCLE,
                new ImageIcon(Objects.requireNonNull(getClass().getResource("images/circle.png"))));
        addShape(EditButton.MOVE,
                new ImageIcon(Objects.requireNonNull(getClass().getResource("images/move.png"))));
        addShape(EditButton.CUBE,
                new ImageIcon(Objects.requireNonNull(getClass().getResource("images/underc.png"))));
        addShape(EditButton.GROUP,
                new ImageIcon(Objects.requireNonNull(getClass().getResource("images/link.png"))));

        addButtonToToolbar(mXmlButton);
        addButtonToToolbar(mJsonButton);
        addButtonToToolbar(mUndoButton);
        addButtonToToolbar(mXmlImportButton);
        addButtonToToolbar(mXmlImportButton2);
        setPreferredSize(new Dimension(500, 500));

        mPanel.requestFocus();
    }

    private void addButtonToToolbar(JButton button) {
        mToolbar.add(button);
        mToolbar.validate();
        repaint();
    }

    /**
     * Injects an available <tt>AbstractShape</tt> into the drawing frame.
     * @param shape The shape to inject.
     * @param icon The icon associated with the injected <tt>AbstractShape</tt>.
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
     * Implements method for the <tt>MouseListener</tt> interface to draw the selected shape into
     * the drawing canvas.
     * 
     * @param evt The associated mouse event.
     */
    public void mouseClicked(MouseEvent evt) {
        if (mPanel.contains(evt.getX(), evt.getY())) {
            Graphics2D g2 = (Graphics2D) mPanel.getGraphics();
            switch (mSelected) {
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
        mPanel.requestFocus();
        mPanel.setFocusable(true);
    }

    public void addShape(AbstractShape shape, Graphics2D g2) {
        commandList.add(new AddCommand(shape, g2, this));
        commandList.executeLastCommand();
        addShapeToList(shape);
    }

    public void addShapeToList(AbstractShape shape) {
        mShapesList.add(shape);
    }

    private void moveShape(MouseEvent evt, Graphics2D g2) {
        if (shapeToMove == null) {
            boolean isShapeSelected = false;
            for (AbstractShape shape : mShapesList) {
                int x = evt.getX() - shape.getX();
                int y = evt.getY() - shape.getY();
                if (!(shape instanceof GroupShape)) {
                    if (x >= 0 && x <= 50 && y >= 0 && y <= 50) {
                        shapeToMove = shape;
                        isShapeSelected = true;
                        break;
                    }
                }
            }
            if (isShapeSelected) {
                commandList.add(new MoveCommand(shapeToMove, g2, this));
                commandList.executeLastCommand();
            }
        } else {
            shapeToMove.setX(evt.getX() - 25);
            shapeToMove.setY(evt.getY() - 25);

            commandList.executeLastCommand();
            shapeToMove = null;
        }
    }

    public void removeShapeList(List<AbstractShape> shapeList) {
        shapeList.forEach(shape -> removeShape(mShapesList.indexOf(shape)));
    }

    public void removeShape(int index) {
        if (!mShapesList.isEmpty()) {
            AbstractShape abstractShape = mShapesList.remove(index);
            if (abstractShape instanceof GroupShape groupShape) {
                mShapesList.addAll(groupShape.getShapes());
            }
            Graphics2D g2 = (Graphics2D) mPanel.getGraphics();
            g2.clearRect(0, 0, mPanel.getWidth(), mPanel.getHeight());
            g2.setColor(Color.WHITE);
            g2.fillRect(0, 0, mPanel.getWidth(), mPanel.getHeight());
            mShapesList.forEach(shape -> {
                shape.draw(g2);
                if (shape instanceof GroupShape groupShape) {
                    groupShape.getShapes().forEach(shape1 -> shape1.draw(g2));
                }
            });
        }
    }



    public int getShapesListSize() {
        return mShapesList.size();
    }

    public int getShapesListIndex(AbstractShape shape) {
        return mShapesList.indexOf(shape);
    }


    /**
     * Implements an empty method for the <tt>MouseListener</tt> interface.
     * 
     * @param evt The associated mouse event.
     */
    public void mouseEntered(MouseEvent evt) {
        mPanel.requestFocus();
    }

    /**
     * Implements an empty method for the <tt>MouseListener</tt> interface.
     * 
     * @param evt The associated mouse event.
     */
    public void mouseExited(MouseEvent evt) {
        mLabel.setText(" ");
        mLabel.repaint();
        mPanel.requestFocus();

    }

    /**
     * Implements method for the <tt>MouseListener</tt> interface to initiate shape dragging.
     * 
     * @param evt The associated mouse event.
     */
    public void mousePressed(MouseEvent evt) {
        if (mPanel.contains(evt.getX(), evt.getY())) {
            Graphics2D g2 = (Graphics2D) mPanel.getGraphics();
            if (mSelected == EditButton.GROUP) {
                GroupShape groupShape = new GroupShape(evt.getX() + 25, evt.getY() + 25);
                addShape(groupShape, g2);
                groupShapeToDo = groupShape;
            }
        }
    }

    /**
     * Implements method for the <tt>MouseListener</tt> interface to complete shape dragging.
     * 
     * @param evt The associated mouse event.
     */
    public void mouseReleased(MouseEvent evt) {
        if (mSelected == EditButton.GROUP) {
            System.out.println(mShapesList);
            groupShapeToDo.setxEnd(evt.getX());
            groupShapeToDo.setyEnd(evt.getY());
            ArrayList<AbstractShape> shapesToGroup = new ArrayList<>();
            mShapesList.forEach(shape -> {
                if((shape != groupShapeToDo) && (shape.getX()>= groupShapeToDo.getX()) && (shape.getX() <= groupShapeToDo.getxEnd()) && (shape.getY()>= groupShapeToDo.getY()) && (shape.getY() <= groupShapeToDo.getyEnd())){
                    shapesToGroup.add(shape);
                }
            });
            System.out.println(shapesToGroup);
            groupShapeToDo.setShapes(shapesToGroup);
            removeShapeList(shapesToGroup);
            System.out.println(mShapesList);
            groupShapeToDo.setGrouped(true);

            groupShapeToDo.draw((Graphics2D) mPanel.getGraphics());
            groupShapeToDo = null;
        }


    }

    /**
     * Implements method for the <tt>MouseMotionListener</tt> interface to move a dragged shape.
     * 
     * @param evt The associated mouse event.
     */
    public void mouseDragged(MouseEvent evt) {
        // Method not used
    }

    /**
     * Implements an empty method for the <tt>MouseMotionListener</tt> interface.
     * 
     * @param evt The associated mouse event.
     */
    public void mouseMoved(MouseEvent evt) {
        modifyLabel(evt);
    }

    private void modifyLabel(MouseEvent evt) {
        mLabel.setText("(" + evt.getX() + "," + evt.getY() + ")");
    }

    /**
     * Simple action listener for shape tool bar buttons that sets the drawing frame's currently
     * selected shape when receiving an action event.
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

    @Override
    public void keyTyped(KeyEvent e) {
        // Do nothing
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if ((e.isMetaDown() || e.isControlDown())
                && (e.getKeyChar() == 'z' || e.getKeyChar() == 'Z')) {
            commandList.undoneLastCommand();
        }
    }
    @Override
    public void keyReleased(KeyEvent e) {
        // Do nothing
    }

    public JButton getmXmlButton() {
        return mXmlButton;
    }
    public JButton getmJsonButton() {
        return mJsonButton;
    }
    public JPanel getmPanel() {
        return mPanel;
    }
}
