package org.firstinspires.ftc.teamcode.Tests;

import android.opengl.Matrix;

import org.firstinspires.ftc.robotcore.external.matrices.ColumnMajorMatrixF;
import org.firstinspires.ftc.robotcore.external.matrices.GeneralMatrixF;
import org.firstinspires.ftc.robotcore.external.matrices.MatrixF;
import org.firstinspires.ftc.robotcore.external.matrices.OpenGLMatrix;

/**
 * Created by Ethan Pereira on 3/7/2018.
 */

public class LeftRightVelMatrix
{
    public double vl, vr;

    //TODO: PUT THIS IN CORRECTLY
    private double radius, length;

    private double vel, omega;

    private GeneralMatrixF inputMat;
    private GeneralMatrixF outputMat;
    private float[] constants = {
            0.5f,0.5f,0.5f,-0.5f
    };
    private GeneralMatrixF constMat = new GeneralMatrixF(2,2,constants);

    public LeftRightVelMatrix(double vel, double omega)
    {
        this.vel = vel;
        this.omega = omega;
        float[] input = {
                (float)this.vel*2,
                (float)(this.omega*length)
        };
        inputMat = new GeneralMatrixF(1,2,input);
        outputMat = new GeneralMatrixF(1,2);
        inputMat.multiply((float)(1/(radius)));
    }

    public double getVl()
    {
        return (double)outputMat.get(0,1);
    }

    public double getVr()
    {
        return (double)outputMat.get(1,1);
    }


    public void update()
    {
        outputMat = (GeneralMatrixF)inputMat.multiplied(constMat);
    }
}

