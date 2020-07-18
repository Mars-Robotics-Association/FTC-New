package org.firstinspires.ftc.teamcode.Odometry;

public class DemoBotOdometry
{
    //Dependencies

    //Variables
    private double X;
    private double Y;
    private double TargetDist;

    //Initializer
    public DemoBotOdometry(){}

    ////STARTUP////

    ////CALLABLE METHODS////
    public void Reset(){
        //Sets x and y to 0
        X = 0;
        Y = 0;
    }
    public void SetTargetDist(double dist) {
        //Sets the target X and Y for odometry
        TargetDist = dist;
    }
    public boolean IsAtTargetDist(double threshold){
        //Determines whether the robot is close enough to the target distance
        if(Math.abs(TargetDist - CalculateDistance()) < threshold){return true;}
        else {return false;}
    }

    ////PRIVATE METHODS////
    private double CalculateDistance(){
        //Calculates distance robot has gone using pythagorean theorem
        return Math.sqrt((X*X) + (Y*Y));
    }

}
