/*
 * Part of Simbrain--a java-based neural network kit
 * Copyright (C) 2005,2007 The Authors.  See http://www.simbrain.net/credits
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
package org.simbrain.network.neurons;

import org.simbrain.network.interfaces.Neuron;


/**
 * <b>CustomNeuron</b>.
 */
public class CustomNeuron extends Neuron {
    /**
     * Default constructor needed for external calls which
     * create neurons then  set their parameters.
     */
    public CustomNeuron() {
        super();
    }

    /**
     * TODO: Not really true...
     * @return time type.
     */
    public int getTimeType() {
        return org.simbrain.network.interfaces.RootNetwork.DISCRETE;
    }

    /**
     * This constructor is used when creating a neuron of
     * one type from another neuron of another type Only values
     * common to different types of neuron are copied.
     * @param n Neuron to make the type
     */
    public CustomNeuron(final Neuron n) {
        super(n);
    }

    /**
     * Returns a duplicate ClampedNeuron (used, e.g., in copy/paste).
     * @return Duplicated neuron
     */
    public CustomNeuron duplicate() {
        CustomNeuron cn = new CustomNeuron();
        cn = (CustomNeuron) super.duplicate(cn);

        return cn;
    }

    /**
     * Update neuron.
     */
    public void update() {
        setBuffer(activation);
    }

    /**
     * @return Name of neuron type.
     */
    public static String getName() {
        return "Custom";
    }
}