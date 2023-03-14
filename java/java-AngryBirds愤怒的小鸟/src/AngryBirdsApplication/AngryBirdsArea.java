/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package AngryBirdsApplication;

import java.util.ArrayList;
import java.util.LinkedList;

import org.jbox2d.callbacks.QueryCallback;
import org.jbox2d.collision.AABB;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.*;
import org.jbox2d.dynamics.joints.*;

/**
 *
 * @author Sumomoxiao
 */
enum QueueItemType {

    MouseDown, MouseMove, MouseUp, KeyPressed, KeyReleased
}

class QueueItem {

    public QueueItemType type;
    public Vec2 p;
    public char c;
    public int code;

    public QueueItem(QueueItemType t, Vec2 pt) {
        type = t;
        p = pt;
    }
    
}

class FixtureQueryCallback implements QueryCallback {

    public final Vec2 point;
    public Fixture fixture;

    public FixtureQueryCallback() {
        point = new Vec2();
        fixture = null;
    }

    /**
     * @see
     * org.jbox2d.callbacks.QueryCallback#reportFixture(org.jbox2d.dynamics.Fixture)
     */
    public boolean reportFixture(Fixture argFixture) {
        Body body = argFixture.getBody();
        if (body.getType() == BodyType.DYNAMIC) {
            boolean inside = argFixture.testPoint(point);
            if (inside) {
                fixture = argFixture;

                return false;
            }
        }

        return true;
    }
}

public abstract class AngryBirdsArea {

    private MouseJoint mouseJoint;
    private Vec2 mouseWorld = new Vec2();
    public final World sworld;
    private final Vec2 gravity;
    public Vec2 slingAnchor;
    public ArrayList<Body> birdlist;
    public ArrayList<Body> oblist;
    public ArrayList<Body> piglist, sling;
    public WeldJoint attach;
    public WeldJointDef attachDef;
    public Body ground;
    public float scale = 1 / 64f;
    float timeStep = 1.0f / 60.0f;
    int velocityIterations = 6;
    int positionIterations = 2;
    public int birdbullets;
    private final LinkedList<QueueItem> inputQueue;

    public AngryBirdsArea() {
        gravity = new Vec2(0, -10f);
        inputQueue = new LinkedList<QueueItem>();
        sworld = new World(gravity, true);
        birdlist = new ArrayList<Body>();
        oblist = new ArrayList<Body>();
        piglist = new ArrayList<Body>();
        sling = new ArrayList<Body>();
        slingAnchor = new Vec2();

    }

    abstract public void initStage();
    long endtime = 0;
    long duration = 0;  //duration of release the bird;
    long descountdown = 0;
    public void step() {
        
        sworld.step(timeStep, velocityIterations, positionIterations);
        
        if (mouseJoint == null && attach == null) {
            
            endtime = System.currentTimeMillis();
            duration = (endtime - releasetime) / 1000;
            
        }


        if (duration > 3 && attach == null) {
            
            if(birdbullets<=birdlist.size()) {
            birdlist.get(birdbullets).setTransform(slingAnchor, 0);
   
            attachDef.bodyB = birdlist.get(birdbullets);
            
            attach = (WeldJoint) this.getWorld().createJoint(attachDef);
            duration = 0;
            
            }

        }

    }

    public ArrayList<Body> getBirds() {
        return birdlist;
    }

    public ArrayList<Body> getObstacles() {
        return oblist;
    }

    public ArrayList<Body> getPigs() {
        return piglist;
    }

    public World getWorld() {
        return sworld;
    }

    public Body getGround() {
        return ground;
    }

    public void setGravity(Vec2 gra) {
        sworld.setGravity(gra);
    }

    public boolean isMouseJointNull() {
        if(mouseJoint == null) 
            return true;
        return false;
    }
    public void update() {
        if (!inputQueue.isEmpty()) {
            synchronized (inputQueue) {
                while (!inputQueue.isEmpty()) {
                    QueueItem i = inputQueue.pop();
                    switch (i.type) {
                        case MouseDown:
                            mouseDown(i.p);
                            break;
                        case MouseMove:
                            mouseMove(i.p);
                            break;
                        case MouseUp:
                            mouseUp(i.p);
                            break;
                    }
                }
            }
        }
        step();
    }
    /**
     * Called for mouse-up
     *
     * @param p
     */
    long releasetime = 0;

    public void mouseUp(Vec2 p) {
        
        float length = 0;
        Vec2 pos = new Vec2();
        pos = p.sub(slingAnchor);
        length = pos.length();

        if (length > 3) {


            pos.x /= length / 3;
            pos.y /= length / 3;

            p = pos;

        }

        if (mouseJoint != null) {
            mouseJoint.m_bodyB.setFixedRotation(false);
            mouseJoint.m_bodyB.setLinearVelocity(pos.negate().mul(7.5f));  //release and shoot!
            sworld.destroyJoint(mouseJoint);
            mouseJoint = null;
            if(birdbullets<birdlist.size()-1)
            birdbullets++;
            releasetime = System.currentTimeMillis();

        }

        /*
         * if (bombSpawning) { completeBombSpawn(p); }
         *
         */
    }
    private final AABB queryAABB = new AABB();
    private final FixtureQueryCallback callback = new FixtureQueryCallback();

    /**
     * Called for mouse-down
     *
     * @param p
     */
    public void mouseDown(Vec2 p) {
        mouseWorld.set(p);

        if (mouseJoint != null) {
            return;
        }

        queryAABB.lowerBound.set(p.x - .001f, p.y - .001f);
        queryAABB.upperBound.set(p.x + .001f, p.y + .001f);
        callback.point.set(p);
        callback.fixture = null;
        sworld.queryAABB(callback, queryAABB);

        if (callback.fixture != null && callback.fixture.m_filter.groupIndex == -1) {

            if (attach != null) {
                sworld.destroyJoint(attach);
                attach = null;
            }

            Body body = callback.fixture.getBody();
            MouseJointDef def = new MouseJointDef();
            def.bodyA = ground;
            def.bodyB = body;
            def.target.set(p);
            def.maxForce = 1000f * body.getMass();
            body.setFixedRotation(true);//NOTE!!!!!!!!!!!!!!!!!
            mouseJoint = (MouseJoint) sworld.createJoint(def);
            body.setAwake(true);
        }
    }
    /**
     * Process MouseEvent.
     *
     * @param p,length
     */
    Vec2 length;

    public void mouseMove(Vec2 p) {
        mouseWorld.set(p);
        Vec2 force = p.sub(slingAnchor);
        float ol = force.length();

        if (force.length() > 3f) {
            force.x /= ol / 3;
            force.y /= ol / 3;
            force.addLocal(slingAnchor);
            p = force;
        }
        if (mouseJoint != null) {
            mouseJoint.setTarget(p);
        }
    }

    public void queueMouseUp(Vec2 p) {
        synchronized (inputQueue) {
            inputQueue.addLast(new QueueItem(QueueItemType.MouseUp, p));
        }
    }

    public void queueMouseDown(Vec2 p) {
        synchronized (inputQueue) {
            inputQueue.addLast(new QueueItem(QueueItemType.MouseDown, p));
        }
    }

    public void queueMouseMove(Vec2 p) {
        synchronized (inputQueue) {
            inputQueue.addLast(new QueueItem(QueueItemType.MouseMove, p));
        }
    }
}
