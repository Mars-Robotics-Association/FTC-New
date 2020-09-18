package org.firstinspires.ftc.teamcode.Navigation.Roadrunner;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.acmerobotics.roadrunner.trajectory.Trajectory;
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

    public void MoveSpline(double x, double y, double tangent){
        Trajectory traj = drive.trajectoryBuilder(new Pose2d())
                .splineTo(new Vector2d(x, y), tangent)
                .build();

        drive.followTrajectory(traj);
    }

    public void Turn(double angle){drive.turn(Math.toRadians(angle));}

    public void ResetPostion(double x, double y, double angle){}//TODO: figure out how to reset robot pos

}
