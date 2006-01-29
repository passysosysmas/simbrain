/*
 * Part of Simbrain--a java-based neural network kit
 * Copyright (C) 2005 Jeff Yoshimi <www.jeffyoshimi.net>
 *
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA  02111-1307, USA.
 */
package org.simbrain.network.dialog.network.layout;

import javax.swing.JComboBox;
import javax.swing.JTextField;

import org.simbrain.util.LabelledItemPanel;
import org.simnet.layouts.*;


/**
 * <b>LayoutPanel</b> allows the user to define the layout of a network.
 */
public class GridLayoutPanel extends AbstractLayoutPanel  {

    /** Spacing field. */
    private JTextField tfNumberOfColumns = new JTextField("10");

    /** Spacing field. */
    private JTextField tfSpacing = new JTextField("40");

    /**
     * Default constructor.
     */
    public GridLayoutPanel() {
        this.addItem("Number of columns", tfNumberOfColumns);
        this.addItem("Spacing between neurons", tfSpacing);
    }

    /** @see AbstractLayoutPanel. */
    public Layout getNeuronLayout() {
        GridLayout layout = new GridLayout(10, 1);
        return layout;
    }

}