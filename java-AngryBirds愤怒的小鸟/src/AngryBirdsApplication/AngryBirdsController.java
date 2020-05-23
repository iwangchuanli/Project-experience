/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package AngryBirdsApplication;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jbox2d.callbacks.ContactImpulse;
import org.jbox2d.callbacks.ContactListener;
import org.jbox2d.collision.Manifold;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Fixture;
import org.jbox2d.dynamics.contacts.Contact;

/**
 *
 * @author Sumomoxiao
 */
public class AngryBirdsController extends MouseAdapter implements Runnable, MouseMotionListener, ContactListener {

    private AngryBirdsArea m_stage;
    public AngryBirdsPanel m_view;
    private final AngryBirdsDraw drawer;
   // private final MusicController music;
    Thread gamethread;
    boolean stop = true;

    AngryBirdsController(AngryBirdsArea m, AngryBirdsPanel v){//, MusicController mc) {
        this.m_stage = m;
        m_view = v;
        drawer = v.getSDDraw();
        v.setStageController(this);
        m.initStage();
        //music = mc;
        drawer.setStage(m);
        this.addListener();

    }

    @Override
    public void run() {
        while (true) {
            try {
                while (!stop) {
                    m_stage.update();
                    drawer.drawStage();
                    try {
                        Thread.sleep(5);
                    } catch (InterruptedException ex) {
                    }
                }
                drawer.drawStage();
                Thread.sleep(12);
            } catch (InterruptedException ex) {
                Logger.getLogger(AngryBirdsController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void start() {
        if (gamethread == null) {
            stop = false;
            gamethread = new Thread(this);
            gamethread.start();
        }
    }

    public void pause() {
        if (isPainting()) {
            stop = true;
        }
    }

    public void resume() {
        if (!isPainting()) {
            stop = false;
        }
    }

    public boolean isPainting() {
        return m_view.isPainting();
    }

    /*
     * synchronized public void setRunthread(boolean runthread) {
     * if(this.stop!=runthread){ this.stop = runthread; }
     *
     *
     * }
     *
     */
    public void addListener() {
        m_view.addMouseListener(new MouseAdapter() {

            @Override
            public void mousePressed(MouseEvent e) {

                if (m_stage != null) {
                    Vec2 pos = new Vec2(e.getX(), e.getY());
                    if (e.getButton() == MouseEvent.BUTTON1) {
                        drawer.getScreenToWorldToOut(pos, pos);
                        m_stage.queueMouseDown(pos);

                    }
                }
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                if (m_stage != null) {
                    Vec2 pos = new Vec2(e.getX(), e.getY());
                    drawer.getScreenToWorldToOut(pos, pos);
                    m_stage.queueMouseUp(pos);
                }
            }
        });

        m_view.addMouseMotionListener(new MouseMotionListener() {

            final Vec2 posDif = new Vec2();
            final Vec2 pos = new Vec2();
            final Vec2 pos2 = new Vec2();

            public void mouseDragged(MouseEvent e) {
                pos.set(e.getX(), e.getY());


                if (m_stage != null) {
                    drawer.getScreenToWorldToOut(pos, pos);
                    m_stage.queueMouseMove(pos);
                }
            }

            @Override
            public void mouseMoved(MouseEvent e) {
                pos2.set(e.getX(), e.getY());
                if (m_stage != null) {
                    drawer.getScreenToWorldToOut(pos2, pos2);
                    m_stage.queueMouseMove(pos2);
                }
            }
        });
    }

    @Override
    public synchronized void beginContact(Contact contact) {
               
          //   drawer.drawContact();
          //   System.out.print("listener thread !!!\n");
    }

    @Override
    public void endContact(Contact contact) {
    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {
        // throw new UnsupportedOperationException("Not supported yet.");
    }

    Fixture fix ;
    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {

       if (contact.m_fixtureA.m_filter.groupIndex == -1 || contact.m_fixtureB.m_filter.groupIndex == -1) {
                      fix = contact.m_fixtureA.m_filter.groupIndex == -1?contact.m_fixtureA:contact.m_fixtureB;
                      
            for (int i = 0; i < contact.getManifold().pointCount; i++) {

                if (impulse.normalImpulses[i] > 0.8) {
                    System.out.print("pushing point \n");
                    drawer.pushContactPoint(fix.m_body.getPosition());
                    /*try {
                        //music.birdScream();
                    } catch (IOException ex) {                    }*/
                }
            }
        }
        if (contact.m_fixtureA.m_filter.groupIndex == 1 || contact.m_fixtureB.m_filter.groupIndex == 1) {
                // System.out.print(" impulse point count  : "+impulse.normalImpulses.length+"\n");
            for (int i = 0; i < contact.getManifold().pointCount; i++) {
                 //System.out.print("wood impulse : "+impulse.normalImpulses[i]+"\n");
                if (impulse.normalImpulses[i] > 3.1f) {
                    //System.out.print("wood impulse : "+impulse.normalImpulses[i]+"\n");
                    /*try {
                        //music.woodCollision();
                    } catch (IOException ex) {
                    }*/
                    return;
                }
            }
        }

    }
}
