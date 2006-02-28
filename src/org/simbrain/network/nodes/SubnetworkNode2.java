
package org.simbrain.network.nodes;

import java.awt.Color;
import java.awt.Paint;
import java.awt.Stroke;
import java.awt.BasicStroke;

import java.awt.geom.Point2D;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import java.util.Iterator;

import edu.umd.cs.piccolo.PNode;

import edu.umd.cs.piccolo.nodes.PPath;
import edu.umd.cs.piccolo.nodes.PText;

import edu.umd.cs.piccolo.util.PBounds;

import org.simbrain.network.NetworkPanel;

import org.simnet.interfaces.Network;

/**
 * Abstract subnetwork node.
 */
abstract class SubnetworkNode2 extends ScreenElement implements PropertyChangeListener {

    /** Tab height. */
    private static final double TAB_HEIGHT = 22.0d;

    /** Default tab width. */
    private static final double DEFAULT_TAB_WIDTH = 100.0d;

    /** Tab inset or border height. */
    private static final double TAB_INSET_HEIGHT = 5.0d;

    /** Tab inset or border width. */
    private static final double TAB_INSET_WIDTH = 6.0d;

    /** Outline inset or border height. */
    public static final double OUTLINE_INSET_HEIGHT = 12.0d;

    /** Outline inset or border width. */
    public static final double OUTLINE_INSET_WIDTH = 12.0d;

    /** Default outline height. */
    private static final double DEFAULT_OUTLINE_HEIGHT = 150.0d;

    /** Default outline width. */
    private static final double DEFAULT_OUTLINE_WIDTH = 150.0d;

    /** Default tab paint. */
    private static final Paint DEFAULT_TAB_PAINT = Color.LIGHT_GRAY;

    /** Default tab stroke. */
    private static final Stroke DEFAULT_TAB_STROKE = new BasicStroke(1.0f);

    /** Default tab stroke paint. */
    private static final Paint DEFAULT_TAB_STROKE_PAINT = Color.DARK_GRAY;

    /** Default outline stroke. */
    private static final Stroke DEFAULT_OUTLINE_STROKE = new BasicStroke(1.0f);

    /** Default outline stroke paint. */
    private static final Paint DEFAULT_OUTLINE_STROKE_PAINT = Color.LIGHT_GRAY;

    /** The subnetwork for this subnetwork node. */
    private final Network subnetwork;

    /** Tab node. */
    private final TabNode tab;

    /** Outline node. */
    private final OutlineNode outline;

    /** The tab paint for this subnetwork node. */
    private Paint tabPaint;

    /** The tab stroke for this subnetwork node. */
    private Stroke tabStroke;

    /** The tab stroke paint for this subnetwork node. */
    private Paint tabStrokePaint;

    /** The outline stroke for this subnetwork node. */
    private Stroke outlineStroke;

    /** The outline stroke paint for this subnetwork node. */
    private Paint outlineStrokePaint;

    /** Intial child layout complete. */
    private boolean initialChildLayoutComplete;


    /**
     * Create a new abstract subnetwork node from the specified parameters.
     *
     * @param networkPanel networkPanel for this subnetwork node, must not be null
     * @param subnetwork subnetwork for this subnetwork node, must not be null
     * @param x x offset for this subnetwork node
     * @param y y offset for this subnetwork node
     */
    protected SubnetworkNode2(final NetworkPanel networkPanel,
                              final Network subnetwork,
                              final double x, final double y) {

        super(networkPanel);

        if (subnetwork == null)
        {
            throw new IllegalArgumentException("subnetwork must not be null");
        }
        this.subnetwork = subnetwork;

        offset(x, y);
        setPickable(true);
        setChildrenPickable(true);

        tab = new TabNode();
        outline = new OutlineNode();

        addChild(outline);
        addChild(tab);

        setBounds(tab.getBounds());

        setLabel(subnetwork.getType());
        setTabPaint(DEFAULT_TAB_PAINT);
        setTabStroke(DEFAULT_TAB_STROKE);
        setTabStrokePaint(DEFAULT_TAB_STROKE_PAINT);
        setOutlineStroke(DEFAULT_OUTLINE_STROKE);
        setOutlineStrokePaint(DEFAULT_OUTLINE_STROKE_PAINT);

        initialChildLayoutComplete = false;
    }


    /** @see ScreenElement */
    public final boolean isSelectable() {
        return true;
    }

    /** @see ScreenElement */
    public final boolean showSelectionHandle() {
        return false;
    }

    /** @see ScreenElement */
    public final boolean isDraggable() {
        return true;
    }

    /** @see ScreenElement */
    public final void resetColors() {
        // empty
    }

    /**
     * Return the label for this subnetwork node.
     * The label may be null.
     *
     * @return the label for this subnetwork node
     */
    public final String getLabel() {
        return tab.getLabel();
    }

