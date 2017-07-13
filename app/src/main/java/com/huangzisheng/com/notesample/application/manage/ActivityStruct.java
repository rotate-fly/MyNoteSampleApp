package com.huangzisheng.com.notesample.application.manage;

import android.app.Activity;

public class ActivityStruct {
	/**
	 * 对应需要跳转的activity
	 */
	private Class<? extends Activity> className;
	/**
	 * 
	 */
	private String title;
	private int itemImg;

    public ActivityStruct(String title, Class<? extends Activity> className) {
		this.title = title;
		this.className = className;
	}

    public ActivityStruct(String title, Class<? extends Activity> className, int itemImg) {
        this.title = title;
        this.className = className;
        this.itemImg = itemImg;
    }	



	public Class<? extends Activity> getClassName() {
		return className;
	}

	public String getTitle() {
		return title;
	}

	public void setClassName(Class<? extends Activity> className) {
		this.className = className;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	
    public int getItemImg()
    {
        return itemImg;
    }

    public void setItemImg(int itemImg)
    {
        this.itemImg = itemImg;
    }	

}