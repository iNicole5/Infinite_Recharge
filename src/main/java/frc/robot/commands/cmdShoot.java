/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Shooter;

public class cmdShoot extends CommandBase {
  /**
   * Creates a new cmdShoot.
   */
  private boolean direction;
  private Shooter shooter;
  private Intake intake;

  public cmdShoot(Shooter shooter, Intake intake, boolean direction) {
    // Use addRequirements() here to declare subsystem dependencies.
    this.shooter = shooter;
    this.intake = intake;
    this.direction = direction;
    addRequirements(shooter);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    shooter.shooterStop();
    //intake.runBalls();
    intake.stopBallStorage();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    shooter.shoot(direction);
    Timer.delay(3);
    intake.runBalls();
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    shooter.shooterStop();
    intake.stopBallStorage();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
