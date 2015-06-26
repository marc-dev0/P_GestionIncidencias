package Utilitarios;

import javax.swing.*;

import java.awt.*;
import java.util.ArrayList;

public class ToolTipWrapper implements ToolTipProvider {
    final Object value;
    final String toolTip;

    public ToolTipWrapper(Object value, String toolTip) {
        this.value = value;
        this.toolTip = toolTip;
    }

    @Override
    public String getToolTip() {
        return toolTip; 
    }

    @Override
    public String toString() {
        return value.toString();
    }

    public class ToolTipRenderer extends DefaultListCellRenderer {

        @Override
        public Component getListCellRendererComponent(JList list, Object value,
                int index, boolean isSelected, boolean cellHasFocus) {
             JComponent component = (JComponent) super.getListCellRendererComponent(list, value, index, isSelected,
                    cellHasFocus);
             String tip = null;
             if (value instanceof ToolTipProvider) {
                 ToolTipProvider ttp = (ToolTipProvider) value;
                 tip = ttp.getToolTip();
             }
             list.setToolTipText(tip);
             return component;
        }
    }
}


