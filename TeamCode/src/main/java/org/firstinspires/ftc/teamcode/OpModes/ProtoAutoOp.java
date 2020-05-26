package org.firstinspires.ftc.teamcode.OpModes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.teamcode.Control.ProtobotArmFuncs;
import org.firstinspires.ftc.teamcode.Control.ProtobotDriveBaseFuncs;
import org.firstinspires.ftc.teamcode.Control.ProtobotUpdater;

public class ProtoAutoOp extends OpMode
{
    //REFS
    private ProtobotUpdater BotLoop;
    private ProtobotDriveBaseFuncs DriveBaseFuncs;
    private ProtobotArmFuncs AttachmentFuncs;

    //VARS


    @Override
    public void init() {
        DriveBaseFuncs = new ProtobotDriveBaseFuncs();
        AttachmentFuncs = new ProtobotArmFuncs();

        BotLoop = new ProtobotUpdater(DriveBaseFuncs, AttachmentFuncs);
        BotLoop.init();
    }

    @Override
    public void start(){
        BotLoop.start();
    }

    @Override
    public void loop() {
        BotLoop.update();
    }

}
