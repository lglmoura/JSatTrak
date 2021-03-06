/**
 * =====================================================================
 * Copyright (C) 2009 Shawn E. Gano
 * 
 * This file is part of JSatTrak.
 * 
 * JSatTrak is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * JSatTrak is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 * 
 * You should have received a copy of the GNU Lesser General Public License
 * along with JSatTrak.  If not, see <http://www.gnu.org/licenses/>.
 * =====================================================================
 */

package jsattrak.utilities;

import javax.swing.Icon;
import javax.swing.tree.DefaultMutableTreeNode;

public class IconTreeNode extends DefaultMutableTreeNode
{

    protected Icon icon;
    protected String iconName;

    public IconTreeNode()
    {
        this(null);
    }

    public IconTreeNode(Object userObject)
    {
        this(userObject, true, null);
    }

    public IconTreeNode(Object userObject, boolean allowsChildren, Icon icon)
    {
        super(userObject, allowsChildren);
        this.icon = icon;
    }

    public void setIcon(Icon icon)
    {
        this.icon = icon;
    }

    public Icon getIcon()
    {
        return icon;
    }

    public String getIconName()
    {
        if (iconName != null)
        {
            return iconName;
        }
        else
        {
            String str = userObject.toString();
            int index = str.lastIndexOf(".");
            if (index != -1)
            {
                return str.substring(++index);
            }
            else
            {
                return null;
            }
        }
    }

    public void setIconName(String name)
    {
        iconName = name;
    }
}
