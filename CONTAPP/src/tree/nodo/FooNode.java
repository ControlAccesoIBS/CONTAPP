package tree.nodo;

import java.awt.Container;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.zkoss.zk.ui.Component;

public class FooNode {
	private FooNode _parent;
	private List<FooNode> _children;
	private int _index;
	private String _label = "";
	public FooNode (FooNode parent, int index, String label) {
		_parent = parent;
		_index = index;
		_label = label;
	}
	public void setParent (FooNode parent) {
		_parent = parent;
	}
	public FooNode getParent () {
		return _parent;
	}

	public void appendChildSecond (int i, FooNode child) {
		if (_children == null)
			_children = new ArrayList();
		_children.add(i, child);
	}
	public void appendChild ( FooNode child) {
		if (_children == null)
			_children = new ArrayList();
		_children.add(child);
	}
	public List<FooNode> getChildren () {
		if (_children == null)
			return Collections.EMPTY_LIST;
		return _children;
	}
	public void setIndex (int index) {
		_index = index;
	}
	public int getIndex () {
		return _index;
	}
	public String getLabel () {
		return _label;
	}
	public String toString () {
		return getLabel();
	}
	
	 // Devuelve el índice del nodo hijo en el nodo padre]
    public int getIndexOfChild(Object parent, Object child) {
      if (!(parent instanceof Container))
        return -1;
      Container c = (Container) parent;
      Component[] children = (Component[]) c.getComponents();
      if (children == null)
        return -1;
      for (int i = 0; i < children.length; i++) {
        if (children[i] == child)
          return i;
      }
      return -1;
    }
    
    //  Devuelve el hijo especificado de un nodo padre.
    public Object getChild(Object parent, int index) {
      if (parent instanceof Container) {
        Container c = (Container) parent;
        return c.getComponent(index);
      }
      return null;
    }
}
