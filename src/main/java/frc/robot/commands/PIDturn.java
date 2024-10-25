package frc.robot.commands;

import frc.robot.subsystems.Drivetrain;
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj2.command.Command;
import com.kauailabs.navx.frc.AHRS;

public class PIDturn extends Command {
  
  Drivetrain dt;
  double angler;
  PIDController pc = new PIDController(0.00333333333, 0, 0);
  

  // Called when the command is initially scheduled.
  
  public PIDturn(Drivetrain dt, double angler) {
    this.dt = dt;
    this.angler = angler;
    addRequirements(dt);
    pc.setTolerance(5);
  }

  @Override
  public void initialize() {
    dt.reset();
    dt.tankDrive(0, 0);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    dt.tankDrive(-pc.calculate(angler), +pc.calculate(angler));
    
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    dt.tankDrive(0, 0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    if (pc.atSetpoint()){
      return true;
    }

    return false;
    
}
}


