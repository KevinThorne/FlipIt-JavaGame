package me.kevint.flipIt.entity;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.UUID;

import me.kevint.flipIt.FlipIt;
import me.kevint.flipIt.display.SurfaceUpdateListener;
import me.kevint.flipIt.entity.component.Component;
import me.kevint.flipIt.entity.component.ComponentPriorityEnum;

public abstract class Entity
{
    private ArrayList<Component> componentList;
    private UUID entityUUID;
    
    protected Rectangle size;
    protected Rectangle bounds = new Rectangle();
    
    public Entity(Component[] componentsToRegister, Point pos)
    {
        this.componentList = new ArrayList<Component>();
        this.entityUUID = UUID.randomUUID();
        
        for(Component component : componentsToRegister)
            this.registerComponent(component);
        
        this.componentList = ComponentPriorityEnum.sortComponentList(componentList);
        for(Component component : componentList)
        {
            component.initialize();
        }
        setPosition(pos);
    }
    
    public UUID getUUID()
    {
        return this.entityUUID;
    }
    
    public Point getPosition() {
		return bounds.getLocation();
	}
	
	public void setPosition(Point newPos) {
		this.bounds.setLocation(newPos);
	}
	public Dimension getSize() {
		return this.bounds.getSize();
	}
	public Rectangle getCollisionBounds() {
		return this.bounds;
	}
    
    public void registerSurfaceUpdateListener(SurfaceUpdateListener ear) {
    	if(!FlipIt.surfaceUpdateListeners.contains(ear)) {
    		FlipIt.surfaceUpdateListeners.add(ear);
    	}
    }
    
    public ArrayList<Component> getComponentList()
    {
        return this.componentList;
    }
    
    private void registerComponent(Component componentToReg)
    {
        Class<? extends Component> componentClass = componentToReg.getClass();
        for(Component component : componentList)
        {
            if(component.getClass() == componentClass)
            {
                return;
            }
        }
        
        componentList.add(componentToReg);
        componentToReg.setParentEntity(this);
        return;
    }
    
    public boolean hasComponentType(Class<? extends Component> componentClass)
    {
        for(Component component : componentList)
        {
            if(component.getClass() == componentClass)
            {
                return true;
            }
        }
        return false;
    }
    
    public <T extends Component> T getComponentByType(Class<T> componentClass)
    {
        for(Component component : componentList)
        {
            if(component.getClass() == componentClass)
            {
                return componentClass.cast(component);
            }
        }
        return null;
    }
    
	public void move(Point deltaPos) {
		setPosition(deltaPos);
	}
	public void bump(Point deltaPos) {
		setPosition(deltaPos);
	}
    
}

