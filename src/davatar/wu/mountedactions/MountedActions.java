package davatar.wu.mountedactions;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.gotti.wurmunlimited.modloader.classhooks.HookException;
import org.gotti.wurmunlimited.modloader.classhooks.HookManager;
import org.gotti.wurmunlimited.modloader.interfaces.PreInitable;
import org.gotti.wurmunlimited.modloader.interfaces.WurmServerMod;

import javassist.CtMethod;

public class MountedActions implements WurmServerMod, PreInitable {
    private static Logger logger = Logger.getLogger("MountedActions");
    
    public static void logException(String msg, Throwable e) {
        if (logger != null) { logger.log(Level.SEVERE, msg, e); }
    }

    public static void logInfo(String msg) {
        if (logger != null) { logger.log(Level.INFO, msg); }
    }

    public String getVersion() {
    	return "0.1";
    }

	@Override
	public void preInit() {
		try {
			logInfo("preInit");
			CtMethod isActionAllowedOnVehicle = HookManager.getInstance().getClassPool().getCtClass("com.wurmonline.server.behaviours.Actions").getDeclaredMethod("isActionAllowedOnVehicle");
			// New actions: 154=ROAD_PACK; 155=ROAD_PAVE 
			//isActionAllowedOnVehicle.setBody("{ return action == 118 || action == 329 || action == 160 || action == 362 || action == 162 || action == 192 || action == 148 || action == 229 || action == 911 || action == 912 || action == 190 || action == 373 || action == 187 || action == 152 || action == 216 || action == 285 || action == 154 || action = 155; }");
			isActionAllowedOnVehicle.setBody("{ return 1 == 1; }");
		} catch(Exception e) {
			logException("init: ", e);
			e.printStackTrace();
			throw new HookException(e);
		}
	}
}
