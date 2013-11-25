package com.example.todo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import android.os.Parcel;
import android.os.Parcelable;

import com.stackmob.sdk.api.StackMobQuery;
import com.stackmob.sdk.callback.StackMobCountCallback;
import com.stackmob.sdk.model.StackMobModel;

public class Kategori extends StackMobModel{
	private String name;
	private Date dueDate;
	private List<Tasks> cocuk;
	private String strValue; 
	private Integer intValue;
	private Tasks toptask;
    private Kategori parentlist;
    public Kategori() {
		super(Kategori.class);
		
	}
	public Kategori(String bbb, Date dueDate) {
		this();
		this.name = bbb;
		this.dueDate = dueDate;
		 cocuk = new ArrayList<Tasks>();
	}
	
	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		
		return this.name;
	}

	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}

	public Date getDueDate() {
		return this.dueDate;
	}

	
	public List<Tasks> getTasks() {
		
        return cocuk;
       
    }

}
