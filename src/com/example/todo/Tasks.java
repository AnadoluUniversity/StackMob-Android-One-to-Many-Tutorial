package com.example.todo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.stackmob.sdk.api.StackMobQuery;
import com.stackmob.sdk.callback.StackMobCountCallback;
import com.stackmob.sdk.model.StackMobModel;

public class Tasks extends StackMobModel {
	private String name;
	private Date dueDate;

	private String owner;
	private Kategori prerequisite;

	public Tasks() {
		super(Tasks.class);
		
	}

	public Tasks(String bbb, Date dueDate, String Owner) {
		this();
		this.name = bbb;
		this.owner=Owner;
		this.dueDate = dueDate;
		
		
		
	}

	public void setName(String name) {
		this.name = name;
	}
	public void setOwner(String owner) {
		this.owner = owner;
	}
	public String getOwner() {
		return this.owner;
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

	public void setPrerequisite(Kategori prereq) {
        this.prerequisite = prereq;
    }

}
