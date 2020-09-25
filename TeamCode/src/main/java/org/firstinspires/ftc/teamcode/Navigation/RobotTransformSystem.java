package org.firstinspires.ftc.teamcode.Navigation;

class RobotTransformSystem
{
    ////Current Robot Coords////
    private double robotX;
    public double getRobotX() { return robotX; }
    private double robotY;
    public double getRobotY() { return robotY; }
    private double robotHeading;
    public double getRobotHeading() { return robotHeading; }

    public RobotTransformSystem(double startX, double startY, double startHeading){
        SetRobotGlobalPose(startX, startY, startHeading);
    }

    public void SetRobotGlobalPose(double x, double y, double heading){
        robotX = x;
        robotY = y;
        robotHeading = heading;
    }

    public double GetDeltaAngleToObject(double rX, double rY, double rH, double oX, double oY){
        //Calculate the distance robot needs to turn to face object
        double deltaX = oX-rX;
        double deltaY = oY-rY;
        double headingFromRobotCenter = Math.toDegrees(Math.atan2(deltaX, deltaY)) + 90;//TODO: make sure 90 degrees is the right offset!
        return headingFromRobotCenter - getRobotHeading();
    }

    public double[] ConvertToGlobal(double x, double y, double heading){
        //Converts local (relative to robot) coords to global coords
        double offsetX = x + getRobotX();
        double offsetY = y + getRobotY();
        double offsetH = heading + getRobotHeading();
        double[] data = {offsetX, offsetY, offsetH};
        return data;
    }

    public double[] ConvertToLocal(double x, double y, double heading){
        //Converts global coords to local (relative to robot) coords
        double offsetX = x - getRobotX();
        double offsetY = y - getRobotY();
        double offsetH = heading - getRobotHeading();
        double[] data = {offsetX, offsetY, offsetH};
        return data;
    }
}
