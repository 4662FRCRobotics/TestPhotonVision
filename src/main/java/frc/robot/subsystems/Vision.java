// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import java.util.List;

import org.photonvision.PhotonCamera;
import org.photonvision.common.hardware.VisionLEDMode;
import org.photonvision.targeting.PhotonTrackedTarget;

public class Vision extends SubsystemBase {
  /** Creates a new Vision. */

  PhotonCamera m_targetCameraFwd;
  //List<PhotonTrackedTarget> m_targets;
  PhotonTrackedTarget m_bestTarget;
  double m_rotationToTarget;

  public Vision() {

    m_targetCameraFwd = new PhotonCamera("photonvision");
    m_targetCameraFwd.setDriverMode(true);

  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    var camResult = m_targetCameraFwd.getLatestResult();
    if (camResult.hasTargets()) {
      //m_targets = camResult.getTargets();
      m_bestTarget = camResult.getBestTarget();
      m_rotationToTarget = m_bestTarget.getYaw();
    } else {
      m_rotationToTarget = 0;
    }
    SmartDashboard.putNumber("RotationToTarget", m_rotationToTarget);
  }

  public void setTargetOn() {
    m_targetCameraFwd.setLED(VisionLEDMode.kOn);
  }

  public void setTargetOff() {
    m_targetCameraFwd.setLED(VisionLEDMode.kOff);
  }
}
