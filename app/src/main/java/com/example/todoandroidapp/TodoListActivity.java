package com.example.todoandroidapp;

import android.app.AlertDialog;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.todoandroidapp.model.Todo;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class TodoListActivity extends AppCompatActivity {

    private TableLayout tableLayout;
    private final static List<Todo> todoProject = new ArrayList<>();
    private Todo todo;
    private int id ;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        id = getIntent().getIntExtra("id", 0);
        setContentView(R.layout.list_main);
        tableLayout = findViewById(R.id.tableLayout);
        final Button addButton = findViewById(R.id.button);
        final ImageButton backToMenu = findViewById(R.id.backToMenu);

        backToMenu.setOnClickListener(view -> {
            onBackPressed();
        });
        addButton.setOnClickListener(view -> {
            showDialogView();
        });

        for (int i = 0; i < 1 ; i++) {
            viewData(id);
        }
    }

    /**
     * <p>
     * shows the dialog view.
     * </p>
     */
    private void showDialogView() {
        final EditText editText = new EditText(this);

        new AlertDialog.Builder(this).setTitle("Add Todo task").setView(editText)
                .setPositiveButton("Save", (dialog, position) -> {
                    final String label = editText.getText().toString();

                    if (!(label.equals(""))) {
                        todo = new Todo();

                        todo.setLabel(label);
                        todo.setParentId(id);
                        todoProject.add(todo);
                        createTableRow(todo);
                    } else {
                        Toast.makeText(getBaseContext(), "Please Provide a task name", Toast.LENGTH_LONG).show();
                    }
                }).setNegativeButton("Cancel", null).create().show();
    }

    /**
     * <p>
     * Creates an row for the table layout
     * </p>
     *
     * @param todo Represents {@link Todo} object
     */
    private void createTableRow(final Todo todo) {
        final TableRow tableRow = new TableRow(this);
        final ImageView closeIcon = new ImageView(this);
        final CheckBox checkBox = new CheckBox(this);
        final TextView todoView = new TextView(this);

        tableRow.setLayoutParams(new TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT,
                TableLayout.LayoutParams.WRAP_CONTENT));
        checkBox.setOnCheckedChangeListener((buttonView, isChecked) -> {
            final TextView textView = (TextView) tableRow.getChildAt(1);
            final int textColor;

            if (isChecked) {
                textColor = Color.GREEN;
            } else {
                textColor = Color.BLACK;
            }
            textView.setTextColor(textColor);
        });
        tableRow.addView(checkBox);
        todoView.setText(todo.getLabel());
        tableRow.addView(todoView);
        closeIcon.setImageResource(R.drawable.baseline_close);
        closeIcon.setOnClickListener(view -> removeItem(tableRow, todo));
        tableRow.addView(closeIcon);
        tableLayout.addView(tableRow);
    }

    /**
     * <p>
     * Removes an item in the table layout
     * </p>
     *
     * @param tableRow Represents table row object
     */
    private void removeItem(final TableRow tableRow, final Todo todoItem) {
        tableLayout.removeView(tableRow);
        todoProject.remove(todoItem);
    }

    private void viewData(final int id) {
        for (final Todo todo : todoProject) {

           if (Objects.nonNull(todo)) {

                if (id == todo.getParentId()) {
                    createTableRow(todo);
                }
           }
            }
        }
    }