    /**
     * Set the label for this subnetwork node to <code>label</code>.
     *
     * @param label label for this subnetwork node
     */
    public final void setLabel(final String label) {
        tab.setLabel(label);
    }

    /**
     * Return the subnetwork for this subnetwork node.
     * The subnetwork will not be null.
     *
     * @return the subnetwork for this subnetwork node
     */
    public final Network getSubnetwork() {
        return subnetwork;
    }

    /**
     * Return the tab paint for this subnetwork node.
     * The tab paint may be null.
     *
     * @return the tab paint for this subnetwork node
     */
    public final Paint getTabPaint() {
        return tabPaint;
    }

    /**
     * Set the tab paint for this subnetwork node to <code>tabPaint</code>.
     *
     * <p>This is a bound property.</p>
     *
     * @param tabPaint tab paint for this subnetwork node
     */
    public final void setTabPaint(final Paint tabPaint) {
        Paint oldTabPaint = this.tabPaint;
        this.tabPaint = tabPaint;
        tab.setTabPaint(tabPaint);
        firePropertyChange("tabPaint", oldTabPaint, this.tabPaint);
    }

    /**
     * Return the tab stroke for this subnetwork node.
     * The tab stroke may be null.
     *
     * @return the tab stroke for this subnetwork node
     */
    public final Stroke getTabStroke() {
        return tabStroke;
    }

    /**
     * Set the tab stroke for this subnetwork node to <code>tabStroke</code>.
     *
     * <p>This is a bound property.</p>
     *
     * @param tabStroke tab stroke for this subnetwork node
     */
    public final void setTabStroke(final Stroke tabStroke) {
        Stroke oldTabStroke = this.tabStroke;
        this.tabStroke = tabStroke;
        tab.setTabStroke(tabStroke);
        firePropertyChange("tabStroke", oldTabStroke, this.tabStroke);
    }

    /**
     * Return the tab stroke paint for this subnetwork node.
     * The tab stroke paint may be null.
     *
     * @return the tab stroke paint for this subnetwork node
     */
    public final Paint getTabStrokePaint() {
        return tabStrokePaint;
    }

    /**
     * Set the tab stroke paint for this subnetwork node to <code>tabStrokePaint</code>.
     *
     * <p>This is a bound property.</p>
     *
     * @param tabStrokePaint tab stroke paint for this subnetwork node
     */
    public final void setTabStrokePaint(final Paint tabStrokePaint) {
        Paint oldTabStrokePaint = this.tabStrokePaint;
        this.tabStrokePaint = tabStrokePaint;
        tab.setTabStrokePaint(tabStrokePaint);
        firePropertyChange("tabStrokePaint", oldTabStrokePaint, this.tabStrokePaint);
    }

    /**
     * Return the outline stroke for this subnetwork node.
     * The outline stroke may be null.
     *
     * @return the outline stroke for this subnetwork node
     */
    public final Stroke getOutlineStroke() {
        return outlineStroke;
    }

    /**
     * Set the outline stroke for this subnetwork node to <code>outlineStroke</code>.
     *
     * <p>This is a bound property.</p>
     *
     * @param outlineStroke outline stroke for this subnetwork node
     */
    public final void setOutlineStroke(final Stroke outlineStroke) {
        Stroke oldOutlineStroke = this.outlineStroke;
        this.outlineStroke = outlineStroke;
        outline.setStroke(outlineStroke);
        firePropertyChange("outlineStroke", oldOutlineStroke, this.outlineStroke);
    }

    /**
     * Return the outline stroke paint for this subnetwork node.
     * The outline stroke paint may be null.
     *
     * @return the outline stroke paint for this subnetwork node
     */
    public final Paint getOutlineStrokePaint() {
        return outlineStrokePaint;
    }

    /**
     * Set the outline stroke paint for this subnetwork node to <code>outlineStrokePaint</code>.
     *
     * <p>This is a bound property.</p>
     *
     * @param outlineStrokePaint outline stroke paint for this subnetwork node
     */
    public final void setOutlineStrokePaint(final Paint outlineStrokePaint) {
        Paint oldOutlineStrokePaint = this.outlineStrokePaint;
        this.outlineStrokePaint = outlineStrokePaint;
        outline.setStrokePaint(outlineStrokePaint);
        firePropertyChange("outlineStrokePaint", oldOutlineStrokePaint, this.outlineStrokePaint);
    }

    /** @see PNode */
    protected void layoutChildren() {
        if (!initialChildLayoutComplete) {
            updateOutlineBoundsAndPath();
            initialChildLayoutComplete = true;
        }
        updateSynapseNodePositions();
    }

    /** @see PNode */
    public void addChild(final PNode child) {
        child.addPropertyChangeListener("fullBounds", this);
        super.addChild(child);
    }

    /** @see PNode */
    public PNode removeChild(final PNode child) {
        child.removePropertyChangeListener("fullBounds", this);
        return super.removeChild(child);
    }

    /** @see PropertyChangeListener */
    public void propertyChange(final PropertyChangeEvent event) {
        updateOutlineBoundsAndPath();
    }

