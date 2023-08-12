package com.example.todoandroidapp.model;

public class Todo {
    private Long id;
    private String label;
    private int parentId;
    private boolean checked;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public int getParentId() {
        return parentId;
    }

    public void setParentId(int parentId) {
        this.parentId = parentId;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked() {
        this.checked = !this.checked ;
    }

    public String toString() {
        return String.format(" %s %s", parentId, label);
    }
}
