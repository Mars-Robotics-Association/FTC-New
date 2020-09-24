package org.firstinspires.ftc.teamcode.Navigation;

class RobotCoordinateSystem
{
    ////Current Robot Coords////
    private double robotX;
    public double getRobotX() { return robotX; }
    private double robotY;
    public double getRobotY() { return robotY; }
    private double robotHeading;
    public double getRobotHeading() { return robotHeading; }

    public void SetRobotPose(double x, double y, double heading){
        robotX = x;
    }
}
