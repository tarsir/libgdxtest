package com.example.libgdxtestgame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

/**
 * Created with IntelliJ IDEA.
 * User: stephen
 * Date: 7/5/13
 * Time: 1:50 AM
 * To change this template use File | Settings | File Templates.
 */

interface ICharacter {
    void move(int xBound, int yBound);
}

public class PCharacter implements ICharacter{
    String name, spriteID;
    Vector2 currentPos, goalPos, pathToGoal, velocity;
    double angle;

    private Vector2 scaled;

    public PCharacter(String name_, String spriteID_) {
        name = name_;
        spriteID = spriteID_;
        currentPos = new Vector2(0,0);
        goalPos = new Vector2();
        pathToGoal = new Vector2();
        velocity = new Vector2();
        scaled = new Vector2();
    }

    public void move(int xBound, int yBound) {
        boolean isTouched = Gdx.input.isTouched();
        if (isTouched) {
            //find goal info
            goalPos.set(Gdx.input.getX(), LibGdxGame.scrnHeight - Gdx.input.getY());
            scaleVec(goalPos);
            //scaleVec(currentPos);
            System.out.println("goalPos=" + goalPos.toString());
            pathToGoal = goalPos.cpy();
            pathToGoal = pathToGoal.sub(currentPos);
            scaleVec(pathToGoal);
            angle = Math.toRadians(pathToGoal.angle());
            velocity.set((float)Math.cos(angle), (float)Math.sin(angle));
            velocity.nor();
            scaleVec(velocity);
            System.out.println("currentPos=" + currentPos.toString() + "\tgoalPos=" + goalPos.toString() + "\tangle=" + angle);
            System.out.println("velocity="+ velocity.toString());
            /*
            currentPos.set(Gdx.input.getX(), LibGdxGame.scrnHeight - Gdx.input.getY());
            System.out.println("currentPos=" + currentPos.toString());
            //scale
            currentPos.x = currentPos.x / (1* LibGdxGame.scaleW);
            currentPos.y = currentPos.y / (1* LibGdxGame.scaleH);
            */

        }
        currentPos.add(velocity);

        //int xBound = (int) (( - 16 * LibGdxGame.scaleW) / LibGdxGame.scaleW);
        //int yBound = (int) ((LibGdxGame.scrnHeight - 16 * LibGdxGame.scaleH) / LibGdxGame.scaleH);
        if (isTouched) System.out.println("Screen bounds are: 0," + xBound + " and 0," + yBound);
        if (currentPos.x < 0) {
            currentPos.x = 0;
        }
        if (currentPos.x > xBound) {
            currentPos.x = xBound;
            //System.out.println("VIOLATION");
        }
        if (currentPos.y < 0) {
            currentPos.y = 0;
        }
        if (currentPos.y > yBound) {
            currentPos.y = yBound;
            //System.out.println("VIOLATION");
        }
        //System.out.println("x=" + currentPos.x + "\ty=" + currentPos.y);

        if (currentPos.dst(goalPos) < 5f) {
            velocity.set(0f,0f);
            //System.out.println("distance = " + currentPos.dst(goalPos));
        }
    }

    public static String fileExt(String spriteIdent) {
        return "sprites/" + spriteIdent;
    }

    void scaleVec(Vector2 inpV) {
        inpV.x = inpV.x / LibGdxGame.scaleW;
        inpV.y = inpV.y / LibGdxGame.scaleH;
    }


}

