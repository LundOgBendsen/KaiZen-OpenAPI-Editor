/*******************************************************************************
 * Copyright (c) 2016 ModelSolv, Inc. and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    ModelSolv, Inc. - initial API and implementation and/or initial documentation
 *******************************************************************************/
package com.reprezen.swagedit.editor.outline;

import org.eclipse.jface.viewers.StyledCellLabelProvider;
import org.eclipse.jface.viewers.StyledString;
import org.eclipse.jface.viewers.StyledString.Styler;
import org.eclipse.jface.viewers.ViewerCell;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.graphics.TextStyle;
import org.eclipse.swt.widgets.Display;

import com.google.common.collect.Iterables;
import com.reprezen.swagedit.Activator;
import com.reprezen.swagedit.Activator.Icons;
import com.reprezen.swagedit.model.AbstractNode;
import com.reprezen.swagedit.schema.TypeDefinition;

public class OutlineStyledLabelProvider extends StyledCellLabelProvider {

    private final Styler TAG_STYLER;
    private final Styler TEXT_STYLER;

    private final Activator activator = Activator.getDefault();

    public OutlineStyledLabelProvider() {
        TAG_STYLER = new Styler() {
            public void applyStyles(TextStyle textStyle) {
                textStyle.foreground = getColor(new RGB(149, 125, 71));
            }
        };

        TEXT_STYLER = new Styler() {
            public void applyStyles(TextStyle textStyle) {
                // do not apply any extra styles
            }
        };
    }

    public Styler getTagStyler() {
        return TAG_STYLER;
    }

    public Styler getTextStyler() {
        return TEXT_STYLER;
    }

    @Override
    public void update(ViewerCell cell) {
        Object element = cell.getElement();

        if (element instanceof AbstractNode) {
            StyledString styledString = getStyledString((AbstractNode) element);

            cell.setText(styledString.toString());
            cell.setStyleRanges(styledString.getStyleRanges());
            cell.setImage(getImage(getIcon((AbstractNode) element)));
        }
    }

    protected StyledString getStyledString(AbstractNode element) {
        StyledString styledString = new StyledString(element.getText(), getTextStyler());

        if (element.getParent() != null && (element.isObject() || element.isArray())) {

            TypeDefinition definition = element.getType();

            String label = null;
            if (definition.asJson().has("title")) {
                label = definition.asJson().get("title").asText();
            } else if (definition.getContainingProperty() != null) {
                label = definition.getContainingProperty();
            }
            
            if (label != null) {
                styledString.append(" ");
                styledString.append(label, getTagStyler());
            }

        }

        return styledString;
    }

    protected Icons getIcon(AbstractNode element) {
        AbstractNode parent = element.getParent();

        if (parent == null) {
            return Icons.outline_document;
        }

        if (parent.isObject()) {
            if (Iterables.isEmpty(element.elements())) {
                return Icons.outline_mapping_scalar;
            } else {
                return Icons.outline_mapping;
            }
        } else if (parent.isArray()) {
            if (Iterables.isEmpty(element.elements())) {
                return Icons.outline_scalar;
            } else {
                return Icons.outline_sequence;
            }
        } else {
            return Icons.outline_scalar;
        }
    }

    protected Color getColor(RGB rgb) {
        return new Color(Display.getCurrent(), rgb);
    }

    protected Image getImage(Icons icon) {
        return activator.getImage(icon);
    }

}
