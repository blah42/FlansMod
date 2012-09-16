package co.uk.flansmods.common;

import java.io.File;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import co.uk.flansmods.client.GuiPlaneMenu;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.ITickHandler;

import net.minecraft.src.*;

public class CommonProxy
{
	public void load() {
	}
	
	public List<File> getContentList(Method method, ClassLoader classloader)
	{
		// this stuff is only done client side.
		List<File> contentPacks = new ArrayList<File>();
		for (File file : FlansMod.flanDir.listFiles())
		{
			if (file.isDirectory())
			{
				// Add the images to the classpath so they can be loaded
				FlansMod.log("Not client. images and models skipped.");
				// Add the directory to the content pack list
				FlansMod.log("Loaded content pack : " + file.getName());
				contentPacks.add(file);
			}
		}
		FlansMod.log("Loaded textures and models.");
		return contentPacks;
	}
	
	public void loadDefaultGraphics()
	{
		FlansMod.log("Not client. Graphic loading skipped.");
	}
	
	public void loadContentPackGraphics(Method method, ClassLoader classloader)
	{
		FlansMod.log("Not client. ContentPack graphic loading skipped.");
	}
	
	public ITickHandler getTickHandler()
	{
		return new CommonTickHandler();
	}

	public Object getClientGui(int ID, EntityPlayer player, World world, int x, int y, int z)
	{
		return null;
	}

	public Container getServerGui(int ID, EntityPlayer player, World world, int x, int y, int z)
	{
		return null;
	}

	public Object loadBulletModel(String[] split, String shortName)
	{
		return null;
	}

	public Object loadMGModel(String[] split, String shortName)
	{
		return null;
	}

	public Object loadAAGunModel(String[] split, String shortName)
	{
	
		return null;
	}

	public Object loadVehicleModel(String[] split, String shortName)
	{
		return null;
	}
	
	public Object loadPlaneModel(String[] split, String shortName)
	{
		return null;
	}
	
	public void spawnAAGun(World world, double posX, double posY, double posZ, AAGunType type, float gunYaw, float gunPitch, Random rand, BulletType bullet, EntityAAGun entity, Entity player)
	{
	}
	
	public void spawnVehicle(World world, double posX, double posY, double posZ, VehicleType type, VehicleData data, EntityPassengerSeat seat, EntityVehicle entity, RotatedAxes axes, EntityPlayer player)
	{
	}

	// SERVER-SIDE
	public void keyPress(int key, int entityID, EntityPlayer player)
	{
		WorldServer world = (WorldServer) player.worldObj;
		Entity entityTest  = world.getEntityByID(entityID);
		
		if (entityTest == null || world.isRemote || !(entityTest instanceof EntityDriveable))
			return;
		
		EntityDriveable entity = (EntityDriveable)entityTest;
		
		if (entity.riddenByEntity != player)
			return;
		
		// if its not the inventory key, do whatever the entity wants.
		if (key != 7)
			entity.pressKey(key);
		
		// #7 is openning the gui, and is done ClientSide
	}
}