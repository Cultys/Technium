package cultys.technium;

public class Axes {

	public float pitch;
	public float yaw;
	public float roll;
	
	public Axes(float pitch, float yaw, float roll)
	{
		this.pitch = pitch;
		this.yaw = yaw;
		this.roll = roll;
	}
	
	public Axes(float pitch, float yaw)
	{
		this.pitch = pitch;
		this.yaw = yaw;
		this.roll = 0.0F;
	}
	
}
