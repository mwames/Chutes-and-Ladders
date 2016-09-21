import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;


public class Arrow 
{
	private BufferedImage arrow;
	private int degrees;
	private int width;
	private int height;
	private int speed;
	private int lastSpeed;
	private boolean valueReady;

	public Arrow()
	{
		try 
		{ 
			arrow = ImageIO.read(new File("images/ArrowIcon.png"));
		} 
		catch (IOException e) 
		{ 
			System.out.println("bad file");
			e.printStackTrace();
		}
		valueReady = false;
	}
	public void paint(Graphics2D g)
	{
		AffineTransform originalTransform = g.getTransform();
		g.translate(this.width - 130, this.height - 130);
		g.rotate(Math.toRadians(degrees));
		g.translate(0 - 25, 0 - 25);
		g.drawImage(arrow, 0 ,0, 50, 50, null);
		g.setTransform(originalTransform);
	}
	public void setInitial(int width, int height)
	{
		this.width = width;
		this.height = height;
		degrees = 0;
	}
	public void updateArrow(int width, int height)
	{
		this.width = width;
		this.height = height;
		
		this.degrees += this.speed;
		if(degrees > 360)
			degrees -= 360;
		if(this.speed > 0)
		{
			valueReady = false;
			this.lastSpeed = speed;
			this.speed--;
		}
		if(this.speed == 0 && this.lastSpeed != this.speed)
		{
			this.lastSpeed = 0;
			valueReady = true;
		}
		
	}
	public int getValue()
	{
		int value = 0;
		if(degrees >= 1 && degrees < 60)
			value = 1;
		if(degrees >= 60 && degrees < 120)
			value = 2;
		if(degrees >= 120 && degrees < 180)
			value = 3;
		if(degrees >= 180 && degrees < 240)
			value = 4;
		if(degrees >= 240 && degrees < 300)
			value = 5;			
		if(degrees >= 300 && degrees <= 360)
			value = 6;
		return value;
	}
	public void spin(GameObject go) 
	{
		this.speed = go.getHeight() * 2;
		
	}
	public int getSpeed() 
	{
		return this.speed;
	}
	
	public void setValueReady(boolean valueReady) {
		this.valueReady = valueReady;
	}
	public boolean isValueReady() {
		return valueReady;
	}
	
}
