import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics2D;


public class GameObject 
{
	private int x;
	private int y;
	private int height;
	private int width;
	private Color color1;
	private Color color2;
	private boolean isGradient;
	private GradientPaint gradient;
	private BasicStroke stroke;
	private int rate;
	
	public GameObject(int x, int y, Color color1, Color color2) //for gradient fill
	{
		this.x = x;
		this.y = y;
		this.height = 100;
		this.width = 30;
		this.color1 = color1;
		this.color2 = color2;
		this.isGradient = true;
	}
	public GameObject(int x, int y)
	{
		this.x = x;
		this.y = y;
		this.height = 100;
		this.width = 30;
		this.stroke = new BasicStroke(2);
		this.isGradient = false;
		this.rate = 2;
		
	}
	
	public void paint(Graphics2D g)
	{
		if(isGradient)
		{
			g.setPaint(gradient);
			g.fillRoundRect(x, y, this.width, this.height, 10, 10);
		}
		else
		{
			g.setColor(Color.BLACK);
			g.setStroke(stroke);
			g.drawRoundRect(x, y, width, height, 10, 10);
		}
	}
	public void grow()
	{
		
		this.height -= this.rate;
		this.y += this.rate;
		
		if(this.height <= 0 || this.height >= 100)
			this.rate *= -1;
		
	}
	public void updateGradient(int x, int y, int height) 
	{
		this.x = x;
		this.y = y + (100 - this.height);
		gradient = new GradientPaint(this.x, this.y, color1, this.x + this.width, this.y + this.height, color2);
		
	}
	public void updateBorder(int x, int y)
	{
		this.x = x - (width + 30);
		this.y = y/2 - this.height/2;
	}
	public void setY(int y)
	{
		this.rate = 1;
		this.y = y;
	}
	public int getHeight() {
		return height;
	}
	public int getWidth() {
		return width;
	}
	public int getX() {
		return x;
	}
	public int getY() {
		return y;
	}
}
