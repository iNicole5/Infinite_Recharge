/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import frc.robot.subsystems.DriveTrain;


import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.CommandBase;

/**
 * An example command that uses an example subsystem.
 */
public class cmdDriveTrain extends CommandBase {

  private final DriveTrain drive;
  private final Joystick joystick;

  public cmdDriveTrain(DriveTrain drive, Joystick joystick) {
    this.joystick = joystick;
    this.drive = drive;
    addRequirements(drive);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    double move = joystick.getRawAxis(1) * 0.8;   
    double turn = joystick.getRawAxis(4) * 0.6;

    double left = move - turn;
    double right = move + turn;

    drive.turnMotors(left, right);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    drive.stopDriveMotors();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false; 
}
}
