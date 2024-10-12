package frc.robot.commands;

import frc.robot.subsystems.Drivetrain;
import edu.wpi.first.wpilibj2.command.Command;



/** An example command that uses an example subsystem. */
public class EncoderDrive extends Command {
  

  Drivetrain dt;
  double setPoint;


  public EncoderDrive(Drivetrain dt, double setPoint) {
    this.dt = dt;
    this.setPoint = setPoint;

    addRequirements(dt);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    dt.resetEnc();
    dt.tankDrive(0, 0);

  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    dt.tankDrive(0.2,0.2);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    dt.tankDrive(0, 0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    if (dt.getMeters() >= setPoint) {
      return true;
    }
    return false;
  }
}
