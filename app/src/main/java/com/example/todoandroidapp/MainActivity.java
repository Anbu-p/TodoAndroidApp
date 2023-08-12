package com.example.todoandroidapp;

import android.app.AlertDialog;

import android.content.Intent;
import android.os.Bundle;

import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.example.todoandroidapp.controller.ProjectController;
import com.example.todoandroidapp.controller.TodoController;
import com.example.todoandroidapp.model.Project;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * <p>
 * Displays the activities of the todo application
 * </p>
 *
 * @author Anbu
 * @version 1.0
 */
public class MainActivity extends AppCompatActivity {

    private static final ProjectController PROJECT_CONTROLLER = new ProjectController();
    private static final TodoController TODO_CONTROLLER = new TodoController();
    private final TodoListActivity todoListActivity = new TodoListActivity();
    private DrawerLayout drawerLayout;
    private ListView listView;
    private ArrayAdapter<Project> arrayAdapter;
    private final List<Project> projects;
    private  Project project;
    private int id = 1;

    public MainActivity() {
        this.projects = new ArrayList<>();
    }

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        drawerLayout = findViewById(R.id.drawerLayout);
        listView = findViewById(R.id.nameListView);
        final ImageButton menuButton = findViewById(R.id.menuButton);
        final ImageButton backToManu = findViewById(R.id.backMenuButton);
        final TextView addListName = findViewById(R.id.addListName);

        menuButton.setOnClickListener(view -> drawerLayout.openDrawer(GravityCompat.START));
        backToManu.setOnClickListener(view -> onBackPressed());
        addListName.setOnClickListener(view -> showDialogView());
        listView.setOnItemClickListener((adapterView, view, i, l) -> {
            Project project = projects.get(i);
                    final Intent intent = new Intent(view.getContext(), TodoListActivity.class);

                    intent.putExtra("id", project.getId());
                    startActivity(intent);
                }
        );

        ;/*((parent, view, position, id) -> startActivity(new Intent(view.getContext(), TodoListActivity.class)));*/
        listView.setOnItemLongClickListener((parent, view, position, id) -> {
            final int removeItem = position;
            final androidx.appcompat.app.AlertDialog.Builder builder = new androidx.appcompat.app.AlertDialog.Builder(MainActivity.this);

            builder.setMessage("Do you want to delete").setPositiveButton("Ok", (dialogInterface, position1) -> {
                projects.remove(removeItem);
                arrayAdapter.notifyDataSetChanged();
                Toast.makeText(getBaseContext(), "Item Deleted", Toast.LENGTH_LONG).show();
            }).setNegativeButton("Cancel", null).show();

            return true;
        });
    }

    /**
     * <p>
     * Extracts the getLabel of projects and returns a list of project getLabel
     * </p>
     *
     * @param projects Represents a list of project
     * @return The list of project getLabel
     */
    private List<String> getLabel(final List<Project> projects) {
        final List<String> labels = new ArrayList<>();

        if (Objects.nonNull(projects)) {

            for (final Project project : projects) {
                labels.add(project.getLabel());
            }
        }

        return labels;
    }

    /**
     * shows the DialogView.
     * </p>
     */
    public void showDialogView() {
        final EditText editText = new EditText(this);

        new AlertDialog.Builder(this).setTitle("Add Project Name").setView(editText)
                .setPositiveButton("Save", (dialog, position) -> {
                    final String label = editText.getText().toString();

                    if (!(label.equals(""))) {

                        if (getLabel(projects).contains(label)) {
                            Toast.makeText(getBaseContext(), "Item Already Exist", Toast.LENGTH_LONG).show();
                        } else {
                            project = new Project();
                            project.setLabel(label);
                            project.setId(id++);
                            projects.add(project);
                            arrayAdapter = new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_list_item_1, projects);
                            listView.setAdapter(arrayAdapter);
                            editText.setText("");
                        }
                    } else {
                        Toast.makeText(getBaseContext(), "Please Provide a task name", Toast.LENGTH_LONG).show();
                    }
                }).setNegativeButton("Cancel", null).create().show();
    }

    /**
     * <p>
     * Goes to the previous layout page
     * </p>
     */
    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
}