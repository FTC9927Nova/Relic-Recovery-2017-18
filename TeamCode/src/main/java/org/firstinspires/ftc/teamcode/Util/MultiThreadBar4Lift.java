package org.firstinspires.ftc.teamcode.Util;

import android.util.Log;

import org.firstinspires.ftc.teamcode.Subsystems.Robot;

/**
 * Created by Ethan Pereira on 2/21/2018.
 */

public class MultiThreadBar4Lift extends Thread{

    Robot robot = new Robot();
    public void run(){
        try{
//            while(robot.bar4.isHit())
//                robot.bar4.setPower(0.7);
//            robot.bar4.setPower(0);
//            robot.bar4.setMoveAngle(103.5);
        while (!robot.bar4.isHit()){
            robot.bar4.setPower(0.3);
        }
        robot.bar4.setPower(0);
        while (!robot.bar4.isLowerHit()){
            robot.bar4.setPower(-0.2);
        }
        robot.bar4.setPower(0);
        }catch (Exception e){
            Log.i("Exception", String.valueOf(e));
        }
    }

}
