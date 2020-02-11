/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import java.util.ArrayList;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import io.github.pseudoresonance.pixy2api.*;
import io.github.pseudoresonance.pixy2api.Pixy2CCC.Block;

public class Pixy extends SubsystemBase {

  private Pixy2 pixycam;
  boolean isCamera = false;
  int state = -1;

  public Pixy() {
    pixycam = Pixy2.createInstance(Pixy2.LinkType.SPI);
  }

  public void runPixy() {
    if (!isCamera)
      state = pixycam.init(1);
    isCamera = state >= 0;

    SmartDashboard.putBoolean("Camera", isCamera);

    pixycam.getCCC().getBlocks(false, 255, 255);
    
    ArrayList<Block> blocks = pixycam.getCCC().getBlocks();

    if(blocks.size() > 0)
    {
      double xcoord = blocks.get(0).getX();
      double ycoord = blocks.get(9).getY();
      String data = blocks.get(0).toString();

      SmartDashboard.putBoolean("present", true);
      SmartDashboard.putNumber("Xccord", xcoord);
      SmartDashboard.putNumber("Yccord", ycoord);
      SmartDashboard.putString("Data", data);
    }
    else
      SmartDashboard.putBoolean("present", false);
      SmartDashboard.putNumber("size", blocks.size());
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
