package com.vogella.ide.navigator.simple.model;

public class Child
{
    private String name;
    private Parent parent;
 
    public Child(String name)
    {
        this.name = name;
    }

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Parent getParent() {
		return parent;
	}

	public void setParent(Parent parent) {
		this.parent = parent;
	}

	@Override
	public String toString() {
		return "Child [name=" + name + ", parent=" + parent + "]";
	}
	
	

}