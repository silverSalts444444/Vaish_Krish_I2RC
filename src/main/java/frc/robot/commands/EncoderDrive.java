package frc.robot.commands;

import frc.robot.subsystems.Drivetrain;
import edu.wpi.first.wpilibj2.command.Command;



/** An example command that uses an example subsystem. */
public class EncoderDrive extends Command {
  
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
  Drivetrain dt;
  double setPoint;
  double motorspeed;

  public EncoderDrive(Drivetrain dt, double setPoint, double motorspeed) {
    this.dt = dt;
    this.setPoint = setPoint;
    this.motorspeed = motorspeed;

    addRequirements(dt);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    setPoint = 1;
    dt.resetEnc();
    dt.tankDrive(0, 0);

  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    dt.tankDrive(0.4,0.4);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
