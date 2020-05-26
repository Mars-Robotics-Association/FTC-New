package org.firstinspires.ftc.teamcode.Control;

public class ProtobotUpdater //TODO add superclass for these
{
    // REFS
    private ProtobotDriveBaseFuncs DriveBaseFuncs;
    private ProtobotArmFuncs AttachmentFuncs;

    // VARS

    public ProtobotUpdater(ProtobotDriveBaseFuncs setDriveBaseFuncs, ProtobotArmFuncs setAttachmentFuncs) {
        DriveBaseFuncs = setDriveBaseFuncs;
        AttachmentFuncs = setAttachmentFuncs;
    }

    public void start(){
        DriveBaseFuncs.start();
        AttachmentFuncs.start();
    }

    public void init(){
        DriveBaseFuncs.init();
        AttachmentFuncs.init();
    }

    public void update(){
        DriveBaseFuncs.update();
        AttachmentFuncs.update();
    }
}
