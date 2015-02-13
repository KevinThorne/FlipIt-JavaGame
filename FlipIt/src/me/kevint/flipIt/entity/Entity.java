package me.kevint.flipIt.entity;

import java.awt.Point;
import java.util.ArrayList;
import java.util.UUID;

import me.kevint.flipIt.FlipIt;
import me.kevint.flipIt.display.SurfaceUpdateListener;
import me.kevint.flipIt.entity.component.Component;
import me.kevint.flipIt.entity.component.ComponentPriorityEnum;
import me.kevint.flipIt.entity.component.GraphicsComponent;
import me.kevint.flipIt.entity.component.GraphicsComponent.AnimationType;
import me.kevint.flipIt.math.Rect;

public abstract class Entity
{
    private ArrayList<Component> componentList;
    private UUID entityUUID;
    private int renderLayer;
    
    private Point pos;
    protected Rect size;
    protected Rect collisionBounds;
    
    public Entity(int renderLayer, Component[] componentsToRegister)
    {
        this.renderLayer = renderLayer;
        this.componentList = new ArrayList<Component>();
        this.entityUUID = UUID.randomUUID();
        
        for(Component component : componentsToRegister)
            this.registerComponent(component);
        
        this.componentList = ComponentPriorityEnum.sortComponentList(componentList);
        for(Component component : componentList)
        {
            component.initialize();
        }
    }
    
    public UUID getUUID()
    {
        return this.entityUUID;
    }
    
    public Point getPosition() {
		return pos;
	}
	
	public void setPosition(Point newPos) {
		this.pos = newPos;
	}
	public Rect getSize() {
		return size;
	}
	public Rect getCollisionBounds() {
		return this.collisionBounds;
	}
    
    public int getRenderLayer()
    {
        return this.renderLayer;
    }
    
    public void registerSurfaceUpdateListener(SurfaceUpdateListener ear) {
    	if(!FlipIt.updateListeners.contains(ear)) {
    		FlipIt.updateListeners.add(ear);
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
    
    public void moveLeft() {
		setPosition(new Point(getPosition().x-1, getPosition().y));
	}
	public void moveRight() {
		setPosition(new Point(getPosition().x+1, getPosition().y));
	}
	public void moveVert(float upwardVelocity) {
		setPosition(new Point(getPosition().x, (int) (getPosition().y-upwardVelocity)));
	}
	public void slideHorz(float steps) {
		setPosition(new Point((int) (getPosition().x+steps), getPosition().y));
	}
    
}

