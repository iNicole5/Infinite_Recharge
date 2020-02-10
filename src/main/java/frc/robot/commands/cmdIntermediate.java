/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Intake;

public class cmdIntermediate extends CommandBase {

  private Intake subIntake;

  public cmdIntermediate(Intake subIntake) {
    // Use addRequirements() here to declare subsystem dependencies.
    this.subIntake = subIntake;
    addRequirements(subIntake);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    //subIntake.intakeStop();
    // subIntake.increaseBalls();
    subIntake.incrementBalls();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
      subIntake.incrementBalls();
      //subIntake.indexBall();
       System.out.println("EXECUTE");

    // subIntake.incrementBalls();
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    //   subIntake.stopBallStorage();
    subIntake.stopBallStorage();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return subIntake.getMiddleSwitch();
  }
}
