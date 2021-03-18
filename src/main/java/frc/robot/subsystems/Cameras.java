package frc.robot.subsystems;

import edu.wpi.cscore.UsbCamera;
import edu.wpi.cscore.VideoSink;
import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Cameras extends SubsystemBase {
    
    public UsbCamera camera_front, camera_wheel;
    public VideoSink server;

    public Cameras() {
        camera_front = CameraServer.getInstance().startAutomaticCapture(0);
        camera_wheel = CameraServer.getInstance().startAutomaticCapture(1);
        server = CameraServer.getInstance().getServer();
        SmartDashboard.putString("Camera", "Front");
    }

    public void setCamera(int i) {
        server.setSource((i == 0) ? camera_front : camera_wheel);
        SmartDashboard.putString("Camera", (i == 0) ? "Front" : "Wheel");
    }
}
