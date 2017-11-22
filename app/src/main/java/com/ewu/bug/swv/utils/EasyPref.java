package com.ewu.bug.swv.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public class EasyPref {

	Context mc;
	SharedPreferences sp;
	Editor ed;

	final String PREF_NAME = "MyPref";

	//pass applicationContext
	public EasyPref(Context mc) {
		super();
		this.mc = mc;

		sp = mc.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
		ed = sp.edit();

	}

	public void setPro(boolean value){
		ed.putBoolean("pro", value);
		ed.commit();
	}
	public boolean isPro(){

		return sp.getBoolean("pro", false);

	}

	public void setInertialStatus(boolean value){
		ed.putBoolean("inertial", value);
		ed.commit();
	}

	public boolean getInertialStatus(){
		return sp.getBoolean("inertial", true);
	}

	public void setAdv(boolean value, int no) {

		if (no == 1) {
			ed.putBoolean("adv1", value);
			ed.commit();
		} else if (no == 2) {
			ed.putBoolean("adv2", value);
			ed.commit();
		}

	}

	public boolean isAdv(int no) {
		if (no == 1)
			return sp.getBoolean("adv1", true);
		if (no == 2)
			return sp.getBoolean("adv2", true);

		return false;
	}

	public void putLastMatchTotalOver(int value) {
		ed.putInt("lastTotalOver", value);
		ed.commit();
	}

	public int getLastMatchTotalOver() {
		return sp.getInt("lastTotalOver", 3);
	}

	public int getHighestScore(int ov) {

		if (ov == 3) {
			return sp.getInt("best3", 0);
		}
		if (ov == 5) {
			return sp.getInt("best5", 0);
		}
		if (ov == 10) {
			return sp.getInt("best10", 0);
		}

		return 0;

	}

	public void putHighestScore(int value, int ov) {

		if (ov == 3) {
			ed.putInt("best3", value);
		}
		if (ov == 5) {
			ed.putInt("best5", value);
		}
		if (ov == 10) {
			ed.putInt("best10", value);
		}

	}

	// public int getCoin() {
	//
	// return sp.getInt("coin", 0);
	// }

	public int getCoin() {

		return sp.getInt("coin", 5000);
	}

	public void putCoin(int value) {

		ed.putInt("coin", value);
		ed.commit();
	}

	public int getBat() {

		return sp.getInt("bat", 5);
	}

	public void putBat(int value) {

		ed.putInt("bat", value);
		ed.commit();
	}

	public boolean isSound() {

		return sp.getBoolean("sound", true);
	}

	public void setSound(boolean value) {

		ed.putBoolean("sound", value);
		ed.commit();
	}

	public boolean isFirstOpen() {

		return sp.getBoolean("firstOpen", true);
	}

	public int launchCounter() {

		return sp.getInt("launchCounter", 0);
	}

	public void putInt(String key, int value) {

		ed.putInt(key, value);
		ed.commit();

	}

	public void putFloat(String key, float value) {

		ed.putFloat(key, value);
		ed.commit();

	}

	public void putString(String key, String value) {

		ed.putString(key, value);
		ed.commit();

	}

	public void putBoolean(String key, boolean value) {

		ed.putBoolean(key, value);
		ed.commit();

	}

	public int getInt(String key, int defValue) {

		return sp.getInt(key, defValue);
	}

	public boolean getBoolean(String key, boolean defValue) {

		return sp.getBoolean(key, defValue);
	}

	public float getFloat(String key, float defValue) {

		return sp.getFloat(key, defValue);
	}

	public String getString(String key, String defValue) {

		return sp.getString(key, defValue);
	}

}