    /**
     * Update synapse node positions.
     */
    private final void updateSynapseNodePositions() {
        for (Iterator i = getChildrenIterator(); i.hasNext(); ) {
            PNode node = (PNode) i.next();
            if (node instanceof NeuronNode) {
                NeuronNode neuronNode = (NeuronNode) node;
                neuronNode.updateSynapseNodePositions();
            }
        }
    }

    /**
     * Update outline bounds and path.
     */
    private void updateOutlineBoundsAndPath() {

        // one of the child nodes' full bounds changed
        PBounds bounds = new PBounds();
        for (Iterator i = getChildrenIterator(); i.hasNext(); ) {
            PNode child = (PNode) i.next();
            if ((!tab.equals(child)) && (!outline.equals(child))) {
                PBounds childBounds = child.getBounds();
                child.localToParent(childBounds);
                bounds.add(childBounds);
            }
        }

        // add (0.0d, 0.0d)
        bounds.add(OUTLINE_INSET_WIDTH, OUTLINE_INSET_HEIGHT);
        // add border
        bounds.setRect(bounds.getX() - OUTLINE_INSET_WIDTH,
                       bounds.getY() - OUTLINE_INSET_HEIGHT,
                       bounds.getWidth() + (2 * OUTLINE_INSET_WIDTH),
                       bounds.getHeight() + (2 * OUTLINE_INSET_HEIGHT) - TAB_HEIGHT);

        // set outline to new bounds
        // TODO:  only update rect if it needs updating
        outline.setBounds(bounds);
        outline.setPathToRectangle((float) bounds.getX(), (float) bounds.getY(),
                                   (float) bounds.getWidth(), (float) bounds.getHeight());
    }


    /**
     * Tab node.
     */
    private class TabNode
        extends PNode {

        /** Label. */
        private PText label;

        /** Background. */
        private PPath background;


        /**
         * Create a new tab node.
         */
        public TabNode() {
            super();

            setPickable(false);
            setChildrenPickable(false);

            label = new PText();
            label.offset(TAB_INSET_HEIGHT, TAB_INSET_WIDTH);

            double backgroundWidth = Math.max(label.getWidth() + (2 * TAB_INSET_WIDTH), DEFAULT_TAB_WIDTH);
            background = PPath.createRectangle(0.0f, 0.0f, (float) backgroundWidth, (float) TAB_HEIGHT);

            setBounds(0.0d, 0.0d, backgroundWidth, TAB_HEIGHT);

            addChild(background);
            addChild(label);
        }


        /**
         * Return the label for this tab node.
         * The label may be null.
         *
         * @return the label for this tab node
         */
        public final String getLabel() {
            return label.getText();
        }

        /**
         * Set the label for this tab node to <code>label</code>.
         *
         * @param label label for this tab node
         */
        public final void setLabel(final String label) {
            this.label.setText(label);
        }

        /**
         * Set the tab paint for this tab node to <code>tabPaint</code>.
         *
         * @param tabPaint tab paint for this tab node
         */
        public final void setTabPaint(final Paint tabPaint)
        {
            background.setPaint(tabPaint);
        }

        /**
         * Set the tab stroke for this tab node to <code>tabStroke</code>.
         *
         * @param tabStroke tab stroke for this tab node
         */
        public final void setTabStroke(final Stroke tabStroke)
        {
            background.setStroke(tabStroke);
        }

        /**
         * Set the tab stroke paint for this tab node to <code>tabStrokePaint</code>.
         *
         * @param tabStrokePaint tab stroke paint for this tab node
         */
        public final void setTabStrokePaint(final Paint tabStrokePaint)
        {
            background.setStrokePaint(tabStrokePaint);
        }
    }

    /**
     * Outline node.
     */
    private class OutlineNode
        extends PPath {

        /**
         * Outline node.
         */
        public OutlineNode() {
            super();

            setPickable(false);
            setChildrenPickable(false);

            offset(0.0d, TAB_HEIGHT);
            setBounds(0.0d, 0.0d, DEFAULT_OUTLINE_WIDTH, DEFAULT_OUTLINE_HEIGHT);
            setPathToRectangle(0.0f, 0.0f, (float) DEFAULT_OUTLINE_WIDTH, (float) DEFAULT_OUTLINE_HEIGHT);
        }


        /**
         * Set the outline stroke for this outline node to <code>outlineStroke</code>.
         *
         * @param outlineStroke outline stroke for this outline node
         */
        public final void setOutlineStroke(final Stroke outlineStroke)
        {
            setStroke(outlineStroke);
        }

        /**
         * Set the outline stroke paint for this outline node to <code>outlineStrokePaint</code>.
         *
         * @param outlineStrokePaint outline stroke paint for this outline node
         */
        public final void setOutlineStrokePaint(final Paint outlineStrokePaint)
        {
            setStrokePaint(outlineStrokePaint);
        }
    }
}