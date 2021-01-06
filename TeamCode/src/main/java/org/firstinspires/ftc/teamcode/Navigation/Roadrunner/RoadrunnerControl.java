package org.firstinspires.ftc.teamcode.Navigation.Roadrunner;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.acmerobotics.roadrunner.trajectory.Trajectory;
import com.acmerobotics.roadrunner.trajectory.constraints.TrajectoryConstraints;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.teamcode.Navigation.Roadrunner.drive.SampleMecanumDrive;

public class RoadrunnerControl
{
    private OpMode opMode;
    private SampleMecanumDrive drive;

    public RoadrunnerControl(OpMode setOpMode){
        opMode = setOpMode;
    }

    public void Init(){
        drive = new SampleMecanumDrive(opMode.hardwareMap);
    }

    public void MoveSpline(double x, double y, double tangent){ //moves using fancy splines
        Trajectory traj = drive.trajectoryBuilder(new Pose2d())
                .splineTo(new Vector2d(x, y), tangent)
                .build();

        drive.followTrajectory(traj);
    }

    public void MoveLine(double x, double y, double heading){ //moves linearly along a line .lineToLinearHeading(new Pose2d(x,y,heading))
        Trajectory traj = drive.trajectoryBuilder(new Pose2d())
                .lineToConstantHeading(new Vector2d(x,y))
                .build();

        drive.followTrajectory(traj);
    }

    public void MoveSplineConstantHeading(double x, double y, double endTangent){
        Trajectory traj = drive.trajectoryBuilder(new Pose2d())
                .splineToConstantHeading(new Vector2d(x,y), endTangent)
                .build();

        drive.followTrajectory(traj);
    }

    public void MoveRaw(Pose2d move){
        drive.setDrivePower(move);
    }
    public void TurnRaw(double speed){
        drive.setMotorPowers(speed, speed, -speed, -speed);
    }

    public void Turn(double angle){drive.turn(Math.toRadians(angle));}
    public void TurnTo(double angle){drive.turn(Math.toRadians(angle) - GetCurrentPose().getHeading());}

    public void SetPose(double x, double y, double heading){drive.setPoseEstimate(new Pose2d(x,y, heading));} //Sets robot pose
    public Pose2d GetCurrentPose(){return drive.getPoseEstimate();}

}
